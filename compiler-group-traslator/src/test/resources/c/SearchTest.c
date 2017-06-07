#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* myList;
	int foundIndex;
	int toSearch;
	int i;

	myList = newArray(vm,"int",&myList , 6 * sizeof (int), makeList(vm,&myList, 6,1, 2, 3, 5, 4 )) ;

	toSearch = 2 ;

	foundIndex = 0 ;
	for (
	i = 0  ; i < 5 ; i++) {
		if ((checkArray(myList,i, "myList", 6) && checkNotNull(&myList[i])? myList[i] : myList[i]) == toSearch) {
			foundIndex = i ;
		}else{
		}
	}
	assertTrue(foundIndex == 1);
	return 0;
}