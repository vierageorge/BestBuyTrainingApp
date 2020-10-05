package com.bestbuy.tests;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

import com.jayway.jsonpath.JsonPath;

public class JsonPathJaywayExamples {
	
	static String jsonResponse;
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		
		jsonResponse = given().when().get("/products").asString();
	}
	
	@BeforeEach
	void printToConsole() {
		System.out.println("-----------Starting the test method-----------");
		System.out.println("    ");
	}
	
	@AfterEach
	void printToConsoleAgain() {
		System.out.println("-----------Ending the test method-----------");
		System.out.println("    ");
	}
	
	@Disabled
	@DisplayName("Get the root element")
	@Test
	public void getRoot() {
		Map<String, ?> rootElement = JsonPath.read(jsonResponse, "$");
		System.out.println(rootElement.toString());
	}
	
	
	@Disabled
	@DisplayName("Get the total value from the response")
	@Test
	public void getTotalFromResponse() {
		int totalValue = JsonPath.read(jsonResponse, "$.total");
		System.out.println(String.valueOf(totalValue));
	}
	
	@Disabled
	@DisplayName("Get all the data elements")
	@Test
	public void getAllDataElements() {
		List<HashMap<String, Object>> dataElements = JsonPath.read(jsonResponse, "$.data");
		dataElements.stream()
			.forEach(System.out::println);
	}
	
	@Disabled
	@DisplayName("Get first data element")
	@Test
	public void getFirtDataElement() {
		HashMap<String, Object> firstElement = JsonPath.read(jsonResponse, "$.data[0]");
		System.out.println(firstElement.toString());
	}
	
	@Disabled
	@DisplayName("Get the last data element")
	@Test
	public void getLastDataElement() {
		HashMap<String, Object> lastDataElement = JsonPath.read(jsonResponse, "$.data[-1]");
		System.out.println(lastDataElement.toString());
	}
	
	@Disabled
	@DisplayName("Get all the ids of the data elements")
	@Test
	public void getAllIdsUnderData() {
		List<String> ids = JsonPath.read(jsonResponse, "$.data[*].id");
		System.out.println(ids.toString());
	}
	
	@Disabled
	@DisplayName("Performs a deep scan of all the ids present in the response")
	@Test
	public void getAllIds() {
		List<String> ids = JsonPath.read(jsonResponse, "$..[*].id");
		System.out.println(ids.toString());
	}
	
	@DisplayName("Get the price of the products whose price is less than 5")
	@Test
	public void getNameOfProductsWithPriceLessThan5() {
		List<String> productNames = JsonPath.read(jsonResponse, "$.data[?(@.price < 5)].name");
		productNames.stream().forEach(System.out::println);
	}
}
