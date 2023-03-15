public class RechargeState extends State
{
	RechargeState() {
		minutesRequiredForState = 0;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		return null;
	}
}
