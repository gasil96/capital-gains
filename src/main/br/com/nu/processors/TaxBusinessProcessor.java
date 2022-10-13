package br.com.nu.processors;

import br.com.nu.constants.OperationType;
import br.com.nu.dtos.OperationDTO;
import br.com.nu.dtos.TaxDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TaxBusinessProcessor {

	private static final Double ZERO = 0.00;
	private static final Double FREE_TAX_LIMIT = 20000.00;
	private static final Double TAX_PERCENTAGE = 0.20;

	public static List<TaxDTO> process(List<OperationDTO> operations) {
		List<TaxDTO> taxs = new ArrayList<>();
		AtomicReference<Integer> purchaseQuantity = new AtomicReference<>(0);
		AtomicReference<Double> average = new AtomicReference<>(0.00);
		AtomicReference<Double> accruedLosses = new AtomicReference<>(0.00);
		AtomicReference<Double> currentQuantityStock = new AtomicReference<>(0.0);

		operations.forEach(operation -> {
			if (OperationType.buy.equals(operation.getType())) {
				taxs.add(new TaxDTO(ZERO));
				purchaseQuantity.getAndSet(purchaseQuantity.get() + 1);
				average.set(calculateWeightedAverage(currentQuantityStock.get(), average.get(), operation, purchaseQuantity.get()));
				currentQuantityStock.updateAndGet(v -> v + operation.getQuantity());
			}

			if (OperationType.sell.equals(operation.getType())) {
				currentQuantityStock.updateAndGet(v -> v - operation.getQuantity());
				double total = operation.getUnitConst() * operation.getQuantity();
				boolean isFreeTax = total <= FREE_TAX_LIMIT;

				// Profit calculate
				if (operation.getUnitConst() > average.get()) {
					double profit = operation.getQuantity() * average.get();

					if (accruedLosses.get() >= profit) {
						accruedLosses.set(accruedLosses.get() - profit);
						taxs.add(new TaxDTO(ZERO));
						return;
					}

					total -= profit;

					if (accruedLosses.get() > ZERO) {
						total -= accruedLosses.get();
					}

					taxs.add(new TaxDTO(isFreeTax ? ZERO : total * TAX_PERCENTAGE));
				}

				// Loss calculate
				if (operation.getUnitConst() < average.get()) {
					double loss = operation.getQuantity() * average.get();
					accruedLosses.updateAndGet(accLoss -> accLoss + loss - (operation.getUnitConst() * operation.getQuantity()));
					taxs.add(new TaxDTO(ZERO));
				}

				// No profit and no loss
				if (Objects.equals(operation.getUnitConst(), average.get())) {
					taxs.add(new TaxDTO(ZERO));
				}
			}

		});

		return taxs;
	}

	public static Double calculateWeightedAverage(Double currentQuantityStock, Double averageCurrent,
	                                              OperationDTO operationCurrent, Integer purchaseQuantity) {
		if (purchaseQuantity > 1) {
			return round(((currentQuantityStock * averageCurrent) + (operationCurrent.getQuantity()
					* operationCurrent. getUnitConst())) / (currentQuantityStock + operationCurrent.getQuantity()));
		}

		return operationCurrent.getUnitConst();
	}

	public static Double round(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}

}
