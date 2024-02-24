#include "mythread.h" 

int process_language() {
	
	FILE *ptofile = fopen("configs/test.txt", "r");
	
	int line = 0;
	
	char input[64];
	
	configuration = (config *) malloc(sizeof(config));
	
	configuration->tiempo_de_inicio = atoi(fgets(input, 64, ptofile));
	configuration->tiempo_de_fin = atoi(fgets(input, 64, ptofile));
	configuration->protocolo = (char *) malloc(sizeof(char));
	strcpy(configuration->protocolo, strtok(fgets(input, 64, ptofile), "\n"));
	configuration->numero_monitores = atoi(fgets(input, 64, ptofile));
	
	configuration->monitors_list = (monitor_queue *) malloc(sizeof(monitor_queue));
	configuration->monitors_list->size = 0;
	configuration->monitors_list->head = NULL;
	configuration->monitors_list->tail = NULL;
	
	int last_heigth = 0;
	int last_width = 20;

	for(int i=configuration->numero_monitores; i>0; i--){
		
		monitor_info *tmp_monitor = (monitor_info *) malloc(sizeof(monitor_info));
		tmp_monitor->id = atoi(strtok(fgets(input, 64, ptofile), "-"));
		tmp_monitor->start_canvas_area = atoi(strtok(NULL, "-"));
		tmp_monitor->end_canvas_area = atoi(strtok(NULL, "\n"));

		last_heigth = tmp_monitor->end_canvas_area;
		last_width = tmp_monitor->start_canvas_area;
		
		if(configuration->monitors_list->head == NULL){
			tmp_monitor->prev = NULL;
			tmp_monitor->next = NULL;

			configuration->monitors_list->head = tmp_monitor;
			configuration->monitors_list->tail = tmp_monitor;
		}else{
			
			tmp_monitor->prev = configuration->monitors_list->tail;
			configuration->monitors_list->tail->next = tmp_monitor;
			configuration->monitors_list->tail = tmp_monitor;
		}
		configuration->monitors_list->size += 1;
	}
	
	if(strcmp(strtok(fgets(input, 64, ptofile), "\n"), "inicio") == 0){ }
	
	int index = 0;
	
	while(1){
		
		fgets(input, 64, ptofile);
		if(strcmp(strtok(input, "\n"), "fin") == 0){
			break;
		}else if(strcmp(strtok(input, "\n"), "-") == 0){
			continue;
		}
		else{
			
			item_info *tmp_item = (item_info *) malloc(sizeof(item_info));
			tmp_item->monitor_id = atoi(strtok(input, "\n"));
			tmp_item->scheduler = atoi(strtok(fgets(input, 64, ptofile), "\n"));

			tmp_item->tiempo_inicial = atoi(strtok(fgets(input, 64, ptofile), "-"));
			tmp_item->tiempo_final = atoi(strtok(NULL, "\n"));

			for(int i = 0; i<5; i++){
				tmp_item->ascii_item[i] = (char *) malloc(sizeof(char)+1);
			}

			strcpy(tmp_item->ascii_item[0], strtok(fgets(input, 64, ptofile), "\n"));
			strcpy(tmp_item->ascii_item[1], strtok(fgets(input, 64, ptofile), "\n"));
			strcpy(tmp_item->ascii_item[2], strtok(fgets(input, 64, ptofile), "\n"));
			strcpy(tmp_item->ascii_item[3], strtok(fgets(input, 64, ptofile), "\n"));
			strcpy(tmp_item->ascii_item[4], strtok(fgets(input, 64, ptofile), "\n"));
			tmp_item->posicion_inicial_x = atoi(strtok(fgets(input, 64, ptofile), "/"));
			tmp_item->posicion_inicial_y = atoi(strtok(NULL, "\n"));
			tmp_item->posicion_final_x = atoi(strtok(fgets(input, 64, ptofile), "/"));
			tmp_item->posicion_final_y = atoi(strtok(NULL, "\n"));
			tmp_item->angulo = atoi(fgets(input, 64, ptofile));
			configuration->item_list[index] = (item_info *) malloc(sizeof(item_info));
			configuration->item_list[index] = tmp_item;
			
			mythread_t threads;
			printf("NAVE %d \n", index);

			int temp_index = index+1;

			mythread_create(&threads, NULL, move_obj, temp_index, tmp_item->scheduler, tmp_item->tiempo_final);
			index ++;

		}
	}
	
	configuration->espacio_entre_objetos = atoi(fgets(input, 64, ptofile));

	
	fclose(ptofile);
	return 0;
}
