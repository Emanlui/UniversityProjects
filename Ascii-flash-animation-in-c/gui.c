#include <ncurses.h>
#include "mythread.h"

void explode_all_ships(){
	
 monitor_info* tmp2 = (monitor_info *) malloc(sizeof(monitor_info));
	tmp2 = configuration->monitors_list->head;


	while(tmp2 != NULL){

			for(int i = 0; i < ITEMS_COUNT; i++){
	    		if(configuration->item_list[i] == NULL) break;

	    		if(configuration->item_list[i]->monitor_id == tmp2->id){
	    			mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y-2, configuration->item_list[i]->posicion_actual_x, "xxxxxxxxxx");
					mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y-1, configuration->item_list[i]->posicion_actual_x, "xxxxxxxxxx");
					mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y,   configuration->item_list[i]->posicion_actual_x, "xxxxxxxxxx");
					mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y+1, configuration->item_list[i]->posicion_actual_x, "xxxxxxxxxx");
					mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y+2, configuration->item_list[i]->posicion_actual_x, "xxxxxxxxxx");
	    		}

	    	}
		
			tmp2 = tmp2->next;

		}


	monitor_info* tmp3 = (monitor_info *) malloc(sizeof(monitor_info));
	tmp3 = configuration->monitors_list->head;
	while(tmp3 != NULL){
		wrefresh(tmp3->canvas);	
		tmp3 = tmp3->next;
	}
	usleep(100000000);
	

}

void verificarChoque(int i, WINDOW *win){

	for(int j = 0; j < ITEMS_COUNT; j++){
    	if(configuration->item_list[j] == NULL) break;

    	if(j!=i && configuration->item_list[j]->monitor_id == configuration->item_list[i]->monitor_id){
/*
    		char dato1[20];
    		char dato2[20];
    		char dato3[20];
    		char dato4[20];
    		sprintf(dato1, "%d", configuration->item_list[i]->posicion_actual_y-2);
    		sprintf(dato2, "%d", configuration->item_list[i]->posicion_actual_y+2);
    		sprintf(dato3, "%d", configuration->item_list[j]->posicion_actual_y-2);
    		sprintf(dato4, "%d", configuration->item_list[j]->posicion_actual_y+2);
	
			mvwprintw(win, configuration->item_list[i]->posicion_actual_y-2, 0, dato1);
			mvwprintw(win, configuration->item_list[i]->posicion_actual_y+2, 0, dato2);
			mvwprintw(win, configuration->item_list[j]->posicion_actual_y-2, 0, dato3);
			mvwprintw(win, configuration->item_list[j]->posicion_actual_y+2, 0, dato4);


    		char indiceix[20];arg
    		char indiceiy[20];
    		char indicejx[20];
    		char indicejy[20];
			sprintf(indiceix, "%d",configuration->item_list[i]->posicion_actual_x);
			sprintf(indiceiy, "%d",configuration->item_list[i]->posicion_actual_y);
			sprintf(indicejx, "%d",configuration->item_list[j]->posicion_actual_x);
			sprintf(indicejy, "%d",configuration->item_list[j]->posicion_actual_y);

			mvprintw(configuration->item_list[i]->posicion_actual_y,0,indiceiy);	
			mvprintw(configuration->item_list[j]->posicion_actual_y,0,indicejy);	
			mvprintw(0,configuration->item_list[i]->posicion_actual_x,indiceix);	
			mvprintw(0,configuration->item_list[j]->posicion_actual_x,indicejx);	
*/
			
			if(
				((configuration->item_list[i]->posicion_actual_x <= configuration->item_list[j]->posicion_actual_x+10
				&& configuration->item_list[j]->posicion_actual_x+10 <= configuration->item_list[i]->posicion_actual_x+10) 
				||
				(configuration->item_list[i]->posicion_actual_x+10 >= configuration->item_list[j]->posicion_actual_x
				&& configuration->item_list[j]->posicion_actual_x >= configuration->item_list[i]->posicion_actual_x)) 
			
			
				&&
				
				((configuration->item_list[i]->posicion_actual_y-2 <= configuration->item_list[j]->posicion_actual_y+2
				&& configuration->item_list[j]->posicion_actual_y+2 <= configuration->item_list[i]->posicion_actual_y+2) 
				||
				(configuration->item_list[i]->posicion_actual_y-2 <= configuration->item_list[j]->posicion_actual_y - 2 
				&& configuration->item_list[j]->posicion_actual_y - 2 <= configuration->item_list[i]->posicion_actual_y+2)) 
				
				)  {

				// Choca la nave
				/*char posiciones[50];
				sprintf(posiciones, "( %s, %s) (pos %s %s)",
					dato3, dato4, dato1,dato2
					);*/

				mvwprintw(win, 1,10, "Choco una nave \n");
				//mvprintw(2,10,posiciones);
				//printf("%s\n", "Choco una nave \n");
			}	
    	}
    }
}

