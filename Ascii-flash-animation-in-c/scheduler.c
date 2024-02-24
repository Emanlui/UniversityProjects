#include "mythread.h" 
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <ncurses.h>


/*
	Searches for the winner number un a given ticket list
*/
bool hasTicket(ticket_list *tl, int chosen_number){

	ticket_list *tmp_tl = tl;
	
	//fast counting of the elements in the list
	while(tmp_tl != NULL){

		if (tmp_tl->number == chosen_number){
			
			return true;
		}
		else{
			tmp_tl = tmp_tl->next;
		}
			
	}

	return false;
}

//---------------------------------------------------------

/*
	fills the list of tickets of each process
*/
int total_given = 0;
void giveTickets(ticket_list *tl, int tickets_each){

	//a copy to move through the original list
	ticket_list *tmp_tl = tl;
	
	//the amount of tickets_each iteration
	for(int t = 0; t < tickets_each; t++){

		while(tmp_tl->next != NULL){
			tmp_tl = tmp_tl->next;
		}

		//creates the new node for the entry ticket
		tmp_tl->next = (ticket_list *) malloc(sizeof(ticket_list));
		
		//places the pointer for the entry ticket
		tmp_tl = tmp_tl->next;
		tmp_tl->next = NULL;

		//assign a ticket number for that new node
		tmp_tl->number = total_given;
		
		//increase the number of tickets given
		total_given++;	
	}
}


