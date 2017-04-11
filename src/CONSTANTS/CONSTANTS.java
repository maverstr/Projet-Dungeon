package CONSTANTS;

import model.BlockNotBreakable;

// CETTE CLASSE EST RESERVEE A LA CREATION DE CONSTANTES.

public class CONSTANTS {
	/* TODO: Should use this only when drawing things. All other x,y reference should be based on 'cell' unit 
	 * example : 
	 * 		iso new BlockNotBreakable(column*CONSTANTS.BLOCK_SIZE, currentLine*CONSTANTS.BLOCK_SIZE,this)
	 * 		do  new BlockNotBreakable(column, currentLine, this)
	 * 
	 * 	-> more compact, more readable (no useless information to filter out when reading)
	 */
	public static final int BLOCK_SIZE = 50; //Taille en pixel d'une case
	
	
	public static final int MAP_HEIGHT = 600; //#pixels de la map ! /!\ suffisants pour les blocs
	public static final int MAP_WIDTH = 600;
	public static final int WINDOW_PIXEL_HEIGHT = 600; // Prend en compte la taille de playerstate etc....
	public static final int WINDOW_PIXEL_WIDTH = 800;

	public static final int MAP_BLOCK_WIDTH = 12; // #blocs
	public static final int MAP_BLOCK_HEIGTH = 12;
	
	
	
	
	public CONSTANTS() {
	
	}

}
