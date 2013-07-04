package net.sf.anathema.platform.fx.dot;

import com.sun.javafx.Utils;
import com.sun.javafx.scene.control.skin.SkinBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jfxtras.labs.internal.scene.control.behavior.ListSpinnerBehavior;
import jfxtras.labs.scene.control.ListSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

/**
 * A heavily modified version of Jonathan Giles's RatingSkin from the ControlsFX project
 */
@SuppressWarnings("UnusedDeclaration")
public class DotSelectionSpinnerSkin<T> extends SkinBase<ListSpinner<T>, ListSpinnerBehavior<T>> {

  public static final String FILLED = "filled";
  public static final String EMPTY = "empty";
  public static final String DOTBACKGROUND = "dotbackground";
  private static final String INVISIBLECONTAINER = "invisiblecontainer";
  private static final String RATING_PROPERTY = "RATING";
  private static final String MAXIMUM_PROPERTY = "MAX";

  private StackPane outerContainer = new StackPane();
  private Pane dotContainer = new HBox();
  private Rectangle overlay = new Rectangle();

  private double rating = -1;
  private final EventHandler<MouseEvent> updateRating = new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
      Point2D location = new Point2D(event.getSceneX(), event.getSceneY());
      updateRating(Double.valueOf(calculateRating(location)).intValue());
    }
  };
  private final EventHandler<MouseEvent> updateOverlay = new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
      Point2D location = new Point2D(event.getSceneX(), event.getSceneY());
      double overlayWidth = Utils.clamp(0, dotContainer.sceneToLocal(location).getX(), dotContainer.getWidth()-2);
      overlay.setVisible(true);
      overlay.setWidth(overlayWidth);
      overlay.setHeight(Dot.SIZE);
    }
  };
  private final EventHandler<MouseEvent> hideOverlay = new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
      overlay.setVisible(false);
    }
  };

  public DotSelectionSpinnerSkin(ListSpinner<T> control) {
    super(control, new ListSpinnerBehavior<>(control));
    getStyleClass().add(INVISIBLECONTAINER);
    createOuterContainer();
    createButtons();
    createOverlay();
    updateRating((Integer) getSkinnable().getValue());
    registerChangeListener(control.valueProperty(), RATING_PROPERTY);
  }

  private void createOuterContainer() {
    outerContainer.setAlignment(Pos.CENTER_LEFT);
    getChildren().setAll(outerContainer);
  }

  private void createOverlay() {
    overlay.setFill(new Color(0, 0, 0, 0.1));
    overlay.setStroke(Color.BLACK);
    overlay.setStrokeWidth(1);
    outerContainer.getChildren().add(overlay);
  }


  @Override
  protected void handleControlPropertyChanged(String p) {
    super.handleControlPropertyChanged(p);
    if (p.equals(RATING_PROPERTY)) {
      updateRating();
    }
  }

  private void createButtons() {
    dotContainer.setPadding(new Insets(1,0,0,0));
    dotContainer.addEventHandler(MOUSE_CLICKED, updateRating);
    dotContainer.addEventHandler(MOUSE_DRAGGED, updateRating);
    dotContainer.addEventHandler(MOUSE_DRAGGED, updateOverlay);
    dotContainer.addEventHandler(MOUSE_RELEASED, hideOverlay);
    for (int index = 0; index < getMaximumValue(); index++) {
      Node backgroundNode = new Dot().create();
      dotContainer.getChildren().add(backgroundNode);
    }
    outerContainer.getChildren().add(dotContainer);
  }

  private double calculateRating(Point2D location) {
    Point2D pointInElement = dotContainer.sceneToLocal(location);
    double x = pointInElement.getX();
    int max = getMaximumValue();
    double width = getSkinnable().getWidth();
    double newRating = (x / width) * max;
    if (newRating < 0.4) {
      newRating = 0;
    } else {
      newRating = Utils.clamp(1, Math.ceil(newRating), getMaximumValue());
    }
    return newRating;
  }

  private void updateRating() {
    updateRating(getSkinnable().getIndex());
  }

  @SuppressWarnings("unchecked")
  private void updateRating(Integer newRating) {
    if (newRating == rating) {
      return;
    }
    rating = Utils.clamp(0, newRating, getMaximumValue());
    if (!getSkinnable().valueProperty().isBound()) {
      getSkinnable().setValue((T) (Integer) Double.valueOf(rating).intValue());
    }
    int max = getMaximumValue();
    List<Node> buttons = new ArrayList<>(dotContainer.getChildren());
    for (int index = 0; index < max; index++) {
      Node button = buttons.get(index);
      List<String> styleClass = button.getStyleClass();
      boolean isFilled = styleClass.contains(FILLED);
      if (index < rating) {
        if (!isFilled) {
          styleClass.remove(EMPTY);
          styleClass.add(FILLED);
          animateFill(button);
        }
      } else if (isFilled) {
        styleClass.remove(FILLED);
        styleClass.add(EMPTY);
        animateDrain(button);
      }
    }
  }

  private void animateDrain(Node button) {
    Bloom bloom = new Bloom(0.0);
    button.setEffect(bloom);
    Timeline timeline = new Timeline();
    KeyValue keyValue = new KeyValue(bloom.thresholdProperty(), 1.0);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(50), keyValue);
    KeyValue keyValue2 = new KeyValue(bloom.thresholdProperty(), 0.0);
    KeyFrame keyFrame1 = new KeyFrame(Duration.millis(500), keyValue2);
    timeline.getKeyFrames().addAll(keyFrame, keyFrame1);
    timeline.play();
  }

  private void animateFill(Node button) {
    Bloom bloom = new Bloom(0.0);
    button.setEffect(bloom);
    Timeline timeline = new Timeline();
    KeyValue keyValue = new KeyValue(bloom.thresholdProperty(), 1.0);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
    KeyValue keyValue2 = new KeyValue(bloom.thresholdProperty(), 0.0);
    KeyFrame keyFrame1 = new KeyFrame(Duration.millis(50), keyValue2);
    timeline.getKeyFrames().addAll(keyFrame, keyFrame1);
    timeline.play();
  }

  @SuppressWarnings("unchecked")
  private int getMaximumValue() {
    ObservableList<Integer> items = (ObservableList<Integer>) getSkinnable().getItems();
    return Collections.max(items);
  }
}