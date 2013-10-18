package net.sf.anathema.charmdatabase.tools;

import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.charmdatabase.view.CharmNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;

public class NewCharmAction {

  private final Resources resources;
  private final ICharmDatabaseManagement model;

  public NewCharmAction(Resources resources, ICharmDatabaseManagement model) {
    this.resources = resources;
    this.model = model;
  }

  public void addToolTo(CharmNavigation view) {
    Tool newTool = view.addEditTemplateTool();
    newTool.setIcon(new BasicUi().getNewIconPathForTaskbar());
    newTool.setTooltip(resources.getString("Charms.Creation.Item.NewActionTooltip"));
    newTool.setCommand(new NewCharmItem(model, resources));
  }
}