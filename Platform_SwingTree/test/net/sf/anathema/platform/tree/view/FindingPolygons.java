package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.CharmPolygon;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.interaction.CursorChanger;
import net.sf.anathema.platform.tree.view.interaction.LeftClickToggler;
import net.sf.anathema.platform.tree.view.interaction.RightClickResetter;
import net.sf.anathema.platform.tree.view.interaction.WheelScaler;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FindingPolygons {
    public static void main(String[] args) {
        PolygonPanel polygonPanel = new PolygonPanel();
        polygonPanel.addPolygon(new CharmPolygon(15, 85));
        initListening(polygonPanel);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(polygonPanel);
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
    }

    private static void initListening(PolygonPanel polygonPanel) {
        polygonPanel.addMouseListener(new LeftClickToggler(polygonPanel));
        polygonPanel.addMouseListener(new RightClickResetter(polygonPanel));
        polygonPanel.addMouseWheelListener(new WheelScaler(polygonPanel));
        polygonPanel.addMouseMotionListener(new CursorChanger(polygonPanel));
    }
}