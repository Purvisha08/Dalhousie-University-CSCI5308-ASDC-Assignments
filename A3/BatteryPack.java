public class BatteryPack implements IBattery
{
	private int batteryPackCharge;
	private int batteryPackCapacity;
	private IBattery iBattery;

	public BatteryPack(IBattery iBattery, int batteryPackCapacity)
	{
		this.iBattery = iBattery;
		this.batteryPackCapacity = batteryPackCapacity;
		this.batteryPackCharge = batteryPackCapacity;
	}

	@Override
	public boolean hasEnoughPowerForMinutes(int minutes) {
		return iBattery.hasEnoughPowerForMinutes(minutes- batteryPackCharge);
	}

	@Override
	public boolean isFullyCharged() {
		boolean checkIsFullyCharged = iBattery.isFullyCharged() && batteryPackCharge == batteryPackCapacity;
		return checkIsFullyCharged;
	}

	@Override
	public void drain(int minutes) {
		if (batteryPackCharge >= minutes) {
			batteryPackCharge = batteryPackCharge - minutes;
		}
		else {
			minutes = minutes - batteryPackCharge;
			batteryPackCharge = 0;
			iBattery.drain(minutes);
		}
	}
	@Override
	public void recharge(int minutes) {
		if (iBattery.isFullyCharged()) {
			batteryPackCharge = batteryPackCharge + minutes * 2;
			batteryPackCharge = Math.min(batteryPackCapacity, batteryPackCharge);
		}
		else {
			iBattery.recharge(minutes);
		}		
	}
}