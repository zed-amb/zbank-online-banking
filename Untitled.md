<?xml version="1.0" encoding="UTF-8"?>  
<project xmlns="http://maven.apache.org/POM/4.0.0"  
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
    <modelVersion>4.0.0</modelVersion>  
  
    <groupId>org.example</groupId>  
    <artifactId>zbank-online-banking</artifactId>  
    <version>1.0-SNAPSHOT</version>  
  
    <properties>        <maven.compiler.source>21</maven.compiler.source>  
        <maven.compiler.target>21</maven.compiler.target>  
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
    </properties>  
  
    <dependencies>        <!-- AWS SDK for EventBridge -->  
        <dependency>  
            <groupId>software.amazon.awssdk</groupId>  
            <artifactId>eventbridge</artifactId>  
            <version>2.25.13</version>  
        </dependency>  
        <!-- AWS Lambda Java Core -->  
        <dependency>  
            <groupId>com.amazonaws</groupId>  
            <artifactId>aws-lambda-java-core</artifactId>  
            <version>1.2.3</version>  
        </dependency>  
        <!-- Jackson for JSON serialization -->  
        <dependency>  
            <groupId>com.fasterxml.jackson.core</groupId>  
            <artifactId>jackson-databind</artifactId>  
            <version>2.17.1</version>  
        </dependency>    </dependencies>  
    <build>        <plugins>            <!-- Maven Shade Plugin to package Lambda-compatible JAR -->  
            <plugin>  
                <groupId>org.apache.maven.plugins</groupId>  
                <artifactId>maven-shade-plugin</artifactId>  
                <version>3.4.1</version>  
                <executions>                    <execution>                        <phase>package</phase>  
                        <goals>                            <goal>shade</goal>  
                        </goals>                        <configuration>                            <createDependencyReducedPom>false</createDependencyReducedPom>  
                            <transformers>                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">  
                                    <mainClass>com.zbank.application.ApplicationHandler</mainClass>  
                                </transformer>                            </transformers>                        </configuration>                    </execution>                </executions>            </plugin>        </plugins>    </build>  
</project>