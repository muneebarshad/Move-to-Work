package move_for_work;

import java.util.ArrayList;
import java.util.Scanner;

import move_for_work.algorithms.*;
import move_for_work.data.*;

public class UserInterface {

	public static void main(String[] args) throws Exception {
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		ArrayList<String> jobNames = unique_jobs.TypesOfIndustry(jobs);
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			//print industry list, get choice
			int n = jobNames.size();
			for (int i = 0; i < n; i++)
				System.out.println((i + 1) + ": " + jobNames.get(i));
			System.out.print("\nPlease select your desired industry by number: ");
			int num = input.nextInt();
			System.out.println();
			
			//get sublist of jobs in industry, sorted by province
			String industry = jobNames.get(num - 1);
			int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(),
					j -> j.industry.compareTo(industry));
			int right = JobFilter.getRightIndex(jobs, 0, jobs.size(),
					j -> j.industry.compareTo(industry));
			
			//print provinces, get choice
			ArrayList<Province> provinces = JobsAnalysis.Provinces(jobs, left, right);
			for (int i = 0; i < provinces.size(); i++)
				System.out.println((i + 1) + ": " + provinces.get(i));
			System.out.print("Please choose your desired region by number: ");
			num = input.nextInt();
			System.out.println();
			Province province = provinces.get(num - 1);
			
			//get relevant province data
			left = JobFilter.getLeftIndex(jobs, left, right,
					j -> j.geography.compareTo(province));
			right = JobFilter.getRightIndex(jobs, left, right,
					j -> j.geography.compareTo(province));
			int vacancies = JobsAnalysis.SumJobVacancies(jobs, left, right);
			float wage = JobsAnalysis.AverageWage(jobs, left, right);
			System.out.println(industry + ", " + province);
			System.out.println("Sum of vacancies: " + vacancies);
			System.out.println("Average wage: " + wage);
			System.out.print("Continue (Y/N)? ");
			String in = input.next();
			if (in.equals("N") || in.equals("n"))
				break;
		}
		
		input.close();
	}

}
