package model;

import java.io.File;
import java.util.ArrayList;

public class CS extends Player {
	
	private static final File spriteFileCSU = new File(GameObject.class.getResource("/resources/sprites/CS_U.png").getFile());
	private static final File spriteFileCSR = new File(GameObject.class.getResource("/resources/sprites/CS_R.png").getFile());
	private static final File spriteFileCSD = new File(GameObject.class.getResource("/resources/sprites/CS_D.png").getFile());
	private static final File spriteFileCSL = new File(GameObject.class.getResource("/resources/sprites/CS_L.png").getFile());
	
	private static final File spriteFilePCSU = new File(GameObject.class.getResource("/resources/sprites/P_CS_U.png").getFile());
	private static final File spriteFilePCSR = new File(GameObject.class.getResource("/resources/sprites/P_CS_R.png").getFile());
	private static final File spriteFilePCSD = new File(GameObject.class.getResource("/resources/sprites/P_CS_D.png").getFile());
	private static final File spriteFilePCSL = new File(GameObject.class.getResource("/resources/sprites/P_CS_L.png").getFile());
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCSU);
	    add(spriteFileCSR);
	    add(spriteFileCSD);
	    add(spriteFileCSL);
	    add(spriteFilePCSU);
	    add(spriteFilePCSR);
	    add(spriteFilePCSD);
	    add(spriteFilePCSL);
	}};

	public CS(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCSU,0,-0.25,1),fileList);
		// TODO Auto-generated constructor stub
	}

}
