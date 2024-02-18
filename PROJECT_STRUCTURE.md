├─src
│  ├─main
│  │  ├─java
│  │  │  └─ecommerce
│  │  │      └─travel
│  │  │          ├─config
│  │  │          │  └─RabbitMQConfig
│  │  │          │  └─SwaggerConfig
│  │  │          ├─customer
│  │  │          │  ├─controller
│  │  │          │  │  └─CustomerController
│  │  │          │  ├─entity
│  │  │          │  │  └─Customer
│  │  │          │  ├─mapper
│  │  │          │  │  └─CustomerMapper
│  │  │          │  ├─model
│  │  │          │  │  └─CustomerModel
│  │  │          │  └─service
│  │  │          │      ├─impl
│  │  │          │      │  └─CustomerServiceImpl
│  │  │          │      └─CustomerService
│  │  │          ├─order
│  │  │          │  ├─controller
│  │  │          │  │  └─OrderController
│  │  │          │  ├─entity
│  │  │          │  │  ├─Order
│  │  │          │  │  └─OrderDetail
│  │  │          │  ├─mapper
│  │  │          │  │  ├─OrderMapper
│  │  │          │  │  └─OrderDetailMapper
│  │  │          │  ├─model
│  │  │          │  │  ├─OrderModel
│  │  │          │  │  └─OrderDetailModel
│  │  │          │  └─service
│  │  │          │      ├─impl
│  │  │          │      │  ├─OrderServiceImpl
│  │  │          │      │  └─OrderProxyServiceImpl
│  │  │          │      ├─OrderService
│  │  │          │      └─OrderProxyService
│  │  │          ├─product
│  │  │          │  ├─controller
│  │  │          │  │  └─ProductController
│  │  │          │  ├─entity
│  │  │          │  │  └─Product
│  │  │          │  ├─mapper
│  │  │          │  │  └─ProductMapper
│  │  │          │  ├─model
│  │  │          │  │  └─ProductModel
│  │  │          │  ├─service
│  │  │          │  │   ├─impl
│  │  │          │  │   │  └─ProductServiceImpl
│  │  │          │  │   └─ProductService
│  │  │          │  └─ProductRabbitConfig
│  │  │          ├─utility
│  │  │          │  ├─dto
│  │  │          │  │   ├─BaseProxyDTO
│  │  │          │  │   ├─OrderDetailProxyDTO
│  │  │          │  │   └─OrderEventProxyDTO
│  │  │          │  ├─entity
│  │  │          │  │   └─Eventlog
│  │  │          │  ├─mapper
│  │  │          │  │   └─EventlogMapper
│  │  │          │  ├─service
│  │  │          │  │   ├─impl
│  │  │          │  │   │  └─EventlogServiceImpl
│  │  │          │  │   └─EventlogService
│  │  │          │  └─utils
│  │  │          │      └─EventlogConstant
│  │  │          └─TravelApplication
│  │  └─resources
│  │      ├─static
│  │      ├─templates
│  │      ├─application.properties
│  │      └─schema.sql
│  └─test
│      └─java
│          └─ecommerce
│              └─travel
│                └─TravelApplicationTests