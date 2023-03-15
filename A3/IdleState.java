public class IdleState extends State
{
	public IdleState() {
		minutesRequiredForState = 0;
		minutesElapsed = minutesRequiredForState;
	}

	@Override
	public State timeElapsed(int minutes) {
		minutesElapsed = Math.max(0, minutesElapsed - minutes);
		if (checkMinutesElapsed()) {
			return getState();
		}
		return null;
	}

	private boolean checkMinutesElapsed() {
		return minutesElapsed == 0;
	}

	private State getState() {
		if (Simulation.instance().getShelf().getItemCount() > 0) {
			State state = Simulation.instance().getFactory().makeClaimItemState();
			return state;
		}
		else {
			State state = Simulation.instance().getFactory().makeFinishedState();
			return state;
		}
	}
}
