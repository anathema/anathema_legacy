package net.sf.anathema.platform.svgtree.view.batik;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGMatrix;
import org.w3c.dom.svg.SVGRect;

public class BoundsCalculator implements IBoundsCalculator {

  private final Map<SVGLocatable, Rectangle> boundsByElement = new HashMap<SVGLocatable, Rectangle>();

  public Rectangle getBounds(SVGLocatable svgElement) {
    if (boundsByElement.containsKey(svgElement)) {
      return boundsByElement.get(svgElement);
    }
    SVGMatrix screenCTM = svgElement.getScreenCTM();
    SVGRect bBox = svgElement.getBBox();
    AffineTransform paintingTransform = new AffineTransform(
        screenCTM.getA(),
        screenCTM.getB(),
        screenCTM.getC(),
        screenCTM.getD(),
        screenCTM.getE(),
        screenCTM.getF());
    Point2D startPoint = paintingTransform.transform(new Point2D.Float(bBox.getX(), bBox.getY()), null);
    float endX = bBox.getX() + bBox.getWidth();
    float endY = bBox.getY() + bBox.getHeight();
    Point2D endPoint = paintingTransform.transform(new Point2D.Float(endX, endY), null);
    Rectangle boundingRectangle = new Rectangle(
        (int) startPoint.getX(),
        (int) startPoint.getY(),
        (int) (endPoint.getX() - startPoint.getX()),
        (int) (endPoint.getY() - startPoint.getY()));
    boundsByElement.put(svgElement, boundingRectangle);
    return boundingRectangle;
  }

  public void reset() {
    boundsByElement.clear();
  }
}