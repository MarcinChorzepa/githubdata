# DDD project

## Description 
    
Stwórz prosty RESTowy serwis, który zwróci informacje

-	Identyfikator 
-	Login
-	Nazwa
-	Typ
-	Url do avatara
-	Data stworzenia
-	Obliczenia <br/>
**API serwisu powinno wyglądać jak poniżej:** <br/>
``` GET /users/{login} 
{"id": "...", 
"login": "...",
"name": "…",
"type": "...",
"avatarUrl": „”,
"createdAt": "..."
"calculations": "..."}
```

Serwis powinien pobrać dane z **https://api.github.com/users/{login}** (np. https://api.github.com/users/octocat) <br/>
i przekazać je w API. W polu calculations powinien być zwrócony wynik działania <br/>
**6 / liczba_followers * (2 + liczba_public_repos)**

Serwis powinien zapisywać w bazie danych liczbę wywołań API dla każdego loginu.<br/>
Baza danych powinna zawierać dwie kolumny: LOGIN oraz REQUEST_COUNT. <br/>
Dla każdego wywołania usługi wartość REQUEST_COUNT powinna być zwiększana o jeden.


## Introduction

Current solution is the proposition of implementation Domain Driven Design 
technology stack :
- Spring Boot 2 (maven)
- PostgresDB
- Docker
test:
- Junit5
- WireMock
- EmbeddedPostgres
- Cucumber

2 domains one to get data from Github , second to put records to DB
Recording requests is done by asynchronous method every time when the request is started.
To avoid multithreading issues the count is done on database (only the method chceking if exists is synchronized) 
Additional endpoint has been added to check thie value of the request count 
#### How to run
##### Build
first you will have to build whole project in order described in pom.xml
```aidl
                      <modules>
        <module>domain</module>
        <module>infrastructure</module>
        <module>app</module>
    </modules>
```
After that you can run docker-compose command which will start db and backend

##### docker-compose 
You can run docker-compose.yml to start application and PostgresDB. It will start application on 8080 and DB on standard port 5432 so be sure that your local instsance is turned off <br/>
```
docker-compose up -d
```
You might need to rebuild the composition ```docker-compose build --no-cache``` <br/>
There is also docker-compose in ./database directory - you can use it to start only the DB and run application manually
##### maven
Runnable file is in the app module
## Usage :

endpoints URL is

http://localhost:8080/users/ <br/>
http://localhost:8080/statistics/

### Documentation 
Swagger
http://localhost:8080/swagger-ui/index.html

```
curl -X GET \
  http://localhost:8080/users/{userLogin}
}
```

```
curl -X GET \
  http://localhost:8080/statistics/{userLogin}
}
```
## Architecture
### domain

- contains all business logic; mandatory information for a 2 domains githubdata and statsrequests ( in future can be extend with validation rules of users inputs ; format of fields and so on)
- contains domain objects. Business concepts and words have their objects in code (ubiquitous language).
- transaction boundaries : manage database transactions ; decide whether actions results should be persisted or rollbacked.
- 3 directories 
    -  domain (entity objects, value objects , aggregators, validators and so...)
    -  infrastructure (interface for data from lower layer <= infrastructure)
    -  presentation (objects to respond to the higher layer => app)
- domain services ass api collector and orchestrator for each domain (transaction in) 

**testing :** only unit test but with 100% coverage (interfaces and domain services are test in IT)

**dependencies :** (almost) nothing. The upper module. See pom.xml.

### Infrastructure

- contains implementation of means for domain to communicate with the outside world 
- is responsible for one major topic ; manage persistence of requestStats and githubDetails with a persistent storage.
- the structure :
    -  Repositories and services to communicate with outside word (githubservice, jparepository)
    -  Integration - place where you do implementation of domain interfaces and map objects 

**testing :** integration test from domain interface to the repositories or services sources (EmbededPostgres, WireMock)

**dependencies** : domain, and many Spring/boot and persistence libraries stuff

### app

**UI and final runnable artifact**
- contains java main Class : entry point to launch the whole
- application packaging : an auto-executable jar
- user interface : user interface is a HTTP Rest/JSON API.

**testing :** integration test using cucumber from endpoint to repository (Cucumber , TestRestTemplate, WireMock, EmbededPostgres)

**dependencies** : GetThingsDone-domain, ddd-infra, and many Spring/boot stuff for Rest JSON

### Conclusions
Thanks to this approach we split responsibilities on different layers. 
We have clear lines of each module responsibilities. Test are easy to write because of these.
Developers can easily marge theirs own code from new features without conflicts with other.
The App module has no logic - it is responsible to decide about where the logic should be started - in controller , service , asyncservice, put to the SQS or other
Each implementation can be easily added or switched for eg. we can swith into mongoDB
In other hand - there is no need for such a small project to use this domain design. Each module has to be independent so there is some overload with DTO objects and mappers
Time spend on the configuration of 3 different modules wasn't worth what the application actually do.

Hope you liked it :)
 

