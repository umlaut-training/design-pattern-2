# Pattern Training 2

## Setup
* Clone this code into a local repository.
* Check if the following runs from the project main folder: `mvn clean package` (in case maven is installed) `mvnw clean package` (otherwise)

Optional: For the mvc-spring demo you need a running Tomcat server or similar. For example:
* Download from https://tomcat.apache.org/download-90.cgi.
* Unzip
* Set CATALINA_HOME user variable to path of installation (not to /bin inside)
* Adjust tomcat7-maven-plugin according to your server setting in the [pom](mvx-pattern/mvc-spring/pom.xml) of mvc-spring module.
* Ad your server to your maven settings:

```
<servers>
  <server>
    <id>TomcatServer</id>
    <username>youradmin</username>
    <password>yourpassword</password>
  </server>
</servers>
````
* Configure the password of your Server in %CATALINA_HOME%\conf\tomcat-users.xml
```
     <role rolename="manager-gui"/>
     <role rolename="manager-script"/>
     <user username="youradmin" password="yourpassword" roles="manager-gui,manager-script" />
````
* With `mvn install` inside [mvc-spring](mvx-pattern/mvc-spring) you can deploy the war to the Tomcat.

## Pattern MVC vs MVP vs MVVM
There are four examples which show different implementations of a simple program with MVX patterns.
Explanations are given in the corresponding slides.

## Strategy Pattern
There is a sample implementations which should serve as base for the implementation of a strategy pattern. There are a
few unittests implemented, which check the structural elements of the pattern.

To Implement the strategy pattern follow the steps:
1. Run the unittests and check which ones fail and why they do
   * "inputOfNull_results_in_exception" should pass
   * "defaultBehaviour_prints_in_native_order" should pass
   * "sort_by_name_prints_in_alphabetic_order" should fail due to a missing interface implementation
   * "structural_implementation_strategy" should fail due to a missing interface implementation
2. Focus on the implementation of the strategy pattern (make the "structural_implementation_strategy" test pass). This can be done by following the steps:
   1. Within the com.umlaut.patterntraining.strategy package, create an Implementation of the ITestStrategy interface for the default strategy behaviour. See the getEntries-Method in StockPrinter.java for reference.
   2. Run the unittests. Verify the failure reason to be a missing field in the StockPrinter.
   3. Add the missing Field and assign an instance of the default strategy.
   4. Run the unittests. Verify the failure reason is a missing setter for the ITestStrategy-Interface.
   5. Add the setter.
   6. Run the unittests again. It should pass now. 
   7. To complete the strategy pattern implementation, alter the implementation of the "getEntries"-Method within the StockPrinter. It should call the strategies "getEntries" method.
3. Implement a new sorting strategy (SortByName) by creating a new ITestStrategy-derived class.
   1. Uncomment the verification code within the try-catch statement of the "sort_by_name_prints_in_alphabetic_order"-Test. Remove the "/*" and "*/".
   2. Check if "sort_by_name_prints_in_alphabetic_order" still fails. Failure reason should be a missing interface implementation.
   3. Within the com.umlaut.patterntraining.strategy package, create an Implementation of the ITestStrategy interface. 
      This time the implementation actually has to change the order of the Elements.
   4. Run the unittests and check the Results. 
   5. (optional) Add code to the main method, that demonstrates the new strategy implementation.