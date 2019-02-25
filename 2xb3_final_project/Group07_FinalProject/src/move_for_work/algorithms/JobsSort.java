package move_for_work.algorithms;

import java.util.ArrayList;

import move_for_work.data.JobInfo;

public class JobsSort{

	private static ArrayList <JobInfo> aux;

	private static boolean less(JobInfo v, JobInfo w) {
		return v.compareTo(w) <	0;
	}

	public static boolean isSorted(ArrayList <JobInfo> x) { // Test whether the array entries are in order.
		for (int i = 1; i < x.size(); i++)
			if (less(x.get(i), x.get(i-1)))
				return false;
		return true;
	}

	public static void sortMergeTD (ArrayList<JobInfo>	x, int n ) {
		aux = new ArrayList<JobInfo>();
		sort2(x,0,n-1);
	}

	private static void sort2(ArrayList<JobInfo> x,int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo +(hi - lo)/2;
		sort2(x,lo,mid);
		sort2(x,mid+1,hi);
		merge(x,lo,mid,hi);
	}
	
	private static void merge(ArrayList<JobInfo> x, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k<= hi; k++)
			aux.add(k,x.get(k));
		for (int k = lo; k<= hi; k++) {
			if(i > mid) x.add(k,x.get(j++));
			else if(j > hi) x.add(k,aux.get(i++)); 
			else if(less(aux.get(j),aux.get(i))) x.add(k,aux.get(j++));
			else x.add(k,aux.get(i++));
		}

	}

}