#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int multiply(int i, int j) {
	return i * j;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 * 3 == 6);
	assertTrue(2 * -3 == -6);
	assertTrue(-2 * 3 == -6);
	assertTrue(-2 * -3 == 6);
	assertTrue(multiply(2, 3) == 6);
	assertTrue(multiply(2, -3) == -6);
	assertTrue(multiply(-2, 3) == -6);
	assertTrue(multiply(-2, -3) == 6);
	return 0;
}