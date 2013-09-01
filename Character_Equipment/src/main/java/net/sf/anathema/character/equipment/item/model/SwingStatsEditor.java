package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArmourStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArtifactStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.CloseCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.presenter.stats.RangedCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.TraitModifyingStatisticsPresenterPage;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.equipment.editor.wizard.AnathemaWizardDialog;
import net.sf.anathema.equipment.editor.wizard.IAnathemaWizardPage;
import net.sf.anathema.equipment.editor.wizard.WizardDialog;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.DialogCloseHandler;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Closure;

import javax.swing.SwingUtilities;

public class SwingStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void editStats(final Resources resources, final String[] definedNames, final IEquipmentStats stats) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        doIt(resources, definedNames, stats);
      }
    });
  }

  private void doIt(Resources resources, String[] definedNames, IEquipmentStats stats) {
    IEquipmentStatisticsCreationModel model = new StatsToModel().createModel(stats);
    model.setForbiddenNames(definedNames);
    runDialog(resources, model);
  }

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  private void runDialog(Resources resources, final IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    IAnathemaWizardPage startPage = chooseStartPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(SwingApplicationFrame.getParentComponent(), startPage);
    dialog.show(new CreateStatsHandler(model));
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

  private class CreateStatsHandler implements DialogCloseHandler {
    private final IEquipmentStatisticsCreationModel model;

    public CreateStatsHandler(IEquipmentStatisticsCreationModel model) {
      this.model = model;
    }

    @Override
    public void handleDialogClose(DialogResult result) {
      if (result.isCanceled()) {
        return;
      }
      whenChangesAreFinished.execute(modelToStats.createStats(model));
    }
  }
}