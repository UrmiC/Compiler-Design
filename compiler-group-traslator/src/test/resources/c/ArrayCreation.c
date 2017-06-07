#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct AC {
}AC;

int main(char* args) {
	vm = newVM();
	struct AC** acs;

	acs = newArray(vm,"struct AC*",&acs , 3 * sizeof (struct AC*), makeList(vm,&acs, 3,NULL, (AC*)newObject(vm, &acs, sizeof (AC)) )) ;
	return 0;
}