package move_for_work.algorithms;

import java.util.ArrayList;

import move_for_work.data.*;

//inclusive range on left, exclusive range on right 
//CURRENTLY DOESN'T CHECK IF YOUR DESIRED CRITERIA ACTUALLY EXISTS.
//E.G. "Wood product manufacturing", YUKON THROWS INDEX ERROR
public class JobFilter {
	
	public static int getLeftIndex(ArrayList<JobInfo> jobs, int lo, int hi, LambdaInt lambdaI) {
		hi--;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int compare = lambdaI.function(jobs.get(mid));
			if (compare >= 0)
				hi = mid - 1;
			else
				lo = mid + 1;
		}		
		return lo;
	}
	
	public static int getRightIndex(ArrayList<JobInfo> jobs, int lo, int hi, LambdaInt lambdaI) {
		hi--;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int compare = lambdaI.function(jobs.get(mid));
			if (compare > 0)
				hi = mid - 1;
			else
				lo = mid + 1;
		}		
		return lo;
	}
	
}
