Writing Unit Tests for Spring MVC:

#### Required Dependencies

```
        <!-- FOR TESTING SUPPORT -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.2.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.0.8.RELEASE</version>
            <scope>test</scope>
        </dependency>

```

For Database, I used MySQL, and I differentiated profiles for Windows and Linux. Following is the example of profile setup.

```
@PropertySource({
        "classpath:application.properties",
        "classpath:application-${activeProfile}.properties"})
public class MysqlConfig {
    ...
}
```

My Test method is self explanatory. So I hope, it will be easy to understand. For any question, mail me at: mainuls18@gmail.com