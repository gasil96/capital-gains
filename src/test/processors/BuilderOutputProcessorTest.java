package test.processors;

import br.com.nu.dtos.TaxDTO;
import br.com.nu.processors.BuilderOutputProcessor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuilderOutputProcessorTest {

	private static final String EXPECTED = "[{\"tax:\"0.00}, {\"tax:\"0.00}, {\"tax:\"0.00}]\n" +
			"[{\"tax:\"0.00}, {\"tax:\"0.00}, {\"tax:\"0.00}]\n";

	@Test
	public void mountStringResultWithSuccess() {
		TaxDTO firstTax = new TaxDTO(0.0);
		TaxDTO secondTax = new TaxDTO(0.0);
		TaxDTO thirdTax = new TaxDTO(0.0);

		String result = BuilderOutputProcessor.mountStringResult(List.of(
				Arrays.asList(firstTax, secondTax, thirdTax),
				Arrays.asList(firstTax, secondTax, thirdTax)));

		assertEquals(EXPECTED, result);
	}

}


