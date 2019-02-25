package cas.finalproject.wt;

public class JobInfo {
	
	//is it necessary for final vars to still be private? 
	final Date date;
	final Province geography;
	final String industry;	//North American industry classification system (NAICS)
	final int vacancies;	//job vacancies
	final int employees;	//payroll employees
	final float vacancyRate;//job vacancy rate
	final float averageWage;//average offered hourly wage
	
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
	
	public boolean noData() {
		return vacancies == -1 && employees == -1 &&
				vacancyRate == -1 && averageWage == -1; 
	}
	
	public String toString() {
		return "{" +
			date.toString() + ", " +
			geography.toString() + ", " +
			industry + ", " +
			Integer.toString(vacancies) + ", " +
			Integer.toString(employees) + ", " +
			Float.toString(vacancyRate) + ", " +
			Float.toString(averageWage) + "}";
	}
	
}