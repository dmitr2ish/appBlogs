# appBlogs
The Blogs app is a standard feed of posts.  
  
Используемые технологии:

Java 11  
Maven  
Spring boot 2.3.2.RELEASE  
PostgresSQL 12.3 (HSQL по умолчанию)  
Bootstrap  
Html5\CSS  
JQery\Ajax  
  
Задача:  
Сделать приложение по аналогии ленты постов.  
  
Описание запуска приложения:  
  
По умолчанию хранение постов осуществляется с помощю базе данных HSQL in-memory,  
поэтому для запуска приложения понадобиться только установленная Java машина,  
вы можете поменять профиль хранения постов на базу данных PostgresSQL через редактирование файла application.properties,  
разумеется у вас должна быть установлена данная база данных.  
  
Команды выполнять из корневой папки,  
  
для Windows  
  
Запуск из командной строки из корневой папки  
mvnw spring-boot:run 
  
для Windows  
  
Создание и запуск jar файла  
mvnw clean package  
cd target  
java -jar -0.0.1-SNAPSHOT.jar  
  
