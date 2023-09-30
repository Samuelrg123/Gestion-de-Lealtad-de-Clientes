# Gestion-de-Lealtad-de-Clientes

A acontinuacion podran encontar los diagramas correspondientes del repositorio.

1). Entidad relacion de la estructura de datos.

2). Diagrama de paquetes. (La información completa dl diagrama  no se puede visalizar en el pdf, se sugiere entrar al siguiente link: https://drive.google.com/file/d/1Je7bz-F5QP5ETHgfH3am4btjCUTVCR8V/view?usp=sharing

Y en: Editar/editar datos para poder visualzar las propiedades de cada paquete.

3). Diagrama de componentes.


# Permite a las empresas comerciales gestionar programas de lealtad para sus clientes. Los usuarios podrían utilizar la API para realizar lo siguiente:

1. Registro de Clientes: Agregar nuevos clientes a la base de datos del programa de lealtad.

2. Acumulación de Puntos: Registrar y actualizar los puntos acumulados por los clientes por cada compra realizada.

3. Recompensas y Beneficios: Consultar y redimir recompensas y beneficios disponibles en función de los puntos acumulados.

4. Historial de Transacciones: Consultar un historial detallado de las transacciones realizadas por cada cliente en el programa de lealtad."

VueJS

Para ejecutar el proyecto en VueJS, se requieren los siguientes pasos:

1.	Clonar el repositorio de GitHub.
2.	Instalar las dependencias del proyecto utilizando el comando npm install.
3.	Crear una base de datos MySQL y configurar la conexión en el archivo .env.
4.	Ejecutar el servidor web utilizando el comando npm run serve.

Una vez que el servidor web esté en ejecución, se puede acceder a la API utilizando la siguiente URL:

http://localhost:8080/api/

La documentación de la API se encuentra disponible en el archivo docs/api.md.
Angular
Para ejecutar el proyecto en Angular, se requieren los siguientes pasos:

1.	Clonar el repositorio de GitHub.
2.	Instalar las dependencias del proyecto utilizando el comando npm install.
3.	Crear una base de datos MySQL y configurar la conexión en el archivo .env.
4.	Ejecutar el servidor web utilizando el comando ng serve.

Una vez que el servidor web esté en ejecución, se puede acceder a la API utilizando la siguiente URL:

http://localhost:4200/api/

La documentación de la API se encuentra disponible en el archivo docs/api.md.

Ejemplos de uso
A continuación, se presentan algunos ejemplos de cómo utilizar la API:

•	Para registrar un nuevo cliente, se puede utilizar la siguiente solicitud HTTP:

POST /api/clients/
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "phone_number": "123-456-7890",
  "birthdate": "1980-01-01",
  "gender": "male",
  "address": "123 Main Street",
  "zip_code": "12345"
}

•	Para acumular puntos por una compra, se puede utilizar la siguiente solicitud HTTP:

POST /api/transactions/
{
  "customer_id": 1,
  "transaction_id": 2,
  "value": 100,
  "points_accumulated": 10
}


•	Para consultar las recompensas y beneficios disponibles, se puede utilizar la siguiente solicitud HTTP:

GET /api/rewards/

•	Para consultar el historial de transacciones de un cliente, se puede utilizar la siguiente solicitud HTTP:

GET /api/transactions/?customer_id=1

Este proyecto es un punto de partida para crear un programa de lealtad para empresas comerciales. Los usuarios pueden personalizar el proyecto para satisfacer sus necesidades específicas.

Personalización

El proyecto se puede personalizar de la siguiente manera:

•	Agregar nuevas recompensas y beneficios: El proyecto incluye una lista de recompensas y beneficios predefinidos. Los usuarios pueden agregar nuevas recompensas y beneficios según sus necesidades.
•	Personalizar el diseño de la interfaz de usuario: El proyecto utiliza un diseño de interfaz de usuario básico. Los usuarios pueden personalizar el diseño de la interfaz de usuario según sus preferencias.
•	Agregar nuevas funciones: El proyecto incluye las funciones básicas necesarias para un programa de lealtad. Los usuarios pueden agregar nuevas funciones según sus necesidades.

Para obtener más información sobre cómo personalizar el proyecto, consulte la documentación del proyecto.







