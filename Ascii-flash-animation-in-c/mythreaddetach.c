#include "mythread.h" 

int mythread_detach(mythread_t *thread)
{

	mythread_private_t *tmp_list = mythread_list;
	
	int value = 1;

	if(mythread_list->tid == thread->tid){
		mythread_list = tmp_list->next;
		mythread_list -> prev = NULL;
		free(tmp_list);
	}else{
		while(tmp_list->next != NULL && tmp_list->tid != thread->tid)
			tmp_list = tmp_list->next;
		
		mythread_private_t *previous_node = tmp_list->prev;
		mythread_private_t *next_node = tmp_list->next;

		if(next_node == NULL)
			previous_node->next = NULL;
		else{
			previous_node->next = next_node;
			next_node->prev = previous_node;
		}

		free(tmp_list);
	}

	printf("\n Valor a eliminar: %d\n", thread->tid);

	if(queue_in -> next == NULL && queue_in -> prev == NULL && queue_in->tid == thread->tid){
		printf("SOlo un elemento\n");
		queue_in = NULL;
		queue_out = NULL;
		free(queue_in);
		free(queue_out);
	}
	if(queue_in->tid == thread->tid){
		printf("Entra primero \n");
		mythread_queue *tmp_queue_first = queue_in; 
		queue_in = queue_in->next;
		queue_in -> prev = NULL;
		tmp_queue_first -> next = NULL;
		free(tmp_queue_first);
	}

	mythread_queue *tmp_queue = queue_in; 

	while(tmp_queue->next != NULL){

		if(tmp_queue->tid == thread->tid){
			if(tmp_queue->prev == NULL){
				mythread_queue *tmp_queue_in = queue_in->prev; 
				tmp_queue_in->next = NULL;
				tmp_queue_in = NULL;
				free(tmp_queue_in);
				queue_in = queue_in->next;
			}else{
				mythread_queue *tmp_queue_prev = tmp_queue->prev;
				mythread_queue *tmp_queue_next = tmp_queue->next;

				tmp_queue_prev -> next = tmp_queue_next;
				tmp_queue_next -> prev = tmp_queue_prev;
				tmp_queue = NULL;
				free(tmp_queue);

			}
		}
		tmp_queue = tmp_queue->next;
	}
	

	if(tmp_queue->tid == thread->tid){
		mythread_queue *tmp_queue_out = queue_out; 
		queue_out = queue_out->prev;
		queue_out -> next = NULL;
		tmp_queue_out-> prev = NULL;
		free(tmp_queue_out);
	}


	return value;

}