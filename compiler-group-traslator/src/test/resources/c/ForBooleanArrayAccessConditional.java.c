#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	bool* B;
	bool b;
	b = false;
	B = newArray(vm,"bool",&B ,1 * sizeof (bool), NULL);
	B[0] = false;
	for ( ; (checkArray(B,0, "B", 1) && checkNotNull(&B[0])? B[0] : B[0]) ; ) {
		b = true;
	}
	assertTrue(b == false);
	return 0;
}