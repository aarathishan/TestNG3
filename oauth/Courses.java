package org.oauth;

import static io.restassured.RestAssured.*;

import java.util.List;

//import org.pojo.ACourses;
import org.pojo.AppResponse;
import org.pojo.WebAutomation;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Courses {

	public static void main(String[] args) {
		
		//code
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiqYlBPI3mrHVTDVIU2P0FogbBQd5eEYZU6HDA3eePdeXVBo70Nfb9C4UoUGlwM6Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String[] s=url.split("code=");
		String[] s1=s[1].split("&scope=");
		
		
		//access token
		String tokenResponse=given().log().all()
	   .queryParam("code",s1[0])
	   .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	   .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
	   .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
	   .queryParam("grant_type","authorization_code").urlEncodingEnabled(false)
	   .when().post("https://www.googleapis.com/oauth2/v4/token")
	   .then().assertThat().statusCode(200).extract().response().asString();
		JsonPath j=new JsonPath(tokenResponse);
		String token=j.get("access_token");
		
		//get courses
	    org.pojo.AppResponse response=given().log().all().queryParam("access_token",token).header("Content-Type","application/json").expect().defaultParser(Parser.JSON)
	   .when().post("https://rahulshettyacademy.com/getCourse.php")
       .then().assertThat().statusCode(200).extract().response().as(AppResponse.class);
		System.out.println(response.getInstructor());
		org.pojo.ACourses c=response.getCourses();
		List<WebAutomation>l=c.getWebAutomation();
		for (int i = 0; i < l.size(); i++) {
			
			System.out.println(l.get(i).getCourseTitle());
			
		}
		
		/*JsonPath js=new JsonPath(response);
		
		int size=js.get("courses.webAutomation.size()");
		for(int i=0;i<size;i++)
		{
			System.out.println(js.get("courses.webAutomation["+i+"].courseTitle"));
			System.out.println(js.get("courses.webAutomation["+i+"].price"));
		}
		
		int prop=js.get("courses.api.size()");
		for(int i=0;i<prop;i++)
		{
			System.out.println(js.get("courses.api["+i+"].courseTitle"));
			System.out.println(js.get("courses.api["+i+"].price"));	
		}*/
		
		
		
	}

}
