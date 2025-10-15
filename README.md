# Retailer-Store

Technologies used:
The project has been created using Java 17 and springboot version 3.5.6

The project makes use of Lombok.

It calculates the reward points, and stores separately for each month corresponding to it’s user id. Also, the cumulative points are stored for each customer. 
P.S. : Just some sample data has been provided (added in helper class), though the project can be modified further if the need arises.

Steps to run application:
	Pre requisite:
			1)Any Java supported IDE.
			2)Lombok installed for that IDE.
Steps:
1. Import the project to your IDE as a maven project.
2. Perform maven clean/update.
3. Start the application by running java class "RewardsApplication.java" as a java application.

Details about the project module:

Entity package:
This package contains the entity classes created (Right now @entity has not been used as no database is integrated. For the same reason Repository package – which contains the classes performing database operations has also not been added )

Service package:
The package currently contains the service bean and it’s implementation class. The implementation class contains the logic implementation, which might also involve interaction with other beans.

Helper package:
Helper package has been added to demonstrate the use of package which holds the classes that have utility services, performing any kind of calculations/operations avoiding injection of other beans.

Controller:
It contains the controller/rest controller classes.

Endpoint included:

GET Request : http://localhost:8080/calculateReward
/calculateReward - It accepts the list of transaction and returns the list of Customer along with the credit points earned in the last three months based on the amount spent, mentioned beside their corresponding month.


Request/Response:
Request:
- Request provided is a list of trsancation. The form for which is as following:
NOTE: 1) Currently the creationDate only accepts the date of format dd/MM/yyyy.
	2) Time accepted is currently 24 hour format.
[
  {
    "custId": 123,
    "custName": "abc",
    "startDate": "07/07/2025",
    "endDate": "13/09/2025",
    "transactions": [
      {
        "amount": 78,
        "creationDate": "13/08/2025"
      },
      {
        "amount": 95,
        "creationDate": "07/07/2025"
      },
      {
        "amount": 80,
        "creationDate": "23/07/2025"
      },
      {
        "amount": 50,
        "creationDate": "13/09/2025"
      },
      {
        "amount": 150,
        "creationDate": "13/09/2025"
      },
      {
        "amount": 54,
        "creationDate": "13/08/2025"
      }
    ]
  },
  {
    "custId": 103,
    "custName": "cde",
    "startDate": "13/07/2025",
    "endDate": "17/09/2025",
    "transactions": [
      {
        "amount": 150,
        "creationDate": "13/07/2025"
      },
      {
        "amount": 100,
        "creationDate": "08/08/2025"
      },
      {
        "amount": 200,
        "creationDate": "22/07/2025"
      },
      {
        "amount": 83,
        "creationDate": "04/09/2025"
      },
      {
        "amount": 290,
        "creationDate": "17/09/2025"
      }
    ]
  },
  {
    "custId": 122,
    "custName": "def",
    "startDate": "13/07/2025",
    "endDate": "13/11/2025",
    "transactions": [
      {
        "amount": 67,
        "creationDate": "13/08/2025"
      },
      {
        "amount": 400,
        "creationDate": "13/07/2025"
      },
      {
        "amount": 47,
        "creationDate": "13/09/2025"
      }
    ]
  }
]

Response received:
[
    {
        "custId": 103,
        "rewardPoints": {
            "AUGUST": 50,
            "JULY": 400,
            "SEPTEMBER": 463
        },
        "totalPoints": 913
    },
    {
        "custId": 123,
        "rewardPoints": {
            "AUGUST": 32,
            "JULY": 75,
            "SEPTEMBER": 150
        },
        "totalPoints": 257
    }
]