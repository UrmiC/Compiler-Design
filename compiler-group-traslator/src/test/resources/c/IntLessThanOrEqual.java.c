#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool lessThanOrEqual(int i, int j) {
	return i <= j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 <= 3 == true);
	assertTrue(3 <= 2 == false);
	assertTrue(2 <= 2 == true);
	assertTrue(lessThanOrEqual(2, 3) == true);
	assertTrue(lessThanOrEqual(3, 2) == false);
	assertTrue(lessThanOrEqual(2, 2) == true);
	return 0;
}