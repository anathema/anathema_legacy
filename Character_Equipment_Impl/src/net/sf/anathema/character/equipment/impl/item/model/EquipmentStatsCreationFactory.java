package net.sf.anathema.character.equipment.impl.item.model;

import java.awt.Component;

import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.EquipmentTypeChoicePresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentStatsCreationFactory implements IEquipmentStatsCreationFactory {

  public IEquipmentStats createNewStats(Component parentComponent, IResources resources) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel();
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    dialog.show();
    if (dialog.isCanceled()) {
      return null;
    }
    return null;
  }
}