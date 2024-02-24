#include<malloc.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <linux/sched.h> 
#include <ncurses.h>

#define FALSE 0
#define TRUE 1

#define RUNNING 0
#define READY 	1 /* Ready to be scheduled */
#define BLOCKED 2 /* Waiting on Join */
#define DEFUNCT 3 /* Dead */

#define ROUND_ROBIN 1 /* Code for checking the scheduler type*/
#define SORTEO 2      /* Code for checking the scheduler type*/
#define TIEMPOREAL 3  /* Code for checking the scheduler type*/
#define ITEMS_COUNT 10
#define MAX_MONITORS 10

typedef struct ticket_list{
  int number;
  struct ticket_list *prev, *next;

}ticket_list;

typedef struct mythread_attr {
  unsigned long stackSize;     /* Stack size to be used by this thread. Default is SIGSTKSZ */
}mythread_attr_t;

/* Thread Handle exposed to the user */
typedef struct mythread {
  pid_t tid; /* The thread-id of the thread */
}mythread_t;

/* The Actual Thread Control Block structure */
typedef struct mythread_private {

  pid_t tid; 				/* The thread-id of the thread */
  int state; 				/* the state in which the corresponding thread will be. */
  void * (*start_func) (void *); 	/* The func pointer to the thread function to be executed. */
  void *args; 				/* The arguments to be passed to the thread function. */
  void *returnValue; 			/* The return value that thread returns. */
  int sched_type;   /* The scheduler typwe*/
  struct mythread_private *prev, *next; 
  int duration;
  ticket_list *tickets;
}mythread_private_t;

/* The Actual Thread Queue for the running */
typedef struct mythread_queue {

  pid_t tid;        /* The thread-id of the thread */
  int state;        /* the state in which the corresponding thread will be. */
  void * (*start_func) (void *);  /* The func pointer to the thread function to be executed. */
  void *args;         /* The arguments to be passed to the thread function. */
  void *returnValue;      /* The return value that thread returns. */
  int sched_type;   /* The scheduler typwe*/
  struct mythread_queue *prev, *next; 

}mythread_queue;

/* The monitor information for the animation */
typedef struct monitor_info {

  int id;
  WINDOW *canvas;  //monitor id
  int start_canvas_area; //monitor canvas area
  int end_canvas_area;
  struct monitor_info *prev, *next; 

}monitor_info;

/* The monitor information for the animation */
typedef struct monitor_queue {

  monitor_info *head;  //queue head node
  monitor_info *tail;  //queue tail node
  int size;  //queue size

}monitor_queue;

/* The monitor information for the animation */
typedef struct item_info {

  int scheduler;  //monitor id
  int monitor_id;  //monitor id
  char *ascii_item[ITEMS_COUNT]; //list of items
  int posicion_actual_x;
  int posicion_actual_y;
  int posicion_inicial_x;
  int posicion_inicial_y;
  int posicion_final_x;
  int posicion_final_y;
  int angulo;
  int tiempo_inicial;
  int tiempo_final;

}item_info;

/* Configuration struct  */
typedef struct config {

  int tiempo_de_inicio;
  int tiempo_de_fin;
  char *protocolo;
  int numero_monitores;
  monitor_queue *monitors_list;
  item_info *item_list[ITEMS_COUNT];
  int espacio_entre_objetos;

}config;

/*
 * mythread_create - prepares context of new_thread_ID as start_func(arg),
 * attr is ignored right now.
 * Threads are activated (run) according to the number of available LWPs
 * or are marked as ready.
 */
int mythread_create(mythread_t *new_thread_ID,
          mythread_attr_t *attr,
          void * (*start_func)(void *),
          void *arg, int sched_type, int duration_time);

/*
 * mythread_yield - switch from running thread to the next ready one
 */
int mythread_yield(void);

/*
 * mythread_join - suspend calling thread if target_thread has not finished,
 * enqueue on the join Q of the target thread, then dispatch ready thread;
 * once target_thread finishes, it activates the calling thread / marks it
 * as ready.
 */
int mythread_join(mythread_t target_thread, void **status);

/*
 * mythread_exit - exit thread, awakes joiners on return
 * from thread_func and dequeue itself from run Q before dispatching run->next
 */
int mythread_detach(mythread_t *new_thread_ID);


void *move_obj(int arg);


// GLOBAL SHARE VARIABLES

extern mythread_private_t *mythread_list; 
extern mythread_queue *queue_in; 
extern mythread_queue *queue_out; 
extern config *configuration;
extern int time_counter;