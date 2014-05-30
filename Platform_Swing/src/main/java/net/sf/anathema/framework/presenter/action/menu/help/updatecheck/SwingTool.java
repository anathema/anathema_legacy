package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.JButton;

public class SwingTool implements Tool {
  private JButton button;

  public SwingTool(JButton button) {
    this.button = button;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    //nothing to do
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    //nothing to do
  }

  @Override
  public void setTooltip(String text) {
    button.setToolTipText(text);
  }

  @Override
  public void setText(String text) {
    button.setText(text);
  }

  @Override
  public void enable() {
    button.setEnabled(true);
  }

  @Override
  public void disable() {
    button.setEnabled(false);
  }

  @Override
  public void setCommand(Command command) {
    button.addActionListener(e -> command.execute());
  }

  @Override
  public void setHotkey(Hotkey s) {
    //nothing to do
    //nothing to do
  }
}
