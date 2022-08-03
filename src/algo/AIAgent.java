package algo;

public class AIAgent implements Agent {

  private int player;
  private Board board;

  public AIAgent(Board board,int player) {
   this.player=player;
    this.board = board;
  }

  @Override
  public void play() {
    
    System.out.println("this player :" + this.player);
    Board maxBoard = new Board();
    float maxPriority = -2.0f;
    for (int i = 0; i < board.cell.length; i++) {
      if (board.cell[i] == 0) {
        Board newBoard = new Board();
        newBoard.copyBoard(board);
        if (player == 1) {
          newBoard.cell[i] = 1;
        } else {
          newBoard.cell[i] = 2;
        }
        System.out.println(player * Main.evals[newBoard.getIndex()]);
        newBoard.dispBoard();

        if (maxPriority < (player * Main.evals[newBoard.getIndex()])) {
          maxPriority = player * Main.evals[newBoard.getIndex()];

          maxBoard.copyBoard(newBoard);
        }
      }
    }

    maxBoard.dispBoard();
    this.board.copyBoard(maxBoard);
  }

 
}
