# Retailer-Store

The project has been created using Java 17 and springboot version 3.5.6

The project makes use of Lombok.
It calculates the reward points, and stores separately for each month corresponding to it’s user id. Also, the cumulative points are stored for each customer. 
P.S. : Just some sample data has been provided (added in helper class), though the project can be modified further if the need arises.

Details about the project module:

Entity package:
This package contains the entity classes created (Right now @entity has not been used as no database is integrated. For the same reason Repository package – which contains the classes performing database operations has also not been added )

Service package:
The package currently contains the service bean and it’s implementation class. The implementation class contains the logic implementation, which might also involve interaction with other beans.

Helper package:
Helper package has been added to demonstrate the use of package which holds the classes that have utility services, performing any kind of calculations/operations avoiding injection of other beans.

Controller:
It contains the controller/rest controller classes.
