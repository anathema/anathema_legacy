package net.sf.anathema.platform.tree.view.draw;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.lib.gui.SwingFontStyleMapping;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.view.transform.SwingTransformer;
import org.apache.commons.lang3.ArrayUtils;

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
  public FontMetrics getFontMetrics() {
    return graphics.getFontMetrics(graphics.getFont());
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
}