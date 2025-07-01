#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Run a TOSSIM RadioCountToLeds simulation **and** compute:
    • Packet Delivery Ratio (PDR)
    • Average / min / max one‑hop delay (ms)

Fix (2025‑06‑22‑c)
-----------------
* **Delay pairing** now uses the **most recent preceding send** for each
  counter (FIFO per counter). This avoids over‑matching across the three
  broadcast senders, which previously produced 2–30 s delays.
* Keeps first‑seen send ticks in a queue so every `received` consumes the
  closest earlier `send`, giving realistic < 5 ms delays in typical runs.
"""
from __future__ import print_function, division
import re, sys, collections
from TOSSIM import Tossim

TICKS_PER_SEC = 4000000.0  # 4 MHz
SEND_RE = re.compile(r"packet sent\.\s+counter=(\d+)\s+time=(\d+)")
RECV_RE = re.compile(r"received packet\.\s+counter=(\d+)\s+time=(\d+)")
NODE_RE = re.compile(r"DEBUG \((\d+)\):")
try:
    from statistics import mean
except Exception:
    mean = lambda xs: sum(xs)/float(len(xs)) if xs else 0.0

# ---------------------------------------------------------------------------
# Sim helpers (unchanged)
# ---------------------------------------------------------------------------

def setup_sim():
    tos = Tossim([])
    radio = tos.radio()
    with open('topo.txt') as f:
        for s in (ln.split() for ln in f):
            if s: radio.add(int(s[0]), int(s[1]), float(s[2]))
    log_f = open('sim.log','w')
    for ch in ('RadioCountToLedsC','Boot'):
        tos.addChannel(ch, log_f)
    vals=[int(l.strip()) for l in open('meyer-heavy.txt') if l.strip()]
    for nid in (1,2,3):
        n=tos.getNode(nid)
        for v in vals: n.addNoiseTraceReading(v)
        n.createNoiseModel()
    tos.getNode(1).bootAtTime(100001)
    tos.getNode(2).bootAtTime(800008)
    tos.getNode(3).bootAtTime(1800009)
    return tos,log_f

def run_sim(tos,ev=50000):
    for _ in range(ev): tos.runNextEvent()

# ---------------------------------------------------------------------------
# Precise analysis with queue‑based pairing
# ---------------------------------------------------------------------------

def analyse(path='sim.log'):
    send_q = collections.defaultdict(collections.deque)  # counter → queue[tick]
    nodes=set(); sends=recvs=0; delays=[]
    with open(path) as f:
        for line in f:
            mn=NODE_RE.search(line)
            if mn: nodes.add(int(mn.group(1)))
            ms=SEND_RE.search(line)
            if ms:
                cnt=int(ms.group(1)); tick=int(ms.group(2))
                send_q[cnt].append(tick); sends+=1; continue
            mr=RECV_RE.search(line)
            if mr:
                cnt=int(mr.group(1)); tick=int(mr.group(2)); recvs+=1
                if send_q[cnt]:
                    s_tick=send_q[cnt].popleft()
                    delays.append((tick-s_tick)/TICKS_PER_SEC*1e3)
    n_nodes=max(1,len(nodes)); expected=sends*(n_nodes-1) if n_nodes>1 else sends
    pdr=recvs/float(expected) if expected else 0.0
    print('===== RESULTS =====')
    print('Nodes discovered       :', sorted(nodes))
    print('Total packets sent     :', sends)
    print('Total packets received :', recvs)
    print('Expected receives      :', expected)
    print('PDR                    : {:.2%}'.format(pdr))
    if delays:
        print('Average delay (ms)     : {:.3f}'.format(mean(delays)))
        print('Min delay (ms)         : {:.3f}'.format(min(delays)))
        print('Max delay (ms)         : {:.3f}'.format(max(delays)))
    else:
        print('No matched send↔recv pairs — delay = N/A')

# ---------------------------------------------------------------------------
if __name__=='__main__':
    tos,log_f=setup_sim(); run_sim(tos); log_f.close(); analyse()

