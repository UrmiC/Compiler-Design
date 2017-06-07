#include<stdio.h>
#include<stdbool.h>

//helper function
int getByteSize(int value){
	int i = 0;
	while (value > 0){
		value = value >> 1;
		i++;
	}
	return i;
}


bool checkDivision(int value){
		if (value != 0){
			return true;
		}
		printf("Exception: Divide by Zero\n");
		exit(0);
}

bool checkArray(int* array, int index, char * name, int size){
	if (index < size && index>-1){
		return true;
		}
	if (size == -1 && (index < (sizeof array)) && index>-1){
		return true;
	}
		//printf("%d\t%d\n", sizeof array, sizeof(int));
		printf("Exception: Array out of Bounds name: %s@%d\tarraySize: %d\n", name, index, sizeof array);
		exit(0);
}

bool checkLeftShift(int num, int shiftVal){
	if (getByteSize(num) + shiftVal <= 32){
		return true;
	}
	printf("Exception: Left Shift Overflow\n");
	exit(0);
}

bool checkNotNull(void * value){
	if (value != NULL){
			return true;
		}
		printf("Exception: Null Variable\n");
		exit(0);
}

void assertTrue(bool v){
		if (v){
		return true;
	}
	printf("Assert Failed  Line: %d\n", __LINE__);
	exit(0);
}
