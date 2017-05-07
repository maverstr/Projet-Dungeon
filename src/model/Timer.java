package model;

public abstract class Timer implements Runnable {
	
	private transient Thread t;
	private int duration;
	private Player player;

	public Timer(int duration, Player player) {
		this.duration = duration;
		this.player = player;
		t = new Thread(this);
		t.start();
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(duration);
			delayedAction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public abstract void delayedAction();

}
