<a id="readme-top"></a>

<!-- TABLE OF CONTENTS -->
# Contents Summary
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#techniques">Techniques</a></li>
        <li><a href="#project-structure">Project Structure</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#frontend">Front-end Project</a></li>
      </ul>
    </li>
    <li>
      <a href="#contact">Contact</a>
    </li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

<a id="about-the-project"></a>

This project is the back-end of an e-commerce website. It roughly demonstrates the project structure and coding techniques that I worked on daily in the company, Ailisi. Due to its demonstration purpose, the project only includes a basic shopping process now.

It is written by Java 11 with an integration of SpringBoot and RabbitMq. As a result, Starting up RabbitMQ server locally is necessary for running the whole program successfully. (If you do not know how to run RabbitMQ server locally on your application. Use the `RABBITMQ_README.md` to get started.)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

<a id="built-with"></a>

* [Java][Java-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Techiniques

<a id="techniques"></a>

* Framework
  * [Spring Boot][SpringBoot-url]
  * [MyBatis][MyBatis-url]
* Database
  * [H2 Database][H2-url]
* Message-oriented Middleware
  * [RabbitMQ][RabbitMQ-url]
* Documentation
  * [Swagger UI][SwaggerUI-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Project Structure

<a id="project-structure"></a>

![project-structure!](/assets/img/project_structure.jpg "Project Structure")

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Prerequisites

You need to install Erlang 20.0.x and RabbitMQ 3.7.3 on windows 10 to make sure that this project can run this project successfully. The installation steps can be found in `RABBITMQ_README.md`.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installation

1. Clone the repo
  ```sh
   git clone https://github.com/altheachu/travel.git
  ```
2. Start up RabbitMQ (details in `RABBITMQ_README.md`)
  ```sh
    rabbitmq-server.bat
  ```
3. Start up SpringBoot project through `/src/main/java/ecommerce/travel/TravelApplication`

### Front-end Project

/* TODO */

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[Java-url]: https://www.java.com/en/
[SpringBoot-url]: https://spring.io/projects/spring-boot
[MyBatis-url]: https://mybatis.org/mybatis-3/
[H2-url]: https://www.h2database.com/html/main.html
[RabbitMQ-url]: https://www.rabbitmq.com/
[SwaggerUI-url]: https://swagger.io/tools/swagger-ui/


