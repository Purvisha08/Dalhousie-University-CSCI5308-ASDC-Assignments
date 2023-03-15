public interface IBattery
{
	public boolean hasEnoughPowerForMinutes(int minutes);
	public boolean isFullyCharged();
	public void drain(int minutes);
	public void recharge(int minutes);
}
