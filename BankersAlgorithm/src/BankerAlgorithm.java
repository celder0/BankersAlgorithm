import java.util.ArrayList;




public class BankerAlgorithm {
	
	private static void calculateAvailable(Process[] p, int[] a)
	{
		int[] avail = new int[p[0].getMax().length];
		for (int process = 0; process < p.length ; process++)
		{
			for(int resource = 0; resource < p[process].getMax().length; resource++)
			{
				avail[resource] += p[process].getAlloc()[resource];
			}
		}
		for(int resource = 0; resource < avail.length; resource++)
		{
			a[resource] = a[resource] - avail[resource];
		}
	}
	
	private static boolean processCanRun(Process p, int[] a)
	{
		boolean canRun = true;
		for (int resource = 0; resource< a.length; resource++)
		{
			if(a[resource] < p.getNeed()[resource]) 
				canRun = false;
		}
		return canRun;
	}
	
	
	private static void releaseCurrentAlloc(Process p, int[] a)
	{
		for (int resource = 0; resource < a.length; resource++)
		{
			a[resource] += p.getAlloc()[resource];
		}
	}
	
	private static void findRunnableProcess(ArrayList<Process> p, int[] a, ArrayList<Integer> c)
	{
		ArrayList<Process> remProc = new ArrayList<Process>();
		remProc = (ArrayList<Process>) p.clone();
		
		ArrayList<Integer> chos = new ArrayList<Integer>();
		chos = (ArrayList<Integer>) c.clone();
		
		int[] avail = a.clone();
		int[] prevAvail = a.clone();
		
		Process runningProcess;
		for (int process = 0; process < remProc.size(); process++)
		{
			runningProcess = remProc.get(process);
			if (processCanRun(remProc.get(process),avail))
			{
				releaseCurrentAlloc(remProc.get(process),avail);
				int processNumber = remProc.get(process).getNumber();
				remProc.remove(process);
				chos.add(processNumber);
				if(remProc.size()>0)
				{
					findRunnableProcess(remProc,avail,chos);	
				}
				else
				{
					System.out.println(chos);
				}
				avail = prevAvail.clone();
				remProc.add(process, runningProcess);
				chos.remove(chos.size()-1);	
			}
		}
	}

	public static void main(String[] args) {
		
//		try (BufferedReader br = File.newBufferedReader(Paths.get("names.txt"), StandardCharsets.UTF_8))
//		{
//			String sCurrentLine;
//			String[] holder = null;
//			while ((sCurrentLine = br.readLine()) != null) {
//				sCurrentLine = sCurrentLine.replaceAll("\"", "");
//				holder = sCurrentLine.split(",");
//			}
//			for (int i = 0; i < holder.length; i++)
//			{
//				names.add(holder[i]);
//			}
//		} catch (IOException e) {
//				e.printStackTrace();
//		}
		int[] p0Alloc = {0,1,0};
		int[] p0Max = {7,5,3};
		Process p0 = new Process(0,p0Alloc,p0Max);
		int[] p1Alloc = {2,0,0};
		int[] p1Max = {3,2,2};
		Process p1 = new Process(1,p1Alloc,p1Max);
		int[] p2Alloc = {3,0,2};
		int[] p2Max = {9,0,2};
		Process p2 = new Process(2,p2Alloc,p2Max);
		int[] p3Alloc = {2,1,1};
		int[] p3Max = {2,2,2};
		Process p3 = new Process(3,p3Alloc,p3Max);
		int[] p4Alloc = {0,0,2};
		int[] p4Max = {4,3,3};
		Process p4 = new Process(4,p4Alloc,p4Max);
		Process[] processes = {p0,p1,p2,p3,p4}; 
		ArrayList<Process> processesRemaining = new ArrayList<Process>();
		
		ArrayList<Integer> chosenPath = new ArrayList<Integer>();
		int[] available = {10,5,7};
		
		calculateAvailable(processes,available);
		
		
		for (int process = 0; process < processes.length; process++)
		{
			processesRemaining.add(processes[process]);
		}
		
		findRunnableProcess(processesRemaining,available,chosenPath);
	}

}
