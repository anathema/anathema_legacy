package net.sf.anathema.character.equipment.creation.presenter.stats;

import javax.swing.JCheckBox;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
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
    // nothing to do
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    IWeaponTagsModel weaponTagsModel = model.getWeaponTagsModel();
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(inputListener);
    }
  }

  @Override
  protected void initPageContent() {
    content = viewFactory.createWeaponTagsView();
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    for (IWeaponTag tag : model.getWeaponTagsModel().getAllTags()) {
      final JCheckBox checkBox = content.addCheckBox(properties.getLabel(tag));
      booleanValuePresentation.initPresentation(checkBox, model.getWeaponTagsModel().getSelectedModel(tag));
      final BooleanValueModel enabledModel = model.getWeaponTagsModel().getEnabledModel(tag);
      enabledModel.addChangeListener(new IBooleanValueChangedListener() {
        public void valueChanged(boolean newValue) {
          checkBox.setEnabled(enabledModel.getValue());
        }
      });
      checkBox.setEnabled(enabledModel.getValue());
    }
  }

  public boolean canFinish() {
      return !isIllegalRangedWeapon();
  }

  private boolean isIllegalRangedWeapon() {
    if (model.getEquipmentType() == EquipmentStatisticsType.RangedCombat) {
      if (!model.getWeaponTagsModel().isRangedTypeTagSelected()) {
        return true;
      }
      if (!model.getWeaponTagsModel().isThrownTypeTagSelected()
    	  && model.getWeaponTagsModel().isThrownWeaponTagSelected()) {
        return true;
      }
    }
    return false;
  }

  public String getDescription() {
    return properties.getPageTitle();
  }

  public IBasicMessage getMessage() {
    if (isIllegalRangedWeapon()) {
      if (!model.getWeaponTagsModel().isRangedTypeTagSelected()) {
        return properties.getSelectRangedWeaponTagMessage();
      }
      if (!model.getWeaponTagsModel().isThrownTypeTagSelected()
          && model.getWeaponTagsModel().isThrownWeaponTagSelected()) {
        return properties.getThrownTagButNotThrownTypeMessage();
      }
    }
    return properties.getDefaultMessage();
  }

  public IPageContent getPageContent() {
    return content;
  }
}