This is a skeleton of Spring Boot application which should be used as a start point to create a working one.
The goal of this task is to create simple web application which allows users to create TODOs via REST API.

Below you may find a proposition of the DB model:

![DB model](DBModel.png)

To complete the exercices please implement all missing classes and functonalites in order to be able to store and retrieve information about tasks and their categories.
Once you are ready, please send it to me (ie link to your git repository) before  our interview.


# How to use
* Start with ```./gradlew bootRun```
* The server is available at ```localhost:8080```

## Categories:
* There are some default Categories created on startup. 

* To list all categories:
```
GET /categories
```

* To list one specific category:
```
GET /categories/{categoryId}
```

* To create additional categories:
```
POST /categories/create
Content-Type: application/json

{
    "categoryName": "Test Category",
    "categoryDescription": "This is a test"
}
```

* To edit a category:
```
PUT /categories/update/{categoryId}

{
        "categoryName": "Edited category",
        "categoryDescription": "Edited category description"
}
```

* To delete a category (also deletes all Tasks related to the category):
```
DELETE /categories/delete/{categoryId}
```



## Tasks:
* To list all tasks:
```
GET /tasks
```

* To list one specific task:
```
GET /tasks/{taskId}
```

* To create a task:
  * Without any category:
  ```
      POST /tasks/create
      Content-Type: application/json
    
      {
          "taskName": "New Task",
          "taskDescription": "This is a new task",
          "deadline": "2023-01-12T22:00:00.000Z"
      }
  ```
  
  * With an existing category:
  ```
    POST /tasks/create
    Content-Type: application/json
    {
      "taskName": "New Task",
      "taskDescription": "This is a new task",
      "deadline": "2023-01-12T22:00:00.000Z",
      "taskCategory": {
          "categoryId": 1
      }
    }
  ```  

  * With a new category:
  ```
    POST /tasks/create
    Content-Type: application/json
    {
      "taskName": "New Task",
      "taskDescription": "This is a new task",
      "deadline": "2023-01-12T22:00:00.000Z",
      "taskCategory": {
          "categoryName": "New Category",
          "categoryDescription": "New Category Description"
      }
    }
  ```
    
    
* To edit a task:
```
PUT /tasks/update/{taskId}

{
        "taskName": "Edited Task",
        "taskDescription": "This is a new task aaaa",
        "deadline": "2023-01-12T22:00:00",
        "taskCategory": {
            "categoryId": 2
        }
}
```

* To delete a task:
```
DELETE /tasks/delete/{taskId}
```


# Known Issues:
* If a category gets deleted, all related tasks also get deleted
* If you list a category, the related tasks are not listed (This is to prevent recursion resulting in an endless 
response json. I don't know if there is a better solution for this problem. Currently, I am using ```JsonIgnoreProperties``` to prevent the problem)
* Tests missing for category CRUD operations (and their interaction with tasks...).  