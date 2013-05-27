package net.sf.anathema.lib.gui;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.ImageIcon;

public class SwingActionTool implements Tool {

  private CommandProxy command = new CommandProxy();
  private final SmartAction action = new CommandAction(command);

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
    this.command.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey s) {
    throw new NotYetImplementedException();
  }

  public void addTo(AddToSwingComponent addTo) {
    addTo.add(action);
  }
}