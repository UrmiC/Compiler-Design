#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Object64 {
}Object64;
struct Object64* returnObject(struct Object64* o) {
	void* RU = o;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct Object64* o;

	o = (Object64*)newObject(vm, &o, sizeof (Object64)) ;
	assertTrue(o == returnObject(o));
	return 0;
}