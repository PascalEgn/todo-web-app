This is a skeleton of Spring Boot application which should be used as a start point to create a working one.
The goal of this task is to create simple web application which allows users to create TODOs via REST API.

Below you may find a proposition of the DB model:

![DB model](DBModel.png)

To complete the exercices please implement all missing classes and functonalites in order to be able to store and retrieve information about tasks and their categories.
Once you are ready, please send it to me (ie link to your git repository) before  our interview.


# My Comments
* Start with ```./gradlew bootRun```



1. Create: To create a new task, you would make a POST request to the /tasks endpoint, with a JSON representation of the task in the request body.

```
POST /tasks
Content-Type: application/json

{
"name": "New Task",
"description": "This is a new task",
"deadline":"2022-10-12T22:00:00.000Z",
"category": 1
}
```

2. Read: To retrieve a list of all tasks, you would make a GET request to the /tasks endpoint. To retrieve a specific task by ID, you would make a GET request to the /tasks/{id} endpoint, where {id} is the ID of the task you want to retrieve.
```
GET /tasks

GET /tasks/1
```

3. Update: To update a task, you would make a PUT request to the /tasks/{id} endpoint, where {id} is the ID of the task you want to update, and with a JSON representation of the updated task in the request body.
```
PUT /tasks/1
Content-Type: application/json

{
"name": "Updated Task",
"description": "This is an updated task",
"deadline":"2022-10-20T22:00:00.000Z",
"category": 2
}
```

4. Delete: To delete a task, you would make a DELETE request to the /tasks/{id} endpoint, where {id} is the ID of the task you want to delete.
```
DELETE /tasks/1
```

