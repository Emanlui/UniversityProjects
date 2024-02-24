#include "mythread.h" 

int mythread_exit(mythread_t *thread)
{

	// The stack and the linked list are defined
	mythread_private_t *tmp_list = mythread_list;
	mythread_queue * tmp_queue = queue_in; 

	// While is not the last element and if the thread is not the one i was looking for
	while(tmp_list->next != NULL && tmp_list->tid != thread->tid)
		tmp_list = tmp_list -> next;
	
	// Change the state
	if(tmp_list->tid == thread->tid)
		tmp_list->state = 3;
	

	// -------------------------------------------------

	while(tmp_queue->next != NULL){
		if(tmp_queue->tid == thread->tid)
			tmp_queue->state = 3;
		
		tmp_queue = tmp_queue -> next;
	}

	// Change the state
	if(tmp_queue->tid == thread->tid)
		tmp_queue->state = 3;
	

}