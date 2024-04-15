## To fire the app from linux

┌──(java㉿java)-[~/Desktop/SMSv1.0/src]
└─$ java -cp StudentManager/mysql-connector-j-8.3.0.jar: StudentManager/*      
Picked up _JAVA_OPTIONS: -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
Error: Could not find or load main class StudentManager.driverLoader.class
Caused by: java.lang.ClassNotFoundException: StudentManager.driverLoader.class
                                                                                                                   
┌──(java㉿java)-[~/Desktop/SMSv1.0/src]
└─$ java -cp StudentManager/mysql-connector-j-8.3.0.jar: StudentManager/StudentManager
Picked up _JAVA_OPTIONS: -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
Welcome to Student Manager 1.0 BY OMZO

└─$ ls                                                                                
driverLoader.class  mysql-connector-j-8.3.0.jar  Student.java          StudentManager.java
driverLoader.java   Student.class                StudentManager.class
                                                                                                                   
┌──(java㉿java)-[~/Desktop/SMSv1.0/StudentManager]
└─$ java -cp mysql-connector-j-8.3.0.jar: StudentManager                
Picked up _JAVA_OPTIONS: -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
Welcome to Student Manager 1.0 BY OMZO



/SMSV1.0
    bin
    |
    lib
        +mysqlconnector.jar
    |
    StudentManager
        
        +StudentManager.java
        
        +driverLoader.java
        
        +Student.java
        
        +LoginPage
        
        +mysqlconnector.jar

/SMSV1.0        //Main directory
/SMSV1.0/bin    
/SMSV1.0/lib/mysqlconnector.jar
/SMSV1.0/StudentManager/StudentManager.java     //Main method here 
/SMSV1.0/StudentManager/driverLoader.java       //direver connector, connection 
/SMSV1.0/StudentManager/Student.java            //constructr
/SMSV1.0/StudentManager/LoginPage.java          //GUI pass/username
/SMSV1.0/StudentManager/mysqlconnector.jar      //JBDC driver for mysql



![Screenshot_2024-04-14_15-06-46](https://github.com/OmZo4/StudentManagerSystem/assets/127079220/a8ca8360-1014-4946-b0bb-1d3f1114b7c4)



![Screenshot_2024-04-14_15-08-15](https://github.com/OmZo4/StudentManagerSystem/assets/127079220/1482f7ca-b531-41d6-acfc-4e40711f856c)
