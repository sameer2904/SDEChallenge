# Google Analytics System Design

### GA enabled apps
These are GA script enabled webapps, which will keep shooting data to GA analytics backend. These apps will have a unique client id. This client id along with unique request id (Generated via either GUID or timestamp) will be sent in each request.

### HAProxy Load Balancers
HAProxy acts as layer 4 load balancer and proxy server for the underlying API Gateway. HAProxy is a open source highly available TCP/HTTP Load Balancer and proxying solution. Its most common use is to improve the performance and reliability of a server environment by distributing the workload across multiple servers.High-Availability (HA ) is provided via Keepalived. HAProxy routes the requests coming from Web/Mobile Visitor site to the API Gateway of the GA. Given the nature of a distributed system built for scalability and stateless request and response handling we can distribute the API gateways spread across geographies.

### API Gateway and Service discovery
We can use AWS API Gateway or any other API gateway to create an "front door" for GA services. API gateway is a layer 7 (HTTP) router that acts as a reverse proxy for upstream services. These API Gateway will invoke a lamda function to process the data further. API gateways are linear scalable and can handle very high volume of API calls. Security of the apis will be handled at this layer.

### Lamda function
We can also use Spring + Java based microservice here as well, but since lamda is easily scalable and can handle much larger volume of calls at much cheaper rate than microservice, so we will use  AWS lamda functions here. These lamda functions will save this data in a time series db like influx db and also put this in a queue (kafka or AWS SQS) for further processing. We can also do certain validation of the data and drop those data which fail the validations.

### kafka queue or SQS Queue
Apache Kafka or SQS Queue is used for building real-time streaming data pipelines that reliably get data between many independent systems or applications. At other end of the queue we have Apache spark to process "Stream of data" from queue.

### Apache Spark parallel stream processing
Spark Streaming is an extension of the core Spark API that enables scalable, high-throughput, fault-tolerant stream processing of live data streams.
It provides a high-level abstraction called a discretized stream, or DStream, which represents a continuous stream of data. We will also use Apache ignite. Apache Ignite is a distributed memory-centric database and caching platform that is used by Apache Spark users to 
	1. Achieve true in-memory performance at scale and avoid data movement from a data source to Spark workers and applications.
	2. More easily share state and data among Spark jobs.
We can also use AWS kensis or any other parallel stream processing service.
These Spark jobs will do the processing of the data, like running aggregators or other creating other reports from this data. The results will be saved in any NoSQL bigdata like cassandra.

### Cassandra DB
Process data and aggregated data will be stored in Cassandra db. Cassandra db is highly suited when documents are to be writen at a very high speed.


### GA Dashboard consumers
These are the users of dashboard. They will fire the search queries from the browsers. These call will reach to the serving side of the GA application. These calls will first hit the HAProxy Load balancers

### Microservices to serve dashboard calls
All the queries from from the LBs will reach to the Microservices, which will connect to the Cassandra DB and calculate the result and serve the request. If the request is for history data, it will pass the call to the different service which will be responsible for re-computing the historic data. We can use Spring + Java for this service. Since this service will be highly computation oriented, java multithreading would be very handy is parallel process.

### Microservices to compute historic data
This service is responsible for connecting to time series db to get raw data and Cassandra db and recompute the results and store it back to Cassandra db in case of re-compute. Since our Cassandra db is write through db with Apache Ignite caching. This service will actually be writing the updated results via Apache Ignite cache to Cassandra DB.

