#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool equal(int i, int j) {
	void* RU = i == j;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 == 3 == false);
	assertTrue(3 == 2 == false);
	assertTrue(2 == 2 == true);
	assertTrue(equal(2, 3) == false);
	assertTrue(equal(3, 2) == false);
	assertTrue(equal(2, 2) == true);
	return 0;
}