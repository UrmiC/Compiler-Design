#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int divide(int i, int j) {
	return i / (checkDivision(j)? j : j);

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(2 / (checkDivision(3)? 3 : 3) == 0);
	assertTrue(2 / (checkDivision(-3)? -3 : -3) == 0);
	assertTrue(-2 / (checkDivision(3)? 3 : 3) == 0);
	assertTrue(-2 / (checkDivision(-3)? -3 : -3) == 0);
	assertTrue(3 / (checkDivision(2)? 2 : 2) == 1);
	assertTrue(3 / (checkDivision(-2)? -2 : -2) == -1);
	assertTrue(-3 / (checkDivision(2)? 2 : 2) == -1);
	assertTrue(-3 / (checkDivision(-2)? -2 : -2) == 1);
	assertTrue(divide(2, 3) == 0);
	assertTrue(divide(2, -3) == 0);
	assertTrue(divide(-2, 3) == 0);
	assertTrue(divide(-2, -3) == 0);
	assertTrue(divide(3, 2) == 1);
	assertTrue(divide(3, -2) == -1);
	assertTrue(divide(-3, 2) == -1);
	assertTrue(divide(-3, -2) == 1);
	return 0;
}