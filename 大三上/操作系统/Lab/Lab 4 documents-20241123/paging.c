#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <getopt.h>
#include <unistd.h>

// Relevant Wikipedia articles:
// - https://en.wikipedia.org/wiki/Memory_paging
// - https://en.wikipedia.org/wiki/Page_fault
// - https://en.wikipedia.org/wiki/Page_table#Multilevel_page_tables
// - https://en.wikipedia.org/wiki/C_dynamic_memory_allocation
// - https://en.wikipedia.org/wiki/Resident_set_size

// C Basics
// What's this "<<" operation? It shifts all bits one position towards the
// most significant bit.
// That means "x << y" is the same as "x" multiplied by "2^y", why?

// What's a pointer type such as "int*"? Think of it as the address pointing to
// the first value of an array of int values. The array can often be of size 1.
// It is common in C to write "*ptr" instead of "ptr[0]", but for sake of
// simplicity we will avoid that notation.

typedef unsigned char BYTE; // use "BYTE" as name for numbers between 0 and 255
typedef BYTE* FRAME;        // use "FRAME" as name for address pointing to BYTE
typedef FRAME* PT4;         // use "PT4" as name for address pointing to FRAME
typedef PT4* PT3;           // use "PT3" as name for address pointing to PT4
typedef PT3* PT2;           // use "PT2" as name for address pointing to PT3
typedef PT2* PT1;           // use "PT1" as name for address pointing to PT2
typedef PT1* CR3;           // use "CR3" as name for address pointing to PT1

// extract "length" bits from x starting from "start"
long extractBits(long x, int start, int length) {
  // "(1L << length)-1" is the same as "(2^(length))-1"
  // That means "(2^(length))-1" is the same as "2^(length-1) + 2^(length-2) + ... + 2^2 + 2^1 + 2^0", why?
    
  // "x & y" sets bits to "1" at positions where both "x" and "y" have a "1".
  // "x & y" sets bits to "0" at positions where either "x" or "y" has a "0".
  // That means "&" can be used to select bits, how?
  return (x >> start) & ((1L << length)-1);
}

// 9*4 = 36-bit page numbers
const long pageTableEntries1 = 1L << 9; // 2^9 == 512
const long pageTableEntries2 = 1L << 9; // 2^9 == 512
const long pageTableEntries3 = 1L << 9; // 2^9 == 512
const long pageTableEntries4 = 1L << 9; // 2^9 == 512
// 12-bit offset
const long frameBytes = 1L << 12; // 2^12 == 4096

FRAME pagewalk(CR3 cr3, long pageNumber) {
  PT1 t1 = cr3[0]; // follow cr3 pointer
  if (t1 == NULL) {
    t1 = (PT1) malloc( sizeof(PT2)*pageTableEntries1);
    for (int i1 = 0; i1 < pageTableEntries1; i1++) { t1[i1] = NULL; }
    cr3[0] = t1; // update cr3 pointer
  }
    
  const long i1 = extractBits(pageNumber, 9+9+9, 9);
  const long i2 = extractBits(pageNumber, 9+9, 9);
  const long i3 = extractBits(pageNumber, 9, 9);
  const long i4 = extractBits(pageNumber, 0, 9);
    
  PT2 t2 = t1[i1];
    
  if (t2 == NULL) {
    // create level-1 page table
    t2 = (PT2) malloc( sizeof(PT3)*pageTableEntries2);
    for (int i2 = 0; i2 < pageTableEntries2; i2++) { t2[i2] = NULL; }
    t1[i1] = t2;
  }
    
  PT3 t3 = t2[i2];
    
  if (t3 == NULL) { 
    t3 = (PT3) malloc( sizeof(PT4)*pageTableEntries3);
    for (int i3 = 0; i3 < pageTableEntries3; i3++) { t3[i3] = NULL; }
    t2[i2] = t3;
  }
    
  PT4 t4 = t3[i3];
    
  if (t4 == NULL) {
    t4 = (PT4) malloc( sizeof(FRAME)*pageTableEntries4);
    for (int i4 = 0; i4 < pageTableEntries4; i4++) { t4[i4] = NULL; }
    t3[i3] = t4;
  }
    
  FRAME frame = t4[i4];
  if (frame == NULL) {
    frame = malloc(frameBytes);
    t4[i4] = frame;
  }
  return frame;
}

