#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Millionobjectclass {
}Millionobjectclass;

int main(char* args) {
	vm = newVM();
	int i;
	struct Millionobjectclass** objects;

	objects = newArray(vm,"struct Millionobjectclass*",&objects ,1000000 * sizeof (struct Millionobjectclass*), NULL) ;

	i = 0 ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		 checkArray(objects,i, "objects", 1000000);
		checkNotNull(&objects[i]);
		objects[i] = (Millionobjectclass*)newObject(vm, &objects, sizeof (Millionobjectclass)) ;
	}
	return 0;
}