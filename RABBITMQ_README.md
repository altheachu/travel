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
    <li>
      <a href="#reference">Reference</a>
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

* Download [RabbitMQ](RabbitMQ-url)
* Double-click the downloaded file and set up (keep clicking 'next' button and 'install' button finally)
* Install RabbitMQ plugins (aim to visit login webpage through browser)
  * open up cmd console
  * find /sbin directory
    ```sh
    cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.3\sbin
    ```
  * key in the following command and press enter key.
    ```sh
    rabbitmq-plugins enable rabbitmq_management
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Use RabbitMQ

<a id="use-rabbitmq"></a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Start Up

<a id="start-up"></a>

* open up cmd console
* Find /spin directory
  ```sh
    cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.3\sbin
  ```
* key in the following command and press enter key.
  ```sh
    rabbitmq-server.bat
  ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Management GUI

<a id="management-gui"></a>

* Open up browser
* key in url [http://localhost:15672](Localhost-url)
* login in management GUI with *'guest'* as Username and Password

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Shut Down

<a id="shut-down"></a>

* Find node name in management GUI(/assets/img/RabbitMQ_node_name.jpg "Node name position in management GUI")
* open up cmd console
* Find /spin directory
  ```sh
    cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.3\sbin
  ```
* key in the following command and press enter key.
  ```sh
    rabbitmqctl stop -n rabbit@[nodeName]
  ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Reference

<a id="reference"></a>

If you can read Chinese, you can find more details [here](reference-url).

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[Erlang-url]: https://www.erlang.org/downloads
[RabbitMQ-url]: https://www.rabbitmq.com/docs/install-windows
[Localhost-url]: http://localhost:15672
[reference-url]: https://blog.csdn.net/qq_42402854/article/details/103032007