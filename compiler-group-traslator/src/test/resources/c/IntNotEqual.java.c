#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool notEqual(int i, int j) {
	return i != j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 != 3 == true);
	assertTrue(3 != 2 == true);
	assertTrue(2 != 2 == false);
	assertTrue(notEqual(2, 3) == true);
	assertTrue(notEqual(3, 2) == true);
	assertTrue(notEqual(2, 2) == false);
	return 0;
}