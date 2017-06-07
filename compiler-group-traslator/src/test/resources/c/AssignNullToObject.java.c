#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct ANTO {
}ANTO;

int main(char* args) {
	vm = newVM();
	struct ANTO* o;
	o = NULL;
	return 0;
}