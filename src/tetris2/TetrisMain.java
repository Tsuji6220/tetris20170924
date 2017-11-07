package tetris2;

import java.applet.Applet;
/*--------------------------------------------------------------
<APPLET CODE="TetrisMain.class" width="350"height="600"></APPLET>
ゲームサイクルの管理・画面描画・入力キーの処理
---------------------------------------------------------------*/
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisMain extends Applet implements Runnable, KeyListener{
	private TetrisField field = new TetrisField();	//ゲームフィールド
  	private Thread thread;		//ゲーム進行用スレッド
  	private static int SPEED = 500;	//1サイクルの速度(ミリ秒)

  	/*-----------------------------------------------
  		コンストラクタ内で初期化とスレッドの実行
  	-------------------------------------------------*/
  	public TetrisMain(){
  		addKeyListener(this);		//キーリスナーの登録
  //		thread = new Thread(this);	//スレッドの生成
  //		thread.start();				//スレッドの実行
  	}
//  	/*-----------------------------------------------
//  		�Q�[�����s
//  	-------------------------------------------------*/
//  	public void run(){
//  		while(true){
//  			if(field.gameMain()){	//�Q�[���̃��C������
//  				repaint();			//��ʕ`��
//  			}else{	//�Q�[���I�[�o�[
//  				break;
//  			}
//  			try{
//  				Thread.sleep(SPEED);
//  			}catch(InterruptedException e){}
//  		}
//  	}
  	/*-----------------------------------------------
  		画面描画
  	-------------------------------------------------*/
  	public void paint(Graphics g){
  		field.drawField(g);	//フィールドの描画
//  		field.drawBlock(g);	//ブロックの描画
  	}
  	/*-----------------------------------------------
  		キーが押された場合の処理
  	-------------------------------------------------*/
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();	//押したキーのコードを取得
//		field.setAction(keyCode);		//取得したキーコードを渡す
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
}