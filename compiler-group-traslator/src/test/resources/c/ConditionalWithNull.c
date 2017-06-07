#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct CWN {
}CWN;

int main(char* args) {
	vm = newVM();
	struct CWN* o;

	o = (CWN*)newObject(vm, &o, sizeof (CWN)) ;

	o = true ? NULL : o ;
	return 0;
}