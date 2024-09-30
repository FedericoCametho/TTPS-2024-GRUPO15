# Grupo 15 - TTPS 2024

## Descripción

Este proyecto es parte del trabajo práctico para la materia TTPS 2024. Consiste en un sistema de gestión de menús y comidas para un buffet.

## Stack Tecnológico

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para la creación de aplicaciones Java.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **Jakarta Persistence (JPA)**: API para la persistencia de datos.
- **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
- **IntelliJ IDEA**: Entorno de desarrollo integrado (IDE).

## Estructura del Proyecto

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Contiene los archivos de configuración y recursos estáticos.
- `src/test/java`: Contiene las pruebas unitarias y de integración.

## Requisitos Previos

- **Java 17** o superior
- **Maven 3.6** o superior
- **IntelliJ IDEA** (recomendado)

## Configuración del Entorno

1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/FedericoCametho/TTPS2024-Buffet.git
    cd TTPS2024-Buffet
    ```

2. **Importar el proyecto en IntelliJ IDEA**:
    - Abrir IntelliJ IDEA.
    - Seleccionar "Open" y elegir la carpeta del proyecto clonado.
    - IntelliJ IDEA detectará automáticamente el proyecto Maven y descargará las dependencias necesarias.

3. **Configurar la base de datos H2**:
    - La configuración de la base de datos H2 se encuentra en `src/main/resources/application.properties`.

## Levantar la Aplicación

1. **Compilar el proyecto**:
    ```bash
    mvn clean install
    ```

2. **Ejecutar la aplicación**:
    - Desde IntelliJ IDEA:
        - Navegar a `src/main/java/com/TTPS2024/buffet/BuffetApplication.java`.
        - Hacer clic derecho y seleccionar "Run 'BuffetApplication'".
    - Desde la línea de comandos:
        ```bash
        mvn spring-boot:run
        ```

3. **Acceder a la aplicación**:
    - La aplicación estará disponible en `http://localhost:8080`.





