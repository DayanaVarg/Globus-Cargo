# ApiRest de Spring Boot consumible para la gestión de usuarios
Este proyecto es un ejemplo de API REST en **Spring Boot** que permite registrar usuarios (ID, nombre y correo electrónico) y almacenarlos temporalmente. Además, incluye la integración con **Swagger** para documentar y probar los endpoints fácilmente.

---

## 🚀 Requisitos previos
- [Java 17+](https://adoptium.net/)  
- [Maven 3.9+](https://maven.apache.org/)   
- IDE recomendado: **IntelliJ IDEA** o **VS Code**

---

## ▶️ Ejecución del proyecto
1. Clona el repositorio:
   ```bash
   git clone https://github.com/DayanaVarg/Globus-Cargo.git

2. Compila y ejecuta
3. Una vez compilado el proyecto acceder a la documentación de Swagger en:
  - http://localhost:8080/swagger-ui.html

## EndPoints
### GET ("/api/users"):
Enlista todas los usuarios registrados en la ejecución.

### POST ("/api/users"):
Registra a un nuevo usuario. Requiere a través del body la siguiente información (El id es generado automáticamente).
```
{
  "name":"Dayana",
  "email" : "daya@gmail.com"
}
```
### PUT ("/api/users/{id}"):
Permite actualizar la información de un usuario en específico. Requiere en el body lo siguiente (La información que se desea cambiar):
```
{
  "name":"Dayana"
  "email" : "daya@gmail.com"
}
```

### DELETE ("/api/users/{id}"):
Elimina toda la información del usuario, del cuál se pase el id por la ruta.
