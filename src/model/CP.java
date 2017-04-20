package model;

import java.io.File;
import java.util.ArrayList;

public class CP extends Player {
	
	private static final File spriteFileCPU = new File(GameObject.class.getResource("/resources/sprites/CP_U.png").getFile());
	private static final File spriteFileCPR = new File(GameObject.class.getResource("/resources/sprites/CP_R.png").getFile());
	private static final File spriteFileCPD = new File(GameObject.class.getResource("/resources/sprites/CP_D.png").getFile());
	private static final File spriteFileCPL = new File(GameObject.class.getResource("/resources/sprites/CP_L.png").getFile());
	
	private static final File spriteFilePCPU = new File(GameObject.class.getResource("/resources/sprites/P_CP_U.png").getFile());
	private static final File spriteFilePCPR = new File(GameObject.class.getResource("/resources/sprites/P_CP_R.png").getFile());
	private static final File spriteFilePCPD = new File(GameObject.class.getResource("/resources/sprites/P_CP_D.png").getFile());
	private static final File spriteFilePCPL = new File(GameObject.class.getResource("/resources/sprites/P_CP_L.png").getFile());
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCPU);
	    add(spriteFileCPR);
	    add(spriteFileCPD);
	    add(spriteFileCPL);
	    add(spriteFilePCPU);
	    add(spriteFilePCPR);
	    add(spriteFilePCPD);
	    add(spriteFilePCPL);
	}};

	public CP(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCPU,0,-0.25,1),fileList);
		
	}

}
