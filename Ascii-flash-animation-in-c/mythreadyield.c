#include "mythread.h" 


int mythread_yield(void){



	/**
						  
	          IN  		   tmp 		  OUT
	NULL <-| Nodo | -> <-| Nodo | -> <-| Nodo | -> NULL

	*/

	// the previous node of the last node
	mythread_queue *tmp = queue_out -> prev;

	// Delete all pointers from the last element
	queue_out->prev->next = NULL;
	queue_out->prev = NULL;

	// Sets the pointer of the last element to the first, linking... 
	queue_out->next = queue_in;

	// The first node now points to the last node (the new first)
	queue_in->prev = queue_out;
	
	// The first node now is the last one
	queue_in = queue_out;

	// The last node is now the tmp (previous node of the last)
	queue_out = tmp;


	return 0;

}