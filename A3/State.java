// This is the State abstract for the State pattern.
public abstract class State
{
	protected int minutesRequiredForState;
	protected int minutesElapsed;
	
	public State()
	{
		minutesElapsed = 0;
	}
	
	// Executes the logic of the current state for a number of minutes.
	// Returns the state to transition to, or null if should remain
	// in the current state.
	public abstract State timeElapsed(int minutes);
	
	// Returns the number of minutes of charge required to execute the state.
	public int getChargeRequiredForState()
	{
		return minutesRequiredForState;
	}
	
	// Returns whether the state represents a terminal / job finished state.
	public boolean isFinished()
	{
		// Default is no, there should be only one terminal state class.
		return false;
	}
}
