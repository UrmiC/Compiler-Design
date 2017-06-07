#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct B1 {
}B1;
struct B1* returnob() {
	void* RU = NULL;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct B1* o;

	o = returnob() ;
	return 0;
}