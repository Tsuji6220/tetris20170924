package tetris;

import java.awt.Graphics;

public class Block6 {
    public static void polygon(int xx,int yy,int block,Graphics offG,int pattern,int ran) {
        switch(ran) {
            case 0:
                if(pattern==0) {
                    offG.setColor(Clr6.clr[ran]);
                    offG.fillRect(xx,yy+block,block,block);
                    offG.fillRect(xx,yy,block,block);
                    offG.fillRect(xx,yy-block,block,block);
                    offG.fillRect(xx,yy-block*2,block,block);

                    offG.setColor(Clr6.clr[7]);
                    offG.drawRect(xx,yy+block,block,block);
                    offG.drawRect(xx,yy,block,block);
                    offG.drawRect(xx,yy-block,block,block);
                    offG.drawRect(xx,yy-block*2,block,block);
                }

                if(pattern==1) {
                    offG.setColor(Clr6.clr[ran]);
                    offG.fillRect(xx-block,yy,block,block);
                    offG.fillRect(xx,yy,block,block);
                    offG.fillRect(xx+block,yy,block,block);
                    offG.fillRect(xx+block*2,yy,block,block);

                    offG.setColor(Clr6.clr[7]);
                    offG.drawRect(xx-block,yy,block,block);
                    offG.drawRect(xx,yy,block,block);
                    offG.drawRect(xx+block,yy,block,block);
                    offG.drawRect(xx+block*2,yy,block,block);
                }

                if(pattern==2) {
                    offG.setColor(Clr6.clr[ran]);
                    offG.fillRect(xx,yy-block,block,block);
                    offG.fillRect(xx,yy,block,block);
                    offG.fillRect(xx,yy+block,block,block);
                    offG.fillRect(xx,yy+block*2,block,block);

                    offG.setColor(Clr6.clr[7]);
                    offG.drawRect(xx,yy-block,block,block);
                    offG.drawRect(xx,yy,block,block);
                    offG.drawRect(xx,yy+block,block,block);
                    offG.drawRect(xx,yy+block*2,block,block);
                }

                if(pattern==3) {
                    offG.setColor(Clr6.clr[ran]);
                    offG.fillRect(xx+block,yy,block,block);
                    offG.fillRect(xx,yy,block,block);
                    offG.fillRect(xx-block,yy,block,block);
                    offG.fillRect(xx-block*2,yy,block,block);

                    offG.setColor(Clr6.clr[7]);
                    offG.drawRect(xx+block,yy,block,block);
                    offG.drawRect(xx,yy,block,block);
                    offG.drawRect(xx-block,yy,block,block);
                    offG.drawRect(xx-block*2,yy,block,block);
                }
                break;

            case 1:
                offG.setColor(Clr6.clr[ran]);
                offG.fillRect(xx,yy,block,block);
                offG.fillRect(xx+block,yy,block,block);
                offG.fillRect(xx,yy-block,block,block);
                offG.fillRect(xx+block,yy-block,block,block);

                offG.setColor(Clr6.clr[7]);
                offG.drawRect(xx,yy,block,block);
                offG.drawRect(xx+block,yy,block,block);
                offG.drawRect(xx,yy-block,block,block);
                offG.drawRect(xx+block,yy-block,block,block);
                break;
        }
    }
}