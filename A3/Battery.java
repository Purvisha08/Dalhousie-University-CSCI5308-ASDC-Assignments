public class Battery implements IBattery
{
	private int capacity;
	private int charge;
	
	public Battery(int capacityMinutes)
	{
		capacity = capacityMinutes;
		charge = capacity;
	}
	
	public int getCharge()
	{
		return charge;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public boolean hasEnoughPowerForMinutes(int minutes)
	{
		if (charge - minutes >= 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isFullyCharged()
	{
		return (charge == capacity);
	}
	
	public void drain(int minutes)
	{
		charge -= minutes;
		charge = Math.max(charge, 0);
	}
	
	public void recharge(int minutes)
	{
		charge += minutes * 2;
		charge = Math.min(capacity, charge);
	}
}