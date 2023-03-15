public class Shelf
{
	private int itemCount;
	
	public Shelf()
	{
		itemCount = 0;
	}
	
	public void setItemCount(int count)
	{
		itemCount = count;
	}
	
	public int getItemCount()
	{
		return itemCount;
	}
	
	public void claimItem()
	{
		itemCount--;
	}
}
