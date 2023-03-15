public class LoadTruckState extends State
{
	LoadTruckState() {
		minutesRequiredForState = 5;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		if (checkMinutesElasped()) {
			State state = Simulation.instance().getFactory().makeIdleState();
			return state;
		}
		return null;
	}

	private boolean checkMinutesElasped() {
		return minutesElapsed == 0;
	}
}
