#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct A4 {
	int d;
}A4;
typedef struct B3 {
	int c;
}B3;
void manipulate() {
	int i;
	struct B3** millionObjects;

	millionObjects = newArray(vm,"struct B3*",&millionObjects ,1000000 * sizeof (struct B3*), NULL) ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		 checkArray(millionObjects,i, "millionObjects", 1000000);
		checkNotNull(&millionObjects[i]);
		millionObjects[i] = (B3*)newObject(vm, &millionObjects, sizeof (B3)) ;

		(checkArray(millionObjects,i, "millionObjects", 1000000) && checkNotNull(&millionObjects[i])? millionObjects[i] : millionObjects[i])->c = 0 ;
	}
	changeValue(millionObjects);

	//set all vars to zero and have the TEMP variable for the return expression
}
void changeValue(struct B3** millionObjects) {
	int i;
	for (
	i = 0  ; i < 1000000 ; i++) {
		(checkArray(millionObjects,i, "millionObjects", 1000000) && checkNotNull(&millionObjects[i])? millionObjects[i] : millionObjects[i])->c = i ;
	}

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct A4** classA4;

	classA4 = newArray(vm,"struct A4*",&classA4 , 2 * sizeof (struct A4*), makeList(vm,&classA4, 2,(A4*)newObject(vm, &classA4, sizeof (A4)) )) ;
	manipulate();
	return 0;
}