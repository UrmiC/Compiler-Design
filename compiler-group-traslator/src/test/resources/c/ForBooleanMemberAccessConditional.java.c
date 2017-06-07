#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct FBMAC {
	bool b;
}FBMAC;

int main(char* args) {
	vm = newVM();
	struct FBMAC* o;
	bool b;
	b = false;
	o = (FBMAC*)newObject(vm, &o, sizeof (FBMAC));
	o->b = false;
	for ( ; (checkNotNull(&(o->b))? o->b : o->b) ; ) {
		b = true;
	}
	assertTrue(b == false);
	return 0;
}