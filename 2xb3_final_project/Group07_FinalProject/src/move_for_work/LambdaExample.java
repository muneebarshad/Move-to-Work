package move_for_work;

import java.util.ArrayList;

import move_for_work.data.*;

public class LambdaExample {
	
	public static void printByCriteria(ArrayList<JobInfo> jobs, int lo, int hi, LambdaBool lambdaB) {
		for (int i = lo; i < hi; i++) {
			JobInfo thisJob = jobs.get(i);
			if (lambdaB.function(thisJob))
				System.out.println(thisJob);
		}		
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		
		LambdaBool lambda = job -> job.geography == Province.CANADA;
		printByCriteria(jobs, 0, 5, lambda);
		
		printByCriteria(jobs, 0, 10, job -> job.vacancies == -1);
		
		Date jul_2017 = new Date(2017, 7);
		String industry = "Total, all industries";
		printByCriteria(jobs, 0, jobs.size(), job ->
		job.date.compareTo(jul_2017) == 0 && job.industry.equals(industry));
	}

}
