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

  private double getHeight(Double point1, Double point2) {
    return point2.y - point1.y;
  }

  private double getWidth(Double point1, Double point2) {
    return point2.x - point1.x;
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
    double ratio = Math.abs(getWidth(startPoint, endPoint) / getHeight(startPoint, endPoint));
    double halfArrowWidth = PolylineSVGArrow.ShaftWidth / 2.0;
    if (endPoint.x > startPoint.x) {
      if (ratio > 1) {
        pointString = addPoint(pointString, endPoint.x - halfArrowWidth, endPoint.y - ratio / halfArrowWidth);
      }
      else {
        pointString = addPoint(pointString, endPoint.x - ratio / halfArrowWidth, endPoint.y - halfArrowWidth);
      }
    }
    else if (startPoint.x > endPoint.x) {
      if (ratio > 1) {
        pointString = addPoint(pointString, endPoint.x + halfArrowWidth, endPoint.y - ratio / halfArrowWidth);
      }
      else {
        pointString = addPoint(pointString, endPoint.x + ratio / halfArrowWidth, endPoint.y - halfArrowWidth);
      }
    }
    else {
      pointString = addPoint(pointString, endPoint.x, endPoint.y - halfArrowWidth);
    }
    return pointString;
  }

  private String addPoint(String pointString, double x, double y) {
    return pointString.concat(String.valueOf(x) + "," + String.valueOf(y) + ISVGCascadeXMLConstants.SPACE); //$NON-NLS-1$
  }
}