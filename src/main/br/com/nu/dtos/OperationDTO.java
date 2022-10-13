package br.com.nu.dtos;

import br.com.nu.constants.OperationType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperationDTO implements Serializable {

	private static final long serialVersionUID = 1224836082734936580L;

	@SerializedName(value = "operation")
	private OperationType type;

	@SerializedName(value = "unit-cost")
	private Double unitConst;

	private Integer quantity;

	public OperationDTO(OperationType operation, Double unitConst, Integer quantity) {
		this.type = operation;
		this.unitConst = unitConst;
		this.quantity = quantity;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public Double getUnitConst() {
		return unitConst;
	}

	public void setUnitConst(Double unitConst) {
		this.unitConst = unitConst;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
