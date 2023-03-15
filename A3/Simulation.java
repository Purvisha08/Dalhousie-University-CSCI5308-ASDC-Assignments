import java.util.*;

public class Simulation
{
	private static Simulation theOneInstance = null;
	private IWarehouseFactory factory;
	private List<Object> robots;
	private int minutesToCompleteSimulation;
	private Shelf shelf;
	private TimerSubject timerSubject;

	public Simulation()
	{
		robots = new ArrayList<Object>();
		minutesToCompleteSimulation = 0;
		shelf = new Shelf();
	}

	public static Simulation instance()
	{
		if (null == theOneInstance)
		{
			theOneInstance = new Simulation();
		}
		return theOneInstance;
	}

	public IWarehouseFactory getFactory()
	{
		return factory;
	}

	public Shelf getShelf()
	{
		return shelf;
	}

	public void build(Arguments args, IWarehouseFactory factory)
	{
		this.factory = factory;
		this.timerSubject = TimerSubject.instance();
		shelf.setItemCount(args.getShelfCount());

		for (int i = 0; i < args.getNumBatteryPacks(); i++) {
			IRobot iRobot =  factory.createRobotWithBatteryPack(args.getDefaultBatteryCapacity(), args.getBatteryPackCapacity());
			timerSubject.attach((ITimerObserver)iRobot);
			this.robots.add(iRobot);
		}

		for (int i = 0; i < args.getNumRobots() - args.getNumBatteryPacks(); i++) {
			IRobot iRobot = factory.createRobot(args.getDefaultBatteryCapacity());
			timerSubject.attach((ITimerObserver)iRobot);
			this.robots.add(iRobot);
		}
	}

	public int run()
	{
		System.out.println("Simulation begun!");
		boolean robotsStillWorking = true;
		while (robotsStillWorking)
		{
			timerSubject.notifySubjects();
			minutesToCompleteSimulation += 1;
			robotsStillWorking = false;
			ListIterator<Object> iter = robots.listIterator();
			while (iter.hasNext())
			{
				IRobot robot = (IRobot)iter.next();
				if (robot.isWorking())
				{
					robotsStillWorking = true;
					break;
				}
			}
		}
		System.out.println("Simulation complete!");
		return minutesToCompleteSimulation;
	}
}

















