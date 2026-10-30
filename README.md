# Triqui

Este proyecto fue realizado para la materia **Programación Orientada a Objetos** de la **Universidad Nacional de Colombia, Sede Medellín**, durante el semestre **2025-1**.

## ¿Cómo funciona?

Triqui es una aplicación de consola para jugar tres en línea, tic tac toe, o también conocido como triqui en un tablero de 3 × 3. Al iniciar, muestra una guía con las posiciones del tablero y permite escoger uno de estos modos:

1. **Jugador 1 vs. Jugador 2**
2. **Jugador vs. computador (nivel fácil)**: el computador elige una casilla disponible al azar.
3. **Jugador vs. computador (nivel medio)**: el computador intenta bloquear algunas jugadas del jugador y, si no encuentra una, elige una casilla disponible al azar.

Los jugadores usan las fichas **X** y **O**. En una partida entre dos personas, los turnos se alternan. Al terminar, el programa muestra el resultado y las estadísticas de los jugadores registrados durante la ejecución. También se puede elegir jugar otra partida.

## Reglas y posiciones

- El tablero tiene tres filas y tres columnas, identificadas con los números **0, 1 y 2**.
- En cada turno, indica primero el número de la fila y luego el de la columna donde quieres poner tu ficha.
- Por ejemplo, fila `1` y columna `2` corresponden a la casilla del medio a la derecha.
- La casilla debe estar vacía. Si está ocupada o los valores no están entre 0 y 2, el programa solicitará otra posición.
- Gana quien complete primero una línea de tres fichas iguales: horizontal, vertical o diagonal.
- Si se ocupan las nueve casillas y nadie ha completado una línea, la partida termina en empate.

Referencia de coordenadas:

```text
             Columna
             0       1       2
         +-------+-------+-------+
Fila  0  | (0,0) | (0,1) | (0,2) |
         +-------+-------+-------+
      1  | (1,0) | (1,1) | (1,2) |
         +-------+-------+-------+
      2  | (2,0) | (2,1) | (2,2) |
         +-------+-------+-------+
```

## Requisitos

- Java 17 o posterior
- Apache Maven

## Compilar y ejecutar

Desde la carpeta `Triqui`, donde está el archivo `pom.xml`, ejecuta:

```bash
mvn compile
java -cp target/classes com.mycompany.triqui.Triqui
```
