#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool greaterThan(int i, int j) {
	void* RU = i > j;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 > 3 == false);
	assertTrue(3 > 2 == true);
	assertTrue(2 > 2 == false);
	assertTrue(greaterThan(2, 3) == false);
	assertTrue(greaterThan(3, 2) == true);
	assertTrue(greaterThan(2, 2) == false);
	return 0;
}