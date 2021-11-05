# Tres en Línea
Implementación del juego "tres en línea" usando el lenguaje de programación Java.

## 1. Propósito
El propósito principal del proyecto es la implementación del juego conocido como "tres en línea", "tres en raya" o "michi"; utilizando para ello el lenguaje de programación Java, principalmente la programación orientada a objetos.

## 2. Descripción del juego
Se juega en una cuadrícula o tablero formado por el trazo de dos líneas horizontales y dos líneas verticales. El juego es jugado por dos jugadores.

Cada jugador elige las X o las O, y en su turno debe poner una en un casillero del tablero, intentando conseguir tres letras iguales seguidas en una línea vertical, horizontal o diagonal.

La partida termina cuando uno de los jugadores consigue enlazar tres fichas iguales seguidas. En caso se llenen todos los casilleros del tablero sin que ninguno de los jugadores consiga enlazar tres de sus fichas seguidas se termina la partida finalizando empatados.

Estas reglas del juego se han plasmado en el proyecto desarrollado utilizando los conceptos y técnicas de la orientación a objetos, precisamente el diseño y diagrama de clases y el lenguaje de programación Java mediante el entorno integrado de desarrollo Eclipse.

El entorno gráfico del producto resultante del proyecto es el entorno de escritorio.

###  El problema
Se desea implementar un software que permita jugar "tres en línea" en dos escenarios: dos jugadores (humano versus humano) y un jugador teniendo contrincante a la computadora (humano versus computadora).

Como es sabido, "tres en línea" se juega en una cuadrícula o tablero formado por el trazo de dos líneas horizontales y dos líneas verticales. Cada jugador tiene una ficha (X para el primer jugador y O para el segundo jugador), y en su turno debe poner una en un casillero del tablero, intentando conseguir tres fichas iguales seguidas en una línea vertical, horizontal o diagonal.

La partida termina cuando uno de los jugadores consigue enlazar tres fichas iguales seguidas. En caso se llenen todos los casilleros del tablero sin que ninguno de los jugadores consiga enlazar tres de sus fichas seguidas, se termina la partida finalizando empatados.

Opcionalmente, cada jugador tiene la posibilidad de establecer su ficha, que puede ser una letra, un número, una sílaba o una palabra.

El software debe contar con la capacidad de determinar la jugada ganadora (fila, columna o diagonal), identificándola de alguna manera que se pueda diferenciar.

Un juego está compuesto por una o más partidas, por cada partida se debe conocer el resultado y la duración; además de los puntajes acumulados de cada jugador.

#### Las clases
De la descripción del problema se identifican los elementos relevantes del entorno, teniendo las siguientes clases:
* **Persona:** Representa a la persona que juega "tres en línea"; eventualmente representa a la computadora.

* **Jugador:** Cada jugador es una persona. Tiene como características relevantes a la ficha con la que juega y el puntaje acumulado en el desarrollo del juego.

* **Casilla:** Representa cada casillero del tablero del juego "tres en línea". Va a contener como valor la ficha establecida por cada jugador.

* **Tablero:** Representa el tablero del juego. Está compuesto por tres filas y tres columnas de casillas.

* **Juego:** Representa el juego "tres en línea". Contiene la información de las partidas que lo forman.

* **Partida:** Representa a cada partida del juego, contiene la información de los turnos, tipo, resultado, tiempo, entre otros.

#### Relaciones entre clases
* **Herencia:** Un jugador es una persona. Consideramos a la computadora como un jugador que representa a una persona.

* **Composición:** La composición es una relación que se utiliza cuando una clase se compone de partes de otra clase.
Una *casilla* es parte de un *tablero* y un tablero tiene uno o muchas casillas.
Una *partida* es parte de un *juego* y un juego tiene una o muchas partidas.

* **Asociaciones:** Las asociaciones representan relaciones entre clases.
Un *jugador* juega una *partida* y una partida es jugada por dos jugadores.
Una *partida* tiene un *tablero* y cada tablero pertenece a una partida.

#### Diagrama de clases
![Diagrama de Clases](/images/TresEnLinea.jpg)

## 3. Alcance del proyecto
El proyecto considera ciertas situaciones que delimitan las funcionalidades del software resultante, las cuales se listan a continuación:

* **Persistencia de datos:** El software sólo es capaz de brindar información de las partidas que se juegan durante la ejecución del software; es decir no contempla el almacenamiento o persistencia de datos en algún tipo de fuente de almacenamiento.

* **Configuración de todos los elementos del negocio:** Solo se considera la configuración de la ficha de cada jugador, que por defecto establece la ficha X para el jugador 1 y la ficha O para el jugador 2.
Algunos elementos toman valores por defecto y no es posible su configuración, el nombre de los jugadores es "Jugador 1" y "Jugador 2".

* **Configuración de los elementos de la interfaz gráfica:** Los colores de la interfaz gráfica son establecidos por defecto y no es posible su configuración personalizada.

* **Generación de la jugada de la computadora:** La selección de la casilla en la que la computadora coloca la ficha correspondiente es generada aleatoriamente, tomando en consideración ~sólo si la casilla se encuentra vacía o no~. Para la generación aleatoria de la casilla se hace uso de un temporizador establecido en 3 segundos, el cual es reiniciado luego de realizar la jugada.
No se considera niveles de juego en las que la computadora podría predecir una jugada ganadora de su contrincante y evitarla.

**Actualización *octubre 2021***
La generación de la jugada de la computadora ya no se realiza sólo si la casilla se encuentra vacía o no, sino que además de tiene en cuenta si el contrincante está a punto de ganar el juego y se bloquea la posible jugada.