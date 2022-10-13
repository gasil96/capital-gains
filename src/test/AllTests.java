package test;

import test.processors.BuilderOutputProcessorTest;
import test.processors.TaxBusinessProcessorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BuilderOutputProcessorTest.class,
		TaxBusinessProcessorTest.class
})
public class AllTests {
}


