import java.util.*;

public class TimerSubject
{
	private static TimerSubject theOneInstance = null;
	private List<ITimerObserver> observers;

	public TimerSubject()
	{
		observers = new ArrayList<ITimerObserver>();
	}
	
	public static TimerSubject instance()
	{
		if (checkNullValue())
		{
			theOneInstance = new TimerSubject();
		}
		return theOneInstance;
	}

	private static boolean checkNullValue() {
		return null == theOneInstance;
	}

	public void attach(ITimerObserver observer) {
        observers.add(observer);
    }

    public void detach(ITimerObserver observer) {
        observers.remove(observer);
    }
	public void notifySubjects() {
		for (ITimerObserver observer : observers) {
			observer.timeElapsed(1);
		}
	}
}

















