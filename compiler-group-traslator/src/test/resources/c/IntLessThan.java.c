#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool lessThan(int i, int j) {
	return i < j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 < 3 == true);
	assertTrue(3 < 2 == false);
	assertTrue(2 < 2 == false);
	assertTrue(lessThan(2, 3) == true);
	assertTrue(lessThan(3, 2) == false);
	assertTrue(lessThan(2, 2) == false);
	return 0;
}