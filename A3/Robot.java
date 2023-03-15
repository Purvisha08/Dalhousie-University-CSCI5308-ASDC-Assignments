public class Robot implements IRobot, ITimerObserver
{
	private IBattery battery;
	private State state;
	private State resumeState;
	
	public Robot(IBattery battery, State startState)
	{
		this.battery = battery;
		state = startState;
	}

	@Override
	public void timeElapsed(int minutes)
	{
		// if recharging
		if (state instanceof RechargeState) {
			battery.recharge(minutes);
			if (battery.isFullyCharged()) {
				state = resumeState;
			}
			return;
		}

		State transitionState = state.timeElapsed(minutes);
		if (transitionState != null)
		{
			if (battery.hasEnoughPowerForMinutes(transitionState.getChargeRequiredForState()))
			{
				battery.drain(transitionState.getChargeRequiredForState());
				state = transitionState;
			}
			else
			{
				resumeState = transitionState;
				state = Simulation.instance().getFactory().makeRechargeState();
			}
		}
	}

	@Override
	public boolean isWorking()
	{
		if (state.isFinished())
		{
			return false;
		}
		return true;
	}
}
