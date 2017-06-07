#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct NEN {
}NEN;
struct NEN* returnNull() {
	return NULL;
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	assertTrue(NULL == returnNull());
	assertTrue(returnNull() == NULL);
	assertTrue(NULL == NULL);
	assertTrue(!(NULL != returnNull()));
	assertTrue(!(returnNull() != NULL));
	assertTrue(!(NULL != NULL));
	assertTrue(NULL == returnNull());
	return 0;
}