#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int unaryPlus(int i) {
	return +i;

	//set all vars to zero and have the TEMP variable for the return expression
}
int unaryMinus(int i) {
	return -i;

	//set all vars to zero and have the TEMP variable for the return expression
}
bool not(bool b) {
	void* RU = !b;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int complement(int i) {
	return ~i;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(unaryPlus(2) == 2);
	assertTrue(unaryPlus(-2) == -2);
	assertTrue(unaryMinus(2) == -2);
	assertTrue(unaryMinus(-2) == 2);
	assertTrue(true);
	assertTrue(!false);
	assertTrue(not(true) == false);
	assertTrue(not(false) == true);
	assertTrue(complement(-5) == 4);
	return 0;
}