/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.util;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.swing.geometry.SmartRectangle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
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
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class GuiUtilities {

  public static final String ENABLED_PROPERTY_NAME = "enabled"; //$NON-NLS-1$

  protected GuiUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static final Window getWindowFor(final EventObject event) {
    if (event == null) {
      return JOptionPane.getRootFrame();
    }
    final Object object = event.getSource();
    if (object instanceof Component) {
      return getWindowFor((Component) object);
    }
    return JOptionPane.getRootFrame();
  }

  public final static Window getWindowFor(final Component component) {
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
   * Sets the location of the given window relative to another component. If the other component is
   * not currently showing, the window is centered on the screen.
   */
  public static final void centerToComponent(final Window window, final Component component) {
    final Window parentWindow = getWindowFor(component);
    if (parentWindow == null || !parentWindow.isVisible()) {
      centerOnScreen(window);
    }
    else {
      final SmartRectangle parentBounds = getSizeOnScreen(parentWindow);
      centerToPoint(window, parentBounds.getCenter());
    }
  }

  private static SmartRectangle getSizeOnScreen(final Component component) {
    return new SmartRectangle(component.getLocationOnScreen(), component.getSize());
  }

  public static void placeRelativeToOwner(final Window window, final RelativePosition position) {
    position.place(window);
    assureIsOnScreen(window);
  }

  public static final void centerOnScreen(final Window window) {
    final Point screenCenter = new SmartRectangle(getScreenBounds(window)).getCenter();
    centerToPoint(window, screenCenter);
  }

  public static Rectangle getScreenBounds(final Component component) {
    return component.getGraphicsConfiguration().getBounds();
  }

  public static final void centerToPoint(final Window window, final Point center) {
    final Dimension size = window.getSize();
    int x = center.x - size.width / 2;
    int y = center.y - size.height / 2;
    x = x < 0 ? 0 : x;
    y = y < 0 ? 0 : y;
    window.setLocation(x, y);
  }

   public static final void centerToParent(final Window window) {
    centerToComponent(window, window.getParent());
  }

  public static final JDialog createDialog(final Component parentComponent, final String title) {
    final JDialog dialog = createRawDialogForParentComponent(parentComponent);
    dialog.setTitle(title);
    accountForScreenSize(dialog);
    return dialog;
  }

  private static JDialog createRawDialogForParentComponent(final Component parentComponent) {
    final Window window = getWindowFor(parentComponent);
    if (window instanceof Frame) {
      return new JDialog((Frame) window);
    }
    if (window instanceof Dialog) {
      return new JDialog((Dialog) window);
    }
    return new JDialog();
  }

  public static final void accountForScreenSize(final Window window) {
    window.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(final ComponentEvent e) {
        assureIsOnScreen((Window) e.getSource());
      }
    });
  }

  public static final void assureIsOnScreen(final Window window) {
    assureIsOnScreen(window, calculateScreenBounds(window));
  }

  public static final void assureIsOnScreen(final Window window, final Rectangle screenBounds) {
    if (window instanceof Frame && ((Frame) window).getExtendedState() == Frame.MAXIMIZED_BOTH) {
      return; //setting bounds would cause the extended state to be set from maximized back to normal!
      //Also in maximized mode the system will take care of the frame being on the screen   
    }
    window.setBounds(calculateOnScreenBounds(window.getBounds(), screenBounds));
    window.validate(); // ohne das war bei niedrigen Auflösungen die Toolbar in GISterm initial nicht sichtbar (ip)
  }

  public static final Rectangle calculateOnScreenBounds(
      final Rectangle componentBounds,
      final Rectangle screenBounds) {
    final Rectangle onScreenBounds = new Rectangle(componentBounds);
    if (onScreenBounds.height > screenBounds.height) {
      onScreenBounds.height = screenBounds.height;
    }
    if (onScreenBounds.width > screenBounds.width) {
      onScreenBounds.width = screenBounds.width;
    }
    onScreenBounds.setLocation(calculateScreenFittingLocation(screenBounds, onScreenBounds));
    return onScreenBounds;
  }

  public static void stopCellEditing(final JTable table) {
    final TableCellEditor cellEditor = table.getCellEditor();
    if (cellEditor != null) {
      cellEditor.stopCellEditing();
    }
  }

  public static Point calculateScreenFittingLocation(
      final Rectangle screenBounds,
      final Rectangle rectangle) {
    final int x = (int) Math.max(
        screenBounds.x,
        Math.min(screenBounds.getMaxX() - rectangle.width, rectangle.x));
    final int y = (int) Math.max(
        screenBounds.y,
        Math.min(screenBounds.getMaxY() - rectangle.height, rectangle.y));
    return new Point(x, y);
  }

  public static Rectangle calculateScreenBounds(final Component anyComponentOnScreen) {
    return calculateScreenBounds(
        anyComponentOnScreen.getGraphicsConfiguration(),
        anyComponentOnScreen.getToolkit());
  }

  public static Rectangle calculateScreenBounds(
      final GraphicsConfiguration graphicsConfiguration,
      final Toolkit toolkit) {
    final Rectangle overallScreenBounds = graphicsConfiguration.getBounds();
    final Insets screenInsets = toolkit.getScreenInsets(graphicsConfiguration);
    final Rectangle screenBounds = new Rectangle(
        overallScreenBounds.x + screenInsets.left,
        overallScreenBounds.y + screenInsets.top,
        overallScreenBounds.width - screenInsets.left - screenInsets.right,
        overallScreenBounds.height - screenInsets.top - screenInsets.bottom);
    return screenBounds;
  }

  public static Set<Container> setContainerEnabled(final Container control, final boolean enabled) {
    return setContainerEnabled(control, enabled, new HashSet<Container>());
  }

  /**
   * Enabled / Disabled einen Container mit allen Unterkomponenten
   * 
   * @param c der Container
   * @param enable true, wenn der Container enabled werden soll
   * @param components die Komponenten, die nicht enabled / disabled werden sollen
   * @return die Komponenten, die vorher schon enabled / disabled waren
   */
  public static Set<Container> setContainerEnabled(
      final Container c,
      final boolean enable,
      final Set<Container> components) {
    final HashSet<Container> enabledComps = new HashSet<Container>();

    if (!components.contains(c)) {
      if (c.isEnabled() == enable) {
        enabledComps.add(c);
      }
      else {
        c.setEnabled(enable);
      }
    }
    return setSubComponentsEnabled(c, enable, components, enabledComps);
  }

  private static Set<Container> setSubComponentsEnabled(
      final Container c,
      final boolean enable,
      final Set<Container> components,
      final Set<Container> enabledComps) {
    final Component[] comps = c.getComponents();
    for (int i = 0; i < comps.length; i++) {
      if (comps[i] instanceof Container) {
        final Set<Container> enabledContainers = setContainerEnabled(
            (Container) comps[i],
            enable,
            components);
        enabledComps.addAll(enabledContainers);
      }
      else {
        if (!components.contains(comps[i])) {
          if (comps[i].isEnabled() == enable) {
            enabledComps.add(c);
          }
          else {
            comps[i].setEnabled(enable);
          }
        }
      }
    }
    return enabledComps;
  }

  public static void repack(final Window window) {
    if (!window.isVisible()) {
      window.pack();
      return;
    }
    final Rectangle oldBounds = window.getBounds();
    final Dimension newSize = window.getPreferredSize();
    final Point newLocation = new Point(
        oldBounds.x + (oldBounds.width - newSize.width) / 2,
        oldBounds.y + (oldBounds.height - newSize.height) / 2);
    Rectangle newBounds = new Rectangle(newLocation, newSize);
    newBounds = calculateOnScreenBounds(newBounds, calculateScreenBounds(window));
    window.setBounds(newBounds);
    window.validate();
  }

  public static void stopCellEditing(final Container container) {
    if (container instanceof JTable) {
      stopCellEditing((JTable) container);
    }
    else {
      for (final Component component : container.getComponents()) {
        if (component instanceof Container) {
          stopCellEditing((Container) component);
        }
      }
    }
  }
}