# Spring Clusus Bloomberg to Analyze FX deals

### Running petclinic locally
Clusus Bloomberg is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
https://github.com/BiranSyangbo/clusus_boomberg-demo
cd clusus_boomberg-demo
./mvnw package
java -jar target/*.jar
```

You can then access Clusus Bloomberg here: [http://localhost:8080/](http://localhost:8080/)

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

> NOTE: Windows users should set `git config core.autocrlf true` to avoid format assertions failing the build (use `--global` to set that flag globally).

## Database configuration

Please change the DB username and password from application.yml

## Working with Clusus Bloomberg in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 11 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA

### Steps:

1) On the command line
    
    git clone https://github.com/BiranSyangbo/clusus_boomberg-demo
    
2) Inside Eclipse or STS
    
    File -> Import -> Maven -> Existing Maven project
    
3) Inside IntelliJ IDEA
    In the main menu, choose `File -> Open` and select the Clusus Bloomberg [pom.xml](pom.xml). Click on the `Open` button.


4) Navigate to Clusus Bloomberg

    Visit [http://localhost:8080](http://localhost:8080) in your browser.
