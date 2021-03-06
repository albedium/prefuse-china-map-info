package com.ooobgy.mapinfo.control;

import java.awt.Component;
import java.awt.Point;
import java.util.List;

import javax.swing.SwingWorker;

import com.ooobgy.mapinfo.conf.Config;
import com.ooobgy.mapinfo.consts.ConfConsts;

/**
 * 控制指定组件进行滑动动画
 * <b>created:</b> 2012-3-8
 * @author 周晓龙  frogcherry@gmail.com
 */
public class Slider extends SwingWorker<Point, Point> {
    private static final int ANIM_TIME = Config.getInt(ConfConsts.SLIDE_TIME);// animation (ms)
    public static final int ANIM_FRM = Config.getInt(ConfConsts.SLIDE_FRAMES);// 动画帧数
    private Component actor;
    private Point destination;

    public Slider(Component c, Point destination) {
        this.actor = c;
        this.destination = destination;
    }

    @Override
    protected Point doInBackground() throws InterruptedException {
        Point originLoc = (Point) actor.getLocation().clone();
        int dx = (originLoc.x - destination.x) / ANIM_FRM;
        int dy = (originLoc.y - destination.y) / ANIM_FRM;
        // System.out.println(dx + "#" + dy);
        final int sleepTime = ANIM_TIME / ANIM_FRM;
        int presentX = originLoc.x;
        int presentY = originLoc.y;

        for (int i = 0; i < ANIM_FRM; i++) {
            Thread.sleep(sleepTime);
            presentX -= dx;
            presentY -= dy;
            publish(new Point(presentX, presentY));
        }
        
        return destination;
    }

    @Override
    protected void process(List<Point> chunks) {
        if (isCancelled()) {
            return;
        }
        for (Point point : chunks) {
            actor.setLocation(point);
        }
    }
    
    @Override
    protected void done() {
        actor.setLocation(destination);
    }
}
