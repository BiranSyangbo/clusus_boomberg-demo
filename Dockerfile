From openjdk:11
copy ./target/clusus-bloomberg.jar clusus-bloomberg.jar
CMD ["java","-jar","clusus-bloomberg.jar"]