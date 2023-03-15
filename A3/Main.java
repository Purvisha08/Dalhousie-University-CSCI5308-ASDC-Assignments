public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Constructing warehouse...");
		Arguments a = new Arguments(args);
		IWarehouseFactory factory = new WarehouseFactory();
		Simulation.instance().build(a, factory);
		int minutes = Simulation.instance().run();
		System.out.println("Simulation run time: " + Integer.toString(minutes));
	}
}
