#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int subtract(int i, int j) {
	return i - j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 - 3 == -1);
	assertTrue(2 - -3 == 5);
	assertTrue(-2 - 3 == -5);
	assertTrue(-2 - -3 == 1);
	assertTrue(subtract(2, 3) == -1);
	assertTrue(subtract(2, -3) == 5);
	assertTrue(subtract(-2, 3) == -5);
	assertTrue(subtract(-2, -3) == 1);
	return 0;
}