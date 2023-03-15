public class MoveToShelfState extends State
{
	public MoveToShelfState() {
		minutesRequiredForState = 4;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		if (checkMinutesElasped()) {
			State state = Simulation.instance().getFactory().makeTakeItemState();
			return state;
		}
		return null;
	}

	private boolean checkMinutesElasped() {
		return minutesElapsed == 0;
	}
}
