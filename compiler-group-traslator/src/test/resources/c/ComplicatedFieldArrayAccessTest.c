#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Complex {
	int* ai;
	struct Complex* next;
	int i;
}Complex;

int main(char* args) {
	vm = newVM();
	struct Complex** ai;

	ai = newArray(vm,"struct Complex*",&ai ,2 * sizeof (struct Complex*), NULL) ;
	 checkArray(ai,0, "ai", 2);
	checkNotNull(&ai[0]);
	ai[0] = (Complex*)newObject(vm, &ai, sizeof (Complex)) ;
	 checkArray(ai,1, "ai", 2);
	checkNotNull(&ai[1]);
	ai[1] = (Complex*)newObject(vm, &ai, sizeof (Complex)) ;

	(checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next = (checkArray(ai,1, "ai", 2) && checkNotNull(&ai[1])? ai[1] : ai[1]) ;

	(checkArray(ai,1, "ai", 2) && checkNotNull(&ai[1])? ai[1] : ai[1])->next = (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0]) ;

	(checkArray(ai,1, "ai", 2) && checkNotNull(&ai[1])? ai[1] : ai[1])->i = 0 ;

	(checkArray(ai,(checkNotNull(&((checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i))? (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i : (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i), "ai", 2) && checkNotNull(&ai[(checkNotNull(&((checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i))? (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i : (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i)])? ai[(checkNotNull(&((checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i))? (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i : (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i)] : ai[(checkNotNull(&((checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i))? (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i : (checkArray(ai,0, "ai", 2) && checkNotNull(&ai[0])? ai[0] : ai[0])->next->i)])->i = 4 ;
	return 0;
}