public class Arguments
{
	private int defaultBatteryCapacity;
	private int numRobots;
	private int shelfCount;
	private int numBatteryPacks;
	private int batteryPackCapacity;
	
	public Arguments(String[] args)
	{
		try
		{
			shelfCount = Integer.parseInt(args[0]);
			numRobots = Integer.parseInt(args[1]);
			defaultBatteryCapacity = Integer.parseInt(args[2]);
			numBatteryPacks = Integer.parseInt(args[3]);
			batteryPackCapacity = Integer.parseInt(args[4]);
		}
		catch (Exception e)
		{
			System.out.println("Invalid arguments!");
			System.exit(0);
		}
		if (numBatteryPacks > numRobots)
		{
			System.out.println("Invalid arguments: more battery packs than robots.");
			System.exit(0);
		}
	}
	
	public int getDefaultBatteryCapacity()
	{
		return defaultBatteryCapacity;
	}
	
	public int getNumRobots()
	{
		return numRobots;
	}
	
	public int getShelfCount()
	{
		return shelfCount;
	}
	
	public int getNumBatteryPacks()
	{
		return numBatteryPacks;
	}
	
	public int getBatteryPackCapacity()
	{
		return batteryPackCapacity;
	}
}
