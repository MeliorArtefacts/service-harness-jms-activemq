# Melior Service Harness :: JMS : ActiveMQ
<div style="display: inline-block;">
<img src="https://img.shields.io/badge/version-2.3-green?style=for-the-badge"/>
<img src="https://img.shields.io/badge/production-ready-green?style=for-the-badge"/>
<img src="https://img.shields.io/badge/compatibility-spring_boot_2.4.5-green?style=for-the-badge"/>
</div>

## Artefact
Get the artefact and the POM file in the *artefact* folder.
```
<dependency>
    <groupId>org.melior</groupId>
    <artifactId>melior-harness-activemq</artifactId>
    <version>2.3</version>
</dependency>
```

## Client
Create a bean to instantiate the ActiveMQ client.  The ActiveMQ client uses connection pooling to improve performance.
```
@Bean("myclient")
@ConfigurationProperties("myclient")
public ActiveMQClient client() {
    return ActiveMQClientBuilder.create().async().build();
}
```

The ActiveMQ client is auto-configured from the application properties.
```
myclient.url=tcp://some.service:61616
myclient.username=user
myclient.password=password
myclient.queue=myQueue
myclient.request-timeout=30
myclient.inactivity-timeout=300
```

Map the attributes in the JSON message to the fields in the JAVA class.
```
public class Message {
    @JsonProperty("category")
    private String category;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("volume")
    private double volume;

    @JsonProperty("balance")
    private double balance;

    ...
}
```

Wire in and use the ActiveMQ client.  There is no need to convert the JAVA object to JSON before using the ActiveMQ client to send the message.  The ActiveMQ client performs the object mapping automatically.
```
@Autowired
private ActiveMQClient client;

public void foo(Message message) throws RemotingException {
    client.send(message)
}
```

The ActiveMQ client may be configured using these application properties.

|Name|Default|Description|
|:---|:---|:---|
|`url`||The URL of the ActiveMQ server|
|`username`||The user name required by the ActiveMQ server|
|`password`||The password required by the ActiveMQ server|
|`minimum-connections`|0|The minimum number of connections to open to the ActiveMQ server|
|`maximum-connections`|1000|The maximum number of connections to open to the ActiveMQ server|
|`connection-timeout`|30 s|The amount of time to allow for a new connection to open to the ActiveMQ server|
|`validate-on-borrow`|false|Indicates if a connection must be validated when it is borrowed from the JDBC connection pool|
|`validation-timeout`|5 s|The amount of time to allow for a connection to be validated|
|`request-timeout`|60 s||The amount of time to allow for a request to the ActiveMQ server to complete
|`backoff-period`|1 s|The amount of time to back off when the circuit breaker trips|
|`backoff-multiplier`|1|The factor with which to increase the backoff period when the circuit breaker trips repeatedly|
|`backoff-limit`||The maximum amount of time to back off when the circuit breaker trips repeatedly|
|`inactivity-timeout`|300 s|The amount of time to allow before surplus connections to the ActiveMQ server are pruned|
|`maximum-lifetime`|unlimited|The maximum lifetime of a connection to the ActiveMQ server|
|`prune-interval`|60 s|The interval at which surplus connections to the ActiveMQ server are pruned|

&nbsp;  
## References
Refer to the [**Melior Service Harness :: Core**](https://github.com/MeliorArtefacts/service-harness-core) module for detail on the Melior logging system and available utilities.
