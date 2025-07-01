// $Id: RadioCountToLedsC.nc,v 1.7 2010-06-29 22:07:17 scipio Exp $

/* (license header unchanged) */

#include "Timer.h"
#include "RadioCountToLeds.h"
#include <inttypes.h>     // PRIu64 for 64‑bit printf
#include "sim_tossim.h"  // sim_time() when running in TOSSIM

/*
 * RadioCountToLeds with counter & timestamp in debug output.
 * Generates lines like:
 *   DEBUG (1): packet sent. counter=1179 time=2878418068851
 *   DEBUG (3): received packet. counter=1179 time=2878443703467
 * so offline scripts can compute precise per‑packet delay.
 */

module RadioCountToLedsC @safe() {
  uses {
    interface Leds;
    interface Boot;
    interface Receive;
    interface AMSend;
    interface Timer<TMilli> as MilliTimer;
    interface SplitControl as AMControl;
    interface Packet;
  }
}
implementation {

  message_t packet;
  bool      locked  = FALSE;
  uint16_t  counter = 0;

  event void Boot.booted() {
    call AMControl.start();
  }

  event void AMControl.startDone(error_t err) {
    if (err == SUCCESS) {
      call MilliTimer.startPeriodic(250);    // 4 Hz
    } else {
      call AMControl.start();
    }
  }

  event void AMControl.stopDone(error_t err) { /* unused */ }

  /* ------------------------------------------------------------------ */
  /* Periodic timer → broadcast                                         */
  /* ------------------------------------------------------------------ */
  event void MilliTimer.fired() {
    radio_count_msg_t *rcm;   /*  ▼▼ all declarations before statements (C89) */

    counter++;
    dbg("RadioCountToLedsC",
        "RadioCountToLedsC: timer fired, counter is %hu.\n", counter);

    if (locked) {
      return;
    }

    rcm = (radio_count_msg_t *)call Packet.getPayload(&packet,
                                   sizeof(radio_count_msg_t));
    if (rcm == NULL) {
      return;
    }

    rcm->counter = counter;
    if (call AMSend.send(AM_BROADCAST_ADDR, &packet,
                         sizeof(radio_count_msg_t)) == SUCCESS) {
      dbg("RadioCountToLedsC",
          "packet sent. counter=%hu time=%" PRIu64 "\n",
          counter, sim_time());
      locked = TRUE;
    }
  }

  /* ------------------------------------------------------------------ */
  /* Receive handler                                                    */
  /* ------------------------------------------------------------------ */
  event message_t *Receive.receive(message_t *bufPtr,
                                   void *payload, uint8_t len) {
    radio_count_msg_t *rcm = (radio_count_msg_t *)payload;  /* declaration first */

    if (len != sizeof(radio_count_msg_t)) {
      return bufPtr;
    }

    dbg("RadioCountToLedsC",
        "received packet. counter=%hu time=%" PRIu64 "\n",
        rcm->counter, sim_time());

    /* LED display of low 3 bits */
    if (rcm->counter & 0x1) { call Leds.led0On(); } else { call Leds.led0Off(); }
    if (rcm->counter & 0x2) { call Leds.led1On(); } else { call Leds.led1Off(); }
    if (rcm->counter & 0x4) { call Leds.led2On(); } else { call Leds.led2Off(); }

    return bufPtr;
  }

  /* ------------------------------------------------------------------ */
  /* Send done                                                          */
  /* ------------------------------------------------------------------ */
  event void AMSend.sendDone(message_t *bufPtr, error_t error) {
    if (&packet == bufPtr) {
      locked = FALSE;
    }
  }

}

