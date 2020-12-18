# Hungry Hippo Food Delivery Application

## Project Team: Po-Hsuan Chiang, Shaun Ho, Nachiket Dani
## TA: Xu Zhou (Jerry)
<br/>

- The application is deployed at: https://hungry-hippos.glitch.me 
- The API is deployed at: http://cs5500-delivery-app.herokuapp.com/
- The database is hosted on the cloud with [MongoDB Atlas]: https://cloud.mongodb.com/v2/5fb00d066dbf6b0a4a01d97b#clusters/detail/DeliveryAppCluster

## Team Documents

- [Design document](https://drive.google.com/file/d/14PeR987p7YaxfjSTStXPXW3JKiiwMDqA/view?usp=sharing)
- [UML](https://lucid.app/lucidchart/invitations/accept/5714de52-557b-41ce-b0ba-b2710ba443b6)
- [Process Diagram (see third page)](https://lucid.app/lucidchart/invitations/accept/5714de52-557b-41ce-b0ba-b2710ba443b6)
- [Wireframe (see second page)](https://lucid.app/lucidchart/invitations/accept/5714de52-557b-41ce-b0ba-b2710ba443b6)


## Deployment Instructions

### Frontend
The front end application was completely developed and deployed using Glitch.
* Visit [Glitch.com](https://glitch.com/)
* Visit [Live Application](https://hungry-hippos.glitch.me) to access the deployed application UI.

### Backend
1. Clone the [backend repository](git@github.com:Food-Delivery-App-CS5500/DeliveryApp.git)
2. Create a new Heroku application
3. Add config variable:
    MONGODB_URI: mongodb+srv://Emily:20201218@deliveryappcluster.zk89o.mongodb.net/HungryHippo?retryWrites=true&w=majority
4. Test the API by going to cs5500-delivery-app.herokuapp.com/ or any of the other endpoints defined in the View package
5. Requirements
   A recent Gradle (>= 6.1.1 but < 7.0.0) and JDK 8.
6. Instructions:
     Building
     `./gradlew build`
     Testing
     `./gradlew test`
     Running
     `./gradlew run`
     The server will start on port 5000 by default.
