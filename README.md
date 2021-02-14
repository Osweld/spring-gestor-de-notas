# Proyecto para gestionar notas
Se creara un proyecto hecho con Spring y Angular para que los alumnos puedan gestionar sus notas


## EndPoints

#### utils
- GET http://localhost:8080/api/utils/career []
- GET http://localhost:8080/api/utils/cycle []
- GET http://localhost:8080/api/utils/year []
- GET http://localhost:8080/api/utils/subjectspercareer/{idCareer}/{idCycle} []
- GET http://localhost:8080/api/utils/activity []
- GET http://localhost:8080/api/utils/activitynumber []

#### Login and Registration
- POST http://localhost:8080/api/registration
- POST http://localhost:8080/auth/login

#### User
- GET http://localhost:8080/api/user

#### Semester
- POST http://localhost:8080/api/semester/{cycleId}/{yearId}
- GET http://localhost:8080/api/semester/{semesterId}
- GET http://localhost:8080/api/semesters []
- DELETE http://localhost:8080/api/semester/{semesterId}

#### Subject per semester
- POST http://localhost:8080/api/subjectspersemester/{subjectsPerCareerId}/{semesterId}
- GET http://localhost:8080/api/subjectpersemester/{subjectsPerSemesterId}
- GET http://localhost:8080/api/subjectspersemester/{semesterId} []
- DELETE http://localhost:8080/api/subjectpersemester/{subjectsPerSemesterId}

#### Assignment
- POST http://localhost:8080/api/assignment/{subjectsPerSemesterId}/{activityId}/{activityNumberId}
- GET http://localhost:8080/api/assignment/{assignmentId}
- GET http://localhost:8080/api/assignments/{subjectsPerSemesterId} []
- DELETE http://localhost:8080/api/assignment/{assignmentId}
- PUT http://localhost:8080/api/assignment

#### Token
- GET http://localhost:8080/api/token/resetpassword/{email}
- GET http://localhost:8080/api/token/sendactivemail/{email}
- PUT http://localhost:8080/api/token/activeaccount/{token}
- PUT http://localhost:8080/api/token/resetpassword/{token}
