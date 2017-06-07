#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void ae(int a, int b) {
	int x;
	int y;
	x = a + b;
	y = a * b;
	while (y > a + b) {
		a = a + 1;
		x = a + b;
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void vbe(int a, int b) {
	int x;
	int y;
	if (a > b) {
		x = b - a;
		y = a - b;
	}else{
		y = b - a;
		x = a - b;
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void lv() {
	int x;
	int y;
	int z;
	x = 2;
	y = 4;
	x = 1;
	if (y > x) {
		z = y;
	}else{
		z = y * y;
	}
	x = z;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	return 0;
}