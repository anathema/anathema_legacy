package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.interaction.CursorChanger;
import net.sf.anathema.platform.tree.view.visualizer.CharmPolygon;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CharmGraphDemo {
  public static void main(String[] args) {
    PolygonPanel polygonPanel = new PolygonPanel();
    CharmPolygon polygon = new CharmPolygon(15, 85);
    polygon.setText("Something Rotten Attitude");
    polygonPanel.add(polygon);
    addArrow(polygonPanel);
    initListening(polygonPanel);
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.add(polygonPanel);
    f.setSize(400, 400);
    f.setLocation(200, 200);
    f.setVisible(true);
  }

  private static void addArrow(PolygonPanel polygonPanel) {
    FlexibleArrow flexibleArrow = new FlexibleArrow();
    flexibleArrow.addPoint(10,10);
    flexibleArrow.addPoint(17,43);
    flexibleArrow.addPoint(200,80);
    polygonPanel.add(flexibleArrow);
  }

  private static void initListening(PolygonPanel polygonPanel) {
    new InteractionTreeListening(polygonPanel).initialize();
    polygonPanel.addMouseMotionListener(new CursorChanger(polygonPanel));
  }
}