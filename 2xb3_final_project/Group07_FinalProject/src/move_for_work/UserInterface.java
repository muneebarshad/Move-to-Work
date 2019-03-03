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
			System.out.print("\nPlease select your desired industry by number (-1 to exit): ");
			int industry = input.nextInt();
			if (industry == -1 )
				break;
			System.out.println();
			industry++;
			
			//print provinces, get choice
			for (Province prov : Province.values())
				System.out.println(prov);
			System.out.print("Please choose your desired region: ");
			String region = input.next();
			Province province = Province.valueOf(region);
			
			//get sublist of jobs in industry, sorted by province
			//get relevant province data and print
			
		}
		
		input.close();
	}

}
