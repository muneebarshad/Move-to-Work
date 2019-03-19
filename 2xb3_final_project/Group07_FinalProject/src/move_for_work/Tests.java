package move_for_work;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import move_for_work.algorithms.Average;
import move_for_work.algorithms.GraphAlgorithm;
import move_for_work.algorithms.JobFilter;
import move_for_work.algorithms.JobsAnalysis;
import move_for_work.algorithms.JobsSort;
import move_for_work.algorithms.unique_jobs;
import move_for_work.data.DatasetReader;
import move_for_work.data.JobInfo;
import move_for_work.data.LambdaInt;
import move_for_work.data.Province;
import move_for_work.data.TwoTuple;

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
		
		//relevant section w/ time tests
		long time = System.currentTimeMillis();
	    JobsSort.sortBasicQuick(jobs);
	    time = System.currentTimeMillis() - time;
	    System.out.println(time + "ms to sort data");
		/*for (int i = 0; i < jobs.size(); i++)
			System.out.println(jobs.get(i));*/
		
	    //relevant section w/ time tests
	    time = System.currentTimeMillis();
		ArrayList<String> jobNames = unique_jobs.TypesOfIndustry(jobs);
		time = System.currentTimeMillis() - time;
	    System.out.println(time + "ms to get job names");
		/*for (int k = 0 ; k < jobNames.size(); k++)
			System.out.println(jobNames.get(k));*/

		assert (JobsSort.isSorted(jobs));
	}
	
	@Test
	public void testUniqueNames() throws Exception {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		//relevant section
		ArrayList<String> jobNames = unique_jobs.TypesOfIndustry(jobs);
		
		//confirmation of correctness
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
	
	@Test
	public void testFilterIndustry() throws Exception {
		String industry = 
				"Aboriginal public administration";
				//"Crop production";
				//"Wood product manufacturing";
		
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		//relevant section w/ time tests
		LambdaInt filter = j -> j.industry.compareTo(industry);
		long time = System.currentTimeMillis();
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);
		time = System.currentTimeMillis() - time;
		System.out.println(time + "ms to get range");
		
		//running checks and display
		boolean leftOK = (left == 0)
				? true : jobs.get(left - 1).compareTo(jobs.get(left)) < 0;		
		boolean rightOK = (right == jobs.size())
				? true : jobs.get(right - 1).compareTo(jobs.get(right)) < 0;
		boolean middleOK = true;
		//System.out.println(left + " " + jobs.get(left).industry + " " + jobs.get(left).geography);
		for (int i = left + 1; i < right; i++) {
			if (jobs.get(i - 1).industry.compareTo(jobs.get(i).industry) != 0) {
				middleOK = false;
				break;
			}
			//System.out.println(i + " " + jobs.get(i).industry + " " + jobs.get(i).geography);
		}
		assert leftOK && middleOK && rightOK;
	}
	
	@Test
	public void testFilterIndustryProvince() throws Exception {
		String industry = 
				"Aboriginal public administration";
				//"Crop production";
				//"Wood product manufacturing";
		Province province =
				Province.ALBERTA;
		
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		//relevant section
		LambdaInt filter = j -> j.industry.compareTo(industry);
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);		
		LambdaInt filterProv = j -> j.geography.compareTo(province);
		left = JobFilter.getLeftIndex(jobs, left, right, filterProv);
		right = JobFilter.getRightIndex(jobs, left, right, filterProv);
		
		//running checks and display
		boolean leftOK = (left == 0)
				? true : jobs.get(left - 1).compareTo(jobs.get(left)) < 0;		
		boolean rightOK = (right == jobs.size())
				? true : jobs.get(right - 1).compareTo(jobs.get(right)) < 0;
		boolean middleOK = true;
		System.out.println(left + " " + jobs.get(left).industry + " " + jobs.get(left).geography);
		for (int i = left + 1; i < right; i++) {
			if (jobs.get(i - 1).compareTo(jobs.get(i)) != 0) {
				middleOK = false;
				break;
			}
			System.out.println(i + " " + jobs.get(i).industry + " " + jobs.get(i).geography);
		}
		assert leftOK && middleOK && rightOK;
	}
	
	@Test
	public void testSumVacancies() {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		//narrowing down scope
		String industry = "Crop production";
		Province province = Province.ALBERTA;
		LambdaInt filter = j -> j.industry.compareTo(industry);
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);		
		LambdaInt filterProv = j -> j.geography.compareTo(province);
		left = JobFilter.getLeftIndex(jobs, left, right, filterProv);
		right = JobFilter.getRightIndex(jobs, left, right, filterProv);
		
		//calculating expected result
		int vacancies = 0;
		int n = jobs.size();
		for (int i = 0; i < n; i++) {
			JobInfo j = jobs.get(i);
			if (j.industry.equals(industry)
					&& j.geography.equals(province)
					&& j.vacancies > 0)
				vacancies += j.vacancies;
		}
		
		//relevant section
		int test = JobsAnalysis.SumJobVacancies(jobs, left, right);
		System.out.println(vacancies + " " + test);
		assert vacancies == test;
	}
	
	@Test
	public void testAverageWage() {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		//narrowing down scope
		String industry = "Crop production";
		Province province = Province.ALBERTA;
		LambdaInt filter = j -> j.industry.compareTo(industry);
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);		
		LambdaInt filterProv = j -> j.geography.compareTo(province);
		left = JobFilter.getLeftIndex(jobs, left, right, filterProv);
		right = JobFilter.getRightIndex(jobs, left, right, filterProv);
		
		//calculating expected result
		float wage = 0;
		int count = 0;
		int n = jobs.size();
		for (int i = 0; i < n; i++) {
			JobInfo j = jobs.get(i);
			if (j.industry.equals(industry)
					&& j.geography.equals(province)
					&& j.averageWage >= 0) {
				wage += j.averageWage;
				count++;
			}
		}
		if (count > 0)
			wage /= count;
		
		//relevant section
		float test = JobsAnalysis.AverageWage(jobs, left, right);
		System.out.println(wage + " " + test);
		assert wage == test;
	}
	
	@Test
	public void testAverageList() {
		String industry = "Crop production";
		
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		LambdaInt filter = j -> j.industry.compareTo(industry);
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);

		//relevant section
		ArrayList<TwoTuple> list = Average.AverageList(jobs, left, right);
		
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i).first + " " + list.get(i).second);
				
		//calculating expected result
		Province province = Province.ALBERTA;
		LambdaInt filterProv = j -> j.geography.compareTo(province);
		left = JobFilter.getLeftIndex(jobs, left, right, filterProv);
		right = JobFilter.getRightIndex(jobs, left, right, filterProv);
		float wage = 0;
		int count = 0;
		int n = jobs.size();
		for (int i = 0; i < n; i++) {
			JobInfo j = jobs.get(i);
			if (j.industry.equals(industry)
					&& j.geography.equals(province)
					&& j.averageWage >= 0) {
				wage += j.averageWage;
				count++;
			}
			
		}
		if (count > 0)
			wage /= count;
		float test = list.get(0).second;
		assert wage == test;
	}
	
	@Test
	public void testGraphWage() throws Exception {
		String industry = "Crop production";
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		LambdaInt filter = j -> j.industry.compareTo(industry);
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(), filter);
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(), filter);
		
		//relevant section
		GraphAlgorithm.GraphWage(jobs, left, right);
		System.out.print("Input anything to exit... ");
		System.in.read();
	}

}