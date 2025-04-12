# 🚌 CIVA Buses - Backend

Resolución de reto técnico de la empresa **CIVA**, desarrollado con **Spring Boot** y base de datos **MySQL**. Este proyecto proporciona una API segura basada en JWT (JSON Web Tokens) con roles específicos para controlar el acceso a las funcionalidades.

## ⚙️ Configuración del Proyecto

### 🔧 application.properties

```properties
spring.application.name=civa-back
server.port=8085

# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/db_bus_civa?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=root
spring.datasource.password=pass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT
security.jwt.token-expiration-in-minutes=3
security.jwt.refresh-token-expiration-in-minutes=5
security.jwt.secret-key=ZXN0YSBlcyBtaSBjb250cmFzZW5hIHNlZ3VyYSAxMjM0NTY3ODkgQUJDYWJj

# CORS
spring.web.cors.allowed-origins=http://localhost:5173
```


## 🔐 Seguridad y Autenticación

La autenticación se implementa usando **JWT (JSON Web Tokens)**.  
Los tokens se emiten al iniciar sesión y se validan en cada solicitud protegida.  
Se maneja también un **refresh token** que permite obtener un nuevo token de acceso sin volver a iniciar sesión.

---

### ⏱ Expiración de Tokens

- **Token de acceso**: 3 minutos.
- **Refresh token**: 5 minutos.  
  Si este token expira, se cierra la sesión automáticamente.

---

### 👤 Roles

Se han definido los siguientes **roles de acceso**:

- `buses_view_all`: permite listar los buses.
- `bus_view_detail`: permite ver el detalle de un bus específico.

Los endpoints están protegidos según el rol asignado al usuario.

---

## 📑 Paginación

El listado de buses se implementa con **paginación** utilizando parámetros de página y tamaño (`page`, `size`)  
para optimizar el rendimiento y facilitar la navegación desde el frontend.

---

## 🚀 Tecnologías Usadas

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- MySQL  
- JWT  
- Maven
