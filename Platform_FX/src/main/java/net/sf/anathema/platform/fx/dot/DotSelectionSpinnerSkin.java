package net.sf.anathema.platform.fx.dot;

import com.sun.javafx.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfxtras.scene.control.ListSpinner;

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
public class DotSelectionSpinnerSkin<T> extends SkinBase<ListSpinner<T>> {

  private static final String INVISIBLECONTAINER = "invisiblecontainer";
  private static final String MAXIMUM_PROPERTY = "MAX";

  private StackPane outerContainer = new StackPane();
  private Pane dotContainer = new HBox();
  private List<Dot> dots = new ArrayList<>();
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
      double overlayWidth = Utils.clamp(0, dotContainer.sceneToLocal(location).getX(), dotContainer.getWidth() - 2);
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
    super(control);
    control.getStyleClass().add(INVISIBLECONTAINER);
    createOuterContainer();
    createOverlay();
    createButtons();
    updateRating((Integer) getSkinnable().getValue());
    control.valueProperty().addListener(new ChangeListener<T>() {
      @Override
      public void changed(ObservableValue<? extends T> observableValue, T t, T t2) {
        updateRating();
      }
    });
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

  private void createButtons() {
    dotContainer.setPadding(new Insets(1, 0, 0, 0));
    dotContainer.addEventHandler(MOUSE_CLICKED, updateRating);
    dotContainer.addEventHandler(MOUSE_DRAGGED, updateRating);
    dotContainer.addEventHandler(MOUSE_DRAGGED, updateOverlay);
    dotContainer.addEventHandler(MOUSE_RELEASED, hideOverlay);
    for (int index = 0; index < getMaximumValue(); index++) {
      if (index > 0 && index % 5 == 0) {
        Rectangle blockSeparator = new Rectangle(Dot.SIZE / 2, Dot.SIZE);
        blockSeparator.getStyleClass().add(INVISIBLECONTAINER);
        dotContainer.getChildren().add(blockSeparator);
      }
      Dot dot = new Dot();
      Node backgroundNode = dot.create();
      dotContainer.getChildren().add(backgroundNode);
      dots.add(dot);
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
    for (int index = 0; index < max; index++) {
      Dot dot = dots.get(index);
      if (index < rating) {
        dot.fill();
      } else {
        dot.drain();
      }
    }
  }

  @SuppressWarnings("unchecked")
  private int getMaximumValue() {
    ObservableList<Integer> items = (ObservableList<Integer>) getSkinnable().getItems();
    return Collections.max(items);
  }
}