package net.sf.anathema.character.equipment.creation;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;

public class CloseCombatStatisticsPresenterPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final IEquipmentStatisticsCreationModel model;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final CloseCombatStatisticsProperties properties;
  private ICloseCombatStatisticsView view;

  public CloseCombatStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = new CloseCombatStatisticsProperties(resources);
    this.resources = resources;
    this.model = model;
    this.viewFactory = viewFactory;
  }

  public IBasicWizardPage getNextPage() {
    return null;
  }

  public boolean canFinish() {
    return true;
  }

  public IPageContent getPageContent() {
    return view;
  }

  public IBasicMessage getMessage() {
    return new BasicMessage(properties.getDefaultMessage());
  }

  public String getDescription() {
    return properties.getPageDescription();
  }

  public void initPresentation(CheckInputListener inputListener) {
    this.view = viewFactory.createCloseCombatStatisticsView();
  }
}