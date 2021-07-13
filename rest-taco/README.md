# Taco App
#### *because every taco needs a REST*

## What does our taco app do?
- registers/creates users
- logs users in & creating a session
- creates taco
- add taco to order-list of tacos for that order
- takes ordering info

## what do we want the taco app to do? Future functionality
- see most recent taco creations (from other users)
- see status of current order: placed, cooking, delivering, arrived
- see contents of current order

## what is the data in our taco app?
- ingredients
- users
- taco (made of ingredients)
- orders (made of taco & users)

## endpoints
- /design
    - POST: takes a validated Taco object
        - saves Taco object to SQL table (TACO)
        - adds Taco object to the current order

- /design/recent
    - GET: returns a list of most recent Taco objects

- /orders/current
    - GET: takes orderId, maybe also userId as params & returns the current Order and it's taco-list

- /orders
  POST: takes a valid Order object and
  -save that Order object to our TACO_ORDERS table

- /registration
    - POST: takes a valid User object and adds it to the user table
      ? possibly start a session
