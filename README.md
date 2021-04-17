<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project is the backend for an application that schedules transfers and calculates their respective fees according to the transfer date and its value (you can find its fronted [here](https://github.com/viictor1224/bank-transfer-front)).

This project was developed in the clean architecture model which helps using the concepts of low coupling and high cohesion.

This backend was built using Java programming language, Apache Maven as a dependency manager and H2 embedded database to persist data.

### Built With

* [Spring](https://spring.io/) as application framework for the Java platform.
* [Java JDK 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html) as programming language.
* [Apache Maven](https://maven.apache.org/) as dependency manager.
* [Docker](https://www.docker.com/) platform as a service product that uses virtualization to deploy softwares.
* [H2 Database](https://www.h2database.com/html/main.html) as embedded database.
* [JUnit 4](https://junit.org/junit4/) as unit testing framework for Java.
* [Swagger](https://swagger.io/) as an open source application that helps generating APIs documentation.



<!-- GETTING STARTED -->
## Getting Started

Install Docker your machine to easily run this application.

### Prerequisites and Installation


1. Download and install [Docker](https://www.docker.com/).
2. Clone the repo
   ```sh
   git clone https://github.com/viictor1224/bank-transfer-scheduler.git
   ```
3. Deploy the application in docker by the following command in the project's root folder:
* CLI (root folder):
  ```sh
  docker run -d -p 8080:8080 viictor1224/bank-transfer-scheduler
  ```
  
* Full Application Deploy (deploying [frontend](https://github.com/viictor1224/bank-transfer-front) for this application):
  ```sh
  docker-compose up
  ```
  

<!-- USAGE EXAMPLES -->
## Usage

Application is already running and now you can test all method requests in  [Postman](https://www.postman.com/). 
You can also use [Swagger](https://swagger.io/) documentation of this application by accessing the following url:
  ```sh
http://localhost:8080/swagger-ui.html
  ```
Using frontend application you can interact with the application using:
  ```sh
http://localhost:4200
  ```

<!-- CONTACT -->
## Contact

Victor Xavier de Melo - [Linkedin](https://www.linkedin.com/in/victor-xavier-388855164/) - [E-mail](victorxm1@gmail.com)
