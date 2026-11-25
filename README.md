# Report App

Aplicación Android (Kotlin + Jetpack Compose) para que los ciudadanos reporten problemas de
infraestructura urbana (huecos, basura, alumbrado dañado) con foto y ubicación GPS, y sigan el
estado de sus reportes.

**Equipo – Important People .\_.**
- Luna Natalia Ruiz Gonzalez
- Logan Alejandro Diaz Casas
- Nicolas Moreno Fonseca

## Estado: Avance (segunda entrega)
Están todas las pantallas de la aplicación, hechas con **Jetpack Compose**, y la navegación entre
ellas con **Navigation Compose**. Todavía no hay consumo de servicios (backend): los datos que se ven
son de ejemplo.

| Pantalla | Qué hace en el avance |
|---|---|
| Login | Formulario; el botón lleva al mapa |
| Registro | Formulario; el botón lleva al mapa |
| Mapa | Mapa de OpenStreetMap con el pin "Reportar aquí"; "Nuevo reporte" abre las opciones |
| Opciones para agregar | Tipos de problema; al escoger uno se abre el formulario |
| Crear reporte | Formulario con foto, tipo, comentario y ubicación; "Enviar" vuelve al mapa |
| Mis reportes | Reportes de ejemplo; al tocar uno se abre el detalle |
| Detalle del reporte | Reporte de ejemplo con el seguimiento de estado |
| Perfil y recompensas | Puntos, nivel, insignias y ranking de ejemplo |

Menú inferior: Mapa, Mis reportes, Perfil. Menú de opciones: Acerca de, Cerrar sesión.

## Idiomas
Inglés (`values/`) y español (`values-es/`); se escoge según el idioma del celular.

## Siguientes pasos
Arquitectura MVVM completa, Firebase (login y reportes), servicio REST, GPS real y notificaciones.

## Cómo abrirlo
1. Abrir la carpeta en Android Studio (Koala o superior).
2. Esperar la sincronización de Gradle.
3. Ejecutar en un emulador o celular con Android 8.0 (API 26) o superior.
