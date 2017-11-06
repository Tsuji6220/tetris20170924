package tetris2;

/*----------------------------------------------------------
	��TetrisField.java
	���C�����F09/01/31
	���t�B�[���h�Ǘ��N���X
	�����C���N���X����̖��߂Ńu���b�N�̑���Ȃǂ��s��
----------------------------------------------------------*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class TetrisField {
	private TetrisBlock blc = new TetrisBlock();
	private int [][] field = new int[HEIGHT][WIDTH];	//�Q�[���t�B�[���h
	//�萔
	static final int CANVAS_HEIGHT = 600, CANVAS_WIDTH = 350;
	static final int WALL = -1, SPACE = 0, BLOCK = 1;
  	static final int HEIGHT = 20 + 1;	//����
  	static final int WIDTH = 10 + 2; 	//��
  	static final int BLOCK_SIZE = 25;	//�P�u���b�N�̕`��T�C�Y

  	private int blockX = 5, blockY = 0;	//�u���b�N����̍��W

  	/*-----------------------------------------------
  		�R���X�g���N�^(������)
  	-------------------------------------------------*/
  	TetrisField(){
  		resetField();	//�t�B�[���h�����Z�b�g
  		resetBlock();	//�u���b�N�̃��Z�b�g
  	}
  	/*-----------------------------------------------
  		�u���b�N�̏�����
  	-------------------------------------------------*/
  	private void resetBlock(){
  		//�u���b�N���W�̏�����
  		blockX = 4;
  		blockY = 0;
  		//�u���b�N�̐���
  		blc.createBlock();
  	}
  	/*-----------------------------------------------
  		�t�B�[���h�̏�����
  	-------------------------------------------------*/
  	private void resetField(){
  		for(int i=0; i<HEIGHT; i++){
  			for(int j=0; j<WIDTH; j++){
  				field[i][j] = SPACE;

  				if(i==HEIGHT-1 || j==0 || j==WIDTH-1){
  					field[i][j] = WALL;
  				}
  			}
  		}
  	}
  	/*-----------------------------------------------
  		�Q�[���̃��C������(�P�T�C�N��)
  	-------------------------------------------------*/
  	public boolean gameMain(){
  		//�������ֈړ�
  		if(moveBlock(0, 1) == false){

  			passiveBlock();	//�u���b�N���t�B�[���h�ɌŒ�
  			checkLine();		//���C�����`�F�b�N�������Ă���Ώ���
  			resetBlock();		//�V�����u���b�N�𐶐�
  		}
  		return isGameOver();	//�Q�[�����s�\���`�F�b�N
  	}
  	/*-----------------------------------------------
  		�Q�[���I�[�o�[�̃`�F�b�N
  	-------------------------------------------------*/
  	private boolean isGameOver(){
  		for(int i=1; i<WIDTH; i++){
  			if(field[0][i] == BLOCK){
  				return false;	//�Q�[���I�[�o�[
  			}
  		}
  		return true;
  	}
  	/*-----------------------------------------------
  		�t�B�[���h�̕`��
  	-------------------------------------------------*/
  	public void drawField(Graphics g){
  		g.setColor(Color.black);
  		g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);	//��U��ʂ�h��Ԃ�

  		for(int i=0; i<HEIGHT; i++){
  			for(int j=0; j<WIDTH; j++){
  				switch(field[i][j]){
  					case SPACE:	//��(���F)
  						g.setColor(Color.black);
  						break;
  					case WALL:	//��(�D�F)
  						g.setColor(Color.gray);
  						break;
  				}
  				g.fillRect(j*BLOCK_SIZE+BLOCK_SIZE, i*BLOCK_SIZE+BLOCK_SIZE*2, BLOCK_SIZE, BLOCK_SIZE);
  				g.setColor(Color.lightGray);
  				g.drawRect(j*BLOCK_SIZE+BLOCK_SIZE, i*BLOCK_SIZE+BLOCK_SIZE*2, BLOCK_SIZE, BLOCK_SIZE);
  			}
  		}
  	}
  	/*-----------------------------------------------
  		�u���b�N�̕`��
  	-------------------------------------------------*/
  	public void drawBlock(Graphics g){
  		int [][] block = blc.getBlock();//���݂̃u���b�N���擾

  		for(int i=blockY; i<blockY+4; i++){
  			for(int j=blockX; j<blockX+4; j++){
  				switch(block[i-blockY][j-blockX]){
  					case BLOCK:	//�u���b�N(���F)
  						g.setColor(Color.white);
  						g.fillRect(j*BLOCK_SIZE+BLOCK_SIZE, i*BLOCK_SIZE+BLOCK_SIZE*2, BLOCK_SIZE, BLOCK_SIZE);
  						g.setColor(Color.lightGray);
  						g.drawRect(j*BLOCK_SIZE+BLOCK_SIZE, i*BLOCK_SIZE+BLOCK_SIZE*2, BLOCK_SIZE, BLOCK_SIZE);
  						break;
  				}
  			}
  		}
  	}
  	/*-----------------------------------------------
  		�u���b�N����̌���
  	-------------------------------------------------*/
  	public void setAction(int keyCode){
  		switch(keyCode){
			case KeyEvent.VK_UP:
				rotateBlock();		//�u���b�N�̉�]
				break;
			case KeyEvent.VK_DOWN:
				moveBlock(0, 1);	//�������ֈړ�
				break;
			case KeyEvent.VK_LEFT:
				moveBlock(-1, 0);	//�������ֈړ�
				break;
			case KeyEvent.VK_RIGHT:
				moveBlock(1, 0);	//�E�����ֈړ�
				break;
			default:
				break;
		}
  	}
  	/*-----------------------------------------------
  		�u���b�N�̈ړ�
  	-------------------------------------------------*/
  	private boolean moveBlock(int dirX, int dirY){
  		if(isMoveBlock(dirX, dirY)){//�u���b�N�̈ړ����\
  			blockX += dirX;
  			blockY += dirY;
  			return true;
  		}
  		return false;
  	}
  	/*-----------------------------------------------
  		�u���b�N�̈ړ����\���ǂ���
  	-------------------------------------------------*/
  	boolean isMoveBlock(int dirX, int dirY){
  		int[][] block = blc.getBlock();
  		int x = 0, y = 0;

  		for(int i=0; i<4; i++){
  			for(int j=0; j<4; j++){
  				if(block[i][j] == BLOCK){
  					x = j + dirX + blockX;
  					y = i + dirY + blockY;

  					if(field[y][x] != SPACE){
  						return false;	//�ړ��ł��Ȃ�
  					}
  				}
  			}
  		}
  		return true;
  	}
  	/*-----------------------------------------------
  		�u���b�N�̉�]
  	-------------------------------------------------*/
  	private boolean rotateBlock(){
  		int[][] block = blc.getRotateBlock();

  		for(int i=0; i<4; i++){
  			for(int j=0; j<4; j++){
  				if(block[i][j] == BLOCK && field[i+blockY][j+blockX] != SPACE){
  					return false;	//��]�ł��Ȃ�
  				}
  			}
  		}
  		blc.rotateBlock();	//�u���b�N����]������
  		return true;
  	}
  	/*-----------------------------------------------
  		�u���b�N���t�B�[���h�ɌŒ肷��
  	-------------------------------------------------*/
  	void passiveBlock(){
  		int[][] block = blc.getBlock();

  		for(int i=0; i<4; i++){
  			for(int j=0; j<4; j++){
  				if(block[i][j] == BLOCK){
  					field[i+blockY][j+blockX] = BLOCK;
  				}
  			}
  		}
  	}

  	/*-----------------------------------------------
  		���C���̃`�F�b�N
  	-------------------------------------------------*/
  	private void checkLine(){
  		boolean flg = true;

  		for(int i=HEIGHT-2; i>0; i--){
  			for(int j=1; j<WIDTH-1;j++){
  				//���Ԃ��������ꍇ�Afalse��Ԃ�
  				if(field[i][j] == SPACE){
  					flg = false;
  				}
  			}
  			if(flg){
  				deleteLine(i);	//�����Ă��郉�C�����폜
  				i++;			//�P�i���炷
  			}else{
  				flg = true;
  			}
  		}
  	}
  	/*-----------------------------------------------
  		���C���̍폜
  	-------------------------------------------------*/
  	private void deleteLine(int lineNum){
  		System.out.println("i:"+lineNum);
  		for(int i=lineNum; i>=0; i--){
  			for(int j=1; j<WIDTH-1; j++){
  				if(i==0){	//�ŏ�i�͋󔒂�
  					field[i][j] = SPACE;
  				}else{
  					field[i][j] = field[i-1][j];
  				}
  			}
  		}
  	}
}