#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int shiftLeft(int i, int j) {
	return i << (checkLeftShift(i,j)? j : j);

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 << (checkLeftShift(2,1)? 1 : 1) == 4);
	assertTrue(-2 << (checkLeftShift(-2,1)? 1 : 1) == -4);
	assertTrue(shiftLeft(2, 1) == 4);
	assertTrue(shiftLeft(-2, 1) == -4);
	return 0;
}