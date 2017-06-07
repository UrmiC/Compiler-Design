#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int complement(int i) {
	return ~i;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(~-5 == 4);
	assertTrue(~4 == -5);
	assertTrue(complement(-5) == 4);
	assertTrue(complement(4) == -5);
	return 0;
}