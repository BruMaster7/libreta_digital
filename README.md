# 📘 Libreta Digital

Proyecto final de la carrera **Profesorado de Informática – CERP del Este (2025)**.  
Desarrollado por el equipo:  
- **Rocha Bruno**  
- **Marabotte Antonella**  
- **Larrosa Lucas**  
- **Correa Franco**  

---

## 📝 Descripción

**Libreta Digital** es una aplicación de escritorio que permite a los docentes gestionar cursos, estudiantes y calificaciones de forma centralizada y segura.  
El sistema busca **digitalizar la libreta tradicional de los centros educativos**, facilitando el acceso a la información, la organización de la asistencia y el registro de evaluaciones.

---

## 🎯 Objetivos

- Brindar una herramienta digital que facilite la **gestión académica** en el aula.  
- Permitir al docente **registrar y consultar** notas, evaluaciones y asistencia de sus estudiantes.  
- Ofrecer una interfaz intuitiva para el **acceso y administración** de la información.  
- Desarrollar una aplicación con **buenas prácticas de Ingeniería de Software**, aplicando lo aprendido en la formación docente.

---

## 🛠️ Tecnologías utilizadas

- **Lenguaje principal:** Java 21  
- **Entorno gráfico:** Swing (WindowBuilder)  
- **Gestión de base de datos:** PostgreSQL  
- **Conexión con la base de datos:** JDBC  
- **Gestión de variables de entorno:** dotenv-java  
- **IDE recomendado:** Eclipse IDE  

---

## 📂 Estructura del proyecto

El proyecto está organizado en paquetes siguiendo un diseño modular:

- `config/` → Manejo de la conexión a la base de datos.  
- `dao/` → Clases de acceso a datos (DAO).  
- `model/` → Entidades principales del dominio (Usuario, Curso, Evaluación, etc.).  
- `services/` → Lógica de negocio (ej. generación de boletines).  
- `views/` → Interfaz gráfica para Docentes y Estudiantes.  
- `.env` → Archivo de variables de entorno para credenciales de la base de datos.  

---

## ⚙️ Instalación y ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/BruMaster7/libreta_digital.git
cd libreta_digital
```
### 2. Configurar la base de datos
- Instalar PostgreSQL.
- Crear una base de datos llamada libreta_digital.
- Importar las tablas desde el script SQL disponible en /resources/sql/.
### 3. Configurar variables de entorno
Crear un archivo .env en la carpeta src/resources/ con el siguiente contenido:
```ini
DB_HOST=localhost
DB_PORT=5432
DB_NAME=libreta_digital
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```
### 4. Compilar y ejecutar
- Abrir el proyecto en Eclipse IDE.
- Ejecutar la clase App.java para iniciar la aplicación.

### Diagrama de componentes
El sistema sigue una arquitectura por capas:

- UI (Views) → Interacción con el usuario.
- Services → Lógica de negocio.
- DAO → Acceso a base de datos.
- Model → Entidades.
- Config → Conexión a PostgreSQL.

👉 El diagrama de componentes completo está disponible en la carpeta /docs/

### 🚀 Funcionalidades principales

- Autenticación de usuarios (docentes/estudiantes).
- Gestión de cursos y estudiantes.
- Registro y consulta de calificaciones.
- Generación de boletines por curso.
- (En desarrollo) Gestión de asistencia.

### 📅 Estado del proyecto

El sistema se encuentra en desarrollo activo.
Actualmente implementa las funcionalidades básicas de conexión, autenticación y visualización de datos, con vistas a expandir a la gestión completa de asistencia y notas.
### 👨‍🏫 Contexto académico

Este proyecto se enmarca como trabajo final integrador de la carrera Profesorado de Informática en el CERP del Este (Uruguay, 2025).
Integra conocimientos de:
- Programación orientada a objetos (Java).
- Diseño de interfaces gráficas.
- Bases de datos (PostgreSQL).
- Patrones de diseño (DAO).
- Arquitectura en capas
- Ingeniería de software y buenas prácticas.

### 📜 Licencia

Este proyecto es de uso académico y educativo.
Se encuentra bajo la licencia MIT, permitiendo su uso, modificación y distribución con fines de aprendizaje.
