#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo7 {
	int* a;
}Foo7;

int main(char* args) {
	vm = newVM();
	struct Foo7* b;

	b = (Foo7*)newObject(vm, &b, sizeof (Foo7)) ;

	b->a = newArray(vm,"int",&b ,10 * sizeof (int), NULL) ;
	 checkArray((checkNotNull(&(b->a))? b->a : b->a),0, "(checkNotNull(&(b->a))? b->a : b->a)", -1);
	checkNotNull(&(checkNotNull(&(b->a))? b->a : b->a)[0]);
	(checkNotNull(&(b->a))? b->a : b->a)[0] = 5 ;
	assertTrue((checkArray((checkNotNull(&(b->a))? b->a : b->a),0, "(checkNotNull(&(b->a))? b->a : b->a)", -1) && checkNotNull(&(checkNotNull(&(b->a))? b->a : b->a)[0])? (checkNotNull(&(b->a))? b->a : b->a)[0] : (checkNotNull(&(b->a))? b->a : b->a)[0]) == 5);
	return 0;
}