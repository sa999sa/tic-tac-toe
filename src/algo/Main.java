package algo;

import java.util.ArrayList;

public class Main {

  //座標配列　（一次元）
  /**
   * |0|1|2|
   * |3|4|5|
   * |6|7|8|
   *
   * 座標の式=x+y*3;
   *
   * float32で表現する場合 数字を座標番号
   * 0.12345678
   */
  public static int gameCount = 0;
  public static float[] evals = new float[19683];

  public static void main(String[] args) {
    Board board = new Board();

    for (int i = 0; i < evals.length; i++) {
      evals[i] = 0;
    }

    Agent[] writeAgent = new Agent[] {
      new WriteScoreAgent(board, 1),
      new WriteScoreAgent(board, -1),
    };

    

    for (int i = 0; i < 9; i++) {
      writeAgent[i % 2].play();

      if (board.judge() != 0) {
        break;
      }
    }

    System.out.println("*****Player戦*****");
    board.init();
    Agent aiAgent = new AIAgent(board, -1);
    Agent playerAgent = new PlayerAgent(board, 1);
    for (int i = 0; i < 9; i++) {
      System.out.println("現在の盤面");

      if (i % 2 == 1) {
        aiAgent.play();
      }

      if (i % 2 == 0) {
        playerAgent.play();
      }
      if (board.judge() != 0) {
        System.out.println("gameSet");
        board.dispBoard();
        break;
      }
    }
  }
}

class Node {

  int player;
  int score = 0;
  Board board;
  Node parent;
  int visitCount = 0;
  int value = 0;
  int result = 0;
  ArrayList<Node> children = new ArrayList<>();

  public Node(Board board, int player) {
    this.board = board;

    this.player = player;
  }

  public int extend() {
    if (board.isfull() || board.judge() != 0) {
      this.result = board.judge();
      this.visitCount += 1;
      this.value += this.result;
      Main.evals[this.board.getIndex()] = (float) this.value / this.visitCount;
      return this.result;
    }
    if (this.children.size() == 0) {
      for (int x = 0; x < 3; x++) {
        for (int y = 0; y < 3; y++) {
          if (this.board.cell[x + y * 3] == 0) {
            Board newBoard = new Board();
            newBoard.copyBoard(this.board);
            if (player == 1) {
              newBoard.cell[x + y * 3] = 1;
            } else {
              newBoard.cell[x + y * 3] = 2;
            }
            Node newNode = new Node(newBoard, player * -1);
            this.children.add(newNode);
          }
        }
      }
    }
    Node maxNode = null;
    double maxPriority = -2.0;
    for (var child : this.children) {
      if (child.visitCount == 0) {
        maxNode = child;
        maxPriority = 100;
      } else {
        double value =
          this.player * (double) child.value / (double) child.visitCount;
        double offset = Math.sqrt(
          2.0 * Math.log(this.visitCount) / (double) child.visitCount
        );
        double priority = offset + value;
        if (priority > maxPriority) {
          maxNode = child;
          maxPriority = priority;
        }
      }
    }

    int result = maxNode.extend();
    this.value += result;
    this.visitCount += 1;

    Main.evals[this.board.getIndex()] = (float) this.value / this.visitCount;

    return result;
  }
  //

}

/**
 */
class Board {

  int[] cell = new int[9];
  ArrayList<Point> points = new ArrayList<Point>();

  /**
   * 盤面の設定
   */
  public void init() {
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        cell[x + y * 3] = 0;
      }
    }
  }

  public int getIndex() {
    int index = 0;
    for (int i = 0; i < this.cell.length; i++) {
      index += cell[i] * Math.pow(3, i);
    }
    // System.out.println(index);
    return index;
  }

  public boolean isfull() {
    boolean judge = true;
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        if (cell[x + y * 3] == 0) {
          judge = false;
        }
      }
    }

    return judge;
  }

  public void copyBoard(Board parents) {
    cell = new int[9];

    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        cell[x + y * 3] = parents.cell[x + y * 3];
      }
    }
  }

  /**ゲームの勝敗判定処理
   */
  public int judge() {
    for (int y = 0; y < 3; y++) {
      if (
        cell[y * 3] == cell[1 + y * 3] && cell[2 + y * 3] == cell[1 + y * 3]
      ) {
        if (cell[y * 3] == 1) {
          return 1;
        } else if (cell[y * 3] == 2) {
          return -1;
        }
      }
    }
    for (int y = 0; y < 3; y++) {
      if (cell[y] == cell[3 + y] && cell[6 + y] == cell[3 + y]) {
        if (cell[y + 3] == 1) {
          return 1;
        } else if (cell[y + 3] == 2) {
          return -1;
        }
      }
    }
    if (cell[0] == cell[4] && cell[4] == cell[8]) {
      if (cell[0] == 1) {
        return 1;
      } else if (cell[0] == 2) {
        return -1;
      }
    }
    if (cell[2] == cell[4] && cell[4] == cell[6]) {
      if (cell[2] == 1) {
        return 1;
      } else if (cell[2] == 2) {
        return -1;
      }
    }
    return 0;
  }

  /**
        描画用処理
     */
  public void dispBoard() {
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        if (cell[x + y * 3] == 1) {
          System.out.print("O");
        } else if (cell[x + y * 3] == 2) {
          System.out.print("X");
        } else {
          System.out.print(" ");
        }
        if (x % 3 != 2) {
          System.out.print("|");
        } else {
          System.out.println("");
        }
      }
    }
  }
}

class Point {

  int posX;
  int posY;
}
