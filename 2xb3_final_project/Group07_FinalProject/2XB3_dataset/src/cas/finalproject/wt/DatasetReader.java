package cas.finalproject.wt;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//1st revision done feb 21 2019
public class DatasetReader {
	private static String[] data;
	private static String coordinate2;
	
	private static String getValue(Scanner input) {
		if (coordinate2 == null || !input.hasNextLine())
			return "-1";
		data = input.nextLine().split("\",\"");
		if (!coordinate2.equals(data[10].substring(0, data[10].length() - 2))) {
			coordinate2 = null;
			return "-1";
		}
		return data[11].isEmpty() ? "-1" : data[11];
	}
	
	private static void addJob(ArrayList<JobInfo> jobs, Scanner input) {
		if (coordinate2 != null)
			data = input.nextLine().split("\",\"");
		coordinate2 = data[10].substring(0, data[10].length() - 2);
		jobs.add(new JobInfo(
				new Date(data[0].substring(1)),
				Province.getProvince(data[1]),
				data[3],
				Integer.parseInt(data[11].isEmpty() ? "-1" : data[11]),
				Integer.parseInt(getValue(input)),
				Float.parseFloat(getValue(input)),
				Float.parseFloat(getValue(input))
				));
	}
	
	public static ArrayList<JobInfo> readData(String filename) throws Exception {
		ArrayList<JobInfo> jobs = new ArrayList<JobInfo>();
		Scanner input = new Scanner(new File(filename));
		input.nextLine(); //skip the headers
		coordinate2 = "";
		while (input.hasNextLine())
			addJob(jobs, input);
		input.close();
		data = null;
		return jobs;
	}
	
	public static void cleanData(ArrayList<JobInfo> jobs) {
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).noData())
				jobs.remove(i--);
		jobs.trimToSize();
	}

	public static void main(String[] args) throws Exception {
		long time = System.currentTimeMillis();
		ArrayList<JobInfo> jobs = readData("14100326.csv");
		time = System.currentTimeMillis() - time;
		int n = jobs.size();
		
		long time2 = System.currentTimeMillis();
		cleanData(jobs);
		time2 = System.currentTimeMillis() - time2;
		
		for (int i = 0; i < 1000; i++) System.out.println(jobs.get(i).industry);
		System.out.println();
		for (int i = jobs.size() - 10; i < jobs.size(); i++) System.out.println(jobs.get(i));
		System.out.println();
		
		System.out.println(time2 + "ms for cleaning, " + jobs.size() + " jobs left");
		System.out.print(time + "ms for " + n + " jobs, ok");
	}

}
