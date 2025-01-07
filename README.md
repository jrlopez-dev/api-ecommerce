Ecommerce API - Documentación

Descripción:

Este proyecto es una API RESTful para un sistema de Ecommerce. Permite realizar operaciones CRUD sobre los productos de la tienda utilizando Spring Boot y MongoDB como base de datos NoSQL.
tambien permite poder consultar la api de Twitter para poder filtrar por una palabra en espesifica.

Tecnologías Utilizadas:

1. Spring Boot: Framework para crear aplicaciones Java.
2. MongoDB: Base de datos NoSQL.
3. Docker: Herramienta para contenerizar la aplicación.
4. Docker Compose: Herramienta para orquestar los contenedores de Docker.
5. Java 17: Lenguaje de programación utilizado.

Pasos para compilar el proyecto: 

1. Descargar del repositorio y ejecutar los siguientes comandos dentro del prouecto.
2. Dentro del proyecto ejecutar el siquiente comando: mvn clean install
3. Siempre dentro del proyecto ejecutar: docker-compose build
4. Luego para levantar los contenedores de docker compose ejecutar: docker-compose up -d

La api estara expuesta en la siguiente url: http://localhost:8080 y MongoDB estara en: localhost:27017,
MongoDB: Usa una herramienta como MongoDB Compass o DBeaver para conectarte a localhost:27017.

Las url de los endponit son los siguientes:

buscar productos:
1. http://localhost:8080/api/buscar?id=1 para buscar por id
2. http://localhost:8080/api/buscar para traer la lista total de productos

Crear producto:
1. http://localhost:8080/api/crear crea un nuevo producto, recibe una DTO

JSON que se debe enviar:
{
"productId": "ASW",
"name": "TEST-1",
"description": "PRODUCTO DE PRUEBA 1",
"price": 25.50,
"stock": 15
}

Actualizar producto:
1. http://localhost:8080/api/actualizar

JSON que se debe enviar (Data de ejemplo): {
"id": 4,
"productId": "ASW",
"name": "TEST-4",
"description": "PRODUCTO DE PRUEBA 4 acualizado",
"price": 25.50,
"stock": 15
}

Eliminar producto:
1. http://localhost:8080/api/eliminar?id=1 se envia el ID del producto que se desea eliminar

Para el tema de la integracion de la api de twitter es necesario reemplazar en el archivo properties el tocken de acceso. La url ya va
configurada actualmente en el properties.
