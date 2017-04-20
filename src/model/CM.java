package model;

import java.io.File;
import java.util.ArrayList;

public class CM extends Player {
	
	private static final File spriteFileCMU = new File(GameObject.class.getResource("/resources/sprites/CM_U.png").getFile());
	private static final File spriteFileCMR = new File(GameObject.class.getResource("/resources/sprites/CM_R.png").getFile());
	private static final File spriteFileCMD = new File(GameObject.class.getResource("/resources/sprites/CM_D.png").getFile());
	private static final File spriteFileCML = new File(GameObject.class.getResource("/resources/sprites/CM_L.png").getFile());
	
	private static final File spriteFilePCMU = new File(GameObject.class.getResource("/resources/sprites/P_CM_U.png").getFile());
	private static final File spriteFilePCMR = new File(GameObject.class.getResource("/resources/sprites/P_CM_R.png").getFile());
	private static final File spriteFilePCMD = new File(GameObject.class.getResource("/resources/sprites/P_CM_D.png").getFile());
	private static final File spriteFilePCML = new File(GameObject.class.getResource("/resources/sprites/P_CM_L.png").getFile());
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCMU);
	    add(spriteFileCMR);
	    add(spriteFileCMD);
	    add(spriteFileCML);
	    add(spriteFilePCMU);
	    add(spriteFilePCMR);
	    add(spriteFilePCMD);
	    add(spriteFilePCML);
	}};

	public CM(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCMU,0,-0.25,1),fileList);
		
	}

}
