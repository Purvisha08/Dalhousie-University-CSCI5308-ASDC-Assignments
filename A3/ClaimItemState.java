public class ClaimItemState extends State
{
	public ClaimItemState() {
		minutesRequiredForState = 1;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		if (checkMinutesElapsed()) {
			if (checkItemCount()) {
				State state = Simulation.instance().getFactory().makeMoveToShelfState();
				return state;
			}
			else {
				State state = Simulation.instance().getFactory().makeIdleState();
				return state;
			}
		}
		return null;
	}

	private boolean checkMinutesElapsed() {
		return minutesElapsed == 0;
	}

	private boolean checkItemCount() {
		return Simulation.instance().getShelf().getItemCount() > 0;
	}
}
