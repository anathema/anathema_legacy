package net.sf.anathema.platform.tree.fx;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.gui.StatefulTooltip;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.tooltip.StatefulFxTooltip;
import net.sf.anathema.platform.tree.display.DisplayPolygonPanel;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.MouseBorderClosure;
import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.Closure;
import net.sf.anathema.platform.tree.view.interaction.ElementContainer;
import net.sf.anathema.platform.tree.view.interaction.Executor;
import net.sf.anathema.platform.tree.view.interaction.MetaKey;
import net.sf.anathema.platform.tree.view.interaction.MouseButton;
import net.sf.anathema.platform.tree.view.interaction.MouseClickClosure;
import net.sf.anathema.platform.tree.view.interaction.MouseMotionClosure;
import net.sf.anathema.platform.tree.view.interaction.MousePressClosure;
import net.sf.anathema.platform.tree.view.interaction.MouseWheelClosure;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.Cursor.DEFAULT;
import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import static net.sf.anathema.platform.tree.fx.FxColorUtils.toFxColor;
import static net.sf.anathema.platform.tree.fx.FxTransformer.convert;
import static net.sf.anathema.platform.tree.view.interaction.MouseButton.Other;
import static net.sf.anathema.platform.tree.view.interaction.MouseButton.Primary;
import static net.sf.anathema.platform.tree.view.interaction.MouseButton.Secondary;

public class FxPolygonPanel implements DisplayPolygonPanel {
  private final ElementContainer container = new ElementContainer();
  private final List<FxSpecialTrigger> specialControls = new ArrayList<>();
  private final StackPane content = new StackPane();
  private final Rectangle background = new Rectangle();
  private final Group canvas = new Group();
  private final Rectangle glasspane = new Rectangle();
  private Tooltip tooltip;
  private AgnosticTransform transform = new AgnosticTransform();

