*Note: This is a simple Java Project, but following this POC is applicable for both any kind of Java based application.*

# Pre-requisits
## Dependencies
#### For Logging
```
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>apache-log4j-extras</artifactId>
        <version>1.2.17</version>
    </dependency>
```

#### For AOP support
```
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
        <version>1.9.4</version>
    </dependency>

    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
```

*Note: Version may change*

In build section, use the following plugin.
```
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>aspectj-maven-plugin</artifactId>
        <version>1.7</version>
        <configuration>
            <complianceLevel>1.8</complianceLevel>
            <source>1.8</source>
            <target>1.8</target>
            <showWeaveInfo>true</showWeaveInfo>
            <verbose>true</verbose>
            <Xlint>ignore</Xlint>
            <encoding>UTF-8 </encoding>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <!-- use this goal to weave all your main classes -->
                    <goal>compile</goal>
                    <!-- use this goal to weave all your test classes -->
                    <goal>test-compile</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
```

#### Configuration

**Log4j**

Here I will only demonstrate properties based configuration. For XML / Java based configuration, please refer to this [Link](http://2min2code.com/articles/log4j_intro/).

```
log4j.rootLogger=INFO, loggerId
log4j.appender.loggerId=org.apache.log4j.FileAppender  
log4j.appender.loggerId.layout=org.apache.log4j.PatternLayout  
log4j.appender.loggerId.layout.ConversionPattern=%d [%t] %-5p (%F:%L) - %m%n  
log4j.appender.loggerId.File=example.log
```

**AOP**

The codes in ExecutionInterceptor is actually doing the job of AOP. ```@Aspect``` introduces this class as the aspect.

The ```@Around``` annotation is the advice, which means the logic inside method annoted with ```@Around``` will be added before and after the method execution declared in annotation's execution.

```
@Aspect
public class ExecutionInterceptor {


    private static final Logger LOGGER = Logger.getLogger(ExecutionInterceptor.class);

    // @Around("@annotation(Audit)")
    @Around("execution(public * *(..))")
    public Object intercept(final ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(point.toString());
        Thread.sleep(2000);
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(String.format("Executed in %s seconds", executionTime));
        return point.proceed();
    }
}

```

We have a method in ```AccountService``` class which matches the expression of advice defined in the ```@Around``` advice.

In main method, when we call the ```withdraw(int val)``` method from ```AccountService``` class, all the calls will be logged in the file defined in log4j configuration.