# IOC

## Contributors
- Andra (web)
- Teodor (mobile)
- Vlad & Adrian (server)

### Server

To run the server:
```
mvn clean install
mvn spring-boot:run
```

To test the server:
```
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"email":"teodorstefu@gmail.com","password":"sugchec"}' http://localhost:8080/auth
```

More details about the API [here](https://app.apiary.io/evshare/editor).
