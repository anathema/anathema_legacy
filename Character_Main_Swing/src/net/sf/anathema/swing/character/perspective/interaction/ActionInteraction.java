package net.sf.anathema.swing.character.perspective.interaction;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.CommandAction;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tool.ProxyAcceleratorMap;

public class ActionInteraction implements Tool {

  private final CommandProxy commandProxy = new CommandProxy();
  private final SmartAction action = new CommandAction(commandProxy);
  private final ProxyAcceleratorMap acceleratorMap = new ProxyAcceleratorMap();
  private final Resources resources;

  public ActionInteraction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    action.setIcon(new ImageProvider().getImageIcon(relativePath));
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    throw new UnsupportedOperationException("Urs: We should never need this.");
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
  public void setCommand(net.sf.anathema.interaction.Command command) {
    commandProxy.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey hotkey) {
    acceleratorMap.register(hotkey, commandProxy);
  }

  public void addTo(ToolBar toolbar) {
    toolbar.addTools(action);
  }

  public void registerHotkeyIn(AcceleratorMap acceleratorMap) {
    this.acceleratorMap.setActualMap(acceleratorMap);
  }
}