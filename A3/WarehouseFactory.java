
public class WarehouseFactory implements IWarehouseFactory
{
	@Override
	public IBattery createBattery(int capacity)
	{
		return new Battery(capacity);
	}

	@Override
	public IRobot createRobot(int batteryCapacity)
	{
		IBattery battery = createBattery(batteryCapacity);
		State startState = makeIdleState();
		return new Robot(battery, startState);
	}

	public IRobot createRobotWithBatteryPack(int batteryCapacity, int packCapacity)
	{
		return new Robot(new BatteryPack(createBattery(batteryCapacity), packCapacity), makeIdleState());
	}

	@Override
	public IdleState makeIdleState() {
		return new IdleState();
	}

	@Override
	public State makeRechargeState() {
		return new RechargeState();
	}

	@Override
	public State makeFinishedState() {
		return new FinishedState();
	}

	@Override
	public State makeClaimItemState() {
		return new ClaimItemState();
	}

	@Override
	public State makeMoveToShelfState() {
		return new MoveToShelfState();
	}

	@Override
	public State makeTakeItemState() {
		return new TakeItemState();
	}

	@Override
	public State makeMoveToTruckState() {
		return new MoveToTruckState();
	}

	@Override
	public State makeLoadTruckState() {
		return new LoadTruckState();
	}
}
