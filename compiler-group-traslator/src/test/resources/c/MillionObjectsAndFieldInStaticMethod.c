#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct MillionObjectsClass1 {
	int a;
}MillionObjectsClass1;
typedef struct MillionObjectsClass2 {
	int b;
}MillionObjectsClass2;
void process() {
	int i;
	struct MillionObjectsClass2** millionObjects;

	millionObjects = newArray(vm,"struct MillionObjectsClass2*",&millionObjects ,1000000 * sizeof (struct MillionObjectsClass2*), NULL) ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		 checkArray(millionObjects,i, "millionObjects", 1000000);
		checkNotNull(&millionObjects[i]);
		millionObjects[i] = (MillionObjectsClass2*)newObject(vm, &millionObjects, sizeof (MillionObjectsClass2)) ;

		(checkArray(millionObjects,i, "millionObjects", 1000000) && checkNotNull(&millionObjects[i])? millionObjects[i] : millionObjects[i])->b = 0 ;
	}

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct MillionObjectsClass1** classMillionObjectsClass1;

	classMillionObjectsClass1 = newArray(vm,"struct MillionObjectsClass1*",&classMillionObjectsClass1 , 2 * sizeof (struct MillionObjectsClass1*), makeList(vm,&classMillionObjectsClass1, 2,(MillionObjectsClass1*)newObject(vm, &classMillionObjectsClass1, sizeof (MillionObjectsClass1)) )) ;
	process();
	return 0;
}