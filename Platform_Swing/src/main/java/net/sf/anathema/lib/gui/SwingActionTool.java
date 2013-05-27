package net.sf.anathema.lib.gui;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.NullCommand;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.platform.Hotkey;

import javax.swing.Action;
import javax.swing.ImageIcon;
import java.awt.Component;

public class SwingActionTool implements Tool {

  private Command command = new NullCommand();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      command.execute();
    }
  };

  @Override
  public void setIcon(RelativePath relativePath) {
    ImageIcon icon = new ImageProvider().getImageIcon(relativePath);
    action.setIcon(icon);
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    throw new UnsupportedOperationException("We'll probably never need this.");
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
    this.command = command;
  }

  @Override
  public void setHotkey(Hotkey s) {
    throw new NotYetImplementedException();
  }

  public Action getAction() {
    return action;
  }
}