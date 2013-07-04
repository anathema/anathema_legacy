package net.sf.anathema.platform.fx.dot;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.util.List;

public class Dot {
  private static final String FILLED = "filled";
  private static final String EMPTY = "empty";
  private static final String DOTBACKGROUND = "dotbackground";

  public static final double SIZE = 18;
  private StackPane indicator;

  /**
   * Drawing code adapted from JFXtras SimpleIndicatorSkin.
   */
  public Node create() {
    this.indicator = prepareContainer();
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
    indicator.getStyleClass().add(FILLED);
    indicator.getStyleClass().add(DOTBACKGROUND);
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

  public List<String> getStyleClass() {
    return indicator.getStyleClass();
  }

  public void fill() {
    List<String> styleClass = getStyleClass();
    boolean isFilled = styleClass.contains(FILLED);
    if (!isFilled) {
      styleClass.remove(EMPTY);
      styleClass.add(FILLED);
      animateFill();
    }
  }

  public void drain() {
    List<String> styleClass = getStyleClass();
    boolean isFilled = styleClass.contains(FILLED);
    if (isFilled) {
      styleClass.remove(FILLED);
      styleClass.add(EMPTY);
      animateDrain();
    }
  }

  private void animateFill() {
    animateChange(500, 50);
  }

  private void animateDrain() {
    animateChange(50, 500);
  }

  private void animateChange(int upTime, int downTime) {
    Bloom bloom = new Bloom(0.0);
    indicator.setEffect(bloom);
    Timeline timeline = new Timeline();
    KeyValue keyValue = new KeyValue(bloom.thresholdProperty(), 1.0);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(upTime), keyValue);
    KeyValue keyValue2 = new KeyValue(bloom.thresholdProperty(), 0.0);
    KeyFrame keyFrame1 = new KeyFrame(Duration.millis(downTime), keyValue2);
    timeline.getKeyFrames().addAll(keyFrame, keyFrame1);
    timeline.play();
  }
}