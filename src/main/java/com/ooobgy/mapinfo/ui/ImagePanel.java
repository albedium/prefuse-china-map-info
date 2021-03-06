package com.ooobgy.mapinfo.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 带有背景图片的JPanel<br>
 * <b>created:</b> 2012-3-10
 * @author 周晓龙  frogcherry@gmail.com
 */
public class ImagePanel extends JPanel {

    private static final long serialVersionUID = -2598338921791772548L;

    public ImagePanel() {
        this.setOpaque(false);
    }
    
    private Image image;

    @Override
    public void update(Graphics g) {
        g.drawImage(image, 0, 0, null);
        super.update(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
        super.paintComponent(g);
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    
}
