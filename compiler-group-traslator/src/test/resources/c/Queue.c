#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Data {
	int i;
}Data;
typedef struct LinkedNode {
	struct Data* data;
	struct LinkedNode* next;
}LinkedNode;
typedef struct LinkedList {
	struct LinkedNode* head;
	struct LinkedNode* last;
	int size;
}LinkedList;
struct LinkedList* llNewLinkedList() {
	struct LinkedList* result;

	result = (LinkedList*)newObject(vm, &result, sizeof (LinkedList)) ;

	result->head = (LinkedNode*)newObject(vm, &result, sizeof (LinkedNode)) ;

	result->last = (checkNotNull(&(result->head))? result->head : result->head) ;
	void* RU = result;
	result = NULL;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
bool llIsEmpty(struct LinkedList* ll) {
	assertTrue(ll != NULL);
	void* RU = (checkNotNull(&(ll->size))? ll->size : ll->size) == 0;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
void llAddLast(struct LinkedList* ll, struct Data* data) {
	assertTrue(ll != NULL && data != NULL);

	ll->last->next = (LinkedNode*)newObject(vm, &ll, sizeof (LinkedNode)) ;

	ll->last = (checkNotNull(&(ll->last->next))? ll->last->next : ll->last->next) ;

	ll->last->data = data ;
	ll->size++;

	//set all vars to zero and have the TEMP variable for the return expression
}
struct Data* llRemoveFirst(struct LinkedList* ll) {
	struct Data* result;
	assertTrue((checkNotNull(&(ll->size))? ll->size : ll->size) > 0);

	result = (checkNotNull(&(ll->head->next->data))? ll->head->next->data : ll->head->next->data) ;

	ll->head = (checkNotNull(&(ll->head->next))? ll->head->next : ll->head->next) ;
	ll->size--;
	void* RU = result;
	result = NULL;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct LinkedList* ll;
	struct Data* d;
	int i;

	ll = llNewLinkedList() ;
	for (
	i = 0  ; i < 10 ; i++) {
		d = (Data*)newObject(vm, &d, sizeof (Data)) ;

		d->i = i + 1 ;
		llAddLast(ll, d);
	}
	while (!llIsEmpty(ll)) {
		d = llRemoveFirst(ll) ;
	}
	return 0;
}