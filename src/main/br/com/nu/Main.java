package br.com.nu;

import br.com.nu.dtos.OperationDTO;
import br.com.nu.dtos.TaxDTO;
import br.com.nu.processors.BuilderOutputProcessor;
import br.com.nu.processors.TaxBusinessProcessor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final List<List<OperationDTO>> ALL_LIST_OPERATIONS = new ArrayList<>();
	private static final List<List<TaxDTO>> ALL_TAXS = new ArrayList<>();

	private final TaxBusinessProcessor processTaxProcessor;
	private final BuilderOutputProcessor builderOutputProcessor;

	public Main(TaxBusinessProcessor processTaxProcessor, BuilderOutputProcessor builderOutputProcessor) {
		this.processTaxProcessor = processTaxProcessor;
		this.builderOutputProcessor = builderOutputProcessor;
	}

	public static void main(String[] args) {
		System.out.println("Please enter list [...] after empty line for end process.");

		startListener();
		startProcessAllOperations();
		startOutputData();
	}

	private static void startListener() {
		Scanner scanner = new Scanner(System.in);
		String line;
		while (!(line = scanner.nextLine()).equals("")) {
			ALL_LIST_OPERATIONS.add(builderList(line));
		}

		scanner.close();
	}

	private static void startProcessAllOperations() {
		ALL_LIST_OPERATIONS.forEach(operations -> ALL_TAXS.add(TaxBusinessProcessor.process(operations)));
	}

	private static void startOutputData() {
		String result = BuilderOutputProcessor.mountStringResult(ALL_TAXS);
		System.out.println(result);
	}

	private static List<OperationDTO> builderList(String line) {
		Gson g = new Gson();
		return g.fromJson(line, new TypeToken<List<OperationDTO>>() {}.getType());
	}

}
