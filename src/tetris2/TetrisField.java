package tetris2;

/*----------------------------------------------------------
	��TetrisField.java
ゲームフィールドの状態を管理
----------------------------------------------------------*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class TetrisField {
	private TetrisBlock blc = new TetrisBlock();
	private int [][] field = new int[HEIGHT][WIDTH];	//フィールドを表現するための

	static final int CANVAS_HEIGHT = 600, CANVAS_WIDTH = 350;
	static final int WALL = -1, SPACE = 0, BLOCK = 1;
  	static final int HEIGHT = 20 + 1;	//高さ
  	static final int WIDTH = 10 + 2; 	//幅
  	static final int BLOCK_SIZE = 25;	//1ブロックの描画サイズ

  	private int blockX = 5, blockY = 0;	//ブロック左上の座標

  	/*-----------------------------------------------
  		コンストラクタ（初期化）
  	-------------------------------------------------*/
  	TetrisField(){
  		resetField();	//フィールドをリセット
 		resetBlock();	//ブロックのリセット
  	}
  	/*-----------------------------------------------
  		ブロックの初期化
  	-------------------------------------------------*/
  	private void resetBlock(){
  		//ブロック座標の初期化
  		blockX = 4;
  		blockY = 0;
  		//ブロックの生成
  		blc.createBlock();
  	}
  	/*-----------------------------------------------
  		fieldをすべて空白にしてから、上辺を除く外枠を壁にする。
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
  		ゲームのメイン処理（１サイクル）
  	-------------------------------------------------*/
  	public boolean gameMain(){
  		//下方向へ移動
  		if(moveBlock(0, 1) == false){

  			passiveBlock();	//ブロックをフィールドに固定
  			checkLine();		//ラインをチェックしそろっていれば消す
  			resetBlock();		//新しいブロックを生成
  		}
  		return isGameOver();	//ゲーム続行可能かチェック
  	}
  	/*-----------------------------------------------
  		ゲームオーバーのチェック
  	-------------------------------------------------*/
  	private boolean isGameOver(){
  		for(int i=1; i<WIDTH; i++){
  			if(field[0][i] == BLOCK){
  				return false;	//ゲームオーバー
  			}
  		}
  		return true;
  	}
  	/*-----------------------------------------------
  		TetrisMainクラスのpaintメソッドから呼びされる
  	-------------------------------------------------*/
  	public void drawField(Graphics g){
  		//一旦画面を塗りつぶす
  		g.setColor(Color.black);
  		g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

  		for(int i=0; i<HEIGHT; i++){
  			for(int j=0; j<WIDTH; j++){
  				switch(field[i][j]){
  					case SPACE:	//空白(黒色)
  						g.setColor(Color.black);
  						break;
  					case WALL:	//壁(灰色)
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
  		ブロックの描画
  	-------------------------------------------------*/
  	public void drawBlock(Graphics g){
  		int [][] block = blc.getBlock();//現在のブロックを取得

  		for(int i=blockY; i<blockY+4; i++){
  			for(int j=blockX; j<blockX+4; j++){
  				switch(block[i-blockY][j-blockX]){
  					case BLOCK:	//ブロック（白色）
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
  		ブロック動作の決定
  	-------------------------------------------------*/
  	public void setAction(int keyCode){
  		switch(keyCode){
			case KeyEvent.VK_UP:
				rotateBlock();		//ブロックの回転
				break;
			case KeyEvent.VK_DOWN:
				moveBlock(0, 1);	//下方向へ移動
				break;
			case KeyEvent.VK_LEFT:
				moveBlock(-1, 0);	//左方向へ移動
				break;
			case KeyEvent.VK_RIGHT:
				moveBlock(1, 0);	//右方向へ移動
				break;
			default:
				break;
		}
  	}
  	/*-----------------------------------------------
  		ブロックの移動
  	-------------------------------------------------*/
  	private boolean moveBlock(int dirX, int dirY){
  		if(isMoveBlock(dirX, dirY)){//ブロックの移動が可能
  			blockX += dirX;
  			blockY += dirY;
  			return true;
  		}
  		return false;
  	}
  	/*-----------------------------------------------
  		ブロックの移動が可能かどうか
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
  						return false;	//移動できない
  					}
  				}
  			}
  		}
  		return true;
  	}
  	/*-----------------------------------------------
  		ブロックの回転
  	-------------------------------------------------*/
  	private boolean rotateBlock(){
  		int[][] block = blc.getRotateBlock();

  		for(int i=0; i<4; i++){
  			for(int j=0; j<4; j++){
  				if(block[i][j] == BLOCK && field[i+blockY][j+blockX] != SPACE){
  					return false;	//回転できない
  				}
  			}
  		}
  		blc.rotateBlock();	//ブロックを回転させる
  		return true;
  	}
  	/*-----------------------------------------------
  		ブロックをフィールドに固定する
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
  		ラインのチェック
  	-------------------------------------------------*/
  	private void checkLine(){
  		boolean flg = true;

  		for(int i=HEIGHT-2; i>0; i--){
  			for(int j=1; j<WIDTH-1;j++){
  				//隙間があった場合、falseを返す
  				if(field[i][j] == SPACE){
  					flg = false;
  				}
  			}
  			if(flg){
  				deleteLine(i);	//そろっているラインを削除
  				i++;			//1段ずらす
  			}else{
  				flg = true;
  			}
  		}
  	}
  	/*-----------------------------------------------
  		ラインの削除
  	-------------------------------------------------*/
  	private void deleteLine(int lineNum){
  		System.out.println("i:"+lineNum);
  		for(int i=lineNum; i>=0; i--){
  			for(int j=1; j<WIDTH-1; j++){
  				if(i==0){	//最上段を空白に
  					field[i][j] = SPACE;
  				}else{
  					field[i][j] = field[i-1][j];
  				}
  			}
  		}
  	}
}