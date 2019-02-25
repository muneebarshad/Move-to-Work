package cas.finalproject.wt;

public class Date implements Comparable<Date> {
	
	final int year;
	final int month;
	
	public Date(int year, int month) {
		this.year = year;
		this.month = month;
	}
	
	public Date(String date) {
		String[] info = date.split("-");
		this.year = Integer.parseInt(info[0]);
		this.month = Integer.parseInt(info[1]);
	}
	
	public String toString() {
		return Integer.toString(year) + "-" + Integer.toString(month);
	}
	
	@Override
	public int compareTo(Date that) {
		int cmp = this.year - that.year;
		if (cmp == 0)
			return this.month - that.month;
		else
			return cmp;
	}
	
}
