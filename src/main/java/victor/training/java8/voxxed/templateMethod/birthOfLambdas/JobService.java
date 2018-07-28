package victor.training.java8.voxxed.templateMethod.birthOfLambdas;

import java.util.function.Supplier;

import victor.training.java8.voxxed.more.fake.Service;
import victor.training.java8.voxxed.templateMethod.JobResults;

@Service
public class JobService {
	
	public void start(Supplier<JobResults> doWork) {
		try {
			System.out.println("set system user on thread");
			JobResults results = doWork.get();
			storeAndLogResults(results);
		} catch (Exception e) {
			storeAndLogResults(JobResults.error(e));
		} finally {
			System.out.println("Clear thread locals...");
		}
	}
	
	private void storeAndLogResults(JobResults results) {
		System.out.println("Persist results: " + results);
	}
}
