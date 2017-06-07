#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int remainder(int i, int j) {
	return i % j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 % 3 == 2);
	assertTrue(2 % -3 == 2);
	assertTrue(-2 % 3 == -2);
	assertTrue(-2 % -3 == -2);
	assertTrue(3 % 2 == 1);
	assertTrue(3 % -2 == 1);
	assertTrue(-3 % 2 == -1);
	assertTrue(-3 % -2 == -1);
	assertTrue(remainder(2, 3) == 2);
	assertTrue(remainder(2, -3) == 2);
	assertTrue(remainder(-2, 3) == -2);
	assertTrue(remainder(-2, -3) == -2);
	assertTrue(remainder(3, 2) == 1);
	assertTrue(remainder(3, -2) == 1);
	assertTrue(remainder(-3, 2) == -1);
	assertTrue(remainder(-3, -2) == -1);
	return 0;
}