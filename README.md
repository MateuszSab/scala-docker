# scala-docker
## Description and purpose 

It's a pure scala application for simple data analysis that takes as an input two datasets: harvest.csv and prices.csv.

Its purpose is to perform basic data analysis and answer following questions: 

* Who is your best gatherer in terms of the amounts of fruits gathered every month? Are there employees that are better at gathering some specific fruit?

* What is your best-earning fruit (overall and by month)? Which is your least profitable fruit (again, overall and by month)?

* Which gatherer contributed most to your income (during the whole year and by month)?

It prints answers to the console. 

The application is ready to be dockerized. 
## Run application 

### Run with sbt 

```
* sbt "run <path to harvest file> <path to price file>" 
```

### Run as a docker container:

* Build docker image:

```
sbt docker
```

* Test with:

```
sbt test
```

* Run docker container: 

```
docker run -v <path from outside the container/path inside container> <your image name> <path to harvest file within container> <path to price file within container>  
```

