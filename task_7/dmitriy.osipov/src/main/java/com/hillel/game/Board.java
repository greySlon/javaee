package com.hillel.game;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("game-board")
public class Board {

  private char[][] array = new char[3][3];

  private char winner;
  private char initChar = ' ';

  public Board() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        array[i][j] = initChar; // Заносим пробел в каждую ячейку массива
      }
    }
  }

  public void print() {
    for (int i = 0; i < 3; i++) {
      System.out.println("-------");
      for (int j = 0; j < 3; j++) {
        System.out.print("|");
        System.out.print(array[i][j]);
      }
      System.out.println("|");
    }
    System.out.println("-------");
  }

  public void fillCell(int row, int column, char currentPlayer) {
    array[row][column] = currentPlayer;
  }

  public boolean isGameFinished() {
    int dimSize = array.length;

    //diagonal
    if ((array[1][1] != initChar)
        && ((array[1][1] == array[0][0] && array[1][1] == array[2][2])
        || (array[1][1] == array[0][2] && array[1][1] == array[2][0]))) {
      winner = array[1][1];
      return true;
    }

    for (int indRow = 0; indRow < dimSize; indRow++) {
      //horizontal
      if ((array[indRow][0] != initChar)
          && (array[indRow][0] == array[indRow][1]
          && array[indRow][1] == array[indRow][2])) {
        winner = array[indRow][0];
        return true;
      }
      //vertical
      if ((array[0][indRow] != initChar)
          && (array[0][indRow] == array[1][indRow]
          && array[1][indRow] == array[2][indRow])) {
        winner = array[0][indRow];
        return true;
      }
    }

    return false;
  }

  public String getWinner() {
    if (winner == 'X') {
      return "Крестики одержали победу";
    } else {
      return "Нолики одержали победу";
    }
  }

  public List<int[]> getFreeCells() {
    int dim = array.length;
    List<int[]> freeCells = new ArrayList<>();
    for (int indRow = 0; indRow < dim; indRow++) {
      for (int indCol = 0; indCol < dim; indCol++) {
        if (array[indRow][indCol] == initChar) {
          int[] cellCoord = {indRow, indCol};
          freeCells.add(cellCoord);
        }
      }
    }
    return freeCells;
  }
}
