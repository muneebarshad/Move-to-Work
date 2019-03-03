package move_for_work.algorithms;

import java.util.ArrayList;
import move_for_work.data.JobInfo;

/**
 * 
 * Adds all the unique types of jobs in an array
 *
 */
public class unique_jobs {
	
	public static ArrayList<String> TypesOfIndustry(ArrayList <JobInfo> x) {
		ArrayList<String> Industry = new ArrayList<String>();
		Industry.add(x.get(0).industry);
		int j = 0;		
		for (int i = 1; i < x.size(); i++) {
			if (Industry.get(j).equals(x.get(i).industry)) {
				i++;
			}
			else {
				Industry.add(x.get(i).industry);
				j++;
			}
		}
		return Industry;
	}
			
}


