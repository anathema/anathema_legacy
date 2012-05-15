package net.sf.anathema.lib.gui.swing;

import com.google.common.base.Preconditions;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A ToggleComponentEnabler manages a JToggleButton and one or more components so that when the
 * toggle button is selected, the components are enabled and vice versa. Furthermore pressing the
 * mouse on a disabled swing will select the toggle button.
 * 
 * Note that JComboBox components will not work properly for receiving mouse click events
 * (http://developer.java.sun.com/developer/bugParade/bugs/4144505.html), so selecting a
 * ToggleButton by klicking the mouse on a disabled combobox will not work.
 * 
 * @author gebhard
 */
public class ToggleComponentEnabler {

  public static void connect(
      final JToggleButton button,
      final IEnableableComponentContainer... components) {
    connectWithDecorations(button, components, new JComponent[0]);
  }

  public static void connectWithDecorations(
      final JToggleButton button,
      final IEnableableComponentContainer[] components,
      final JComponent[] decorations) {
    new ToggleComponentEnabler(button, components, decorations);
  }

  public static void connect(final JToggleButton button, final JComponent component) {
    connect(button, new DefaultEnableableComponentContainer(component));
  }

  private final JToggleButton button;
  private final IEnableableComponentContainer[] components;

  private ToggleComponentEnabler(
      final JToggleButton button,
      final IEnableableComponentContainer[] components,
      final JComponent[] decorations) {
    Preconditions.checkNotNull(button);
    Preconditions.checkNotNull(components);
    this.button = button;
    this.components = components;
    setButtonListeners();
    listenToComponentClicks(components);
    updateComponentsEnabled();
    addDecorationListeners(decorations);
  }

  private void addDecorationListeners(final Component[] decorations) {
    for (int i = 0; i < decorations.length; i++) {
      final Component currentDecoration = decorations[i];
      currentDecoration.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(final MouseEvent e) {
          button.doClick();
        }
      });
      addEnabledListener(currentDecoration);
    }
  }

  private void addEnabledListener(final Component component) {
    button.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(final PropertyChangeEvent evt) {
            component.setEnabled(button.isEnabled());
          }
        });
    component.setEnabled(button.isEnabled());
  }

  private void listenToComponentClicks(final IEnableableComponentContainer[] componentsToListenTo) {
    for (int i = 0; i < componentsToListenTo.length; ++i) {
      listenToComponentClicks(componentsToListenTo[i].getComponents());
    }
  }

  private void listenToComponentClicks(final Component[] componentsToListenTo) {
    for (int i = 0; i < componentsToListenTo.length; ++i) {
      final Component component = componentsToListenTo[i];
      component.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(final MouseEvent e) {
          if (button.isEnabled() && !button.isSelected()) {
            button.doClick();
            if (e.getSource() instanceof Component) {
              ((Component) e.getSource()).requestFocus();
            }
          }
        }
      });
      if (component instanceof Container) {
        listenToComponentClicks(((Container) component).getComponents());
      }
    }
  }

  private void setButtonListeners() {
    button.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(final ItemEvent e) {
        updateComponentsEnabled();
      }
    });
    button.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(final PropertyChangeEvent evt) {
            updateComponentsEnabled();
          }
        });
  }

  private void setComponentsEnabled(final boolean enabled) {
    for (int i = 0; i < components.length; ++i) {
      components[i].setEnabled(enabled);
    }
  }

  private void updateComponentsEnabled() {
    setComponentsEnabled(button.isSelected() && button.isEnabled());
  }
}