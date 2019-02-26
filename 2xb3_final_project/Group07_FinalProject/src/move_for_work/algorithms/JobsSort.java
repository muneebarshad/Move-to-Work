package move_for_work.algorithms;

import java.util.ArrayList;


import move_for_work.data.JobInfo;


public class JobsSort{

	private static void exch(ArrayList <JobInfo>	a,	int i,	int j)
	{	
	JobInfo t	=	a.get(i);	
	a.set(i,a.get(j));	
	a.set(j,t);	
	}
	
	private static boolean less(JobInfo v, JobInfo w)
	{	return v.compareTo(w) <	0;	}
	
	private static int TwoWaypartition(ArrayList <JobInfo> x, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		JobInfo v= x.get(lo);
		while(true) {
			while(less(x.get(++i),v)) if (i == hi) break;
			while(less(v,x.get(--j))) if (j == lo) break;
			if (i >= j) break;
			exch(x,i,j);
		}
		exch(x,lo,j);
		return j;
	}
	
	private static void sortQuick( ArrayList <JobInfo> x, int lo , int hi) {
		if (hi <= lo) return;
		int j = TwoWaypartition(x,lo,hi);
		sortQuick(x,lo,j-1);
		sortQuick(x,j+1,hi);
	}
	
	public static void sortBasicQuick (ArrayList <JobInfo> x ) {
		int lo = 0;
		int hi = x.size() - 1;
		sortQuick(x,lo,hi);
		
	}
	
	public static boolean isSorted(ArrayList <JobInfo> a) { // Test whether the array entries are in order.
		for (int i = 1; i < a.size(); i++)
			if (less(a.get(i), a.get(i - 1)))
				return false;
		return true;
	}
	

}