// compute page table overhead in bytes (and optionally total of frame bytes)
long countPageTableBytes(CR3 cr3, int countFrameBytes) {
  PT1 t1 = cr3[0]; 
  long bytes = 0;
    
  if (t1 != NULL) { 
    for (int i1 = 0; i1 < pageTableEntries1; i1++) {
      bytes += sizeof(PT2);
      PT2 t2 = t1[i1];
            
      if (t2 != NULL) { 
        for (int i2 = 0; i2 < pageTableEntries2; i2++) {
          bytes += sizeof(PT3);
          PT3 t3 = t2[i2];
          if (t3 != NULL) {
            for (int i3 = 0; i3 < pageTableEntries3; i3++) {
              PT4 t4 = t3[i3];
              bytes += sizeof(PT4);
              if (t4 != NULL) { 
                for (int i4 = 0; i4 < pageTableEntries4; i4++) {                                    
                  bytes += sizeof(FRAME);
                  if (countFrameBytes != 0) {
                    if (t4[i4] != NULL) { bytes += frameBytes; }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  return bytes;
}

// deallocate page tables (free space)
CR3 freePageTables(CR3 cr3) {
  if (cr3 != NULL) {
    PT1 t1 = cr3[0];
    if (t1 != NULL) {
      for (int i1 = 0; i1 < pageTableEntries1; i1++) {
        PT2 t2 = t1[i1];
        if (t2 != NULL) {
          for (int i2 = 0; i2 < pageTableEntries2; i2++) {
            PT3 t3 = t2[i2];
            if (t3 != NULL) {
              for (int i3 = 0; i3 < pageTableEntries3; i3++) {
                PT4 t4 = t3[i3];
                if (t4 != NULL) {
                  for (int i4 = 0; i4 < pageTableEntries4; i4++) {
                    if (t4[i4] != NULL) { free((void*) t4[i4]); }
                  }
                  free((void*) t4);
                }
              }
              free((void*) t3);
            }
          }
          free((void*) t2);
        }
      }
      free((void*) t1);
    }
    free((void*) cr3);
  }
  return NULL;
}

// input: pointer to first byte of char array (terminated with '\0')
// output: characters until '\0' interpreted as decimal number
long parseLong(const char* c) {
  if (c[0] == '\0') {
    return -1; // empty string
  }  
  for (int i = 0; c[i] != '\0'; ++i) {
    if ((c[i] < '0') || (c[i] > '9')) {
      return -1; // non-number string
    }
  }
  return atol(c);
}

int main(int argc, char* argv[]) { 
  CR3 cr3 = (CR3) malloc(sizeof(PT1));
  cr3[0] = NULL;
    
  // limit to 100000 interactions
  for (int iter = 0; iter < 100000; ++iter) {
    long overhead = countPageTableBytes(cr3, 0);
    long total = countPageTableBytes(cr3, 1);
    long pages =(total-overhead)/(4096);
        
    char s[8096];    
    if (iter == 0) {
      s[0] = '?';
      s[1] = '\0';       
    } 
    else {     
      if (iter > 1) {
        printf("[Virtual Memory]\n");
        printf("\t4-level page tables: %ld kilobytes\n", overhead/1024);
        printf("\t%ld frames: %ld kilobytes\n", pages, (total-overhead)/1024);
      }
            
      // Ask the user to input some text
      printf("\n> ");
            
      // Get and save the text
      scanf("%s", s);
    }
        
    if ((strcmp(s, "?") == 0) || (strcmp(s, "help") == 0)) {
      printf("\n[Info] This program simulates a 4-level paging system with:\n- 48-bit virtual addresses (36-bit page number)\n- 4 kilobytes per page (12-bit offset) \n- 512 entries per page table (9-bit page table indices)\n- 4 kilobytes per page table (64-bit page table entries)\n\n");
            
      printf("[MacOS X] Open another terminal and run the command:\ntop -pid %ld -stats pid,command,mem,faults,state -o faults\n\n", (long) getpid());
            
      printf("[Ubuntu] Open another terminal and run the following command:\nps -p%ld -o pid,command,%smem,rss,majflt,minflt\n\n", (long) getpid(), "%");
            
      printf("[General] Enter a number (e.g., 123) or a range (e.g., 1:1000) to simulate allocating those page numbers. Enter 'help' to display this again and 'exit' to quit.\n\n");
            
      continue;
    }
        
    if (strcmp(s, "exit") == 0) { break; }
    if (strcmp(s, "reset") == 0) {
      if (cr3 != NULL) {
        cr3 = freePageTables(cr3);
        cr3 = (CR3) malloc(sizeof(PT1));
        cr3[0] = NULL;
      }
      continue;
    }
        
    if (strcmp(s, "q") == 0) { break; } 
    long b = -1;
    for (int i = 0; s[i] != '\0'; i++) { 
      if (s[i] == ':') {       
        s[i] = '\0'; 
        const char* c1 = (const char*) &s;
        const char* c2 = c1+i+1;        
        b = parseLong(c2);
        break;       
      }
    }
        
    long a = parseLong(s);
    if (b == -1) { b = a; }
    if ((a < 0) || (b < a)) {
      printf("request ignored (empty range)\n\n");
      continue;      
    } 
    else if ((a < 0) || (b < 0) || (a >= 1L << 36) || (b >= 1L << 36)) { 
      printf("request ignored (any page number has to be between 0 and 2^36-1 = %ld)\n\n", (1L << 36)-1);
      continue;
    }
    if (a == b) { printf("[Simulator] allocating page %ld ...", a); } 
    else { printf("[Simulator] allocating pages between %ld and %ld ...", a,b); }
    for (long pageNumber = a; pageNumber <= b; pageNumber++) { pagewalk(cr3, pageNumber); }
    printf(" done.\n\n");
  }
  if (cr3 != NULL) { cr3 = freePageTables(cr3); }
  return 0;
}