# Banking Management System

## **Installation & Demo**

### **Packages Required:**
- MySQL Server
- MySQL Workbench
- Java Development Kit (JDK) 11 or higher
- Apache Tomcat Server
- Maven (for dependency management)
- Any IDE (e.g., IntelliJ IDEA, Eclipse)

### **Other Requirements:**
- Any Chromium-based browser (e.g., Chrome)
- Postman (for API testing)

---

## **Setup Process**

### **Clone the Project**
```bash
git clone https://github.com/yourusername/YourProjectName
```

### **Set Up MySQL Database**
1. Open MySQL Workbench.
2. Create a new database (e.g., `banking_management`).
3. Run the SQL scripts provided in the `sql` folder to set up the necessary tables and data.

### **Configure Database Connection**
1. Open the project in your IDE.
2. Locate the `application.properties` or `database.properties` file.
3. Update the database connection details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_management
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### **Build the Project**
1. Open a terminal in the project folder.
2. Run the following command to install required dependencies:
```bash
mvn clean install
```

### **Deploy the Application**
1. Open Apache Tomcat Server.
2. Deploy the generated WAR file located in the `target` folder of your project.
3. Start the Tomcat server.

### **Access the Application**
Open your browser and navigate to:
```bash
http://localhost:8080/YourProjectName
```

### **Interact with the Application**
- Use the web interface to interact with the banking management features.
- You can also use Postman to test the REST APIs provided by the application.

---

### **Additional Resources**
I've also included all relevant documents (reports, presentations, base paper, etc.) in case anyone wants to use this as a minor/major project.

---

Feel free to contribute and enhance the project!

