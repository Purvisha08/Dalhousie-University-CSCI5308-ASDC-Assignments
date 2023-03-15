public class MoveToTruckState extends State
{
	public MoveToTruckState() {
		minutesRequiredForState = 4;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		if (checkMinutesElasped()) {
			State state = Simulation.instance().getFactory().makeLoadTruckState();
			return state;
		}
		return null;
	}

	private boolean checkMinutesElasped() {
		return minutesElapsed == 0;
	}
}
