#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct NewIDFoo {
}NewIDFoo;

int main(char* args) {
	vm = newVM();
	struct NewIDFoo* f;
	struct NewIDFoo** g;
	struct NewIDFoo** h;

	f = (NewIDFoo*)newObject(vm, &f, sizeof (NewIDFoo)) ;

	g = newArray(vm,"struct NewIDFoo*",&g ,4 * sizeof (struct NewIDFoo*), NULL) ;

	h = newArray(vm,"struct NewIDFoo*",&h , 3 * sizeof (struct NewIDFoo*), makeList(vm,&h, 3,f, (checkArray(g,0, "g", 4) && checkNotNull(&g[0])? g[0] : g[0]) )) ;
	return 0;
}