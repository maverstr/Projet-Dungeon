package model;

public interface RedrawObservable {
	  public void addRedrawObserver(RedrawObserver obs);

	  public void removeRedrawObserver(RedrawObserver obs);

	  public void notifyRedrawObserver();
}