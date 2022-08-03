package algo;

public class WriteScoreAgent implements Agent {

  private Board board;
  private int player;

  public WriteScoreAgent(Board board, int player) {
    this.board = board;
    this.player = player;
  }

  public void play() {
    Node node = new Node(board, player);
    for (int j = 0; j < 100000; j++) {
      node.extend();
    }
    Node maxNode = null;

    double maxPriority = 0;

    for (var child : node.children) {
      double value =
        this.player * (double) child.value / (double) child.visitCount;
      double offset = Math.sqrt(
        2.0 * Math.log(node.visitCount) / (double) child.visitCount
      );
      double priority = offset + value;

      if (priority > maxPriority) {
        maxNode = child;
        maxPriority = priority;
      }
    }

    this.board.copyBoard(maxNode.board);
  }
}
