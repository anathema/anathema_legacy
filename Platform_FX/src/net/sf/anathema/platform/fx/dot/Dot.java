package net.sf.anathema.platform.fx.dot;

import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Dot {

  public static final double SIZE = 18;

  /**
   * Drawing code adapted from JFXtras SimpleIndicatorSkin.
   */
  public Node create() {
    StackPane indicator = prepareContainer();
    Circle frame = createFrame(SIZE);
    Circle corpus = createCorpus(SIZE);
    addInnerShadow(corpus);
    Ellipse highlight = createHighlight(SIZE);
    indicator.getChildren().addAll(frame, corpus, highlight);
    indicator.setCache(true);
    return indicator;
  }

  private StackPane prepareContainer() {
    StackPane indicator = new StackPane();
    indicator.getStyleClass().add(DotSelectionSpinnerSkin.FILLED);
    indicator.getStyleClass().add(DotSelectionSpinnerSkin.DOTBACKGROUND);
    indicator.getChildren().clear();
    return indicator;
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
    highlight.setStyle("-fx-translate-y:" + -0.225 * size + ";");
    return highlight;
  }
}