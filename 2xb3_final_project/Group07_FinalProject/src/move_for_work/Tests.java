package move_for_work;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import move_for_work.algorithms.JobsSort;
import move_for_work.algorithms.unique_jobs;
import move_for_work.data.DatasetReader;
import move_for_work.data.JobInfo;

public class Tests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadCleanData() throws Exception {
		long time = System.currentTimeMillis();
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		time = System.currentTimeMillis() - time;
		System.out.println(time + "ms to initialize data");
		
		time = System.currentTimeMillis();
		DatasetReader.cleanData(jobs);
		time = System.currentTimeMillis() - time;
		System.out.println(time + "ms to clean data");
		
		boolean allHaveData = true;
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).noData()) {
				allHaveData = false;
				break;
			}
		assert allHaveData;
	}
	
	@Test
	public void testSortIndustry() throws Exception {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		//System.out.println("Industries");		
		//System.out.println(jobs.size());
		
	    JobsSort.sortBasicQuick(jobs);
		/*for (int i = 0; i < jobs.size(); i++)
			System.out.println(jobs.get(i));*/
		
		ArrayList<String> jobNames = unique_jobs.TypesOfIndustry(jobs);
		/*for (int k = 0 ; k < jobNames.size(); k++)
			System.out.println(jobNames.get(k));*/

		assert (JobsSort.isSorted(jobs));
	}
	
	@Test
	public void testUniqueNames() throws Exception {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		ArrayList<String> jobNames = unique_jobs.TypesOfIndustry(jobs);
		
		ArrayList<String> uniqueNames = new ArrayList<String>();
		int n = jobNames.size();
		boolean namesAreUnique = true;
		for (int i = 0; i < n; i++) {
			if (uniqueNames.contains(jobNames.get(i))) {
				System.out.print("uh oh");
				namesAreUnique = false;
				break;
			}
			else {
				uniqueNames.add(jobNames.get(i));
			}
		}
		System.out.print("jobNames " + jobNames.size()
			+ ", uniqueNames " + uniqueNames.size());
		assert namesAreUnique;
	}

}