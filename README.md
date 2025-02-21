**Installation & Demo**
FraudBlock

**Packages Required:**

MySQL Server

MySQL Workbench

Java Development Kit (JDK) 11 or higher

Apache Tomcat Server

Maven (for dependency management)

Any IDE (e.g., IntelliJ IDEA, Eclipse)

Other Requirements:

Any Chromium-based browser (e.g., Chrome)

Postman (for API testing)

**1.Setup Process**

Clone the Project

git clone https://github.com/yourusername/YourProjectName

**Set Up MySQL Database**

-Open MySQL Workbench.

-Create a new database (e.g., product_identification).

-Run the SQL scripts provided in the sql folder to set up the necessary tables and data.

**Configure Database Connection**

Open the project in your IDE.

Locate the application.properties or database.properties file.

Update the database connection details:


spring.datasource.url=jdbc:mysql://localhost:3306/product_identification

spring.datasource.username=root

spring.datasource.password=yourpassword

**Build the Project**

Open a terminal in the project folder.

Run the following command to install required dependencies:

mvn clean install

Deploy the Application

**Open Apache Tomcat Server.**

Deploy the generated WAR file located in the target folder of your project.

Start the Tomcat server.

Access the Application

**Open your browser and navigate to:**

http://localhost:8080/YourProjectName

Interact with the Application

Use the web interface to interact with the product identification features.

You can also use Postman to test the REST APIs provided by the application.
