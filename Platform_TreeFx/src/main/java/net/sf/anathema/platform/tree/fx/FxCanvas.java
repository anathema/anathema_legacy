package net.sf.anathema.platform.tree.fx;

import com.google.common.collect.Lists;
import com.sun.javafx.scene.control.skin.FxFontUtils;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.view.draw.Canvas;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

import static net.sf.anathema.platform.tree.fx.FxColorUtils.toFxColor;

public class FxCanvas implements Canvas {

  private GraphicsContext graphics;

  public FxCanvas(GraphicsContext graphics) {
    this.graphics = graphics;
  }

  @Override
  public void setStrokeWidth(Width width) {
    graphics.setLineWidth(width.width);
  }

  @Override
  public void setColor(RGBColor color) {
    Paint paint = toFxColor(color);
    graphics.setStroke(paint);
    graphics.setFill(paint);
  }

  @Override
  public void drawPolyline(Iterable<Coordinate> coordinates) {
    double[] xCoordinates = collectXCoordinates(coordinates);
    double[] yCoordinates = collectYCoordinates(coordinates);
    graphics.strokePolyline(xCoordinates, yCoordinates, xCoordinates.length);
  }

  @Override
  public void fill(TransformedShape shape) {
    //TODO: transform shape and fill it
  }

  @Override
  public void draw(AgnosticShape shape) {
    //TODO: draw the shape as a line
  }

  @Override
  public Area measureText(String text) {
    double width = FxFontUtils.calculateStringWidth(graphics.getFont(), text);
    double height = FxFontUtils.calculateStringHeight(graphics.getFont(), text);
    return new Area(width, height);
  }

  @Override
  public void drawString(String text, Coordinate coordinate) {
    graphics.strokeText(text, coordinate.x, coordinate.y);
  }

  @Override
  public void setFontStyle(FontStyle style, int textSize) {
    FontWeight weight;
    if (style == FontStyle.Bold) {
      weight = FontWeight.BOLD;
    } else {
      weight = FontWeight.NORMAL;
    }
    Font textFont = Font.font("SansSerif", weight, textSize);
    graphics.setFont(textFont);
  }

  @Override
  public Rectangle calculateBounds(AgnosticShape shape) {
    Bounds bounds = FxTransformer.convert(shape).getBoundsInParent();
    Coordinate origin = new Coordinate(bounds.getMinX(), bounds.getMinY());
    Area area = new Area(bounds.getWidth(), bounds.getHeight());
    return new Rectangle(origin, area);
  }

  private double[] collectXCoordinates(Iterable<Coordinate> coordinates) {
    List<Double> xCoordinates = Lists.newArrayList();
    for (Coordinate coordinate : coordinates) {
      xCoordinates.add((double) coordinate.x);
    }
    return ArrayUtils.toPrimitive(xCoordinates.toArray(new Double[xCoordinates.size()]));
  }

  private double[] collectYCoordinates(Iterable<Coordinate> coordinates) {
    List<Double> yCoordinates = Lists.newArrayList();
    for (Coordinate coordinate : coordinates) {
      yCoordinates.add((double) coordinate.y);
    }
    return ArrayUtils.toPrimitive(yCoordinates.toArray(new Double[yCoordinates.size()]));
  }
}
