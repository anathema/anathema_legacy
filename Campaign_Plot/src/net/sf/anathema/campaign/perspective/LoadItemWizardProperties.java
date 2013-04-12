package net.sf.anathema.campaign.perspective;

import net.sf.anathema.campaign.load.selection.IObjectSelectionProperties;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class LoadItemWizardProperties implements IObjectSelectionProperties {

  private final Resources resources;
  private final AgnosticUIConfiguration renderer;

  public LoadItemWizardProperties(Resources resources, AgnosticUIConfiguration ui) {
    this.resources = resources;
    this.renderer = ui;
  }

  @Override
  public AgnosticUIConfiguration getCellRenderer() {
    return renderer;
  }

  @Override
  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.LoadAction.Message.Default"));
  }

  @Override
  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.LoadMenu.Name");
  }
}