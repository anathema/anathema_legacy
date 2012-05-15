package net.sf.anathema.lib.gui.swing;

import javax.swing.JComponent;

public class DefaultEnableableComponentContainer implements IEnableableComponentContainer {

  private final JComponent panel;

  public DefaultEnableableComponentContainer(final JComponent panel) {
    this.panel = panel;
    GuiUtilities.setContainerEnabled(panel, isEnabled());
  }

  public boolean isEnabled() {
    return panel.isEnabled();
  }

  @Override
  public JComponent[] getComponents() {
    return new JComponent[]{ panel };
  }

  @Override
  public void setEnabled(final boolean enabled) {
    GuiUtilities.setContainerEnabled(panel, enabled);
  }
}