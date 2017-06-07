#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void multipleInitAndIncOrDec() {
	int i;
	int j;
	int k;
	for (i = 0, j = 0, k = 0 ; i < 10 ; i++, j++, k++) {
	}
	assertTrue(i == 10);
	assertTrue(j == 10);
	assertTrue(k == 10);
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	multipleInitAndIncOrDec();
	return 0;
}