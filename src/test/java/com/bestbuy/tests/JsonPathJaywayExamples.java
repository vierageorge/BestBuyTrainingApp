package com.bestbuy.tests;

import static io.restassured.RestAssured.*;

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
	
	@DisplayName("Get the root element")
	@Test
	public void getRoot() {
		Map<String, ?> rootElement = JsonPath.read(jsonResponse, "$");
		System.out.println(rootElement.toString());
	}
	
	
	@DisplayName("Get the total value from the response")
	@Test
	public void getTotalFromResponse() {
		int totalValue = JsonPath.read(jsonResponse, "$.total");
		System.out.println(String.valueOf(totalValue));
	}
	
}
