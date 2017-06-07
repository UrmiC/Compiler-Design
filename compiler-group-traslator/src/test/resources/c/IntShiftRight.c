#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int shiftRight(int i, int j) {
	return i >> j;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 >> 1 == 1);
	assertTrue(-2 >> 1 == -1);
	assertTrue(shiftRight(2, 1) == 1);
	assertTrue(shiftRight(-2, 1) == -1);
	return 0;
}