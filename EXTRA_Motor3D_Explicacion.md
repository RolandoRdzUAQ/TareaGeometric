# Reporte Técnico: Desarrollo de Motor de Renderizado 3D en Consola

## 1. Motivación
Para este proyecto quise ir más allá de la simple impresión de texto en la terminal. Sabiendo que los IDEs y consolas manejan interfaces bidimensionales de caracteres, me propuse el reto de construir un **Motor de Renderizado 3D** completamente desde cero utilizando únicamente Java puro y matemáticas, sin depender de librerías gráficas como OpenGL o JavaFX. 

El resultado es `Motor3D.java`, un programa capaz de proyectar mallas vectoriales (*wireframes*) rotando en tiempo real directamente en la terminal, con capacidad de modificar sus dimensiones en vivo.

## 2. Fundamentos Matemáticos (Álgebra Lineal)

Para lograr representar un objeto 3D, tuve que definir una estructura de vértices en el espacio utilizando una clase interna `Vec3` (coordenadas $x, y, z$) y conectarlos mediante "aristas" (clase `Edge`).

El núcleo del motor es la **Matriz de Rotación**. Para lograr el efecto 3D, apliqué transformaciones trigonométricas a cada vértice en cada fotograma. Las fórmulas matemáticas que implementé en el método `rotar()` son el equivalente algorítmico de multiplicar el vector por las matrices de rotación en los tres ejes:

**1. Rotación en X:**
$$y' = y \cdot \cos(\alpha_x) - z \cdot \sin(\alpha_x)$$
$$z' = y \cdot \sin(\alpha_x) + z \cdot \cos(\alpha_x)$$

**2. Rotación en Y:**
$$x' = x \cdot \cos(\alpha_y) + z' \cdot \sin(\alpha_y)$$
$$z'' = -x \cdot \sin(\alpha_y) + z' \cdot \cos(\alpha_y)$$

**3. Rotación en Z:**
$$x'' = x' \cdot \cos(\alpha_z) - y' \cdot \sin(\alpha_z)$$
$$y'' = x' \cdot \sin(\alpha_z) + y' \cdot \cos(\alpha_z)$$

La variable $\alpha_z$ (que en mi código llamé `angleZ`) se incrementa continuamente, sirviendo como el "motor" del tiempo en mi animación.

## 3. Proyección Ortográfica
Una vez que tenía los puntos rotando en un espacio 3D, necesitaba aplanarlos a una pantalla 2D (la consola). Al proyectarlos, tuve que compensar la forma de la terminal. Los caracteres en la consola son más altos que anchos (usualmente en una proporción de 2:1), por lo que apliqué un multiplicador de corrección que llamé `ASPECT_RATIO = 2.0` para evitar que el rectángulo o el círculo se vieran aplastados.

## 4. Rasterización: El Algoritmo de Bresenham
Una vez proyectados los puntos $A$ y $B$, ¿cómo dibujo una línea recta entre ellos si la pantalla está compuesta por una cuadrícula discreta de caracteres?

Para solucionar esto implementé mi propia versión del **Algoritmo de Línea de Bresenham** en el método `dibujarLinea()`. Este algoritmo determina qué píxeles (o en mi caso, caracteres `#`) deben "encenderse" en mi matriz de buffers (`char[][]`) para formar una aproximación visual perfecta de una línea recta, calculando la pendiente de forma óptima usando sumas y restas en lugar de costosas divisiones de punto flotante.

## 5. Interactividad y Concurrencia (Hilos)
El reto final fue la interactividad. La clase `Scanner` bloquea el flujo del programa hasta que se da *Enter*. Si la ponía en el bucle de renderizado, la figura se congelaría. 

Para solucionar esto, diseñé una arquitectura asíncrona:
1. **Hilo Principal:** Ejecuta un bucle infinito a 20 FPS (usando `Thread.sleep(50)`) encargado puramente de limpiar la consola (`\033[H`), calcular la matriz, trazar las líneas e imprimir el buffer.
2. **Hilo Secundario (Demonio):** Un `Thread` dedicado exclusivamente a escuchar el `Scanner` del teclado en segundo plano.

Para comunicar ambos hilos de forma segura, utilicé variables marcadas con el modificador `volatile` (ej. `private static volatile double curW`). Esto garantiza que cuando yo modifico el ancho del objeto desde el teclado en el hilo 2, el hilo 1 lea el valor actualizado en el espacio de memoria instantáneamente en el siguiente fotograma, logrando mutaciones geométricas sin interrumpir la animación.


## 6. Entorno de Ejecución y Siguientes Pasos
Es crucial destacar que este motor fue diseñado para ejecutarse exclusivamente en emuladores de terminal reales y robustos (como Bash, Zsh o terminales nativas de Linux/macOS). Debido a que la ilusión de animación depende de **secuencias de escape ANSI** para manipular el cursor y limpiar la pantalla en tiempo real, **este código no funcionará correctamente en la consola de depuración integrada de Eclipse** (ni en NetBeans). Ejecutarlo en dichos IDEs producirá un volcado de texto infinito e incomprensible, ya que sus consolas estándar no interpretan estos comandos nativamente.

El siguiente paso evolutivo para este desarrollo sería dar el salto formal a una Interfaz Gráfica de Usuario (GUI). En lugar de rasterizar caracteres en un arreglo bidimensional, el mismo motor matemático de matrices de rotación podría portarse directamente a **Java Swing** (o JavaFX). Al realizar esta migración, reemplazaríamos el buffer de la terminal por un lienzo nativo (`Graphics2D`), permitiendo dibujar líneas reales y polígonos sólidos en una ventana del sistema operativo, logrando gráficos de alta fidelidad sin perder el control matemático subyacente.
