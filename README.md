# Demo Project

## Swagger URL: http://localhost:8080/swagger-ui.html

![alt text](./docs/ToDoSwagger.png)

## Choose the front-end project that will be built

```xml
<configuration>
    <nodeVersion>${node.version}</nodeVersion>
    <!--workingDirectory>src/frontend-vue</workingDirectory-->
    <workingDirectory>src/frontend-angular</workingDirectory>
    <!--workingDirectory>src/frontend-react</workingDirectory-->
</configuration>
```

```xml
<configuration>
    <outputDirectory>target/classes/static</outputDirectory>
    <resources>
        <resource>
            <!--directory>src/frontend-vue/dist</directory-->
            <directory>src/frontend-angular/dist/frontend-angular</directory>
            <!--directory>src/frontend-react/public</directory-->
            <filtering>true</filtering>
        </resource>
    </resources>
</configuration>
```

## Execute maven build:

```bash
mvn clean install
```