package utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class otpAPI {

    // Function to send the POST request and get OTP code
    public static String getOtpCode() {
        // URL for the login API
        String url = "https://maas-uat.futurework.com.sa/maas/login";

        // Send the POST request with headers, query parameters, and body, then capture the response
        Response response = RestAssured.given()
                .baseUri(url) // Set the base URI
                .queryParam("userName", ConfigReader.get("user.email")) // Add query parameter for userName
                .queryParam("mobile", ConfigReader.get("user.password")) // Add query parameter for mobile
                .queryParam("userType", "SME") // Add query parameter for userType
                .header("Accept", "application/json, text/plain, */*") // Add Accept header
                .header("Authorization", "Bearer <your_token_here>") // Example header for Authorization
                .body("{}") // Empty JSON body (if needed)
                .log().all()
                .post(); // Send POST request

        response.then().log().all();
        // Log the complete response for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check if the request was successful
        if (response.getStatusCode() == 200) {
            // Extract the OTP code from the response using JSON Path
            String otpCode = response.jsonPath().getString("code");
            System.out.println("OTP Code: " + otpCode); // Print OTP code for debugging
            return otpCode; // Return the OTP code
        } else {
            // Handle errors if response is not successful
            throw new RuntimeException("Failed to get OTP, status code: " + response.getStatusCode() + ", Response: " + response.getBody().asString());
        }
    }
}
