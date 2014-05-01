
public class Process {
	private int[] need;
	private int[] max;
	private int[] alloc;
	private int processNum;
	
	Process(int p, int[] a, int[] m)
	{
		int [] n = new int[a.length];
		this.processNum = p;
		this.alloc = a.clone();
		this.max = m.clone();
		for (int i = 0; i < n.length; i++)
		{
			n[i] = m[i] - a[i];
		}
		this.need = n.clone();
	}
	
	public int[] getMax()
	{
		return max;
	}
	
	public int[] getAlloc()
	{
		return alloc;
	}
	
	public int[] getNeed()
	{
		return need;
	}
	
	public int getNumber()
	{
		return processNum;
	}	
}
