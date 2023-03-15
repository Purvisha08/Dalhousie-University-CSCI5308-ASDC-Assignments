public class TakeItemState extends State
{
	TakeItemState() {
		minutesRequiredForState = 2;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		boolean checkMinutesElapsed = minutesElapsed == 0;
		if (checkMinutesElapsed) {
			Simulation.instance().getShelf().claimItem();
			State state = Simulation.instance().getFactory().makeMoveToTruckState();
			return state;
		}
		return null;
	}
}
