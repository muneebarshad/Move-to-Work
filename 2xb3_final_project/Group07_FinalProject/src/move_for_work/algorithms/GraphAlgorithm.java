package move_for_work.algorithms;

import java.util.ArrayList;

import move_for_work.StdDraw;
import move_for_work.data.DatasetReader;
import move_for_work.data.JobInfo;
import move_for_work.data.TwoTuple;

public class GraphAlgorithm {

    public static void GraphWage(ArrayList<JobInfo> jobs, int lo, int hi) {
    	StdDraw.setCanvasSize(1024, 512);
    	StdDraw.setXscale(0, 150);
    	StdDraw.setYscale(0, 50);
    	StdDraw.setPenColor(StdDraw.BLUE);
    	
    	ArrayList<TwoTuple> list = Average.AverageList(jobs, lo, hi);    	
		for(int i = 0; i < list.size(); i++) {
			float value = list.get(i).second;
			float height = value;
			float x = 10*(i+1);
            StdDraw.filledRectangle(x, 0, 3, height);
            StdDraw.text(x, height + 1, String.valueOf(list.get(i).first));
        }

    }
    
    public static void main(String[] args) {
    	String industry = "Crop production";
    	ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		DatasetReader.cleanData(jobs);
		JobsSort.sortBasicQuick(jobs);
		
		int left = JobFilter.getLeftIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
		int right = JobFilter.getRightIndex(jobs, 0, jobs.size(),
				j -> j.industry.compareTo(industry));
    	
    	GraphWage(jobs, left, right);
    }
}

