package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArmourStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArtifactStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.CloseCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.presenter.stats.RangedCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.TraitModifyingStatisticsPresenterPage;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.wizard.AnathemaWizardDialog;
import net.sf.anathema.character.equipment.wizard.IAnathemaWizardPage;
import net.sf.anathema.character.equipment.wizard.WizardDialog;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.resources.Resources;

public class SwingStatsEditor implements StatsEditor {

  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public IEquipmentStats editStats(Resources resources, String[] definedNames, IEquipmentStats stats) {
    IEquipmentStatisticsCreationModel model = new StatsToModel().createModel(stats);
    model.setForbiddenNames(definedNames);
    return runDialog(resources, model);
  }

  private IEquipmentStats runDialog(Resources resources, IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    IAnathemaWizardPage startPage = chooseStartPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(SwingApplicationFrame.getParentComponent(), startPage);
    DialogResult result = dialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return modelToStats.createStats(model);
  }

  private IAnathemaWizardPage chooseStartPage(Resources resources, IEquipmentStatisticsCreationModel model,
                                              IEquipmentStatisticsCreationViewFactory viewFactory) {
    switch (model.getEquipmentType()) {
      case CloseCombat:
        return new CloseCombatStatisticsPresenterPage(resources, model, viewFactory);
      case RangedCombat:
        return new RangedCombatStatisticsPresenterPage(resources, model, viewFactory);
      case Armor:
        return new ArmourStatisticsPresenterPage(resources, model, viewFactory);
      case Artifact:
        return new ArtifactStatisticsPresenterPage(resources, model, viewFactory);
      case TraitModifying:
        return new TraitModifyingStatisticsPresenterPage(resources, model, viewFactory);
      default:
        throw new IllegalArgumentException("Type must be defined to edit.");
    }
  }
}