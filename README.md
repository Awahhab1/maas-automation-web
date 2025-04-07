# Selenium TestNG Framework with POM and Extent Report

## 1. Introduction
This project is a test automation framework developed using **Selenium WebDriver**, **TestNG**, **Page Object Model** **Page Factory** and **Extent Reports**. It aims to provide a reliable solution for automated UI testing with detailed reporting and screenshots for easy analysis.

---

## 2. Setup Instructions

### Pre-requisites:
1. **Java**: Make sure you have **Java JDK** installed. If not, you can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Maven**: The project is built using Maven for dependency management. You can install Maven from [Apache Maven](https://maven.apache.org/).

3. **IDE**: You can use **IntelliJ IDEA**, **Eclipse**, or any other Java IDE for running the tests.

4. **Selenium WebDriver**: Ensure that **Selenium WebDriver** is set up in the project via Maven dependencies. The necessary dependencies will be automatically fetched through Maven when the project is built.

## 3.Usage
   ### Running Tests:
* To run the tests, simply execute the testng.xml file. This file contains all the test cases to be executed.

* Before that update the config.properties file if required

* Open the testng.xml file, which is located in the root directory of your project.

* Right-click on the testng.xml file in your IDE (e.g., IntelliJ IDEA or Eclipse) and select Run.

* Alternatively, you can run the tests using Maven with the following command:


### `mvn test`

###  Viewing the Reports:
After the tests are executed, you can view the Extent Reports in the test-output/ExtentReport.html file.

Open this HTML file in any browser to view detailed reports, including test case results, execution status, logs.

## 4. Test Case Descriptions
## Test Cases:
Login Test: Validates the user login functionality with valid credentials.

Registration Test: Verifies the user registration process with valid inputs.

### Preconditions:
For login tests, ensure that the user credentials are correct and the user account exists.

For registration tests, ensure that the email and phone number used for registration are valid.

### Expected Outcomes:
* Login Test: The user should be redirected to the control panel page after entering correct credentials and OTP.
Note For OPT Automate the underline API call using Rest assured and  read code from the response

* Registration Test: After successful registration, the user should see a success message and be redirected to the login page.

### Error Handling:
If invalid credentials are entered, an error message is displayed.

If the user tries to register with an already registered email or invalid data, an error message is displayed.

## 5 Project Structure 


### Mass Automation Project Structure

```
**mass-automation/
- .idea/                          # IDE configuration files (IntelliJ)
- logs/                          # Log files directory
- reports/                       # Test execution reports
- screenshots/                   # Screenshots from test runs
- src/
  - main/
    - java/
      - pages/                   # Page Object classes
        - BasePage.java
        - HomePage.java
        - LoginPage.java
        - RegistrationPage.java
  - test/
    - java/
      - testBase/                # Base test classes
        - BaseTest.java
      - testCases/               # Test case implementations
      - utility/                 # Utility/helper classes
    - resources/                # Test resources
- config/
    -- config.properties
    -- log4j2.xml                  # Logging configuration
- target/                       # Build output directory
- .gitignore                    # Specifies files to ignore in version control
- pom.xml                       # Maven project configuration file
- README.md                     # Project documentation
- testing.xml                   # Test configuration file**
```

### Key Components
- **Page Objects**: Located in `src/main/java/pages/` for UI automation
- **Test Structure**: Organized under `src/test/java/` with:
    - Base tests (`testBase/`)
    - Test cases (`testCases/`)
    - Utilities (`utility/`)
- **Configuration**:
    - `log4j2.xml` for logging
    - `testing.xml` for test configurations
- **Build Management**:
    - `pom.xml` for Maven dependencies
   ```
## 6. Extensibility
### Adding New Tests:
To add new tests, follow the steps below:

#### Create a New Test Class:

* Create a new class in the testCases directory.

* Annotate your test methods with @Test for TestNG.

## Define Page Object Models (POM):

Add page objects to the pages directory for interacting with new UI elements.

Ensure all actions are separated by page object class for maintainability.

## Add Test Data:

Use the ConfigReader utility to read test data from a config.properties file, or pass test data via test annotations.

## Add Assertions:

Use assertions like Assert.assertEquals(), Assert.assertTrue(), etc., to validate the functionality of the application.

## Add Logs:

If a test fails, make sure to capture screenshots and log relevant information for debugging.

## Run Your Test:

After adding your new test cases, execute them via testng.xml or Maven.