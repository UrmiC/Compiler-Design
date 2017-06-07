#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool and(bool b, bool c) {
	void* RU = b && c;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue((true && true) == true);
	assertTrue((true && false) == false);
	assertTrue((false && true) == false);
	assertTrue((false && false) == false);
	assertTrue(and(true, true) == true);
	assertTrue(and(true, false) == false);
	assertTrue(and(false, true) == false);
	assertTrue(and(false, false) == false);
	return 0;
}