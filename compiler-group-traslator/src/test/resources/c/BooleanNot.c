#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
bool not(bool b) {
	void* RU = !b;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(!false == true);
	assertTrue(!true == false);
	assertTrue(not(false) == true);
	assertTrue(not(true) == false);
	return 0;
}