package move_for_work;

import java.util.ArrayList;

import move_for_work.algorithms.JobFilter;
import move_for_work.algorithms.JobsSort;
import move_for_work.data.DatasetReader;
import move_for_work.data.JobInfo;
import move_for_work.data.TwoTuple;

public class Graph {

    public static void Graph(String industry) {

    	StdDraw.setCanvasSize(1024, 512);
    	StdDraw.setXscale(0, 150);
    	StdDraw.setYscale(0, 50);
    	/*
		String industry = 
				"Local, municipal and regional public administration";
				*/
				ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
		ArrayList<TwoTuple> list = Average.Average(jobs, left, right);
		

    	StdDraw.setPenColor(StdDraw.BLUE);
		for(int i = 0; i < list.size(); i++){
			float value = list.get(i).second;
			float height = value;
			float x = 10*(i+1);
            StdDraw.filledRectangle(x, 0, 3, height);
            StdDraw.text(x, height + 1, String.valueOf(list.get(i).first));
            }

        }
    public static void main(String[] args) {
    	Graph("Local, municipal and regional public administration");
    }
}

