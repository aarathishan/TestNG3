package org.pojos;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.google.HouseAddress;
import org.pojo.google.Responses;
import org.pojo.res.DeResponse;
import org.pojo.res.location;

public class GoogleMaps {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		DeResponse dr=new DeResponse();
		dr.setAccuracy(20);
		dr.setAddress("Aaryav coss");;
		dr.setLanguage("tamil");
		dr.setPhone_number("7795545388");
		dr.setName("Aarathi");
		dr.setWebsite("www.details.com");
		location l=new location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		List<String> l1=new ArrayList<String>();
		l1.add("More");
		l1.add("Koshys");
		dr.setTypes(l1);
		
		 Responses pr=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(HouseAddress.houseDetails("name","num"))
		.expect().defaultParser(Parser.JSON).when().post("/maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).extract().response().as(Responses.class);
		System.out.println(pr.getId()); 
		System.out.println(pr.getPlace_id());
		System.out.println(pr.getReference());
		System.out.println(pr.getScope());
		System.out.println(pr.getStatus());
		
	}

}
