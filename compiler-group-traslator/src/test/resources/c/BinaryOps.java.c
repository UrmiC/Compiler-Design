#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int add(int a, int b) {
	return a + b;
	//set all vars to zero and have the TEMP variable for the return expression
}
int subtract(int a, int b) {
	return a - b;
	//set all vars to zero and have the TEMP variable for the return expression
}
int multiply(int a, int b) {
	return a * b;
	//set all vars to zero and have the TEMP variable for the return expression
}
int divide(int a, int b) {
	return a / (checkDivision(b)? b : b);
	//set all vars to zero and have the TEMP variable for the return expression
}
int mod(int a, int b) {
	return a % b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool greaterThan(int a, int b) {
	return a > b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool greaterThanOrEqual(int a, int b) {
	return a >= b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool equal(int a, int b) {
	return a == b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool lessThan(int a, int b) {
	return a < b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool lessThanOrEqual(int a, int b) {
	return a <= b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool notEqual(int a, int b) {
	return a != b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool boolAnd(bool a, bool b) {
	return a && b;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool boolOr(bool a, bool b) {
	return a || b;
	//set all vars to zero and have the TEMP variable for the return expression
}
int shiftLeft(int i, int j) {
	return i << (checkLeftShift(i,j)? j : j);
	//set all vars to zero and have the TEMP variable for the return expression
}
int shiftRight(int i, int j) {
	return i >> j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int unsignedShiftRight(int i, int j) {
	return ((unsigned int)i) >> j;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(add(2, 3) == 5);
	assertTrue(add(2, -3) == -1);
	assertTrue(add(-2, 3) == 1);
	assertTrue(add(-2, -3) == -5);
	assertTrue(subtract(2, 3) == -1);
	assertTrue(subtract(2, -3) == 5);
	assertTrue(subtract(-2, 3) == -5);
	assertTrue(subtract(-2, -3) == 1);
	assertTrue(multiply(2, 3) == 6);
	assertTrue(multiply(2, -3) == -6);
	assertTrue(multiply(-2, 3) == -6);
	assertTrue(multiply(-2, -3) == 6);
	assertTrue(divide(2, 3) == 0);
	assertTrue(divide(2, -3) == 0);
	assertTrue(divide(-2, 3) == 0);
	assertTrue(divide(-2, -3) == 0);
	assertTrue(divide(3, 2) == 1);
	assertTrue(divide(3, -2) == -1);
	assertTrue(divide(-3, 2) == -1);
	assertTrue(divide(-3, -2) == 1);
	assertTrue(mod(2, 3) == 2);
	assertTrue(mod(2, -3) == 2);
	assertTrue(mod(-2, 3) == -2);
	assertTrue(mod(-2, -3) == -2);
	assertTrue(mod(3, 2) == 1);
	assertTrue(mod(3, -2) == 1);
	assertTrue(mod(-3, 2) == -1);
	assertTrue(mod(-3, -2) == -1);
	assertTrue(true);
	assertTrue(!false);
	assertTrue(greaterThan(2, 3) == false);
	assertTrue(greaterThan(3, 2) == true);
	assertTrue(greaterThan(2, 2) == false);
	assertTrue(greaterThanOrEqual(2, 3) == false);
	assertTrue(greaterThanOrEqual(3, 2) == true);
	assertTrue(greaterThanOrEqual(2, 2) == true);
	assertTrue(equal(2, 3) == false);
	assertTrue(equal(3, 2) == false);
	assertTrue(equal(2, 2) == true);
	assertTrue(lessThan(2, 3) == true);
	assertTrue(lessThan(3, 2) == false);
	assertTrue(lessThan(2, 2) == false);
	assertTrue(lessThanOrEqual(2, 3) == true);
	assertTrue(lessThanOrEqual(3, 2) == false);
	assertTrue(lessThanOrEqual(2, 2) == true);
	assertTrue(notEqual(2, 3) == true);
	assertTrue(notEqual(3, 2) == true);
	assertTrue(notEqual(2, 2) == false);
	assertTrue(boolAnd(true, true) == true);
	assertTrue(boolAnd(true, false) == false);
	assertTrue(boolAnd(false, true) == false);
	assertTrue(boolAnd(false, false) == false);
	assertTrue(boolOr(true, true) == true);
	assertTrue(boolOr(true, false) == true);
	assertTrue(boolOr(false, true) == true);
	assertTrue(boolOr(false, false) == false);
	assertTrue(shiftLeft(2, 1) == 4);
	assertTrue(shiftLeft(-2, 1) == -4);
	assertTrue(shiftRight(2, 1) == 1);
	assertTrue(shiftRight(-2, 1) == -1);
	assertTrue(unsignedShiftRight(2, 1) == 1);
	assertTrue(unsignedShiftRight(-2, 1) == 2147483647);
	return 0;
}