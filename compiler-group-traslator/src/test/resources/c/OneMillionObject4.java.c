#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct OneMillionObject4A {
	int i;
}OneMillionObject4A;
struct OneMillionObject4A** manipulate() {
	int i;
	struct OneMillionObject4A** millionObjects;
	millionObjects = newArray(vm,"struct OneMillionObject4A*",&millionObjects ,1000000 * sizeof (struct OneMillionObject4A*), NULL);
	for (i = 0 ; i < 1000000 ; i++) {
		millionObjects[i] = (OneMillionObject4A*)newObject(vm, &millionObjects, sizeof (OneMillionObject4A));
		(checkArray(millionObjects,i, "millionObjects", 1000000) && checkNotNull(&millionObjects[i])? millionObjects[i] : millionObjects[i])->i = 0;
	}
	changeValue(millionObjects);
	return millionObjects;
	//set all vars to zero and have the TEMP variable for the return expression
}
void changeValue(struct OneMillionObject4A** millionObjects) {
	int i;
	for (i = 0 ; i < 1000000 ; i++) {
		(checkArray(millionObjects,i, "millionObjects", 1000000) && checkNotNull(&millionObjects[i])? millionObjects[i] : millionObjects[i])->i = i;
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct OneMillionObject4A** retValue;
	retValue = manipulate();
	return 0;
}