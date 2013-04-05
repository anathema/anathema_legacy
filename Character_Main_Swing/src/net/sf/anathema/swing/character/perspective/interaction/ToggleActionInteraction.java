package net.sf.anathema.swing.character.perspective.interaction;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.ToggleInteraction;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JToggleButton;
import java.awt.Component;

public class ToggleActionInteraction implements ToggleInteraction {

  private final CommandProxy commandProxy = new CommandProxy();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      commandProxy.execute();
    }
  };
  private final JToggleButton button = new JToggleButton(action);
  private final Resources resources;

  public ToggleActionInteraction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    action.setIcon(new ImageProvider().getImageIcon(relativePath));
  }

  @Override
  public void setTooltip(String key) {
    action.setToolTipText(resources.getString(key));
  }

  @Override
  public void setText(String key) {
    action.setName(resources.getString(key));
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
    commandProxy.setDelegate(command);
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
  public void addTo(ToolBar toolbar) {
    toolbar.add(button);
  }
}
