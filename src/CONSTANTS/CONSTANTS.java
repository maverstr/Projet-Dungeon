package CONSTANTS;


// CETTE CLASSE EST RESERVEE A LA CREATION DE CONSTANTES.

public class CONSTANTS {
	

	
	
	public static final int MAP_HEIGHT = 800; //#pixels de la map ! 
	public static final int MAP_WIDTH = 800;
	public static final int WINDOW_PIXEL_HEIGHT = MAP_HEIGHT+36; // Prend en compte la taille de playerstate etc....
	public static final int WINDOW_PIXEL_WIDTH = MAP_WIDTH+226;

	public static int MAP_BLOCK_WIDTH = 12; // #blocs
	public static int MAP_BLOCK_HEIGTH = 12;
	
	public static int BLOCK_SIZE = Math.min(MAP_HEIGHT,MAP_WIDTH)/Math.max(MAP_BLOCK_WIDTH, MAP_BLOCK_HEIGTH); //Taille en pixel d'une case
	
	
	
	
	public CONSTANTS() {
	
	}

}
