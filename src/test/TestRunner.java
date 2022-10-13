
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.AllTests;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		if (result.getFailureCount() == 0) {
			System.out.println("Tests passed: " + result.getRunCount());
		}
	}

}
