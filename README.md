# IOC

## Contributors
- Andra (web)
- Teodor (mobile)
- Vlad & Adrian (server)

### Server

The server is using the mysql database. To install, configure and run:
```
sudo apt-get install mysql-server
/usr/bin/mysql_secure_installation
mysql -u root -p
```

You have to initially create the database:
```
create database evshare;
```

To run the server:
```
mvn clean install
mvn spring-boot:run
```

To test the server authentication:
```
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"email":"teodorstefu@gmail.com","password":"sugchec"}' http://localhost:8080/auth
```

To test the server user register:
```
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"email":"teodorstefu@gmail.com","firstName":"Teodor","lastName":"Stefu","password":"sugchec"}' http://localhost:8080/user/register
```

To test the server user get details:
```
curl http://localhost:8080/user?id=teodorstefu@gmail.com
```

To test the server event creation:
```
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"name":"BEST PARTY EVAH","location":"La Facultate","date":"12-04-2016"}' http://localhost:8080/event
```

More details about the API [here](https://app.apiary.io/evshare/editor).
