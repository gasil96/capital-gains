package test.processors;

import br.com.nu.constants.OperationType;
import br.com.nu.dtos.OperationDTO;
import br.com.nu.dtos.TaxDTO;
import br.com.nu.processors.TaxBusinessProcessor;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaxBusinessProcessorTest {

	private static final Double PERIODIC_TITHE = 16.66666666666667;

	@Test
	public void processWithSuccessCaseOne() {
		OperationDTO op1 = new OperationDTO(OperationType.buy, 10.00, 100);
		OperationDTO op2 = new OperationDTO(OperationType.sell, 15.00, 50);
		OperationDTO op3 = new OperationDTO(OperationType.sell, 15.00, 50);

		List<TaxDTO> result = TaxBusinessProcessor.process(Arrays.asList(op1, op2, op3));

		assertEquals(3, result.size());
		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.get(0).getTax().toString()));
		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.get(1).getTax().toString()));
		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.get(2).getTax().toString()));
	}

	@Test
	public void processWithSuccessCaseTwo() {
		OperationDTO op1 = new OperationDTO(OperationType.buy, 10.00, 10000);
		OperationDTO op2 = new OperationDTO(OperationType.sell, 20.00, 5000);
		OperationDTO op3 = new OperationDTO(OperationType.sell, 5.00, 5000);

		List<TaxDTO> result = TaxBusinessProcessor.process(Arrays.asList(op1, op2, op3));

		assertEquals(3, result.size());
		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.get(0).getTax().toString()));
		assertEquals(new BigDecimal("10000.0"), new BigDecimal(result.get(1).getTax().toString()));
		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.get(2).getTax().toString()));
	}

	@Test
	public void testCalculateWeightedAverageWithSuccessPurchaseQuantityBiggerThenTwo() {
		OperationDTO firstOperation = new OperationDTO(OperationType.buy, 20.00, 10);
		OperationDTO secondOperation = new OperationDTO(OperationType.buy, 10.00, 5);

		Double result = TaxBusinessProcessor.calculateWeightedAverage(firstOperation.getQuantity().doubleValue(), firstOperation.getUnitConst(),
				secondOperation, 2);

		assertEquals(new BigDecimal("16.67"), new BigDecimal(result.toString()));
	}

	@Test
	public void testCalculateWeightedAverageWithSuccessPurchaseQuantityBiggerThenOne() {
		OperationDTO onlyOperation = new OperationDTO(OperationType.buy, 10.00, 10);

		Double result = TaxBusinessProcessor.calculateWeightedAverage(0.0, 0.0, onlyOperation, 1);

		assertEquals(new BigDecimal("10.0"), new BigDecimal(result.toString()));
	}

	@Test
	public void testRoundWithSuccess() {
		Double result = TaxBusinessProcessor.round(PERIODIC_TITHE);

		assertEquals(new BigDecimal("16.67"), new BigDecimal(result.toString()));
	}

	@Test
	public void testRoundWithZero() {
		Double result = TaxBusinessProcessor.round(0.0);

		assertEquals(new BigDecimal("0.0"), new BigDecimal(result.toString()));
	}

}
