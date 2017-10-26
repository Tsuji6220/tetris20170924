package tetris;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class main6 extends Applet implements Runnable, KeyListener {
    private int x,y;
    private int startXX=50,startYY=20;
    private int LOWSPEED=1000,HIGHSPEED=100;
    private int SPEED=LOWSPEED;
    private int block=10;
    private int width=100,height=200;
    private int margin=10;
    private int xx,yy;
    private int pattern,turn;
    private int ran=0;

    private boolean[][] Status;
    private int[][] colorStatus;

    private Graphics offG;
    private Image img;
    private Thread th;

    public void init() {
        setSize(200,270);
        setSize(200,270);
        setLayout(null);
        img = createImage(width,height);
        offG = img.getGraphics();

        addKeyListener(this);
        requestFocus();

        x=width/block;
        y=height/block;

        Status = new boolean[x][y+1];
        colorStatus = new int[x][y];

        for(int i=0;i<x;i++) {
            for(int j=0;j<y;j++) {
                Status[i][j] = true;
            }
        }

        xx=startXX;
    }

    public void start() {
        if(th==null) {
            th = new Thread(this);
            th.start();
        }
    }

    public void paint(Graphics G) {
        offG.clearRect(0,0,width,height);
        offG.setColor(Color.gray);
        offG.fillRect(0,0,width,height);

        Block6.polygon(xx,yy,block,offG,pattern,ran);

        for(int i=0;i<x;i++) {
            for(int j=0;j<y;j++) {
                if(Status[i][j]==false) {
                    offG.setColor(Clr6.clr[colorStatus[i][j]]);
                    offG.fillRect(i*block,j*block,block,block);
                    offG.setColor(Color.black);
                    offG.drawRect(i*block,j*block,block,block);
                }
            }
        }

        G.drawImage(img,margin,margin,this);
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

            if(Seigyo6.downCheck(xx,yy,block,Status,colorStatus,pattern,ran)) {
                yy=startYY;
                ran = (int)(Math.random()*10)%2;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            if(Seigyo6.leftCheck(xx,yy,width,block,Status,pattern,ran)) {
                xx-=block;
            }
            repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(Seigyo6.rightCheck(xx,yy,width,block,Status,pattern,ran)) {
                xx+=block;
            }
            repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            SPEED = HIGHSPEED;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            SPEED = LOWSPEED;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            turn++;
            pattern = turn%4;
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
