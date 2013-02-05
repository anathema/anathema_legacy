package net.sf.anathema.swing.character.perspective.interaction;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JButton;
import java.awt.Component;

public class ActionInteraction implements Interaction {

  private final CommandProxy commandProxy = new CommandProxy();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      commandProxy.execute();
    }
  };
  private final IResources resources;
  private final Class<?> inquirer;

  public ActionInteraction(IResources resources, Class<?> inquirer) {
    this.resources = resources;
    this.inquirer = inquirer;
  }

  @Override
  public void setIcon(String relativePath) {
    action.setIcon(resources.getImageIcon(inquirer, relativePath));
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

  public JButton createButton() {
    return new JButton(action);
  }
}
