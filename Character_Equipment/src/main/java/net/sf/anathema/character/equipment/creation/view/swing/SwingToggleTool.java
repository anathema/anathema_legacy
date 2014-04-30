package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.Component;

public class SwingToggleTool implements ToggleTool {

  private final JToggleButton button;

  public SwingToggleTool(JToggleButton button) {
    this.button = button;
  }

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
    ImageIcon imageIcon = new ImageProvider().getImageIcon(relativePath);
    button.setIcon(imageIcon);
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
    button.setAction(new SmartAction(button.getText()) {
      @Override
      protected void execute(Component parentComponent) {
        command.execute();
      }
    });
  }

  @Override
  public void setHotkey(Hotkey s) {
    //nothing to do
  }
}