void *move_obj(int arg){

	monitor_info* canvas_tmp = (monitor_info *) malloc(sizeof(monitor_info));
	canvas_tmp = configuration->monitors_list->head;

	int last_width = 0; 
	int index_ship = (int *) arg;
	int max_width;
	int max_height;


	while(canvas_tmp != NULL){

		canvas_tmp->canvas = newwin(canvas_tmp->end_canvas_area,
							canvas_tmp->start_canvas_area,
							0,
							last_width);

		last_width += canvas_tmp->start_canvas_area;
			
		canvas_tmp = canvas_tmp->next;
	}
	//-----------------------------------------------------------------

	clear(); // Clear the screen of all
		

	// previously-printed characters
	refresh();
	
	
	WINDOW* tmp_canvas = (WINDOW *) malloc(sizeof(WINDOW));
	/*WINDOW *tmp = newwin(configuration->monitors_list->head->start_canvas_area, 
								configuration->monitors_list->head->end_canvas_area,
								0,
								120);*/
	
	monitor_info* tmp_box = (monitor_info *) malloc(sizeof(monitor_info));
	tmp_box = configuration->monitors_list->head;
	while(tmp_box != NULL){
		if(tmp_box->id == index_ship){
			tmp_canvas = tmp_box->canvas;	
			max_width = tmp_box->start_canvas_area;
			max_height = tmp_box->end_canvas_area;
		} 


		box(tmp_box->canvas, 0, 0);
		tmp_box = tmp_box->next;

	}


	
	if(time_counter > configuration->tiempo_de_fin){
		
		//explotan todas las naves
		explode_all_ships();                          // esta bueno
		refresh();
		
	}
	
	time_counter++;

    if(configuration->item_list[index_ship-1]->posicion_actual_y < configuration->item_list[index_ship-1]->posicion_final_y){
    	configuration->item_list[index_ship-1]->posicion_actual_y = configuration->item_list[index_ship-1]->posicion_actual_y + 1;
    }

    if(configuration->item_list[index_ship-1]->posicion_actual_x < configuration->item_list[index_ship-1]->posicion_final_x){
    	configuration->item_list[index_ship-1]->posicion_actual_x = configuration->item_list[index_ship-1]->posicion_actual_x + 1;
    	if(configuration->item_list[index_ship-1]->posicion_actual_x+5 > max_width){

    		if(configuration->item_list[index_ship-1]->monitor_id < configuration->monitors_list->tail->id){
    			configuration->item_list[index_ship-1]->monitor_id += 1;  
    			configuration->item_list[index_ship-1]->posicion_actual_x = 0;
    		}
    	}
    }

    if(configuration->item_list[index_ship-1]->posicion_actual_y > configuration->item_list[index_ship-1]->posicion_final_y){
    	configuration->item_list[index_ship-1]->posicion_actual_y = configuration->item_list[index_ship-1]->posicion_actual_y - 1;
    }

    if(configuration->item_list[index_ship-1]->posicion_actual_x > configuration->item_list[index_ship-1]->posicion_final_x){
    	configuration->item_list[index_ship-1]->posicion_actual_x = configuration->item_list[index_ship-1]->posicion_actual_x - 1;
    	if(configuration->item_list[index_ship-1]->posicion_actual_x < 0){
    		if(configuration->item_list[index_ship-1]->monitor_id > configuration->monitors_list->head->id){
    			configuration->item_list[index_ship-1]->monitor_id -= 1; 

				monitor_info* tmp_x_canva = (monitor_info *) malloc(sizeof(monitor_info));
				tmp_x_canva = configuration->monitors_list->head;

				int x_canva_value = 0;

    			while(tmp_x_canva != NULL){
    				if(tmp_x_canva->id == configuration->item_list[index_ship-1]->monitor_id)
    					x_canva_value = tmp_x_canva->start_canvas_area-10;
    				tmp_x_canva = tmp_x_canva->next;
    			}

    			configuration->item_list[index_ship-1]->posicion_actual_x = x_canva_value;
 
    		}
    	}
    }


    monitor_info* tmp2 = (monitor_info *) malloc(sizeof(monitor_info));
	tmp2 = configuration->monitors_list->head;


	while(tmp2 != NULL){

		for(int i = 0; i < ITEMS_COUNT; i++){
    		if(configuration->item_list[i] == NULL) break;

    		if(configuration->item_list[i]->monitor_id == tmp2->id){
    			mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y-2, configuration->item_list[i]->posicion_actual_x, configuration->item_list[i]->ascii_item[0]);
			    mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y-1, configuration->item_list[i]->posicion_actual_x, configuration->item_list[i]->ascii_item[1]);
			    mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y, configuration->item_list[i]->posicion_actual_x, configuration->item_list[i]->ascii_item[2]);
			    mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y+1, configuration->item_list[i]->posicion_actual_x, configuration->item_list[i]->ascii_item[3]);
			    mvwprintw(tmp2->canvas, configuration->item_list[i]->posicion_actual_y+2, configuration->item_list[i]->posicion_actual_x, configuration->item_list[i]->ascii_item[4]);

    		}

    	}
	
		tmp2 = tmp2->next;

	}
	
    verificarChoque(index_ship-1, tmp_canvas);

	
	monitor_info* tmp3 = (monitor_info *) malloc(sizeof(monitor_info));
	tmp3 = configuration->monitors_list->head;
	while(tmp3 != NULL){
		wrefresh(tmp3->canvas);	
		tmp3 = tmp3->next;
	}	

}
