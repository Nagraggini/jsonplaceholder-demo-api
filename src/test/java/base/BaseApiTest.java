package base;

import org.junit.jupiter.api.BeforeEach;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseApiTest {
	
	@BeforeEach
	void setup() {
		// Testing this website:
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
		RestAssured.requestSpecification=RestAssured
				.given()
				.accept(ContentType.JSON) // Response type is JSON
				.contentType(ContentType.JSON); // Request body type is JSON
	
		/*
		 * If a test fails (e.g., it receives an unexpected status code), 
		 * this setting automatically outputs both the REQUEST and the RESPONSE 
		 * to the console in full detail.
		 */
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
