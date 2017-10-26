package tetris;

/* awtはGUIアプリケーションを作成するためのクラスライブラリ */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class main extends Applet implements Runnable, KeyListener {

    private int x,y;
    private long SPEED=100;
    private int block=10;
    private int width=100,height=200;
    private int margin=10;
    private int xx,yy;

    private Graphics offG;
    private Image img;
    private Thread th;

    /* ゲーム画面の大きさや座標の設定 */
    public void init() {
        setSize(200,270);
        setLayout(null);
        img = createImage(width,height);
        offG = img.getGraphics();

        addKeyListener(this);
        requestFocus();

        x=width/block;
        y=height/block;

        xx=80;
    }

    public void start() {
        if(th==null) {
            th = new Thread(this);
            th.start();
        }
    }

    public void paint(Graphics G) {
        offG.clearRect(margin,margin,width,height);
        offG.setColor(Color.gray);
        offG.fillRect(margin,margin,width,height);

        offG.setColor(Color.orange);
        offG.fillRect(xx,yy+margin,block,block);

        G.drawImage(img,0,0,this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        while(th == Thread.currentThread()) {
            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            yy += block;
            if(yy==200) {
                yy=0;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            if(xx>=block+margin) {
                xx-=block;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(xx<=width-block*2) {
                xx+=block;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}