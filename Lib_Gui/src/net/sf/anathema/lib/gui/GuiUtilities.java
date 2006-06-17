package net.sf.anathema.lib.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class GuiUtilities extends net.disy.commons.swing.util.GuiUtilities {
  private GuiUtilities() {
    // nothing to do
  }

  public static void displayOnScreenCenter(JFrame frame) {
    frame.pack();
    centerOnScreen(frame);
    frame.setVisible(true);
  }

  public static void displayOnScreenCenter(JFrame frame, Dimension size) {
    frame.setSize(size);
    centerOnScreen(frame);
    frame.setVisible(true);
  }

  public static void setJMenuBar(JFrame frame, Iterator<JMenu> menus) {
    JMenuBar menuBar = new JMenuBar();
    while (menus.hasNext()) {
      menuBar.add(menus.next());
    }
    frame.setJMenuBar(menuBar);
  }

  public static JMenuItem addJMenuItem(JMenu menu, String label) {
    JMenuItem menuItem = new JMenuItem(label);
    menu.add(menuItem);
    return menuItem;
  }

  public static JDialog createDialog(Component component, String title, boolean modal) {
    Window parent = getParentWindow(component);
    if (parent instanceof JDialog) {
      return new JDialog((JDialog) parent, title, modal);
    }
    return new JDialog((JFrame) parent, title, modal);
  }

  public static Window getParentWindow(Component parent) {
    Container container = parent.getParent();
    if (container instanceof JFrame) {
      return (JFrame) container;
    }
    if (container instanceof JDialog) {
      return (JDialog) container;
    }
    if (container instanceof JComponent) {
      return getParentWindow(container);
    }
    return JOptionPane.getRootFrame();
  }

  public static void setEnabled(Container container, boolean enabled) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        setEnabled((Container) component, enabled);
      }
      else {
        component.setEnabled(enabled);
      }
    }
    container.setEnabled(enabled);
  }

  public static void show(JDialog dialog) {
    dialog.pack();
    centerOnScreen(dialog);
    dialog.setVisible(true);
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
      public void run() {
        component.invalidate();
        component.validate();
        component.repaint();
      }
    });
  }

  public static void invokeOnEventDispatchThread(Runnable runner) {
    if (SwingUtilities.isEventDispatchThread()) {
      runner.run();
    }
    else {
      try {
        SwingUtilities.invokeAndWait(runner);
      }
      catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void setObjects(DefaultListModel model, Object[] objects) {
    model.clear();
    for (Object element : objects) {
      model.addElement(element);
    }
  }

  public static Dimension calculateComboBoxSize(Object[] objects, ListCellRenderer renderer) {
    ChangeableJComboBox<Object> box = new ChangeableJComboBox<Object>(objects, false);
    box.setRenderer(renderer);
    return box.getPreferredSize();
  }
}