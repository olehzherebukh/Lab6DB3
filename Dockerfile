From openjdk:8
copy target/zherebukh_mvc-1.0-SNAPSHOT.jar zherebukh_mvc-1.0-SNAPSHOT.jar
CMD ["java","-jar","zherebukh_mvc-1.0-SNAPSHOT.jar"]