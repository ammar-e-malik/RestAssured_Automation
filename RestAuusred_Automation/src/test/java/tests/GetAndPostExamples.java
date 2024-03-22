package tests;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;



public class GetAndPostExamples {
	
	@Test
		public void get_1() {
		
			baseURI = "https://reqres.in/api";
			
			given()
				.get("/users?page=2")
			.then()
				.statusCode(200)
				.body("data.id[0]", equalTo(7));
		}

		
		@Test
		public void get_2() {
			
			baseURI = "https://reqres.in/api";
			given()
				.get("/users?page=2")
			.then()
				.statusCode(200)
				.body("data.id[0]", equalTo(7))
				.log().all();
		}
		
		@Test
		public void get_3() {
			
			baseURI = "https://reqres.in/api";
			
			given()
				.param("Key", "values")
				.header("key", "value")
				.get("/users?page=2")
			.then()
				.statusCode(200)
				.log().all()
				.body("data.first_name", hasItems("Michael","Lindsay"));
		}
		
		//@Test
		public void post() {
			
			baseURI = "https://reqres.in/api";

			Map<String, Object> map = new HashMap<String, Object>();

	//		map.put("name", "Raghav");
	//		map.put("job", "Teacher");

	//		System.out.println(map);

			JSONObject request = new JSONObject();

			request.put("name", "Ammar");
			request.put("job", "IT Professional");

			System.out.println(request);
			System.out.println(request.toJSONString());

			given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
			when().
			post("/users").
			then().
			statusCode(201);

		}


		@Test
		public void put() {
			
			baseURI = "https://reqres.in/api";

			JSONObject request = new JSONObject();

			request.put("name", "Ben");
			request.put("job", "Engineer");

			System.out.println(request);
			System.out.println(request.toJSONString());

			given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
			when().
			put("/users/2").
			then().
			statusCode(200);

		}

		@Test
		public void patch() {

			JSONObject request = new JSONObject();

			request.put("name", "Lisa");
			request.put("job", "Actress");

			System.out.println(request);
			System.out.println(request.toJSONString());

			given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
			when().
			patch("https://reqres.in/api/users/3").
			then().
			statusCode(200).
			log().all();

		}

		@Test
		public void delete() {
			
			baseURI = "https://reqres.in/api";
			
			when().
			delete("/users/5").
			then().
			statusCode(204).
			log().all();
		}

}
