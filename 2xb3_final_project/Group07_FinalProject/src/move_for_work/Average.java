package move_for_work;

import java.util.ArrayList;

import move_for_work.algorithms.JobFilter;
import move_for_work.algorithms.JobsAnalysis;
import move_for_work.algorithms.JobsSort;
import move_for_work.data.DatasetReader;
import move_for_work.data.JobInfo;
import move_for_work.data.Province;
import move_for_work.data.TwoTuple;

public class Average {
    
	public static ArrayList<TwoTuple> Average(ArrayList<JobInfo> jobs, int lo, int hi){
		ArrayList<TwoTuple> List = new ArrayList<TwoTuple>();
		
		for (int i = 0; i < Province.values().length; i++) {
			Province province = Province.values()[i];
			int left1 = JobFilter.getLeftIndex(jobs, lo, hi, j -> j.geography.compareTo(province));
			int right1 = JobFilter.getRightIndex(jobs, lo, hi, j -> j.geography.compareTo(province));
			float Average = JobsAnalysis.AverageWage(jobs, left1, right1);
			List.add(new TwoTuple(province, Average));
		}
		return List;
	}
	


	public static void main(String[] args) throws Exception {
		String industry = 
				"Aboriginal public administration";
				ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
		/*
		left = JobFilter.getLeftIndex(jobs, left, right,
				j -> j.geography.compareTo(province));
		right = JobFilter.getRightIndex(jobs, left, right,
				j -> j.geography.compareTo(province));
				*/
		ArrayList<TwoTuple> list = Average(jobs, left, right);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).first + " " + list.get(i).second);
		}

	}
}