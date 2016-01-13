# flashcard-website

![Login screen](6.\ Abnahmephase/Analyse/login.png)

A demo site to create and learn with flashcards. It was created for the lecture "Web Engineering" at the Baden-Wuerttemberg Cooperative State University Karlsruhe. The goal was to use the technologies taught like JSP, Javabeans, HTML, Javascript and MySQL.

## Installation

The simplest was of trying the demo is by using docker-compose. After installing docker-compose and cloning the repository, you need 

```sh
cd flashcard-website/4.\ Implementierungsphase/Eclipse\ Workspace/

# Creates war file
docker-compose up build

# Start website and database
docker-compose up -d server && docker-compose logs
```

Now you should be able to see the website at <http://localhost:8080>. You can use the dummy account with the username `test@test.de` and the password `test` or create your own account.

## More Screenshots

![Overview screen](6.\ Abnahmephase/Analyse/overview.png)

![Question screen](6.\ Abnahmephase/Analyse/learningscreen-answer.png)

![Edit screen](6.\ Abnahmephase/Analyse/learningscreen-edit.png)

![Result screen](6.\ Abnahmephase/Analyse/learningscreen-end.png)