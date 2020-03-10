# Uno Coding Challenge

## Tasks

Deploy a microservice using ECS or EKS (AWS).

Microservice should have a single HTTP endpoint. Request should provide two date values:

fromDate (DD.MM.YYYY)

toDate (DD.MM.YYYY)

Response should be the number of days between the two dates (exclusive). Both dates will be greater than 01.01.1900. Dates may be in the future. 

The result for each call should be stored in a DynamoDB table.

Please explicitly calculate the number of days between the two dates - do not use any built-in functions.


Example 1

fromDate = 01.01.2020

toDate = 03.01.2020

daysDiff = 1


Example 2

fromDate = 01.01.2020

toDate = 01.01.2030

daysDiff = 3652

## Running DynamoDB locally

Open a command line first, and go to the project root.

```
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```

Open another command line window and create a new table, if you don't have it, 
and run the below command in a single line:

```
aws dynamodb create-table --table-name DatesCalculatorHistory 
    --attribute-definitions AttributeName=id,AttributeType=S --key-schema AttributeName=id,KeyType=HASH 
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000
```

## Build

```
mvn clean package
```

## Run ( command line or using your favorite IDE )
```
mvn spring-boot:run
```

## Docker build and push to ECR
```
aws ecr get-login-password | docker login --username AWS --password-stdin 796202935654.dkr.ecr.us-east-1.amazonaws.com/uno

docker build -t uno .

docker tag uno:latest 796202935654.dkr.ecr.us-east-1.amazonaws.com/uno:latest

docker push 796202935654.dkr.ecr.us-east-1.amazonaws.com/uno:latest
```

## Contributing
I would love to have a chat about the project.

## License
[MIT](https://choosealicense.com/licenses/mit/)