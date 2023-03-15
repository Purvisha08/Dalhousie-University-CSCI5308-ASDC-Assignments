
public class FinishedState extends State
{
	FinishedState() {
		minutesRequiredForState = 0;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		return null;
	}

	@Override
	public boolean isFinished()
	{
		return true;
	}
}
