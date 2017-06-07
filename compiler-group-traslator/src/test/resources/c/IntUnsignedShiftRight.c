#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int unsignedShiftRight(int i, int j) {
	return ((unsigned int)i) >> j;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(((unsigned int)2) >> 1 == 1);
	assertTrue(((unsigned int)-2) >> 1 == 2147483647);
	assertTrue(unsignedShiftRight(2, 1) == 1);
	assertTrue(unsignedShiftRight(-2, 1) == 2147483647);
	return 0;
}