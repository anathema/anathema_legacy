package net.sf.anathema.character.equipment.creation;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.TagPageProperties;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;

public class WeaponTagsPresenterPage extends AbstractAnathemaWizardPage {

  private final IEquipmentStatisticsCreationModel model;
  private final IResources resources;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final TagPageProperties properties;
  private IPageContent content;

  public WeaponTagsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.resources = resources;
    this.model = model;
    this.viewFactory = viewFactory;
    this.properties = new TagPageProperties(resources);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    //nothing to do
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    //nothing to do
  }

  @Override
  protected void initPageContent() {
    content = viewFactory.createWeaponTagsView();
  }

  public boolean canFinish() {
    return true;
  }

  public String getDescription() {
    return properties.getPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getDefaultMessage();
  }

  public IPageContent getPageContent() {
    return content;
  }
}