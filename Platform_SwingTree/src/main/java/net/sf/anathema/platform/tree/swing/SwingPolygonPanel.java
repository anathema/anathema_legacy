package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.DisplayPolygonPanel;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.MouseBorderClosure;
import net.sf.anathema.platform.tree.view.draw.Canvas;
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

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.MOVE_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static net.sf.anathema.lib.gui.swing.ColorUtilities.toAwtColor;

public class SwingPolygonPanel extends JPanel implements DisplayPolygonPanel {
  private AgnosticTransform transform = new AgnosticTransform();
  private ElementContainer container = new ElementContainer();
  private final List<ButtonSpecialControl> specialControls = new ArrayList<>();

  public SwingPolygonPanel() {
    setLayout(null);
    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g.create();
    AffineTransform affineTransform = SwingTransformer.convert(transform);
    graphics.transform(affineTransform);
    graphics.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
    graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    Canvas canvas = new SwingGraphicsCanvas(graphics);
    for (GraphicsElement element : container) {
      element.paint(canvas);
    }
  }

  @Override
  public void refresh() {
    this.revalidate();
    super.repaint();
  }

  @SuppressWarnings("ConstantConditions") //Is null during superclass initialization
  @Override
  public void revalidate() {
    if (specialControls != null) {
      for (SpecialControlTrigger specialControlTrigger : specialControls) {
        specialControlTrigger.transformThrough(transform);
      }
    }
    super.revalidate();
  }

  @Override
  public SpecialControlTrigger addSpecialControl() {
    ButtonSpecialControl specialControl = new ButtonSpecialControl();
    specialControls.add(specialControl);
    specialControl.transformOriginalCoordinates(transform);
    specialControl.addTo(this);
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
  public void changeCursor(Coordinate elementCoordinates) {
    container.onElementAtPoint(elementCoordinates).perform(new SetHandCursor()).orFallBackTo(new SetDefaultCursor());
  }

  @Override
  public void clear() {
    container.clear();
    for (ButtonSpecialControl specialControl : specialControls) {
      specialControl.remove();
    }
    specialControls.clear();
    repaint();
  }

  @Override
  public Executor onElementAtPoint(Coordinate elementCoordinates) {
    return container.onElementAtPoint(elementCoordinates);
  }

  @Override
  public void addMousePressListener(final MousePressClosure listener) {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        listener.mousePressed(new Coordinate(e.getX(), e.getY()));
      }
    });
  }

  @Override
  public void addMouseClickListener(final MouseClickClosure listener) {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        MouseButton button = determineMouseButton(e);
        MetaKey key;
        if (e.isControlDown()) {
          key = MetaKey.CTRL;
        } else {
          key = MetaKey.NONE;
        }
        listener.mouseClicked(button, key, new Coordinate(e.getX(), e.getY()), e.getClickCount());
      }
    });
  }

  @Override
  public void addMouseWheelListener(final MouseWheelClosure listener) {
    addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        listener.mouseWheelMoved(e.getWheelRotation(), new Coordinate(e.getX(), e.getY()));
      }
    });
  }

  @Override
  public void addMouseBorderListener(final MouseBorderClosure listener) {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        listener.mouseEntered();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        listener.mouseExited();
      }
    });
  }

  @Override
  public void addMouseMotionListener(final MouseMotionClosure listener) {
    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        MouseButton button = determineMouseButton(e);
        listener.mouseDragged(button, new Coordinate(e.getX(), e.getY()));
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        listener.mouseMoved(new Coordinate(e.getX(), e.getY()));
      }
    });
  }

  private MouseButton determineMouseButton(MouseEvent e) {
    MouseButton button;
    if (SwingUtilities.isLeftMouseButton(e)) {
      button = MouseButton.Left;
    } else {
      button = MouseButton.Right;
    }
    return button;
  }

  @Override
  public void setBackground(RGBColor color) {
    setBackground(toAwtColor(color));
  }

  @Override
  public void showMoveCursor() {
    setCursor(Cursor.getPredefinedCursor(MOVE_CURSOR));
  }

  @Override
  public void resetAllTooltips() {
    ToolTipManager.sharedInstance().setEnabled(false);
    ToolTipManager.sharedInstance().setEnabled(true);
  }

  @Override
  public JComponent getComponent() {
    return this;
  }

  @Override
  public void setTransformation(AgnosticTransform transform) {
    this.transform = transform;
    repaint();
  }

  private class SetDefaultCursor implements Runnable {
    @Override
    public void run() {
      setCursor(Cursor.getDefaultCursor());
    }
  }

  private class SetHandCursor implements Closure {
    @Override
    public void execute(InteractiveGraphicsElement polygon) {
      setCursor(getPredefinedCursor(HAND_CURSOR));
    }
  }
}