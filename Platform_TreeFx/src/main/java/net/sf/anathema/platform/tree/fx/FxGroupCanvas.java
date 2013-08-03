package net.sf.anathema.platform.tree.fx;

import com.sun.javafx.scene.control.skin.FxFontUtils;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Transform;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.view.draw.Canvas;

import static net.sf.anathema.platform.tree.fx.FxColorUtils.toFxColor;

public class FxGroupCanvas implements Canvas {

  private Group group;
  private Width strokeWidth = new Width(0);
  private RGBColor strokeAndFill;
  private Font textFont;

  public FxGroupCanvas(Group group) {
    this.group = group;
  }

  @Override
  public void setStrokeWidth(Width width) {
    strokeWidth = width;
  }

  @Override
  public void setColor(RGBColor color) {
    strokeAndFill = color;
  }

  @Override
  public void drawPolyline(Iterable<Coordinate> coordinates) {
    Polyline polyline = new Polyline();
    configureShapeWithoutFill(polyline);
    for (Coordinate coordinate : coordinates) {
      polyline.getPoints().addAll((double) coordinate.x, (double) coordinate.y);
    }
    group.getChildren().add(polyline);
  }

  @Override
  public void fill(TransformedShape shape) {
    Shape fxShape = FxTransformer.convert(shape.shape);
    Transform fxTransform = FxTransformer.convert(shape.transform);
    configureShapeWithoutStroke(fxShape);
    fxShape.getTransforms().add(fxTransform);
    group.getChildren().add(fxShape);
  }

  @Override
  public void draw(AgnosticShape shape) {
    Shape fxShape = FxTransformer.convert(shape);
    configureShapeWithoutFill(fxShape);
    group.getChildren().add(fxShape);
  }

  @Override
  public Area measureText(String text) {
    double width = FxFontUtils.calculateStringWidth(textFont, text);
    double height = FxFontUtils.calculateStringHeight(textFont, text);
    return new Area(width, height);
  }

  @Override
  public void drawString(String text, Coordinate coordinate) {
    Text label = new Text(text);
    label.setX(coordinate.x);
    label.setY(coordinate.y);
    label.setFont(textFont);
    group.getChildren().add(label);
  }

  @Override
  public void setFontStyle(FontStyle style, int textSize) {
    FontWeight weight;
    if (style == FontStyle.Bold) {
      weight = FontWeight.BOLD;
    } else {
      weight = FontWeight.NORMAL;
    }
    this.textFont = Font.font("SansSerif", weight, textSize);
  }

  @Override
  public Rectangle calculateBounds(AgnosticShape shape) {
    Bounds bounds = FxTransformer.convert(shape).getBoundsInParent();
    Coordinate origin = new Coordinate(bounds.getMinX(), bounds.getMinY());
    Area area = new Area(bounds.getWidth(), bounds.getHeight());
    return new Rectangle(origin, area);
  }

  private void configureShapeWithoutStroke(Shape fxShape) {
    configureShape(fxShape);
    fxShape.setStroke(null);
  }

  private void configureShapeWithoutFill(Shape fxShape) {
    configureShape(fxShape);
    fxShape.setFill(null);
  }

  private void configureShape(Shape shape) {
    Paint paint = toFxColor(strokeAndFill);
    shape.setStroke(paint);
    shape.setStrokeWidth(strokeWidth.width);
    shape.setFill(paint);
  }
}
