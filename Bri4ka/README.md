bri4ka.com

Usage
This is a web-based Java application that offers entertainment to users through the following features:

**Registration:** Users can register an account by sending a `PUT` request to `http://localhost:8888/users` with the required information:
POST localhost:8888/users
Content-Type: application/json
{
    "first_name" : "John",
    "last_name" : "Doe",
    "username" : "John.Doe",
    "email" : "john.doe@gmail.com",
    "password" : "John_Doe911",
    "confirm_password" : "Ivan4o_Ivanov",
    "age" : 66,
    "address" : "Nowhere"
}

**Profile Browsing:** Users can browse profiles of other registered users by sending a `GET` request to `http://localhost:8888/users/20`.

**Login:** Users can log in to their account by sending a `POST` request to `http://localhost:8888/login` with their username and password:
POST http://localhost:8888/login
Content-Type: application/json
{
"username" : "john.doe",
"password" : "John_Doe911"
}

**Car Browsing:** Users can browse cars by sending a `GET` request to `http://localhost:8888/cars/6`.

**Adding Photos:** Users can add photos by sending a `PUT` request to `http://localhost:8888/cars/8/images` with the required information.

**Download Pictures:** Users can download pictures by sending a `GET` request to `http://localhost:8888/images/9` with appropriate Postman settings to mimic a browser.

**Buying a Car and Adding a Photo:** In the application, you have the option to buy a car and upload a photo of your new acquisition.

**Liking a Car:** If you find a car that you like, you can express your interest by liking it.

To interact with the application, you can use Postman to send HTTP requests and receive responses. Make sure to set the appropriate request method (`GET`, `POST`, etc.) and provide the necessary request parameters and headers.

Feel free to explore the different endpoints and functionalities of the application using Postman.
