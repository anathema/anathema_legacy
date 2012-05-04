package net.sf.anathema.platform.tree.view.draw;

public class CharmPolygon extends FilledPolygon {
  public CharmPolygon(int x, int y) {
    addPoint(0, 8);
    addPoint(30, 8);
    addPoint(25, 0);
    addPoint(150, 0);
    addPoint(145, 8);
    addPoint(175, 8);
    addPoint(175, 79);
    addPoint(145, 79);
    addPoint(150, 87);
    addPoint(25, 87);
    addPoint(30, 79);
    addPoint(0, 79);
    addPoint(0, 8);
    moveBy(x, y);
    moveBy(2, 2); //accommodate for stroke width
  }
}