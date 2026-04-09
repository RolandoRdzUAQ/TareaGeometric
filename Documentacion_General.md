# Documentación del Proyecto: Herencia y Objetos Geométricos (Geometric Object)

## 1. Introducción
Este proyecto implementa un sistema de clases basado en los principios de la Programación Orientada a Objetos (POO), específicamente **Herencia, Encapsulamiento y Polimorfismo**. El objetivo principal es modelar figuras geométricas a partir de una superclase genérica, derivando clases concretas que calculan sus propiedades matemáticas.

## 2. Estructura de Clases

### 2.1. Superclase: `GeometryObject`
Es la clase base de la jerarquía. Define los atributos fundamentales que cualquier figura geométrica debe tener en este contexto.
* **Atributos (Encapsulados):** `color` (String), `filled` (boolean), y `dateCreated` (Date).
* **Constructores:** Cuenta con un constructor por defecto que inicializa la fecha de creación, y un constructor sobrecargado para definir el color y el estado de relleno desde la instanciación.
* **Métodos:** Métodos *getters* y *setters* estándar para acceder a la información, además de un método sobrescrito `toString()` para reportar el estado del objeto.

### 2.2. Clases Derivadas
Ambas clases utilizan la palabra reservada `extends` para heredar de `GeometryObject` y hacen uso de `super()` en sus constructores para inicializar los atributos de la clase padre.

* **`Class_Circle` (Círculo):**
  * Introduce el atributo específico `radius`.
  * Implementa fórmulas geométricas utilizando la constante `Math.PI` para calcular el área (`Math.PI * radius * radius`) y el perímetro/circunferencia (`2 * Math.PI * radius`).
* **`Class_Rectangle` (Rectángulo):**
  * Introduce los atributos `width` y `height`.
  * Calcula el área (`width * height`) y el perímetro (`2 * (width + height)`).

## 3. Pruebas y Ejecución

* **`Clase_principal`:** Es el punto de entrada principal donde se instancian los objetos, se les asignan valores específicos y se invoca la impresión de sus datos (demostrando polimorfismo).
* **`PruebaGeometrica`:** Se desarrolló una rama independiente para el testing. Este módulo utiliza aserciones (`assert`) nativas de Java para comprobar lógicamente que las fórmulas matemáticas sean exactas y que las clases hijas sean correctamente reconocidas como instancias de `GeometryObject`.
