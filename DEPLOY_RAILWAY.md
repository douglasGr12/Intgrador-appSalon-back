Guía rápida para desplegar en Railway

1) Preparar el proyecto
- El `Dockerfile` ya empaqueta la aplicación con `./mvnw package` y crea `app.jar`.
- `application.properties` fue actualizado para leer `PORT` y soportar variables `SPRING_DATASOURCE_*`.
- Se añadió `DataSourceConfig` para convertir `DATABASE_URL` (formato Railway/Heroku)
  a una URL JDBC automática.

2) Crear un servicio PostgreSQL en Railway
- En Railway crea un nuevo plugin/addon de PostgreSQL. Railway te dará una variable
  llamada `DATABASE_URL` (ej. `postgres://user:pass@host:port/dbname`).

3) Variables de entorno en Railway
- Asegúrate de que Railway exporte `DATABASE_URL`. No es necesario establecer
  `SPRING_DATASOURCE_URL` si `DATABASE_URL` existe.
- Railway también proveerá `PORT`; la aplicación usa `server.port=${PORT:8080}`.

4) Despliegue mediante Docker (opcional)
- Railway puede desplegar desde un Dockerfile. El Dockerfile incluido:
  - Compila con Maven
  - Copia `app.jar` y arranca con `java -jar app.jar`

5) Probar localmente
- Compilar con Maven (sin Docker):
  ```bash
  ./mvnw package -DskipTests
  java -jar target/*.jar
  ```
- Probar con Docker:
  ```bash
  docker build -t appsalon-backend .
  docker run -e PORT=8080 -p 8080:8080 -e DATABASE_URL="postgres://user:pass@host:5432/apidb" appsalon-backend
  ```

6) Notas y recomendaciones
- Si Railway habilita TLS/SSL para la base de datos, `DataSourceConfig` añade `sslmode=require`.
- Verifica que las migraciones/DDL (si las tienes) sean seguras en producción; `spring.jpa.hibernate.ddl-auto=update`
  puede no ser apropiado en producción.
