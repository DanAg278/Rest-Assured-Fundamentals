# REST Assured Fundamentals

Supporting code for the [Rest Assured Fundamentals](https://www.udemy.com/course/rest-assured-fundamentals/?referralCode=2A76479D71A62609414D) course on Udemy

Rest Assured code developed with Java using the [VideoGames API](https://videogamedb.uk/swagger-ui/index.html) and the [Football API](https://www.football-data.org/)

## Prerequisites

- Java Development Kit (JDK) installed
- Apache Maven installed

## Installation and Usage

Follow these steps to set up and run the project:

1. **Clone the Repository**
2. **Download Dependencies with Maven**

    ```shell
   mvn clean install
3. **Set Up the Configuration**
   Create a config.properties file to store your API Key. You can do this manually or using a text editor.  
   Remember to store it following the path src/test/config.properties  
   Replace YOUR_API_KEY with your actual API key. You can get this key once you register in the football API webpage.

    ```shell
    football.api.token=YOUR_API_KEY