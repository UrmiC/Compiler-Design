#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int negate(int i) {
	return -i;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(-2 == -2);
	assertTrue(-+-2 == 2);
	assertTrue(negate(2) == -2);
	assertTrue(negate(-2) == 2);
	return 0;
}