
# Chain Reaction - Java & Swing

## Introduction

**Chain Reaction** is a clone of the popular Chain Reaction game, built using Java and the Swing framework. In this two-player strategy game, players take turns placing orbs in a grid. The goal is to dominate the board by causing chain reactions that eliminate your opponent's orbs.

The game is played on an `n x n` grid. Each cell can hold a certain number of orbs before it "explodes," sending orbs to neighboring cells and potentially causing a chain reaction. The player who completely eliminates their opponent's orbs wins.

---

## Features

- Turn-based two-player game (Red vs Blue).
- Chain reaction mechanic based on grid cell capacity.
- Graphical user interface (GUI) built with Java Swing.
- Simple and intuitive gameplay.
- **Backtracking Algorithm** used for efficient game state management and to simplify code complexity.

---

## Prerequisites

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) version 8 or higher.
- [Git](https://git-scm.com/) (optional, to clone the repository).

---

## Quick Start

1. **Clone the repository**:

    ```bash
    git clone https://github.com/AnnonymousBanda/ChainReaction
    ```

2. **Navigate to the project directory**:

    ```bash
    cd ChainReaction
    ```

3. **Compile the project**:

    ```bash
    javac Main.java
    ```

4. **Run the application**:

    ```bash
    java Main
    ```

5. **Enjoy the game**:  
   The game window will appear with an 8x8 grid by default. The red player goes first.

    - Take turns clicking on the grid cells to place orbs.
    - When a cell reaches its maximum capacity, it will explode, sending orbs to adjacent cells and causing chain reactions.
    - The game ends when one player dominates the board and eliminates the other player's orbs.

---

## Technical Details

### How the Game Works

- Each cell has a capacity based on its position:
    - Corner cells: Capacity of 2 orbs.
    - Edge cells: Capacity of 3 orbs.
    - Inner cells: Capacity of 4 orbs.
- When a cell reaches its capacity, it "explodes," sending orbs to adjacent cells, potentially triggering a chain reaction.

### Backtracking Algorithm

The game logic leverages **Backtracking** to simplify the code by managing and exploring the game state efficiently. The backtracking approach allows the game to calculate the possible moves, simulate chain reactions, and backtrack when necessary to find the optimal state. This helps reduce the complexity of the game logic, making it easier to manage game state transitions and ensuring the game operates smoothly.

---

## Gameplay

![Gameplay Screenshot](Images/gameplay.gif)

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
