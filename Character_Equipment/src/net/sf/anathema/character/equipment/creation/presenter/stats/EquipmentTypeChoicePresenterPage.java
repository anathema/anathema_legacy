package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentTypeChoiceProperties;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTypeChoicePresenterPage extends AbstractAnathemaWizardPage {

  private final Map<EquipmentStatisticsType, IAnathemaWizardPage> pagesByType = new HashMap<EquipmentStatisticsType, IAnathemaWizardPage>();
  private final EquipmentTypeChoiceProperties properties;
  private final BasicMessage defaultMessage;
  private final IResources resources;
  private final IEquipmentTemplateEditModel editModel;
  private final IEquipmentStatisticsCreationModel model;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private MaterialTypesPresenterPage materialsPage;
  private CheckInputListener inputListener;
  private boolean materialSpecific;
  private IEquipmentTypeChoiceView view;

  public EquipmentTypeChoicePresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentTemplateEditModel editModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = new EquipmentTypeChoiceProperties(resources);
    this.defaultMessage = new BasicMessage(properties.getTypeChoiceMessage());
    this.resources = resources;
    this.model = model;
    this.viewFactory = viewFactory;
    this.editModel = editModel;
    
    materialSpecific = model.getApplicableMaterialsModel().getValidMaterials().length > 0 &&
    	model.getApplicableMaterialsModel().getValidMaterials().length !=
    	MagicalMaterial.values().length;
  }

  public boolean canFinish() {
    return false;
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    model.addEquipmentTypeChangeListener(inputListener);
    this.inputListener = inputListener;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
	
	addMaterialPage(getMaterialsPage(), inputListener);
    addPage(
        EquipmentStatisticsType.CloseCombat,
        new CloseCombatStatisticsPresenterPage(resources, model, viewFactory),
        inputListener);
    addPage(
        EquipmentStatisticsType.RangedCombat,
        new RangedCombatStatisticsPresenterPage(resources, model, viewFactory),
        inputListener);
    addPage(
        EquipmentStatisticsType.Armor,
        new ArmourStatisticsPresenterPage(resources, model, viewFactory),
        inputListener);
    addPage(
        EquipmentStatisticsType.Shield,
        new ShieldStatisticsPresenterPage(resources, model, viewFactory),
        inputListener);
    addPage(
    	EquipmentStatisticsType.Artifact,
    	new ArtifactStatisticsPresenterPage(resources, model, viewFactory),
    	inputListener);
  }
  
  private MaterialTypesPresenterPage getMaterialsPage()
  {
	  if (materialsPage == null)
		  materialsPage = new MaterialTypesPresenterPage(resources, model, viewFactory);
	  return materialsPage;
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createTypeChoiceView();
    String label = properties.getOffensiveLabel();
    addStatisticsTypeRow(label, EquipmentStatisticsType.CloseCombat);
    addStatisticsTypeRow("", EquipmentStatisticsType.RangedCombat); //$NON-NLS-1$
    view.addHorizontalLine();
    addStatisticsTypeRow(properties.getDefensiveLabel(), EquipmentStatisticsType.Armor);
    addStatisticsTypeRow("", EquipmentStatisticsType.Shield); //$NON-NLS-1$
    view.addHorizontalLine();
    addStatisticsTypeRow(properties.getOtherLabel(), EquipmentStatisticsType.Artifact);
    if (editModel != null && 
    	editModel.getMaterialComposition() == MaterialComposition.Variable)
    {
    	//view.addHorizontalLine();
    	view.addCheckBox(properties.getMaterialToggleLabel(),
    			new ItemListener()
    			{
					@Override
					public void itemStateChanged(ItemEvent e) {
						materialSpecific = e.getStateChange() == ItemEvent.DESELECTED;
						inputListener.changeOccured();
					}
    			}, !materialSpecific);
    }
  }

  private void addPage(final EquipmentStatisticsType type, IAnathemaWizardPage page, CheckInputListener inputListener) {
    pagesByType.put(type, page);
    addFollowupPage(page, inputListener, new ICondition() {
      public boolean isFullfilled() {
        return !materialSpecific && model.isEquipmentTypeSelected(type);
      }
    });
    getMaterialsPage().appendPage(page, inputListener, new ICondition() {
        public boolean isFullfilled() {
            return model.isEquipmentTypeSelected(type);
          }
        });
  }
  
  private void addMaterialPage(IAnathemaWizardPage page, CheckInputListener inputListener)
  {
	addFollowupPage(page, inputListener, new ICondition()
	{
		@Override
		public boolean isFullfilled() {
			return materialSpecific && getMaterialsPage().canFlipToNextPage();
		}	
	});
  }
  
  

  private void addStatisticsTypeRow(String label, final EquipmentStatisticsType type) {
    Action action = new SmartAction(properties.getIcon(type)) {
		private static final long serialVersionUID = 1L;

	@Override
      protected void execute(Component parentComponent) {
        model.setEquipmentType(type);
      }
    };
    String typeLabel = properties.getLabel(type);
    view.addStatisticsRow(label, action, typeLabel, model.getEquipmentType() == type);
  }

  public IPageContent getPageContent() {
    return view;
  }

  public IBasicMessage getMessage() {
    return defaultMessage;
  }

  public String getDescription() {
    return properties.getTypeChoiceTitle();
  }
}