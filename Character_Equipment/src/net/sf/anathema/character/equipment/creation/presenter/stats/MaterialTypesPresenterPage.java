package net.sf.anathema.character.equipment.creation.presenter.stats;

import javax.swing.JCheckBox;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.creation.model.stats.IApplicableMaterialsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.view.IWeaponTagsView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

public class MaterialTypesPresenterPage extends AbstractAnathemaWizardPage
{
	private final IEquipmentStatisticsCreationModel model;
	private final IEquipmentStatisticsCreationViewFactory viewFactory;
	private final IResources resources;
	private IWeaponTagsView content;

  public MaterialTypesPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
	    this.model = model;
	    this.viewFactory = viewFactory;
	    this.resources = resources;
  }
  
  public void appendPage(IAnathemaWizardPage page, CheckInputListener inputListener, ICondition condition)
  {
	  addFollowupPage(page, inputListener, condition);
  }
  
  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    // nothing to do
  }
  
  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    IApplicableMaterialsModel materialsModel = model.getApplicableMaterialsModel();
    for (MagicalMaterial material : MagicalMaterial.values()) {
      materialsModel.getSelectedModel(material).addChangeListener(inputListener);
    }
  }
  
  public boolean canFinish()
  {
	  return false;
  }

  @Override
  protected void initPageContent() {
	 content = viewFactory.createWeaponTagsView();
	 BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
	 for (final MagicalMaterial material : MagicalMaterial.values())
	 {
	    final JCheckBox checkBox = content.addCheckBox(resources.getString("MagicMaterial." + material.name()));
	    booleanValuePresentation.initPresentation(checkBox, model.getApplicableMaterialsModel().getSelectedModel(material));
	    checkBox.setSelected(model.getApplicableMaterialsModel().getSelectedModel(material).getValue());
	 }
  }
  
  public IPageContent getPageContent() {
	    return content;
	  }
  
  public String getDescription() {
	    return resources.getString("Equipment.Creation.Materials.PageTitle");
	  }
  
  public IBasicMessage getMessage() {
	  return new BasicMessage(resources.getString("Equipment.Creation.Materials.DefaultMessage")); //$NON-NLS-1$
	  }
}