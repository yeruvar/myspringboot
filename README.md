This application is a "microservice" spring boot application which provides rest api's. 

 Prerequisites to start the application:
 1. Java 1.8
 2. Maven 3.5 or later
 
 
 To run the application, simply run
 
 		mvn spring-boot:run
 		
 To test the application, simple run
 		mvn test
 		
 To build the application, simply run
 		mvn clean install
 
 This application provides the following apis's.
 
 1. A Hello World Rest  End point. To call this end point please use the following command
 	
 		Rest api: 		http://localhost:8080/hello    
 		Method:  		GET
 		Curl Command: 	curl -X GET http://localhost:8080/hello
 		
 2. A Rest end point to return unique word count of a text. This end point accepts a json containing a paragraph as input and returns the sorted unique words with count ignoring the case . Please use the following end point to call this end point
 
 		Rest api: 		http://localhost:8080/words    
 		Method:  		POST
 		Curl Command: 	curl -H "Content-Type: application/json" -X POST  -d'{ "id": 1, "text": "return count of the unique words in the text sorted in the alphabhetic order"}' http://localhost:8080/words
 		
 3. A Rest end point which returns json array with the first  fibonacci numbers. Command to execute this end point
 
 		Rest api: 		http://localhost:8080/fibonacci/{number}
 		Method:			GET
 		Curl Command: 	curl -X GET http://localhost:8080/fibonacci/10
 		
 4. A Rest end point which leads to deadlock
 
 		Rest api:		http://localhost:8080/transfer
 		Method:			GET
 		Curl Command:	curl -X GET http://localhost:8080/transfer
 		
 5. Rest end points which add's, searches, updates user to the in-process hsql database
 
 		Rest api: 		http://localhost:8080/users
 		Method:			GET  -- Searches for all users
 		Curl Command: 	curl -X GET http://localhost:8080/users
 		
 		Rest api: 		http://localhost:8080/users/{id}
 		Method:			GET  -- Searches for all user of given id
 		Curl Command: 	curl -X GET http://localhost:8080/users/1
 		
 		Rest api: 		http://localhost:8080/users
 		Method:			POST  -- Add an user
 		Curl Command: 	curl -H "Content-Type: application/json" -X POST  -d '{"id": 25, "name": "John","email": "john@gmail.com"}' http://localhost:8080/users
 		
 		Rest api: 		http://localhost:8080/users/{id}
 		Method:			PUT  -- Update an user
 		Curl Command: 	curl -H "Content-Type: application/json" -X PUT -d '{"id": 1, "name": "Raj","email": "raj@gmail.com"}' http://localhost:8080/users/1
 		
 		Rest api: 		http://localhost:8080/users/{id}
 		Method:			Delete  -- Delete an user
 		Curl Command: 	curl -X DELETE http://localhost:8080/users/2
 		
  6. Rest end points to fetch data from external system
  		
  		Rest api: 		http://localhost:8080/data
 		Method:			GET  -- Searches for all data
 		Curl Command: 	curl -X GET http://localhost:8080/data
 		
 		Rest api: 		http://localhost:8080/data/{id}
 		Method:			GET  -- Search for data of given id
 		Curl Command: 	curl -X GET http://localhost:8080/data/1
 		
 		
 
 		
 
 