# aws-concepts-be
AWS training concepts

# Technologies
Uses Java 8 - Spring Boot 2.3.1 - MySQL 5 - AWS Cloudformation Templates

How to Run:

mvn spring-boot:run -Dspring.profiles.active=local

Debug mode:

mvn spring-boot:run -Dspring.profiles.active=local -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5006"