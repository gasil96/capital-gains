package br.com.nu.dtos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TaxDTO implements Serializable {

	NumberFormat formatter = new DecimalFormat("#0.00");

	private static final long serialVersionUID = 35231326599055683L;

	private Double tax;

	public TaxDTO(Double tax) {
		this.tax = tax;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	@Override
	public String toString() {
		return "{\"tax:\"" + formatter.format(tax) + "}";
	}
}
