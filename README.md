# Las Típicas - App de Empanadas

## Descripción
Este proyecto consiste en el desarrollo de una aplicación móvil creada en Android Studio utilizando el lenguaje **Kotlin** y **Jetpack Compose**, bajo la arquitectura **MVVM**.
El propósito es implementar un sistema digital para la venta de empanadas y bebidas. El sistema permite a los usuarios registrarse (incluyendo foto de perfil con la cámara), navegar por un catálogo de productos, realizar pedidos y visualizar su historial de compras.
La aplicación se conecta a un Backend propio desarrollado en **Node.js** y utiliza **MongoDB Atlas** para la persistencia de datos en la nube.

## Estudiantes
* Alex Ruiz
* Jesus Muñoz

## Funcionalidades Implementadas

* **Gestión de Usuarios y Seguridad**
    Permite el registro de nuevos usuarios capturando datos personales y una fotografía de perfil utilizando la **cámara nativa** del dispositivo. Incluye un sistema de Login seguro con validación de credenciales y manejo de sesiones mediante **DataStore** y Tokens.

* **Catálogo y Carrito de Compras**
    Muestra un listado de productos (Empanadas y Bebidas) con sus precios e imágenes. Los usuarios pueden agregar productos a un carrito de compras, donde se calcula automáticamente el total a pagar antes de confirmar el pedido.

* **Historial de Pedidos en la Nube**
    Integra una sección de historial donde cada usuario puede ver exclusivamente sus pedidos anteriores. Estos datos son persistentes y se obtienen en tiempo real desde una base de datos **MongoDB Atlas** a través de una API REST.

## Pasos para Ejecutar el Proyecto

Sigue estas instrucciones para clonar y ejecutar la aplicación en tu entorno local:

### Requisito Previo: Backend
Asegúrate de que el servidor **Node.js** esté corriendo localmente o en la nube para que la App pueda iniciar sesión y cargar pedidos.

### Ejecución de la App Móvil
1.  Abrir la aplicación **Android Studio**.
2.  Presionar en la sección superior izquierda el nombre de la app (o ir al menú de inicio) y seleccionar la opción **"Clone repository..."**.
3.  En la sección donde está escrito "URL", colocar el enlace del repositorio:
    ```
    https://github.com/Jesus-Munoz-C/LasTipicas-Grupo6.git
    ```
4.  Presionar **"Clone"** para que el archivo sea clonado correctamente.
5.  Sincronizar las dependencias de la app (Gradle) para descargar librerías como Retrofit, Coil y CameraX.
6.  Ir al apartado de **"Device manager"** y seleccionar un emulador (Recomendado API 34 o superior).
7.  **Importante:** Asegúrate de que la IP en `RetrofitClient` corresponda a la de tu servidor (o `10.0.2.2` si es local).
8.  Por último, ejecutar la app en `MainActivity` presionando el logo de "Play".

## Tecnologías Utilizadas
* **Android Studio** (Kotlin + Jetpack Compose)
* **Node.js & Express** (API REST Backend)
* **MongoDB Atlas** (Base de Datos Nube)
* **Retrofit** (Conexión HTTP)
* **DataStore** (Persistencia Local)
* **CameraX & Coil** (Manejo de Imágenes)
