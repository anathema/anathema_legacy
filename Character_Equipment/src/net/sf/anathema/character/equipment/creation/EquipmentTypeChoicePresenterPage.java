package net.sf.anathema.character.equipment.creation;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

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
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTypeChoicePresenterPage extends AbstractAnathemaWizardPage {

  private final Map<EquipmentStatisticsType, IAnathemaWizardPage> pagesByType = new HashMap<EquipmentStatisticsType, IAnathemaWizardPage>();
  private final IEquipmentStatisticsCreationModel model;
  private final EquipmentTypeChoiceProperties properties;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private IEquipmentTypeChoiceView view;
  private final IResources resources;

  public EquipmentTypeChoicePresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new EquipmentTypeChoiceProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  public boolean canFinish() {
    return false;
  }

  public void initPresentation(CheckInputListener inputListener) {
    model.addEquipmentTypeChangeListener(inputListener);
    addPage(
        EquipmentStatisticsType.CloseCombat,
        new CloseCombatStatisticsPresenterPage(resources, model, viewFactory),
        inputListener);
    this.view = viewFactory.createTypeChoiceView();
    String label = properties.getOffensiveLabel();
    addStatisticsTypeRow(label, EquipmentStatisticsType.CloseCombat);
    addStatisticsTypeRow("", EquipmentStatisticsType.RangedCombat); //$NON-NLS-1$
    view.addHorizontalLine();
    addStatisticsTypeRow(properties.getDefensiveLabel(), EquipmentStatisticsType.Armor);
    addStatisticsTypeRow("", EquipmentStatisticsType.Shield); //$NON-NLS-1$
  }

  private void addPage(EquipmentStatisticsType type, IAnathemaWizardPage page, CheckInputListener inputListener) {
    pagesByType.put(type, page);
    page.initPresentation(inputListener);
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
    return pagesByType.get(model.getEquipmentType());
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