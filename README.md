 Course Search with Elasticsearch – Spring Boot (Assignment A)
=========================================================================
 Overview
-------------------------------------------------------------------------
This Spring Boot application connects to a local Elasticsearch instance to index and search course documents.
It supports:
------------

Full-text search (title & description)

Filters (age range, category, type, price range, start date)

Sorting (upcoming date, price ascending/descending)

Pagination

----------------------------------------------------------------------
🛠 Tech Stack
----------------------------------------------------------------------
Java 17

Spring Boot (replace with your version)

Spring Data Elasticsearch

Elasticsearch 

Docker & Docker Compose

Jackson (for JSON parsing)

Lombok 

--------------------------------------------------------------------------
Project Structure
---------------------------------------------------------------------------

src/main/java/com/in
    ├── config
    │   └── JacksonConfig.java
    ├── controller
    │   └── CourseSearchController.java
    ├── dto
    │   └── CourseSearchResponse.java
    ├── entity
    │   └── CourseDocument.java
    ├── repository
    │   └── CourseRepository.java
    ├── service
    │   ├── CourseSearchService.java
    │   └── DataLoaderService.java
src/main/resources
    ├── application.properties
    ├── sample-courses.json
docker-compose.yml
pom.xml

---------------------------------------------------------------------------------
 Setup Instructions
----------------------------------------------------------------------------------

1.Clone the Repository

git clone https://github.com/DevrajKalpure/CourseSearchwithElasticsearch-.git

cd Intern_UndoSchool_CourseSearch

++++++++++++++++++++++++++++++++++++++++++++++++++

2.Start Elasticsearch with Docker

Ensure Docker is installed.
Run:
docker-compose up -d
Verify:
curl http://localhost:9200
You should see JSON output with cluster details.

++++++++++++++++++++++++++++++++++++++++++++++++++++

3.Configure Application

Check src/main/resources/application.properties:

spring.elasticsearch.uris=http://localhost:9200

+++++++++++++++++++++++++++++++++++++++++++++++++++++

4.Build & Run Application

./mvnw clean install
./mvnw spring-boot:run

When the application starts:
It reads sample-courses.json

+++++++++++++++++++++++++++++++++++++++++++++++++++++++

-------------------------------------------------------
API Endpoint
-------------------------------------------------------

Search Courses
GET /api/search

| Name      | Type    | Description                                |
| --------- | ------- | ------------------------------------------ |
| q         | String  | Search keyword (title/description)         |
| minAge    | Integer | Minimum age filter                         |
| maxAge    | Integer | Maximum age filter                         |
| category  | String  | Course category                            |
| type      | String  | ONE\_TIME / COURSE / CLUB                  |
| minPrice  | Double  | Minimum price                              |
| maxPrice  | Double  | Maximum price                              |
| startDate | String  | ISO-8601 date (e.g., 2025-06-10T00:00:00Z) |
| sort      | String  | upcoming / priceAsc / priceDesc            |
| page      | Integer | Default 0                                  |
| size      | Integer | Default 10                                 |

------------------------------------------------------------------------
Example:
------------------------------------------------------------------------

curl "http://localhost:8080/api/search?q=math&minAge=5&maxAge=12&category=Science&type=COURSE&sort=priceAsc&page=0&size=5"

Example Response

{
  "total": 2,
  "courses": [
    {
      "id": "1",
      "title": "Math for Beginners",
      "category": "Math",
      "price": 150.0,
      "nextSessionDate": "2025-06-10T15:00:00Z"
    }
  ]
}

Sample Endpoints

http://localhost:8080/api/search
http://localhost:8080/api/search?q=python
http://localhost:8080/api/search?category=Art&type=ONE_TIME
http://localhost:8080/api/search?minAge=10&maxAge=14
http://localhost:8080/api/search?minPrice=50&maxPrice=150&sort=priceDesc
http://localhost:8080/api/search?startDate=2025-07-01T00:00:00Z