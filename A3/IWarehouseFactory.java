public interface IWarehouseFactory
{
	// Robots and batteries
	public IBattery createBattery(int capacity);
	public IRobot createRobot(int batteryCapacity);
	public IRobot createRobotWithBatteryPack(int batteryCapacity, int packCapacity);
	// States
	// .. You must complete the interface with methods to instantiate your state objects.
	public State makeIdleState();
	public State makeRechargeState();
	public State makeFinishedState();
	public State makeClaimItemState();
	public State makeMoveToShelfState();
	public State makeTakeItemState();
	public State makeMoveToTruckState();
	public State makeLoadTruckState();
}
