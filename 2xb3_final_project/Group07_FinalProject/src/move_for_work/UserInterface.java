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
		
		ArrayList<Province> provinceList = new ArrayList<Province>();
		for (Province p : Province.values())
			if (p != Province.CANADA && p != Province.ERROR)
				provinceList.add(p);
		for (int i = 0; i < provinceList.size(); i++)
			System.out.println((i + 1) + ": " + provinceList.get(i));
		System.out.print("Hello. Please enter your province by number: ");
		int in = input.nextInt();
		System.out.println();
		Province userProvince = provinceList.get(in - 1);
		
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
			LambdaInt industryFilter = j -> j.industry.compareTo(industry);
			int industryL = JobFilter.getLeftIndex(jobs, 0, jobs.size(), industryFilter);
			int industryR = JobFilter.getRightIndex(jobs, 0, jobs.size(), industryFilter);
			
			//display graph
			GraphAlgorithm.GraphWage(jobs, industryL, industryR);
			
			//print provinces, get choice
			ProvinceSearch pSearch = new ProvinceSearch();
			Province[] myOrder = pSearch.getOrder(userProvince);
			for (int i = 0; i < myOrder.length; i++)
				System.out.println((i + 1) + ": " + myOrder[i]);
			
			System.out.print("Industry average wages displayed. Please choose your desired region by number: ");
			num = input.nextInt();
			System.out.println();
			Province province = myOrder[num - 1];
			
			//get relevant province data
			LambdaInt provinceFilter = j -> j.geography.compareTo(province);
			int provinceL = JobFilter.getLeftIndex(jobs, industryL, industryR, provinceFilter);
			int provinceR = JobFilter.getRightIndex(jobs, industryL, industryR, provinceFilter);
			int vacancies = JobsAnalysis.SumJobVacancies(jobs, provinceL, provinceR);
			float wage = JobsAnalysis.AverageWage(jobs, provinceL, provinceR);
			System.out.println(industry + ", " + province);
			System.out.println("Sum of vacancies: " + vacancies);
			System.out.println("Average wage: " + wage);
			System.out.print("Continue (Y/N)? ");
			String inString = input.next();
			StdDraw.clear();
			if (inString.equals("N") || inString.equals("n"))
				break;
		}
		
		input.close();
		System.exit(0);
	}

}
