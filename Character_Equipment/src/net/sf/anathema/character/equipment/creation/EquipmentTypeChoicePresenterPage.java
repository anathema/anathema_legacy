package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.EquipmentTypeChoiceProperties;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTypeChoicePresenterPage extends AbstractAnathemaWizardPage implements IPresenter {

  private final IEquipmentStatisticsCreationModel model;
  private final EquipmentTypeChoiceProperties properties;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private IEquipmentTypeChoiceView view;

  public EquipmentTypeChoicePresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = new EquipmentTypeChoiceProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  public boolean canFinish() {
    return model.getEquipmentType() != null;
  }

  public void initPresentation() {
    this.view = viewFactory.createTypeChoiceView();
    String label = properties.getOffensiveLabel();
    addStatisticsTypeRow(label, EquipmentStatisticsType.CloseCombat);
    addStatisticsTypeRow("", EquipmentStatisticsType.RangedCombat); //$NON-NLS-1$
    view.addHorizontalLine();
    addStatisticsTypeRow(properties.getDefensiveLabel(), EquipmentStatisticsType.Armor);
    addStatisticsTypeRow("", EquipmentStatisticsType.Shield); //$NON-NLS-1$
  }

  private void addStatisticsTypeRow(String label, final EquipmentStatisticsType type) {
    Action action = new SmartAction(properties.getIcon(type)) {
      @Override
      protected void execute(Component parentComponent) {
        model.setEquipmentType(type);
      }
    };
    String typeLabel = properties.getLabel(type);
    view.addStatisticsRow(label, action, typeLabel);
  }

  public IBasicWizardPage getNextPage() {
    return null;
  }

  public IBasicWizardPage getPreviousPage() {
    return null;
  }

  public IPageContent getPageContent() {
    return view;
  }

  public IBasicMessage getMessage() {
    return new BasicMessage(properties.getTypeChoiceMessage());
  }

  public String getDescription() {
    return properties.getTypeChoiceTitle();
  }
}