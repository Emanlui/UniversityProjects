
#include "mythread.h"
#include <malloc.h>
#include <string.h>
#include <stdlib.h>
/* We are explicitly including these files, since the flags required to be passed
 * clone() system call pick up the standard signal values from these header fiiles.
 */
#include <signal.h>
#include <errno.h>


/* To be able to use getttid(), we define a function for ourselves that 
   directly references the system call.
 */
#include <sys/syscall.h>
#include <sys/types.h>
#define _GNU_SOURCE  
#include <sched.h>   

// This function creates a new thread
int mythread_create(mythread_t * new_thread_ID,
		    mythread_attr_t * attr,
		    void *(*start_func) (void *), void* arg, int sched_type, int duration_time)
{

	// This is the stack of the thread
	char *stack;
	// This is the head that contains the stack
	char *stackhead;

	// Arguments
	int *int_arg = (int*) arg;

	// Process ID
	pid_t tid;

	// Status
	int status;

	// We set the stack size
	stack = (char *) malloc (1024*1024);

	// In case there is an error
	if(!stack){
		fprintf(stderr, "Error");
		exit(1);
	}

	// Always -1 because can leave you with overflow of memory
	stackhead = stack + 1024*1024 - 1;

	// This creates the thread
	/*
	if ((tid =
			clone(start_func, stackhead, SIGCHLD, str)) == -1) {
				printf("clone failed! \n");
				printf("ERROR: %s \n", strerror(errno));
			return (-errno);
	}
*/
	waitpid(tid, &status,0);


	new_thread_ID->tid = tid;

	mythread_private_t *new_node = (mythread_private_t *) malloc(sizeof(mythread_private_t));

	new_node->tid = new_thread_ID->tid;
	new_node->start_func = start_func;
	new_node->args = int_arg;
	new_node->returnValue = 0;	
	new_node->sched_type = sched_type;
	new_node->next = NULL;
	new_node->state = 1;
	new_node->duration = duration_time; 
	new_node->tickets = NULL;
				


	if(mythread_list == NULL){
		mythread_list = (mythread_private_t *) malloc(sizeof(mythread_private_t));
		mythread_list->tid = new_thread_ID->tid;
	  	mythread_list->state = 1; 				
	  	mythread_list->start_func = start_func;
	  	mythread_list->args = int_arg;
	  	mythread_list->returnValue = 0;	
	  	mythread_list->sched_type = sched_type;
	  	mythread_list->duration = duration_time;
	    mythread_list->prev = NULL;
	    mythread_list->next = NULL;
	    mythread_list->tickets = NULL;
	    
	}else{

		mythread_private_t *tmp = mythread_list;
		while(tmp->next != NULL){
			tmp = tmp->next;
		}
		new_node->prev = tmp;

	    tmp->next = new_node;

	}	

	new_node->prev = NULL;

	// This is the queue part
	if(queue_in == NULL && queue_out == NULL){
		queue_in = new_node;
		queue_out = new_node;
	}else{

		if(queue_in->next == NULL){
			queue_in->next = new_node;
		}
		queue_out->next = new_node;
		new_node -> prev = queue_out;
		queue_out = new_node;

	}

	printf("Imprimiendo lista de threads \n");

	mythread_private_t *tmp = mythread_list;

	while(tmp != NULL){
		printf("ID: %d\n", tmp->tid);
			tmp = tmp->next;
	}

	return 0;
}
