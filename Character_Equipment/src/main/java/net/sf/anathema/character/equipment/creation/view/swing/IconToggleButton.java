package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.framework.value.NoFocusButton;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class IconToggleButton {

  private final JButton button = new NoFocusButton();
  private boolean selected;
  private Icon selectedIcon;
  private Icon unselectedIcon;

  public IconToggleButton(Icon activeIcon) {
    setIconSet(activeIcon, null);
    button.setPreferredSize(getPreferredSize(activeIcon));
  }

  private void setIconSet(Icon selectedIcon, Icon unselectedIcon) {
    this.selectedIcon = selectedIcon;
    this.unselectedIcon = unselectedIcon;
    button.setIcon(isSelected() ? selectedIcon : unselectedIcon);
    button.setRolloverIcon(unselectedIcon);
    button.setSelectedIcon(selectedIcon);
    button.setRolloverSelectedIcon(selectedIcon);
    button.setDisabledSelectedIcon(selectedIcon);
  }

  private Dimension getPreferredSize(Icon icon) {
    return new Dimension(icon.getIconWidth() + 4, icon.getIconHeight() + 4);
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    button.setSelected(selected);
    button.setIcon(isSelected() ? selectedIcon : unselectedIcon);
  }

  public void setEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public JComponent getComponent() {
    return button;
  }

  public void addActionListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  public boolean isSelected() {
    return selected;
  }
}