  public FxPolygonPanel() {
    Rectangle clippingRectangle = new Rectangle(0, 0);
    sizeLikeContentPane(background);
    sizeLikeContentPane(glasspane);
    sizeLikeContentPane(clippingRectangle);
    glasspane.setFill(Color.TRANSPARENT);
    content.setClip(clippingRectangle);
    canvas.setManaged(false);
    content.getChildren().addAll(background, canvas, glasspane);
    setBackground(RGBColor.White);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        tooltip = new Tooltip();
      }
    });
  }

  @Override
  public void refresh() {
    canvas.getChildren().clear();
    canvas.getTransforms().clear();
    FxGroupCanvas fxGroupCanvas = new FxGroupCanvas(canvas);
    Transform fxTransform = convert(transform);
    canvas.getTransforms().add(fxTransform);
    for (GraphicsElement graphicsElement : container) {
      graphicsElement.paint(fxGroupCanvas);
    }
    for (FxSpecialTrigger specialControl : specialControls) {
      specialControl.addTo(fxGroupCanvas);
    }
  }

  @Override
  public SpecialControlTrigger addSpecialControl() {
    final FxSpecialTrigger specialControl = new FxSpecialTrigger();
    specialControls.add(specialControl);
    glasspane.addEventHandler(MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        MouseButton button = determineMouseButton(event);
        Coordinate glasspaneCoordinate = determineCoordinate(event);
        Coordinate coordinate = determineCoordinateInCanvas(glasspaneCoordinate);
        if (specialControl.contains(coordinate) && button == MouseButton.Primary) {
          specialControl.toggle();
        }
      }
    });
    return specialControl;
  }

  @Override
  public void add(InteractiveGraphicsElement element) {
    container.add(element);
  }

  @Override
  public void add(GraphicsElement element) {
    container.add(element);
  }

  @Override
  public void changeCursor(Coordinate glassPaneCoordinates) {
    Coordinate elementCoordinates = determineCoordinateInCanvas(glassPaneCoordinates);
    container.onElementAtPoint(elementCoordinates).perform(new SetHandCursor()).orFallBackTo(new SetDefaultCursor());
  }

  @Override
  public void clear() {
    container.clear();
    for (FxSpecialTrigger specialControl : specialControls) {
      specialControl.remove();
    }
    specialControls.clear();
    refresh();
  }

  @Override
  public Executor onElementAtPoint(Coordinate glassPaneCoordinates) {
    Coordinate elementCoordinates = determineCoordinateInCanvas(glassPaneCoordinates);
    return container.onElementAtPoint(elementCoordinates);
  }

  @Override
  public void addMousePressListener(final MousePressClosure listener) {
    glasspane.addEventHandler(MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        listener.mousePressed(determineCoordinate(event));
      }
    });
  }

  @Override
  public void addMouseClickListener(final MouseClickClosure listener) {
    glasspane.addEventHandler(MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        MouseButton button = determineMouseButton(event);
        MetaKey key = determineMetaKey(event);
        Coordinate coordinate = determineCoordinate(event);
        listener.mouseClicked(button, key, coordinate, event.getClickCount());
      }
    });
  }

  @Override
  public void addMouseWheelListener(final MouseWheelClosure listener) {
    glasspane.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent scrollEvent) {
        int wheelClicks = (int) scrollEvent.getDeltaY() / 40;
        listener.mouseWheelMoved(wheelClicks, determineCoordinate(scrollEvent));
      }
    });
  }

  @Override
  public void addMouseBorderListener(final MouseBorderClosure listener) {
    glasspane.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        listener.mouseEntered();
      }
    });
    glasspane.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        listener.mouseExited();
      }
    });
  }

  @Override
  public void addMouseMotionListener(final MouseMotionClosure listener) {
    glasspane.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        listener.mouseMoved(determineCoordinate(mouseEvent));
      }
    });
    glasspane.addEventHandler(MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        listener.mouseDragged(determineMouseButton(mouseEvent), determineCoordinate(mouseEvent));
      }
    });
  }

  @Override
  public void setBackground(RGBColor color) {
    background.setFill(toFxColor(color));
  }

  @Override
  public void showMoveCursor() {
    glasspane.setCursor(MOVE);
  }

  @Override
  public void resetAllTooltips() {
    //TODO: Reset Tooltips (if FX requires this at all)
  }

  @Override
  public void setTransformation(AgnosticTransform transform) {
    this.transform = transform;
    refresh();
  }

  @Override
  public int getWidth() {
    return (int) content.getWidth();
  }

  @Override
  public int getHeight() {
    return (int) content.getHeight();
  }

  @Override
  public void setToolTipText(String toolTipText) {
    if (toolTipText == null) {
      Tooltip.uninstall(glasspane, tooltip);
      return;
    }
    tooltip.setText(toolTipText);
    Tooltip.install(glasspane, tooltip);
  }

  @Override
  public StatefulTooltip createConfigurableTooltip() {
    return new StatefulFxTooltip(glasspane);
  }

  private MouseButton determineMouseButton(MouseEvent event) {
    if (event.getButton() == PRIMARY) {
      return Primary;
    }
    if (event.getButton() == SECONDARY) {
      return Secondary;
    }
    return Other;
  }

  private MetaKey determineMetaKey(MouseEvent event) {
    if (event.isControlDown()) {
      return MetaKey.CTRL;
    }
    return MetaKey.NONE;
  }

  private Coordinate determineCoordinate(MouseEvent event) {
    return new Coordinate(event.getX(), event.getY());
  }

  private Coordinate determineCoordinate(ScrollEvent event) {
    return new Coordinate(event.getX(), event.getY());
  }

  private Coordinate determineCoordinateInCanvas(Coordinate glassPaneCoordinate) {
    Point2D sceneCoordinate = glasspane.localToScene(glassPaneCoordinate.x, glassPaneCoordinate.y);
    Point2D point2D = canvas.sceneToLocal(sceneCoordinate);
    return new Coordinate(point2D.getX(), point2D.getY());
  }

  public Node getNode() {
    return content;
  }

  private void sizeLikeContentPane(Rectangle rectangle) {
    rectangle.widthProperty().bind(content.widthProperty());
    rectangle.heightProperty().bind(content.heightProperty());
  }

  private class SetHandCursor implements Closure {
    @Override
    public void execute(InteractiveGraphicsElement polygon) {
      glasspane.setCursor(HAND);
    }
  }

  private class SetDefaultCursor implements Runnable {
    @Override
    public void run() {
      glasspane.setCursor(DEFAULT);
    }
  }
}