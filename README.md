* JWT (JSON Web Token) is a commonly used method for token-based authentication and authorization in web applications. In Spring Boot, you can validate and check JWT tokens using various libraries.

* To validate JWT tokens in Spring Boot, you can use the jjwt library. This library allows you to parse, verify, and generate JWT tokens in your application.


  # dipendancy

    <!-- security -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
  
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-gson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.2</version>
			<scope>compile</scope>
		</dependency>
  
		<dependency>
			<groupId>com.nimbusds</groupId>
			<artifactId>nimbus-jose-jwt</artifactId>
			<version>9.10</version>
		</dependency>




 
