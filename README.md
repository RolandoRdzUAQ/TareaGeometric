# 🔷 Proyecto: Objetos Geométricos & Renderizado 3D en Consola

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Terminal](https://img.shields.io/badge/Terminal-4D4D4D?style=for-the-badge&logo=gnu-bash&logoColor=white)
![POO](https://img.shields.io/badge/POO-Conceptos-blue?style=for-the-badge)

¡Bienvenido al repositorio del proyecto **Geometric Object**! 

Este proyecto nació como una práctica académica de Programación Orientada a Objetos (POO), pero fue expandido para incluir un desafío de ingeniería de bajo nivel: un motor gráfico 3D ejecutado enteramente desde la terminal.

## 🚀 Visión General

El repositorio está dividido en dos grandes hitos técnicos:
1. **Arquitectura POO:** La construcción de una jerarquía de clases robusta (`GeometryObject`, `Class_Circle`, `Class_Rectangle`) aplicando herencia, polimorfismo y pruebas unitarias con aserciones nativas (`assert`).
2. **Motor 3D Interactivo:** Un programa paralelo (`Motor3D.java`) que aplica álgebra lineal pura (matrices de rotación) para renderizar figuras tridimensionales mutantes en formato *wireframe* directo en la consola.

---

## 📚 Estructura de la Documentación

Para entender a fondo la ingeniería detrás de este código, he preparado dos documentos detallados. Te invito a leerlos:

### 📄 1. [Documentación General (POO y Lógica Base)](./Documentacion_General.md)
En este archivo encontrarás la explicación de la arquitectura estándar del proyecto.
* **¿Qué incluye?** * Explicación de la superclase y las clases derivadas.
  * Fórmulas matemáticas aplicadas (Área, Perímetro).
  * Detalles sobre la batería de pruebas unitarias (`PruebaGeometrica`) y cómo el polimorfismo interactúa en el programa principal.

### 🎮 2. [Reporte Técnico: Motor 3D en Consola](./Motor3D_Explicacion.md)
En este documento detallo el proceso matemático y lógico para lograr gráficos en una interfaz de texto.
* **¿Qué incluye?**
  * Aplicación de **matrices de rotación** sobre los ejes X, Y y Z.
  * Implementación nativa del **Algoritmo de Bresenham** para el trazado de líneas (rasterización).
  * Explicación de la arquitectura de **Hilos (Concurrency)** usada para permitir interactividad en tiempo real sin congelar la animación.

---

## ⚙️ Cómo Ejecutar el Proyecto

> **⚠️ Nota Importante:** Para la correcta visualización del Motor 3D, es obligatorio usar un emulador de terminal real (como Git Bash, Zsh, bash de Linux o la terminal de macOS). La consola integrada de IDEs como Eclipse o NetBeans no soporta los comandos ANSI necesarios.

**Compilar todo el proyecto:**
bash
``javac src/Principal/*.java``

Opción A: Ejecutar el programa principal (POO estándar)

Bash
`java -cp src Principal.Clase_principal`

Opción B: Correr la batería de pruebas (Testing)

Bash
`java -ea -cp src Principal.PruebaGeometrica`

Opción C: Iniciar el Motor 3D Interactivo

Bash
`java -cp src Principal.Motor3D`
