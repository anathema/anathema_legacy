package net.sf.anathema.platform.svgtree.document.components;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public class PolylineSVGArrow {
  private static final double ShaftWidth = 6.5;
  private final List<Double> pointList = new ArrayList<Double>();

  public void addPoint(double x, double y) {
    pointList.add(new Point2D.Double(x, y));
  }

  public Element toXML() {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element g = new DefaultElement(group);
    g.addAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_ARROW, SVGConstants.SVG_TRUE_VALUE);
    createLine(g);
    return g;
  }

  private void createLine(Element g) {
    QName lineName = SVGCreationUtils.createSVGQName(SVGConstants.SVG_POLYLINE_TAG);
    Element line = g.addElement(lineName);
    line.addAttribute(SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
    line.addAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK);
    line.addAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(ShaftWidth));
    String pointString = ""; //$NON-NLS-1$
    for (int pointIndex = 0; pointIndex < pointList.size() - 1; pointIndex++) {
      pointString = addPoint(pointString, pointList.get(pointIndex).x, pointList.get(pointIndex).y);
    }
    pointString = createFinalLinePart(
        pointString,
        pointList.get(pointList.size() - 2),
        pointList.get(pointList.size() - 1));
    line.addAttribute(SVGConstants.SVG_POINTS_ATTRIBUTE, pointString);
    line.addAttribute(ISVGCascadeXMLConstants.ATTRIB_MARKER_START, ISVGCascadeXMLConstants.vALUE_ARROWBOTTOM_REFERENCE);
    line.addAttribute(ISVGCascadeXMLConstants.ATTRIB_MARKER_END, ISVGCascadeXMLConstants.VALUE_ARROWHEAD_REFERENCE);
  }

  private String createFinalLinePart(String pointString, Double startPoint, Double endPoint) {
    double horizontalCathetus = endPoint.x - startPoint.x;
    double verticalCathetus = endPoint.y - startPoint.y;
    double hypothenuse = Math.sqrt(Math.pow(horizontalCathetus, 2) + Math.pow(verticalCathetus, 2));
    double shortenedHypothenuse = hypothenuse - 10;
    double shortenedVerticalCathetus = (verticalCathetus / hypothenuse) * shortenedHypothenuse;
    double shortenedHorizontalCathetus = (horizontalCathetus / hypothenuse) * shortenedHypothenuse;
    double newEndPointX = startPoint.x + shortenedHorizontalCathetus;
    double newEndPointY = startPoint.y + shortenedVerticalCathetus;
    pointString = addPoint(pointString, newEndPointX, newEndPointY);
    return pointString;
  }

  private String addPoint(String pointString, double x, double y) {
    return pointString.concat(String.valueOf(x) + "," + String.valueOf(y) + ISVGCascadeXMLConstants.SPACE); //$NON-NLS-1$
  }
}