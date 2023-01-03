# TODO list - REST API

This project is a simple TODO list REST API application created using Spring framework. It allows user to manipulate his todo list 
by using REST endpoints through tools like `curl` or `Postman`.


##  Concepts used in this Application


* [@RestController](https://spring.io/guides/gs/rest-service/)
* [@Service](https://spring.io/guides/gs/rest-service/)
* [@Entity](https://spring.io/guides/gs/rest-service/)
* [CrudRepository](https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html)


### Usage
* Clone the repository and open it with your preferred IDE.
* Make sure you have installed and if not download and install it from source:
  * Java
    ```
    java --version
    ```
  * Maven 
    ```
    mvn --version
    ```
* Create Docker container for the PostgreSQL database. The following command will create a container and initialize
PostgreSQL database.
    ```
    docker-compose up -d
    ```
* Run the application using your preferred IDE.
* Interact with the application using `curl` or `Postman`.



##  Application Demo with [Postman](https://www.postman.com/):

Exported Postman collection is located in `./postman/TodoList.postman_collection.json`. This JSON file can be imported directly into 
the Postman and it lets user easily interact with the application.