# spring-holidays

Simple REST supplier of holidays for given year or period.

### usage

This application is designed to work with "project-task" as a holidays supplier.
But since it is a web service it can be used standalone if one likes.

### How to get holidays for a year

http://localhost:8080/annual?year=2019

as a return it sends JSON with holidays for the 2019.

### How to get holidays for a period

http://localhost:8080/period?begin=2019-05-01&end=2021-03-31

as a return it sends JSON with holidays for the given period

### JSON format

[ 
   { 
      "name":"Nowy Rok",
      "iso":"2019-01-01"
   },
   { 
      "name":"Trzech Królów",
      "iso":"2019-01-06"
   },
...
]
