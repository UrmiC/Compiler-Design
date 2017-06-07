#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool or(bool b, bool c) {
	return b || c;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue((true || true) == true);
	assertTrue((true || false) == true);
	assertTrue((false || true) == true);
	assertTrue((false || false) == false);
	assertTrue(or(true, true) == true);
	assertTrue(or(true, false) == true);
	assertTrue(or(false, true) == true);
	assertTrue(or(false, false) == false);
	return 0;
}