# Gestión de tareas

Esta aplicación es un backend de tareas, donde se puede crear, eliminar y listar tareas.

La idea de este repositorio es reflejar conceptos de arquitectura limpia y utilizar varias características de Spring para agilizar el desarrollo.

## Estructura de directoios
```
.
├── .github/workflows -> Acciones de github (Sonar cloud)
├── docker-compose.yml
├── Dockerfile
└─── src
     ├── tasks
     │    ├── domain
     │    │    ├── execeptions  -> Excepciones de dominio
     │    │    ├── models       -> Modelos de nuestra aplicación que no dependen de infraestructura.
     │    │    ├── repositories -> Interfaces de repositorios que realizan la inersión de dependencia.
     │    │    └── services     -> Casos de uso de nuestr aplicación.
     │    ├── infrastructure
     │    │    ├── controllers  -> Controladores de nuestra aplicación.
     │    │    ├── entities     -> Entidades de JPA con metodos de mapeo a modelos de dominio.
     │    │    └── repositories -> Implementaciones de repositorios y adaptadores que usarán la interfaz de dominio.
     └── ChecklistApplication.java
```
## Testing
Los tests de esta aplicación se hicieron sin Spring Test para evitar los tiempos de espera en los que se levanta todo el contexto de Spring, como la lógica de dominio de la aplicación esta desacoplada de la infraestructura (Los frameworks externos) podemos mockear componentes mediante la inyección de dependencias y testear nuestro casos de uso sin necesidad de Spring Test.

## Sonar Cloud
Esta heramienta nos permite tener analizar nuestro proyecto para ver si cumple con ciertas métricas de calidad.

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=nicovegasr_checklist-spring-jpa)](https://sonarcloud.io/summary/new_code?id=nicovegasr_checklist-spring-jpa)

## Despliegue
Debido a el uso de Docker y Docker-Compose, el despliegue de la aplicación es muy sencillo, solo es necesario tener instalado Docker y Docker-Compose en el equipo y ejecutar el siguiente comando en la raíz del proyecto:
* docker-compose up -d

En caso de querer parar la aplicación, ejecutar el siguiente comando:
* docker-compose down

Y si se quiere eliminar las imagenes y volúmenes asociadas a el proyecto:
* docker-compose down --rmi all

En la configuración de docker-compose se estableció un healthcheck para la base de datos, sin embargo, en caso de que no funcionase por alguna razón y el backend dé un `Communication failed` al intentar conectarse, con volver a ejecuta `docker-compose up -d` pasados unos segundos debería iniciarse sin poblemas (o levantar manualmente el contenedor  del backend.)


## Tecnologías/Estrategias utilizadas
* Spring Boot
* Spring Data
* Clean Architecture
* Adaptadores de persistencia
* Docker
* Dockcer-Compose
* Testing
* Sonar Cloud