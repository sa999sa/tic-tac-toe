package algo;

import java.util.Scanner;

public class PlayerAgent implements Agent {

  private Board board;
  private int player;

  public PlayerAgent(Board board, int player) {
    this.board = board;
    this.player = player;
  }

  @Override
  public void play() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      board.dispBoard();
      System.out.print("x座標の値を入力してください:");
      int x = sc.nextInt();
      System.out.print("y座標の値を入力してください:");
      int y = sc.nextInt();
      if (0 <= x && x <= 9 && 0 <= y && y <= 9) {
        if (board.cell[x + y * 3] == 0) {
          if (player == 1) {
            board.cell[x + y * 3] = 1;
          } else {
            board.cell[x + y * 3] = 2;
          }

          break;
        } else {
          System.out.println("すでに入力済みの座標です。");
          System.out.println("もう一度入力し直してください。");
        }
      } else {
        System.out.println("範囲外の座標です。");
        System.out.println("もう一度入力し直してください。");
      }
    }
  }
}
