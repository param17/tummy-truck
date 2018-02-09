# Tummy Truck
REST API to simulate part of an online food ordering system.

## Technologies Used
* Spring
* Hibernate
* MySQL
* Ehcache
* Tomcat
* Maven
* Log4j
* Jersey

## Steps to Setup and Run
1. Install Tomcat (or any application server)
2. Install MySQL
3. Create a Database called 'tummytruck' using following command
    ```sql
    CREATE DATABASE tummytruck;
    USE tummytruck;
    ```
4. Run the MySQL script to create the necessary tables. Script is at [init.sql](../blob/master/src/main/scripts/init.sql)
5. Update the user credetials for MySQL in [spring-hibernate-jersey2.xml](../blob/master/src/main/webapp/WEB-INF/spring-hibernate-jersey2.xml)
6. Update the log location at [log4j.properties](../blob/master/src/main/resources/log4j.properties)
7. Change the overflow filesystem location for ehcache at [ehcache.xml](../blob/master/src/main/resources/ehcache.xml)
8. Go to project directory and run following command on terminal
    ```bash
    mvn clean install
    ```
9. It should build the war inside the target location.
10. Place that war inside the webapps directory in your tomcat location.
11. Now, you can run the REST APIs using any REST Client (Postman, Advanced REST Client, or DHC REST Client)
12. Enjoy!

## Notes on implementation details
* I started with working on basic details and structure of REST by implementing the GET, PUT, DELETE, with few more.
* Used sub-resources for linking Restaurants with Menu and with MenuItems.
* Implemented find feature to make it easier for user to search by name or part of name.
* Added second level caching using `ehcache3` to boost performance and offload the database.
* I believe how much of consistency tuning can be done, depends largely on what kind of website or product we are trying to build. For example, something like in a Bank firm we don't want to loosen up consitency of data (Hence, prefer SQL). Whereas in say Zappos, we can go with lower value of consistency to improve the performace to support high read rate (Hence, prefer NoSQL).
* Currenly I'm using Hibernate with MySQL and ehcache to have cache for each entity. I think, I can try with intergrating with Cassendra background as well.
* I've used the exception wrapper for handling exception and I've also created custom exceptions to customize how we should be handling in different scenarios.
* I tried to write as much JUnits possible, I think I wrote about 36ish. I've used JMockito framework, Since I feel more confortable with it and I believe it's more readable.
* I'm logging the exceptions using log4j, with customization properties.
* I've added the pom.xml for the project including all the libraries used. I've used Java 1.8 for development on my system with MySQL 5.7.21 and Tomcat 9.0.4 and coding on Eclipse Oxygen.
