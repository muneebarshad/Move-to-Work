package move_for_work.data;

public class JobInfo implements Comparable<JobInfo> {
	
	//is it necessary for final vars to still be private? 
	public final Date date;
	public final Province geography;
	public final String industry;	//North American industry classification system (NAICS)
	public final int vacancies;	//job vacancies
	public final int employees;	//payroll employees
	public final float vacancyRate;//job vacancy rate
	public final float averageWage;//average offered hourly wage
	
	public JobInfo(Date date, Province geography, String industry, int vacancies,
			int employees, float vacancyRate, float averageWage) {
		this.date = date;
		this.geography = geography;
		this.industry = industry;
		this.vacancies = vacancies;
		this.employees = employees;
		this.vacancyRate = vacancyRate;
		this.averageWage = averageWage;
	}
	
	//-1 denotes no data for this value
	//when summing, averaging, etc., ignore values < 0
	public boolean noData() {
		return vacancies == -1 && employees == -1 &&
				vacancyRate == -1 && averageWage == -1; 
	}
	
	@Override
	public int compareTo(JobInfo that)
	{
		int cmp = (this.industry.compareTo(that.industry)); 
		if(cmp != 0)
			return cmp;
		return this.geography.compareTo(that.geography);
	}
	
	public String toString() {
		return "{" +
			date.toString() + ", " +
			geography.toString() + ", \"" +
			industry + "\", " +
			Integer.toString(vacancies) + ", " +
			Integer.toString(employees) + ", " +
			Float.toString(vacancyRate) + ", " +
			Float.toString(averageWage) + "}";
	}
	
}