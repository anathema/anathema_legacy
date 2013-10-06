package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.JToggleButton;
import java.awt.Component;

public class SwingToggleTool implements ToggleTool {

  private final CommandProxy proxy = new CommandProxy();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      proxy.execute();
    }
  };
  private final JToggleButton button = new JToggleButton(action);

  @Override
  public void select() {
    button.setSelected(true);
  }

  @Override
  public void deselect() {
    button.setSelected(false);
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
    action.setToolTipText(text);
  }

  @Override
  public void setText(String text) {
    action.setName(text);
  }

  @Override
  public void enable() {
    action.setEnabled(true);
  }

  @Override
  public void disable() {
    action.setEnabled(false);
  }

  @Override
  public void setCommand(Command command) {
    proxy.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey s) {
    //nothing to do
  }

  public JToggleButton getButton() {
    return button;
  }
}