int scheduler(mythread_private_t *thread){

	/*
		assigned 3ms taking into consideration the 
		recommendation of the chapter 2.4.3 from the
		book: Sistemas Operativos Modernos 3era Edicion
	*/

printf("ERROR\n");
	initscr();
    noecho();
    curs_set(FALSE);

    printf("ERROR\n");

	//variables used depending on the scheduler selected
	int QUANTUM = 3;
	int TOTAL_TICKETS = 20; 
	int winner_ticket = 0;
	int last_winner = 0;
	int finished,waited_time,elapsed_time=0;
	int list_length = 0;
	mythread_private_t *tmp_list = mythread_list;

	//fast counting of the elements in the list
	while(tmp_list != NULL){
		tmp_list = tmp_list->next;
		list_length++;	
	}
	printf("Length of the entry list: %d\n", list_length);

	//resets the list after count it
	tmp_list = mythread_list;

	/*
		configurations depending on the scheduler selected
	*/
	
	//Round robin (also the default one)
	if (tmp_list->sched_type == ROUND_ROBIN || tmp_list->sched_type == NULL){

		//checkpoint for the start of the list
		mythread_queue *start = tmp_list;
		//reset counters
		finished = 0;
		waited_time = 0;

		//loop for processing the list of process
		while(1){


			//needs to check if the process has any duration left
			if (tmp_list->duration == 0){
				
				tmp_list=tmp_list->next;
				
				continue;
				
			}

			//checks if there is time to be reduced
			if (tmp_list->duration > QUANTUM){
				move_obj(tmp_list->args);
				usleep(500000);
				//substracts the QUANTUM given to the process
				tmp_list->duration -= QUANTUM;
			}
			else{
				//if there is leftover time, add it and to the total waited time 
				if (tmp_list->duration > 0){
					move_obj(tmp_list->args);
					usleep(500000);
					waited_time += QUANTUM - tmp_list->duration;
					tmp_list->duration = 0;
					finished++;
				}
			}
			elapsed_time++;

			printf("Process ID %d\n",tmp_list->tid);
			printf("Process STATE %d\n",tmp_list->state);
			printf("Process SCHEDULER %d\n",tmp_list->sched_type);
			printf("Process DURATION %d\n",tmp_list->duration);
			printf("Elapsed Time %d\n",elapsed_time);
			printf("FINISHED %d\n", finished);
			printf("\n====================\n");
			//checks if there are more nodes in the list to be visited
			if (tmp_list->next != NULL){
				
				tmp_list = tmp_list->next;
				
			}

			//checks if all the nodes were processed during the loop while
			if (finished == list_length){
				break;
			}
			else{
				//goes back at the start of the list searching the remaining nodes
				tmp_list = start;
			}

		}

		//validation data
		printf("Length of the list processed: %d\n", list_length);	
		printf("Nodes processed: %d\n",finished);
		printf("Waited_time: %d\n",waited_time);
		printf("Elapsed Time %d\n",elapsed_time);

		return waited_time;
	}

	//Lottery 
	if (tmp_list->sched_type == SORTEO){

		elapsed_time = 0;
		printf("SCHEDULER LOTTERY SELECTED\n");
		//checkpoint for the start of the list
		mythread_queue *start = tmp_list;
		
		//reset counters
		finished = 0;
		waited_time = 0;
		int tickets_each = floor(TOTAL_TICKETS/list_length);
	
		//fill the tickets of each process
		int p = 0;
		while(tmp_list != NULL){
		
			tmp_list->tickets = (ticket_list *) malloc(sizeof(ticket_list));
			
			//fills the ticket list of the current process
			giveTickets(tmp_list->tickets, tickets_each);

			//moves to the next process to be filled with tickets
			tmp_list = tmp_list->next;
			
		}	

		printf("Tickets Given: %d\n",total_given);	

		//reset for the tmplist
		tmp_list = mythread_list;	
		
		srand(time(NULL));
		
		//generates a random winner number
		winner_ticket = rand() % TOTAL_TICKETS + 1;
		
		while(1){
			elapsed_time++;
			//checks if this process has the winner ticket
			if (hasTicket(tmp_list->tickets,winner_ticket)){

				//checks if there is time to be reduced
				if (tmp_list->duration > QUANTUM){

					//substracts the QUANTUM given to the process
					tmp_list->duration -= QUANTUM;

					move_obj(tmp_list->args);
					usleep(500000);
				
					if (tmp_list->duration == 0){
						finished++;						
					}
				}
				else{
					//if there is leftover time, add it and to the total waited time 
					if (tmp_list->duration > 0){
						waited_time += QUANTUM - tmp_list->duration;
						tmp_list->duration = 0;

						move_obj(tmp_list->args);
						usleep(500000);
					
						finished++;
					}
				}
				
				//generates a new winner ticket for the next process
				winner_ticket = rand() % TOTAL_TICKETS + 1;
			}

			//checks if there are more nodes in the list to be visited
			if (tmp_list->next != NULL){
				
				tmp_list = tmp_list->next;
			}

			//checks if all the nodes were processed during the loop while
			else if (finished == list_length){
				break;
			}

			//case when nobody has the winner ticket
			else{
				//goes back at the start of the list searching the remaining nodes
				tmp_list = mythread_list;
				
				//generates a new winner ticket for the next process
				winner_ticket = rand() % TOTAL_TICKETS + 1;
			}

		}

		//validation data
		printf("Length of the list processed: %d\n", list_length);	
		printf("Nodes processed: %d\n",finished);
		printf("Waited_time: %d\n",waited_time);
		printf("Elapsed time: %d\n", elapsed_time);
	}


	//TiempoReal
	if (tmp_list->sched_type == TIEMPOREAL){
		printf("Error 404: Scheduler Not Found\n");
/*
		mythread_private_t *tmp_list1 = thread;
		tmp_list1->sched_type = ROUND_ROBIN;

		mythread_private_t *tmp_list2 = thread;
		tmp_list2->sched_type = SORTEO;

		int elapsed_time1 = scheduler(tmp_list1);
		int elapsed_time2 = scheduler(tmp_list2);

		if (elapsed_time1 < elapsed_time2){
			mythread_list->sched_type = ROUND_ROBIN;
			printf("assigned ROUND_ROBIN\n");
		}
		else{
			printf("assigned LOTTERY\n");
			mythread_list->sched_type = SORTEO;
		}*/
	}


	return elapsed_time;
}
