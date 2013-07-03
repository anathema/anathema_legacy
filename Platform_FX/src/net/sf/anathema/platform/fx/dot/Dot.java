package net.sf.anathema.platform.fx.dot;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Dot {

  public static final double SIZE = 18;

  /**Drawing code adapted from JFXtras SimpleIndicatorSkin.*/
  public Node create() {
    Group indicator = prepareContainer();
    Shape outerBounds = createBounds(SIZE);
    indicator.getChildren().add(outerBounds);
    Circle frame = createFrame(SIZE);
    Circle corpus = createCorpus(SIZE);
    addInnerShadow(corpus);
    Ellipse highlight = createHighlight(SIZE);
    indicator.getChildren().addAll(frame, corpus, highlight);
    indicator.setCache(true);
    return indicator;
  }

  private Group prepareContainer() {
    Group indicator = new Group();
    indicator.getStyleClass().add(DotSelectionSpinnerSkin.FILLED);
    indicator.getChildren().clear();
    return indicator;
  }

  private Shape createBounds(double size) {
    Shape outerBounds = new Rectangle(0, 0, size, size);
    outerBounds.setOpacity(0.0);
    return outerBounds;
  }

  private Circle createFrame(double size) {
    Circle frame = new Circle(0.5 * size, 0.5 * size, 0.45 * size);
    frame.getStyleClass().add("indicator-inner-frame-fill");
    return frame;
  }

  private Circle createCorpus(double size) {
    Circle corpus = new Circle(0.5 * size, 0.5 * size, 0.43 * size);
    corpus.getStyleClass().add("indicator-main-fill");
    return corpus;
  }

  private void addInnerShadow(Circle corpus) {
    InnerShadow innerShadow = new InnerShadow();
    innerShadow.setWidth(0.2880 * corpus.getLayoutBounds().getWidth());
    innerShadow.setHeight(0.2880 * corpus.getLayoutBounds().getHeight());
    innerShadow.setOffsetX(0.0);
    innerShadow.setOffsetY(0.0);
    innerShadow.setRadius(0.2880 * corpus.getLayoutBounds().getWidth());
    innerShadow.setColor(Color.BLACK);
    innerShadow.setBlurType(BlurType.GAUSSIAN);
    corpus.setEffect(innerShadow);
  }

  private Ellipse createHighlight(double size) {
    Ellipse highlight = new Ellipse(0.504 * size, 0.294 * size, 0.26 * size, 0.15 * size);
    highlight.getStyleClass().add("indicator-highlight-fill");
    return highlight;
  }
}