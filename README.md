# Springboot and Spring Security System


This is a Springboot and Spring Security with JWT Authentication and Authorization Microservice with Access and refresh token features.

## JSON WEB TOKEN (JWT)
> This is just a way for application to transmit information. Application A to talk to application B securely. is very popular this days because it is very small, self contained and secure. Because it is small, you pass it into request bodies, headers and forms. It is easy to work it and it has become very popular for APIs for authorization. The way it works is that your application is going to give the token to a user. Create token take the user information and sign the token. The signature is digital and when ever the user wants to access the information, the user will send their information as authorization. JWY is mostly used for authorization. The JWT has 3 parts. The header, payload and signature;

- Header - contains the algorithm and type
```{
	"alg":"HS256",
	"type":"JWT"
}```

 - Payload - contains information about the owner of the token
```{
	"sub":"julietN",
	"name":"Juliette Nkwor"
	"iat":1516239022,
	"claims":create,edit"
}```

- Signature - To create the signature - you have to take the encoded header, encoded payload and use the secret with the algorithm and then sign the token.

``256-bit-secret``

## Authentication

- Verifies who you are. Let's say you want to access an application, you need to provide your email and password. The most popular form of doing this is by using login form. 

## Authorization 

- Decides if you have permission to access a resource. 

> The simple way of explaining authentication and authorization is, a user wants to access a resource, you login in with either their username and password usually a Post request. When the application verifies that the requests are correct, the application sends a JSN Web Token to the user. This information will clients credentials and expiration date. Whenever the user wants to access any resource in the future, the user will send a GET request to the application with the JWT. The application will take the request, check the JWT and ensures that the token is valid and also check that the token has permission to access this resource that they are requesting. 

> Another aspect of this application is the concept of Refresh Token. This another token that is usually given to the user, whenever the access token is expired, they will send a refresh token and you will give them a new one. This is to improve the user experience so that you don't keep asking the user to login. So instead of asking the user to login again, the application will request for the refresh token and as long as the refresh token is valid, the application will give them a new access token. 
