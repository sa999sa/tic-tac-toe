package algo;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {
    Board board=new Board();

    @Test
    public void winBoard1(){
        board.init();
        board.cell[0]=1;
        board.cell[1]=1;
        board.cell[2]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard2(){
        board.init();
        board.cell[3]=1;
        board.cell[4]=1;
        board.cell[5]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard3(){
        board.init();
        board.cell[6]=1;
        board.cell[7]=1;
        board.cell[8]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard4(){
        board.init();
        board.cell[0]=1;
        board.cell[3]=1;
        board.cell[6]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard5(){
        board.init();
        board.cell[1]=1;
        board.cell[4]=1;
        board.cell[7]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard6(){
        board.init();
        board.cell[2]=1;
        board.cell[5]=1;
        board.cell[8]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard7(){
        board.init();
        board.cell[0]=1;
        board.cell[4]=1;
        board.cell[8]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void winBoard8(){
        board.init();
        board.cell[2]=1;
        board.cell[4]=1;
        board.cell[6]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void loseBoard1(){
        board.init();
        board.cell[0]=2;
        board.cell[1]=2;
        board.cell[2]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard2(){
        board.init();
        board.cell[3]=2;
        board.cell[4]=2;
        board.cell[5]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard3(){
        board.init();
        board.cell[6]=2;
        board.cell[7]=2;
        board.cell[8]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard4(){
        board.init();
        board.cell[0]=2;
        board.cell[3]=2;
        board.cell[6]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard5(){
        board.init();
        board.cell[1]=2;
        board.cell[4]=2;
        board.cell[7]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard6(){
        board.init();
        board.cell[2]=2;
        board.cell[5]=2;
        board.cell[8]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard7(){
        board.init();
        board.cell[0]=2;
        board.cell[4]=2;
        board.cell[8]=2;
        assertEquals(-1, board.judge());
    }
    @Test
    public void loseBoard8(){
        board.init();
        board.cell[2]=2;
        board.cell[4]=2;
        board.cell[6]=2;
        assertEquals(-1, board.judge());
    }
    
    @Test
    public void debagBoard(){
        board.init();
    
        assertEquals(0, board.judge());
    }
    @Test
    public void copyBoardTest(){
        board.init();
        board.cell[2]=2;
        board.cell[4]=2;
        board.cell[6]=2;
        
        Board subBoard=new Board();
        subBoard.copyBoard(board);
        assertArrayEquals(subBoard.cell, board.cell);
    }
    @Test
    public void winBoard(){
        board.init();
        board.cell[0]=1;
        board.cell[3]=1;
        board.cell[4]=2;
        board.cell[7]=2;
        board.cell[6]=1;
        assertEquals(1, board.judge());
    }
    @Test
    public void tableTest(){
        board.init();
        
        board.cell[1]=2;
        board.cell[0]=1;
        int index=0;
        for(int i=0;i<board.cell.length;i++){
            index+=board.cell[i]*((int)Math.pow(3, i));
        }
        assertEquals(index, 7);
    }
}
