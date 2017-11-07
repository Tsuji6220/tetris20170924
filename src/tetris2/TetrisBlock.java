package tetris2;

/*-----------------------------------------------
	��TetrisBlock.java
テトリスブロックの管理
-------------------------------------------------*/
import java.util.Random;

class TetrisBlock{
	Random rnd = new Random();
	private int[][] Block;	//�u���b�N

	/*-----------------------------------------------
  		�u���b�N�̐���
  	-------------------------------------------------*/
	public void createBlock(){
		int n = rnd.nextInt(7);	//�����_���Ƀu���b�N�𐶐�

		switch(n){
			case 0:
				Block = new int[][]{ {0, 1, 0, 0},
						    	     {0, 1, 0, 0},
						    		 {0, 1, 0, 0},
						    		 {0, 1, 0, 0} };
				break;
			case 1:
				Block = new int[][]{ {0, 0, 0, 0},
				             		 {0, 1, 1, 0},
							 		 {0, 1, 1, 0},
									 {0, 0, 0, 0} };
				break;
			case 2:
				Block = new int[][]{ {0, 0, 0, 0},
							         {0, 1, 1, 0},
							         {1, 1, 0, 0},
							 		 {0, 0, 0, 0} };
				break;
			case 3:
				Block = new int[][]{ {0, 0, 0, 0},
							 		 {1, 1, 0, 0},
									 {0, 1, 1, 0},
									 {0, 0, 0, 0} };
				break;
			case 4:
				Block = new int[][]{ {0, 0, 0, 0},
									 {0, 1, 0, 0},
							 		 {1, 1, 1, 0},
									 {0, 0, 0, 0} };
				break;
			case 5:
				Block = new int[][]{ {0, 1, 0, 0},
							 		 {0, 1, 0, 0},
							 		 {0, 1, 1, 0},
							 		 {0, 0, 0, 0} };
				break;
			default:
				Block = new int[][]{ {0, 0, 1, 0},
							 		 {0, 0, 1, 0},
							 		 {0, 1, 1, 0},
							 		 {0, 0, 0, 0} };
				break;
		}
	}
	/*-----------------------------------------------
  		���݂̃u���b�N�𓾂�
  	-------------------------------------------------*/
	public int[][] getBlock(){
		return Block;
	}
	/*-----------------------------------------------
  		�u���b�N����]������
  	-------------------------------------------------*/
	public void rotateBlock(){
		Block = getRotateBlock();
	}
	/*-----------------------------------------------
  		�u���b�N����]�������u���b�N�𓾂�
  		���ۂ̃u���b�N�͉�]�����Ȃ�
  	-------------------------------------------------*/
	public int[][] getRotateBlock(){
		int[][] tmp = new int[4][4];

		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				tmp[i][j] = Block[3-j][i];
			}
		}
		return tmp;
	}
}