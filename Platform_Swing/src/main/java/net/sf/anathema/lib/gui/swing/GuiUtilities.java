package net.sf.anathema.lib.gui.swing;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class GuiUtilities {

  public static Window getWindowFor(EventObject event) {
    if (event == null) {
      return JOptionPane.getRootFrame();
    }
    Object object = event.getSource();
    if (object instanceof Component) {
      return getWindowFor((Component) object);
    }
    return JOptionPane.getRootFrame();
  }

  public static Window getWindowFor(Component component) {
    if (component == null) {
      return JOptionPane.getRootFrame();
    }
    if (component instanceof JPopupMenu) {
      return getWindowFor(((JPopupMenu) component).getInvoker());
    }
    if (component instanceof Window) {
      return (Window) component;
    }
    return getWindowFor(component.getParent());
  }

  /**
   * Sets the location of the given window relative to another swing. If the other swing is
   * not currently showing, the window is centered on the screen.
   */
  private static void centerToComponent(Window window, Component component) {
    Window parentWindow = getWindowFor(component);
    if (parentWindow == null || !parentWindow.isVisible()) {
      centerOnScreen(window);
    } else {
      SmartRectangle parentBounds = getSizeOnScreen(parentWindow);
      centerToPoint(window, parentBounds.getCenter());
    }
  }

  private static SmartRectangle getSizeOnScreen(Component component) {
    return new SmartRectangle(component.getLocationOnScreen(), component.getSize());
  }

  public static void placeRelativeToOwner(Window window, RelativePosition position) {
    position.place(window);
    assureIsOnScreen(window);
  }

  public static void centerOnScreen(Window window) {
    Point screenCenter = new SmartRectangle(getScreenBounds(window)).getCenter();
    centerToPoint(window, screenCenter);
  }

  private static Rectangle getScreenBounds(Component component) {
    return component.getGraphicsConfiguration().getBounds();
  }

  private static void centerToPoint(Window window, Point center) {
    Dimension size = window.getSize();
    int x = center.x - size.width / 2;
    int y = center.y - size.height / 2;
    x = x < 0 ? 0 : x;
    y = y < 0 ? 0 : y;
    window.setLocation(x, y);
  }

  public static void centerToParent(Window window) {
    centerToComponent(window, window.getParent());
  }

  public static JDialog createDialog(String title) {
    JDialog dialog = createRawDialogForParentComponent();
    dialog.setTitle(title);
    accountForScreenSize(dialog);
    return dialog;
  }

  public static JDialog createRawDialogForParentComponent() {
    return new JDialog();
  }

  private static void accountForScreenSize(Window window) {
    window.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        assureIsOnScreen((Window) e.getSource());
      }
    });
  }

  private static void assureIsOnScreen(Window window) {
    assureIsOnScreen(window, calculateScreenBounds(window));
  }

  private static void assureIsOnScreen(Window window, Rectangle screenBounds) {
    if (window instanceof Frame && ((Frame) window).getExtendedState() == Frame.MAXIMIZED_BOTH) {
      return; //setting bounds would cause the extended state to be set from maximized back to normal!
      //Also in maximized mode the system will take care of the frame being on the screen   
    }
    window.setBounds(calculateOnScreenBounds(window.getBounds(), screenBounds));
    window.validate(); // ohne das war bei niedrigen Aufloesungen die Toolbar in GISterm initial nicht sichtbar (ip)
  }

  private static Rectangle calculateOnScreenBounds(Rectangle componentBounds, Rectangle screenBounds) {
    Rectangle onScreenBounds = new Rectangle(componentBounds);
    if (onScreenBounds.height > screenBounds.height) {
      onScreenBounds.height = screenBounds.height;
    }
    if (onScreenBounds.width > screenBounds.width) {
      onScreenBounds.width = screenBounds.width;
    }
    onScreenBounds.setLocation(calculateScreenFittingLocation(screenBounds, onScreenBounds));
    return onScreenBounds;
  }

  private static void stopCellEditing(JTable table) {
    TableCellEditor cellEditor = table.getCellEditor();
    if (cellEditor != null) {
      cellEditor.stopCellEditing();
    }
  }

  private static Point calculateScreenFittingLocation(Rectangle screenBounds, Rectangle rectangle) {
    int x = (int) Math.max(screenBounds.x, Math.min(screenBounds.getMaxX() - rectangle.width, rectangle.x));
    int y = (int) Math.max(screenBounds.y, Math.min(screenBounds.getMaxY() - rectangle.height, rectangle.y));
    return new Point(x, y);
  }

  private static Rectangle calculateScreenBounds(Component anyComponentOnScreen) {
    return calculateScreenBounds(anyComponentOnScreen.getGraphicsConfiguration(), anyComponentOnScreen.getToolkit());
  }

  private static Rectangle calculateScreenBounds(GraphicsConfiguration graphicsConfiguration, Toolkit toolkit) {
    Rectangle overallScreenBounds = graphicsConfiguration.getBounds();
    Insets screenInsets = toolkit.getScreenInsets(graphicsConfiguration);
    return new Rectangle(overallScreenBounds.x + screenInsets.left, overallScreenBounds.y + screenInsets.top,
            overallScreenBounds.width - screenInsets.left - screenInsets.right, overallScreenBounds.height - screenInsets.top - screenInsets.bottom);
  }

  /**
   * Enabled / Disabled einen Container mit allen Unterkomponenten
   *
   * @param c          der Container
   * @param enable     true, wenn der Container enabled werden soll
   * @param components die Komponenten, die nicht enabled / disabled werden sollen
   * @return die Komponenten, die vorher schon enabled / disabled waren
   */
  private static Set<Container> setContainerEnabled(Container c, boolean enable, Set<Container> components) {
    HashSet<Container> enabledComps = new HashSet<>();

    if (!components.contains(c)) {
      if (c.isEnabled() == enable) {
        enabledComps.add(c);
      } else {
        c.setEnabled(enable);
      }
    }
    return setSubComponentsEnabled(c, enable, components, enabledComps);
  }

  @SuppressWarnings("SuspiciousMethodCalls")
  private static Set<Container> setSubComponentsEnabled(Container c, boolean enable, Set<Container> components, Set<Container> enabledComps) {
    Component[] comps = c.getComponents();
    for (Component comp : comps) {
      if (comp instanceof Container) {
        Set<Container> enabledContainers = setContainerEnabled((Container) comp, enable, components);
        enabledComps.addAll(enabledContainers);
      } else {
        if (!components.contains(comp)) {
          if (comp.isEnabled() == enable) {
            enabledComps.add(c);
          } else {
            comp.setEnabled(enable);
          }
        }
      }
    }
    return enabledComps;
  }

  public static void repack(Window window) {
    if (!window.isVisible()) {
      window.pack();
      return;
    }
    Rectangle oldBounds = window.getBounds();
    Dimension newSize = window.getPreferredSize();
    Point newLocation = new Point(oldBounds.x + (oldBounds.width - newSize.width) / 2, oldBounds.y + (oldBounds.height - newSize.height) / 2);
    Rectangle newBounds = new Rectangle(newLocation, newSize);
    newBounds = calculateOnScreenBounds(newBounds, calculateScreenBounds(window));
    window.setBounds(newBounds);
    window.validate();
  }

  public static void stopCellEditing(Container container) {
    if (container instanceof JTable) {
      stopCellEditing((JTable) container);
    } else {
      for (Component component : container.getComponents()) {
        if (component instanceof Container) {
          stopCellEditing((Container) component);
        }
      }
    }
  }

  public static void setEnabled(Container container, boolean enabled) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        setEnabled((Container) component, enabled);
      } else {
        component.setEnabled(enabled);
      }
    }
    container.setEnabled(enabled);
  }

  public static void revalidateTree(Container parent) {
    if (!parent.isVisible()) {
      parent.invalidate();
      parent.validate();
      return;
    }
    while (parent != null) {
      revalidate(parent);
      parent = parent.getParent();
    }
  }

  public static void revalidate(final Container component) {
    invokeOnEventDispatchThread(new Runnable() {
      @Override
      public void run() {
        component.invalidate();
        component.validate();
        component.repaint();
      }
    });
  }

  private static void invokeOnEventDispatchThread(Runnable runner) {
    if (SwingUtilities.isEventDispatchThread()) {
      runner.run();
    } else {
      try {
        SwingUtilities.invokeAndWait(runner);
      } catch (InterruptedException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }
}