package cas.finalproject.wt;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadCleanData() throws Exception {
		long time = System.currentTimeMillis();
		ArrayList<JobInfo> jobs = DatasetReader.readData("14100326.csv");
		time = System.currentTimeMillis() - time;
		System.out.println(time + "ms to initialize data");
		
		time = System.currentTimeMillis();
		DatasetReader.cleanData(jobs);
		time = System.currentTimeMillis() - time;
		System.out.println(time + "ms to clean data");
		
		boolean allHaveData = true;
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).noData()) {
				allHaveData = false;
				break;
			}
		assert allHaveData;
	}

}