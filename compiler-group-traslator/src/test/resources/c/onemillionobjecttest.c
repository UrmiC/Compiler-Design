#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Item {
	int val;
	struct Item* next;
}Item;

int main(char* args) {
	vm = newVM();
	struct Item* curr;
	struct Item* head;
	int i;

	head = NULL ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		curr = (Item*)newObject(vm, &curr, sizeof (Item)) ;

		curr->val = i ;

		curr->next = head ;

		head = curr ;
	}

	curr = head ;
	return 0;
}