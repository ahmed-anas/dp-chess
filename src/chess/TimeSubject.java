package chess;

public interface TimeSubject
{
	public void notifyObserver();
	public void registerObserver(TimeObserver observer);
}
