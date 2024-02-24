#include "mythread.h" 


int mythread_chsched(mythread_t * new_thread_ID, int sched_type){

	mythread_private_t *tmp_list = mythread_list;

	while(tmp_list->next != NULL && tmp_list->tid != new_thread_ID->tid)
			tmp_list = tmp_list->next;
	
	if(tmp_list->tid == new_thread_ID->tid)
		tmp_list -> sched_type = sched_type;

	mythread_queue * tmp_queue = queue_in; 
	while(tmp_queue != NULL && tmp_queue->tid != new_thread_ID->tid){
			tmp_queue = tmp_queue->next;
	}
	tmp_queue -> sched_type = sched_type;

	return 0;

}