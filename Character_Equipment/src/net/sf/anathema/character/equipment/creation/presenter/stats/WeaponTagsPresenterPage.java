package net.sf.anathema.character.equipment.creation.presenter.stats;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

public class WeaponTagsPresenterPage extends AbstractAnathemaWizardPage {

  private final IEquipmentStatisticsCreationModel model;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final TagPageProperties properties;
  private IWeaponTagsView content;

  public WeaponTagsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
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
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    for (IWeaponTag tag : model.getWeaponTagsModel().getAllTags()) {
      booleanValuePresentation.initPresentation(
          content.addCheckBox(properties.getLabel(tag)),
          model.getWeaponTagsModel().getBooleanModel(tag));
    }
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