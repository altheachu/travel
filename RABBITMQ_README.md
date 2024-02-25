<a id="readme-top"></a>

<!-- TABLE OF CONTENTS -->
# Contents Summary

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#introduction">Introduction</a>
      <ul>
        <li><a href="#version-config">Version Config</a></li>
        <li><a href="#installation-procedure">Installation Procedure</a></li>
      </ul>
    </li>
    <li>
      <a href="#use-rabbitmq">Use RabbitMQ</a>
      <ul>
        <li><a href="#start-up">Start Up</a></li>
        <li><a href="#management-gui">Management GUI</a></li>
        <li><a href="#shut-down">Shut Down</a></li>
      </ul>
    </li>
  </ol>
</details>

<!-- Introduction -->
## Introduction

<a id="introduction"></a>

RabbitMQ is built based on Erlang, so you must install Erlang before installing RabbitMQ.

### Version Config

To install RabbitMQ successfully, the version of Erlang and RabbitMQ should match with each other. You can refer to the following table. 

![Erlang-RabbitMQ-config!](/assets/img/Erlang_rabbitMQ_config.jpg "Erlang & RabbitMQ config")

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installation Procedure

* Download [Erlang](Erlang-url)
* Double-click the downloaded file and set up (keep clicking 'next' button)
* Set up environmental variables:
  * Set up Erlang home as a system variable with a value of Erlang program path.
    
  ![Erlang-Home-Setting](/assets/img/Erlang_home.jpg "Set Erlang home as a system variable")
  
  * Amend the path in User Variable
  ![Erlang-Path-Setting-01](/assets/img/Erlang_path_01.jpg "Find Path Variable and click 'Edit' button.")
  ![Erlang-Path-Setting-02](/assets/img/Erlang_path_02.jpg "Add the bin folder path (with the Erlang home) of Erlang.")

[//]: # (https://blog.csdn.net/qq_42402854/article/details/103032007)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[Erlang-url]: https://www.erlang.org/downloads