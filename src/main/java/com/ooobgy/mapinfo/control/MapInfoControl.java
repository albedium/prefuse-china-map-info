package com.ooobgy.mapinfo.control;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.SwingUtilities;

import prefuse.controls.ControlAdapter;

import com.ooobgy.comm.util.ValidationUtility;
import com.ooobgy.mapinfo.color.ColorType;
import com.ooobgy.mapinfo.color.ColorUtil;
import com.ooobgy.mapinfo.exception.IllDataException;
import com.ooobgy.mapinfo.pojo.Province;

/**
 * 操作控制器
 * <b>created:</b> 2012-3-8
 * @author 周晓龙  frogcherry@gmail.com
 */
public class MapInfoControl extends ControlAdapter {
    private Point prePt;
    private Point originLoc;
    private Rebounder rebounder;
    private Robot robot;
    private Map<ColorType, Set<Province>> provincesMap;
    private Province preTouchedProv;

    public MapInfoControl(Map<ColorType, Set<Province>> provincesMap) throws AWTException {
        super();
        robot = new Robot();
        this.provincesMap = provincesMap;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        prePt = (Point) e.getLocationOnScreen().clone();
        originLoc = (Point) e.getComponent().getLocation().clone();
        if (rebounder != null) {
            rebounder.cancel(true);
        }
        //SwingUtilities.convertPointToScreen(prePt, e.getComponent());
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        Point pt = (Point) e.getLocationOnScreen().clone();
        Color color = robot.getPixelColor(pt.x, pt.y);
        ColorType normColor = ColorUtil.matchColor(color);
        Point touchPt = (Point) e.getPoint().clone();
        Province province = matchProvince(normColor, touchPt);
        if (province != null && preTouchedProv.getId() != province.getId()) {
            System.out.println(province.getName());
            //TODO: print the info.
        }
    }

    private Province matchProvince(ColorType normColor, Point touchPt) {
        Set<Province> provinces = provincesMap.get(normColor);
        if (provinces == null) {
            throw new IllDataException("Illegal data or map image!");
        }
        
        for (Province province : provinces) {
            if (ValidationUtility.isInRange(touchPt.x, province.getMinX(), province.getMaxX(), true, true)
                    && ValidationUtility.isInRange(touchPt.y, province.getMinY(), province.getMaxY(), true, true)) {
                return province;
            }
        }
        
        return null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        if (SwingUtilities.isLeftMouseButton(e)) {
            Component c = e.getComponent();
            Point pt = (Point) e.getLocationOnScreen().clone();
//            SwingUtilities.convertPointToScreen(pt, c);
            int dx = pt.x - prePt.x;
            int dy = pt.y - prePt.y;
//            System.out.println(dx);
            // System.out.println(dx + "#" + dy);
            // Point dispLoc = MapInfoDisplay.this.getLocat ion();
            c.setLocation(originLoc.x + dx, originLoc.y + dy);
//            c.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        RebounderOld rebounder = new RebounderOld(e.getComponent(), new Point(0, 0));
//        rebounder.run();
        rebounder = new Rebounder(e.getComponent(), new Point(0, 0));
        rebounder.execute();
    }

}

