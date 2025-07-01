// execute this code from Unix/Linux shell (not WSL!) using the command:
// "gcc -o virtual virtual.c; ./virtual"
// Relevant Wikipedia articles:
// - https://en.wikipedia.org/wiki/Virtual_address_space
// - https://en.wikipedia.org/wiki/Pointer_(computer_programming)

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

typedef unsigned char uint8_t;
int offsetBits = 0;

// Input: address that points to a particular byte
// Action: print address as decimal/hexadecimal numbers
// Output: none
// "const" => pointer cannot be changed
// "void" => generic type
// "*" => value/address pointing to object/value
void printAddress(const void* ptr) {
  // reinterpret pointer address as 64-bit integer
  long index = (long) ptr;
    
  // in python we could write this as pageBytes = (2**offsetBits)
  // see also https://en.wikipedia.org/wiki/Arithmetic_shift
  long pageBytes = 1L << offsetBits;
    
  // in python we could write this as pageNumber = index*(2**offsetBits)
  long pageNumber = index >> offsetBits;
    
  // bitstring with offsetBits many consecutive 1s as least significant bits
  long offsetMask = (1L << offsetBits)-1;
    
  // "x & y" sets all bit positions to 0 where "x" and "y" disagree
  // see also https://en.wikipedia.org/wiki/Mask_(computing))
  long offset = index & offsetMask;
    
  printf( "hexadecimal: %p\n", ptr);
  printf( "decimal: %ld * %ld + %ld = %ld\n\n", pageNumber, pageBytes, offset, index);
}

int main(int argc, char* argv[]) {
  // get page size from system and compute the number of offset bits from that
  for (long pageBytes = sysconf(_SC_PAGE_SIZE)-1; pageBytes != 0; pageBytes /= 2) {
    ++offsetBits;
  }
    
  printf("\n");
  printf("this system uses %d offset bits in each virtual address\n", offsetBits);
  printf("that means the page size is 2^%d = %ld bytes\n\n", offsetBits, 1L << offsetBits);
    
  printf("---------\n\n");
    
  // printf( "virtual address pointing to NULL:\n");
  // printAddress(0);
    
  // allocate pair of 64-bit number on stack (that's 2*8 bytes)
  long arrayOnStack[] = {12, 23};
    
  printf( "virtual address pointing to 1st byte of global offsetBits variable:\n");
  printAddress(&offsetBits);
    
  printf( "virtual address pointing to 1st byte of printAddress function:\n");
  printAddress(&printAddress);
    
  printf( "virtual address pointing to 1st byte of array on stack:\n");
  printAddress(&arrayOnStack);
    
  printf("---------\n\n");
    
  // allocate 4*8 bytes on heap and interpret these bytes as a 64-bit long array
  long* arrayOnHeap = (long*) malloc(4*8);
    
  for (int i = 0; i < 4; ++i) { arrayOnHeap[i] = 123; }
    
  printf( "virtual address pointing to 1st byte of arrayOnHeap[0]:\n");
  printAddress(&arrayOnHeap[0]);
    
  printf( "virtual address pointing to 1st byte of arrayOnHeap[1]:\n");
  printAddress(&arrayOnHeap[1]);
    
  printf( "virtual address pointing to 1st byte of arrayOnHeap[2]:\n");
  printAddress(&arrayOnHeap[2]);
    
  printf( "virtual address pointing to 1st byte of arrayOnHeap[3]:\n");
  printAddress(&arrayOnHeap[3]);
    
  printf("---------\n\n");
    
  return 0;
}