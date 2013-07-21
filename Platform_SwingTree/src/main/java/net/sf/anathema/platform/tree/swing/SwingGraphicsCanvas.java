package net.sf.anathema.platform.tree.swing;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.lib.gui.SwingFontStyleMapping;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.view.draw.Canvas;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.List;

import static net.sf.anathema.lib.gui.swing.ColorUtilities.toAwtColor;

public class SwingGraphicsCanvas implements Canvas {

  private Graphics2D graphics;

  public SwingGraphicsCanvas(Graphics2D graphics) {
    this.graphics = graphics;
  }

  @Override
  public void setStrokeWidth(Width width) {
    graphics.setStroke(new BasicStroke(width.width));
  }

  @Override
  public void setColor(RGBColor color) {
    graphics.setColor(toAwtColor(color));
  }

  @Override
  public void drawPolyline(Iterable<Coordinate> coordinates) {
    int[] xCoordinates = collectXCoordinates(coordinates);
    int[] yCoordinates = collectYCoordinates(coordinates);
    graphics.drawPolyline(xCoordinates, yCoordinates, xCoordinates.length);
  }

  @Override
  public void fill(TransformedShape shape) {
    AffineTransform swingTransform = SwingTransformer.convert(shape.transform);
    Shape swingShape = SwingTransformer.convert(shape.shape);
    Shape transformedShape = swingTransform.createTransformedShape(swingShape);
    graphics.fill(transformedShape);
  }

  @Override
  public void draw(AgnosticShape shape) {
    Shape swingShape = SwingTransformer.convert(shape);
    graphics.draw(swingShape);
  }

  @Override
  public Area measureText(String text) {
    FontMetrics metrics = graphics.getFontMetrics();
    return new Area(metrics.stringWidth(text), metrics.getHeight());
  }

  @Override
  public void drawString(String text, Coordinate coordinate) {
    graphics.drawString(text, coordinate.x, coordinate.y);
  }

  @SuppressWarnings("MagicConstant")
  @Override
  public void setFontStyle(FontStyle style, int textSize) {
    int swingStyle = SwingFontStyleMapping.map(style);
    Font textFont = new Font("SansSerif", swingStyle, textSize);
    graphics.setFont(textFont);
  }

  @Override
  public Rectangle calculateBounds(AgnosticShape shape) {
    java.awt.Rectangle bounds = SwingTransformer.convert(shape).getBounds();
    Area area = new Area(bounds.width, bounds.height);
    Coordinate origin = new Coordinate(bounds.getX(), bounds.getY());
    return new Rectangle(origin, area);
  }


  private int[] collectXCoordinates(Iterable<Coordinate> coordinates) {
    List<Integer> xCoordinates = Lists.newArrayList();
    for (Coordinate coordinate : coordinates) {
      xCoordinates.add(coordinate.x);
    }
    return ArrayUtils.toPrimitive(xCoordinates.toArray(new Integer[xCoordinates.size()]));
  }

  private int[] collectYCoordinates(Iterable<Coordinate> coordinates) {
    List<Integer> yCoordinates = Lists.newArrayList();
    for (Coordinate coordinate : coordinates) {
      yCoordinates.add(coordinate.y);
    }
    return ArrayUtils.toPrimitive(yCoordinates.toArray(new Integer[yCoordinates.size()]));
  }

  @Override
  public int hashCode() {
    return graphics.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
}