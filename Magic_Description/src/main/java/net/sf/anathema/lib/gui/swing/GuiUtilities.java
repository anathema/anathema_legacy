package net.sf.anathema.lib.gui.swing;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class GuiUtilities {

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
    invokeOnEventDispatchThread(() -> {
      component.invalidate();
      component.validate();
      component.repaint();
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