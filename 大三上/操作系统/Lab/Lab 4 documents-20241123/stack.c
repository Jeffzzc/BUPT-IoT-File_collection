// execute this code from Unix/Linux shell (not WSL!) using the command:
// non-optimised compilation:
// gcc -O0 -o stack0 stack.c; objdump -d stack0; ./stack0
// optimised compilation:
// gcc -O3 -o stack3 stack.c; objdump -d stack3; ./stack3

// Relevant wikipedia pages:
// - https://en.wikipedia.org/wiki/Stack-based_memory_allocation
// - https://en.wikipedia.org/wiki/Recursion_(computer_science)
// - https://en.wikipedia.org/wiki/Disassembler

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// compilers uses the stack to store return addresses, local variables and backed up registers.
// stack pointer (SP) is the virtual address pointing to the first byte of the current process's stack
//
// e.g., pushing 4 bytes on the stack is executed by two operations (can be 2+ instructions)
// (1.) decrement SP by 4
// (2.) write the four bytes into the virtual addresses SP, SP+1, SP+2, SP+3
//
// e.g., pulling 4 bytes from the stack is exectued by two operations:
// (1.) read the four bytes from the virtual addresses SP, SP+1, SP+2, SP+3
// (2.) increment SP by 4

// "getStackPointer()" returns the current process's stack pointer (SP)
long getStackPointer() {
  return (long) __builtin_frame_address(0);
}

long f(long x) {
  if (x == 1) { // termination condition
    return getStackPointer();
  }
    
  const long sp1 = getStackPointer();
  const long sp2 = f(x-1);
    
  // return value is smallest stack pointer value
  // (it is presumed here that stack grows towards smaller addresses)
  if (sp2 < sp1) { return sp2; } 
  else { return sp1; }
}

int main(int argc, char* argv[]) {
  printf("\n");
  for (int recursionLevels = 1; recursionLevels <= 10000; ++recursionLevels) {
    const long sp1 = getStackPointer(); // get stack pointer address
    const long sp2 = f(recursionLevels); // f returns stack pointer address
        
    printf("stack pointer moves by %ld bytes from %d recursive calls\n", sp1-sp2, recursionLevels);
        
    // jump to particular number of recursions
    if (recursionLevels == 10 || recursionLevels == 100 || recursionLevels == 1000) {
      printf("...\n");
      recursionLevels = 10*recursionLevels-1;
    }
  }      
  return 0;
}
