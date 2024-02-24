#include "mythread.h"
#include <unistd.h>
#include <sched.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <ncurses.h>

mythread_private_t *mythread_list;
mythread_queue *queue_in; 
mythread_queue *queue_out; 
config *configuration;
int time_counter;
void print_stack(mythread_queue *tmp_queue){
	while(tmp_queue != NULL){
		printf("| ID: %d ", tmp_queue->tid);
		printf("| SCHED TYPE: %d ", tmp_queue->sched_type);
		printf("| STATE: %d -> \n", tmp_queue->state);
		tmp_queue = tmp_queue->next;
	}
}

void print_list(mythread_private_t *tmp_list){
	while(tmp_list != NULL){
			printf("| ID: %d ", tmp_list->tid);
			printf("| SCHED TYPE: %d ", tmp_list->sched_type);
			printf("| STATE: %d -> \n", tmp_list->state);
			tmp_list = tmp_list->next;
	}
}

void *thread_func(void *arg)
{	
	printf(" %s \n", (char *) arg);
	for(int i = 0; i < 3; i++) printf("- %d\n", i);
	return 0;
}

int main(int argc, char **args)
{
	process_language();
	for(int i = 0; i < ITEMS_COUNT; i++){

        if(configuration->item_list[i] == NULL) break;

        configuration->item_list[i]->posicion_actual_x = configuration->item_list[i]->posicion_inicial_x;
        configuration->item_list[i]->posicion_actual_y = configuration->item_list[i]->posicion_inicial_y;

    }
	mythread_private_t *tmp_list = mythread_list;	

	int Total_elapsed_time = scheduler(tmp_list);
	
	endwin();
    return 0;

}