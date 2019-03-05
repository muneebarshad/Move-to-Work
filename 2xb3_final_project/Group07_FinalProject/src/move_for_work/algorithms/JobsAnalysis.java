package move_for_work.algorithms;

import java.util.ArrayList;

import move_for_work.data.JobInfo;
import move_for_work.data.Province;

//-1 checks are necessary since -1 is used as dummy
public class JobsAnalysis {
	
	public static int SumJobVacancies(ArrayList<JobInfo> jobs, int lo, int hi) {
		int vacancies = 0;
		for (int i = lo; i < hi; i++) {
			JobInfo j = jobs.get(i);
			if (j.vacancies < 0)
				continue;
			vacancies += j.vacancies;
		}
		return vacancies;
	}
	
	//currently also accounts for jobs with avg wage of 0. should it?
	public static float AverageWage(ArrayList<JobInfo> jobs, int lo, int hi) {
		float wage = 0;
		int total = 0;
		for (int i = lo; i < hi; i++) {
			JobInfo j = jobs.get(i);
			if (j.averageWage < 0)
				continue;
			wage += j.averageWage;
			total++;
		}
		if (total > 0)
			wage /= total;
		return wage;
	}
	
	public static ArrayList<Province> Provinces(ArrayList<JobInfo> jobs, int lo, int hi) {
		ArrayList<Province> provinces = new ArrayList<Province>();
		for (int i = lo; i < hi; i++) {
			Province p = jobs.get(i).geography;
			if (provinces.contains(p))
				continue;
			provinces.add(p);
		}
		return provinces;
	}
}
