# Autocomplete
This project is implemention of Autocomplete service.</br>
In this service I used Java 8, Spring boot 2.6.7 and H2 DB.

## Data Structure explanation
 In this implementation, I used TRIE Data Structure to save the word.</br>
 In a TRIE Data Structure, the Worst Case is O(Length-Of-Word).

## Rest API
In this service we have 3 REST API'S.</br>
 1. API for adding new word.</br>
      `Method:` POST</br>
      `URL:` http://localhost:9090/autocomplete/add/{wordToAdd} The parameter is taken for the path.</br>
      `Response:`  if succeed returning the item that was added.</br>
       ```json
       {
          "id": 202,
          "value": "Idan"
       }
       ```
      `in Error:`
      
      ```json
      {
        "timestamp": "5/05/2022 - 18:32:35",
        "message": "[IdaN] already exist in DB",
        "status": "BAD_REQUEST",
        "path": "/autocomplete/add/IdaN"
      }
      ```
2. API for searching prefix case senstive.</br>
      `Method:` GET</br>
      `URL:` http://localhost:9090/autocomplete/sensitive/{prefix} The parameter is taken for the path.</br>
      `Response:` list of the matching words.</br>
       `
       [
         "Adam",
         "Adrian"
       ]
       `  
3. API for searching prefix case insenstive.</br>
      `Method:` GET</br>
      `URL:` http://localhost:9090/autocomplete/insensitive/{prefix} The parameter is taken for the path.</br>
      `Response:`  list of the matching words.</br>
      
   
# Instructions for running the application
## Without docker
    1. mvn clean install.
    2. java -jar target\autocomplete-0.0.1-SNAPSHOT.jar 
    (The server port defined to be 9090)
## With docker
    1. build the project => mvn clean install.
    2. build docker image => docker image build --tag autocomplete_img ./
    3. create and run the new container => docker container run -d --name autocomplete_cont -p 9090:9090 autocomplete_img
