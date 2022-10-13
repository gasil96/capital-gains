package br.com.nu.processors;

import br.com.nu.dtos.TaxDTO;

import java.util.List;

public class BuilderOutputProcessor {

	public static String mountStringResult(List<List<TaxDTO>> allTaxs) { //TODO: UNIT TEST [string not empty]
		StringBuilder result = new StringBuilder();

		allTaxs.forEach(
				taxs -> {
					result.append(taxs);
					result.append("\n");
				}
		);

		return result.toString();
	}

}
