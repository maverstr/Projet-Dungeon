package CONSTANTS;


// CETTE CLASSE EST RESERVEE A LA CREATION DE CONSTANTES.

public class CONSTANTS {
	

	
	
	private static final int MAP_HEIGHT = 800; //#pixels de la map ! 
	private static final int MAP_WIDTH = 800;
	private static final int WINDOW_PIXEL_HEIGHT = MAP_HEIGHT+36; // Prend en compte la taille de playerstate etc....
	private static final int WINDOW_PIXEL_WIDTH = MAP_WIDTH+200;

	private static int MAP_BLOCK_WIDTH = 10; // #blocs
	private static int MAP_BLOCK_HEIGHT = 10;
	

	private static boolean DARKNESS_MODIFIER = false; //Reduce line of sight if true
	private static int LINE_OF_SIGHT = 3;
	
	
	
	
	public CONSTANTS() {
	
	}
	
	public static void setMAP_BLOCK_WIDTH(int value){
		MAP_BLOCK_WIDTH = value;
	}
	

	public static int getMAP_BLOCK_WIDTH(){
		return MAP_BLOCK_WIDTH;
	}
	
	public static void setMAP_BLOCK_HEIGHT(int value){
		MAP_BLOCK_HEIGHT = value;
	}
	
	public static int getMAP_BLOCK_HEIGHT(){
		return MAP_BLOCK_HEIGHT;
	}
	
	public static void setLINE_OF_SIGHT(int value){
		LINE_OF_SIGHT = value;
	}
	
	public static int getLINE_OF_SIGHT(){
		return LINE_OF_SIGHT;
	}
	
	public static int getMAP_WIDTH(){
		return MAP_WIDTH;
	}
	
	public static int getMAP_HEIGHT(){
		return MAP_HEIGHT;
	}

	public static boolean getDARKNESS_MODIFIER(){
		return DARKNESS_MODIFIER;
	}
	
	public static void setDARKNESS_MODIFIER(boolean value){
		DARKNESS_MODIFIER = value;
	}
	
	public static int getWINDOW_PIXEL_WIDTH(){
		return WINDOW_PIXEL_WIDTH;
	}
	
	public static int getWINDOW_PIXEL_HEIGHT(){
		return WINDOW_PIXEL_HEIGHT;
	}
	


}
