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
      JToggleButton button,
      IEnableableComponentContainer... components) {
    connectWithDecorations(button, components, new JComponent[0]);
  }

  public static void connectWithDecorations(
      JToggleButton button,
      IEnableableComponentContainer[] components,
      JComponent[] decorations) {
    new ToggleComponentEnabler(button, components, decorations);
  }

  public static void connect(JToggleButton button, JComponent component) {
    connect(button, new DefaultEnableableComponentContainer(component));
  }

  private final JToggleButton button;
  private final IEnableableComponentContainer[] components;

  private ToggleComponentEnabler(
      JToggleButton button,
      IEnableableComponentContainer[] components,
      JComponent[] decorations) {
    Preconditions.checkNotNull(button);
    Preconditions.checkNotNull(components);
    this.button = button;
    this.components = components;
    setButtonListeners();
    listenToComponentClicks(components);
    updateComponentsEnabled();
    addDecorationListeners(decorations);
  }

  private void addDecorationListeners(Component[] decorations) {
    for (Component currentDecoration : decorations) {
      currentDecoration.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
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
          public void propertyChange(PropertyChangeEvent evt) {
            component.setEnabled(button.isEnabled());
          }
        });
    component.setEnabled(button.isEnabled());
  }

  private void listenToComponentClicks(IEnableableComponentContainer[] componentsToListenTo) {
    for (IEnableableComponentContainer aComponentsToListenTo : componentsToListenTo) {
      listenToComponentClicks(aComponentsToListenTo.getComponents());
    }
  }

  private void listenToComponentClicks(Component[] componentsToListenTo) {
    for (Component component : componentsToListenTo) {
      component.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
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
      public void itemStateChanged(ItemEvent e) {
        updateComponentsEnabled();
      }
    });
    button.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            updateComponentsEnabled();
          }
        });
  }

  private void setComponentsEnabled(boolean enabled) {
    for (IEnableableComponentContainer component : components) {
      component.setEnabled(enabled);
    }
  }

  private void updateComponentsEnabled() {
    setComponentsEnabled(button.isSelected() && button.isEnabled());
  }
}