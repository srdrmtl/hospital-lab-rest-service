# Namık Kemal University Hospital Lab Document Service

[![N|Spring Boot](http://www.javaoptimum.com/wp-content/uploads/2017/04/springboot.png)](https://spring.io/)

### Required

  - Java 1.8
  - Maven 3.2 or higher version
  - Mysql Server

### Installation

```sh
$ git clone https://github.com/srdrmtl/hospital-lab-rest-service.git
```
You should create a database and import `Dump20181230.sql`.(If you dont understand that , you should learn about mysql, mysql-server, mysql workbench etc.)

Open project from your best ide (We used Netbeans) and find `application.properties` file
If you're open project from Netbeans just like there ;

[![N|Spring Boot](http://resimag.com/p1/a494365574e.png)](https://google.com/)

Then, you should change the these database properties
```sh
$ spring.datasource.url=###jdbc:mysql://localhost:3306/YourDatabaseName###
$ spring.datasource.username=###root###
$ spring.datasource.password=###MysqlPassword###
```
And you should `mvn spring-boot:run` main folder.

After this, if everything is okey you can access to link and see some json data `localhost:8080/user/all`  correctly.

# Api Informations
## User Api Information
### User Model
```sh
 {
        "id": Long,
        "fileId": "String",
        "tcId": "String",
        "name": "String",
        "blood": "String(Max 4 Character)",
        "address": "String",
        "date":Date Example("11/01/2019")
    }
```
### User Api Table
| Api Link | Information | Variable Type | Mapping
| ------ | ------ | ------ | ------ |
| /user/{id} | Get User Information with Id Parameter from database  | [PathVariable] Long id | GET |
| /user/all | Get All Users from database | No Parameter | GET |
| /user/homeresults | Get passed user list (2 Weeks) | No Parameter | GET |
| /user/find/{searchText} | Search in all users with search key | [PathVariable] String searchText | GET |
| /user/fileid/{fileid} | Get User with fileid parameters from database | [PathVariable] String fileid | GET |
| /user/add | Save User | User | POST |
| /user/update/{id} | Update User with id parameter | User , [PathVariable] Long id | PUT |


## Report Api Information

### Report Api Model
Note: Im sorry for this model used to turkish variable names.
```sh
    {
        "raporId": Long,
        "dosyaNo": "String",
        "myloblast": Integer,
        "promyelosit": Integer,
        "myelosit": Integer,
        "metamyelosit": Integer,
        "comak": Integer,
        "parcali": Integer,
        "bazofilikSeri": Integer,
        "eozinofilikSeri": Integer,
        "lenfosit": Integer,
        "promonosit": Integer,
        "monosit": Integer,
        "plazmaHucresi": Integer,
        "proeritroblast": Integer,
        "bazofilikErit": Integer,
        "polikromalofilikErit": Integer,
        "ortokromantofilikErit": Integer,
        "megakaryositler": "String",
        "sellulerite": "String",
        "tani": "String",
        "raporEden": "String",
        "rapor": "String",
        "tarih": Date Example("11/01/2019")
        "resim1": "String(Download Link)",
        "resim2": "String(Download Link)",
        "resim3": "String(Downdload Link)"
    }
```

### Report Api Table

| Api Link | Information | Variable Type | Mapping
| ------ | ------ | ------ | ------ |
| /report/{id} | Get Report Information with Id Parameter from database  | [PathVariable] Long id | GET |
| /report/all | Get All Reports from database | No Parameter | GET |
| /report/find/{searchText} | Search in all Reports with search key | [PathVariable] String searchText | GET |
| /report/fileid/{fileid} | Get Report with fileid parameters from database | [PathVariable] String fileid | GET |
| /report/add | Save Report | Report | POST |
| /report/update/{id} | Update Report with id parameter | Report , [PathVariable] Long id | PUT |

## About File Upload Service

You can use FileUploadService for upload images , you can also upload multiple images :)

| Api Link | Information | Variable Type | Mapping
| ------ | ------ | ------ | ------ |
| /upload/{fileId} | Upload to single image(png,jpeg,jpg,gif) | [RequestParam] String file {form-data},String fileId| POST |
| /uploadMultiple/{fileId} | Upload to multiple image(png,jpeg,jpg,gif) | [RequestParam] String files {form-data}, String fileId| GET |
| /downloadFile/{fileId}/{fileName} | Download Image from Application Server | String fileName, String fileId | GET |

## Web Security and Other Informations
We used to jwt(java web token) for security. Need to be new role for security and we created Laborant(lab worker) model;

### Laborant Model
```sh
    {
        "username": "String",
        "password": "String" //This column hashed sha256.

    }
```

### Security Services

| Api Link | Information | Variable Type | Mapping
| ------ | ------ | ------ | ------ |
| /login | login with username and password | Laborant | POST |
| /laborant/register | Register | Laborant | POST |

!Update : Changed the image upload and download service, you can download images to real names.

