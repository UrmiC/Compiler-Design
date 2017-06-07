#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* i;
	int* j;
	bool* b;
	bool* c;

	i = newArray(vm,"int",&i ,4 * sizeof (int), NULL) ;

	j = newArray(vm,"int",&j , 4 * sizeof (int), makeList(vm,&j, 4,1, 2, 3 )) ;

	b = newArray(vm,"bool",&b ,4 * sizeof (bool), NULL) ;

	c = newArray(vm,"bool",&c , 3 * sizeof (bool), makeList(vm,&c, 3,true, false )) ;
	return 0;
}