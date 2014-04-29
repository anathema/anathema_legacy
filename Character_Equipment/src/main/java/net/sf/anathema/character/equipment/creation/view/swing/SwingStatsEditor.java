package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.ArmourStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.ArtifactStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.CloseCombatStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.character.equipment.creation.presenter.GeneralStatsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.ModifierStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.RangedStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.item.model.ModelToStats;
import net.sf.anathema.character.equipment.item.model.NullClosure;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.dialog.core.OperationResult;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.util.Closure;

import javax.swing.SwingUtilities;

public class SwingStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void editStats(Resources resources, IEquipmentStatisticsCreationModel model) {
    SwingUtilities.invokeLater(() -> runDialog(resources, model));
  }

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  private void runDialog(Resources resources, final IEquipmentStatisticsCreationModel model) {
    EquipmentStatisticsPresenterPage view = new EquipmentStatisticsPresenterPage();
    initPresentation(resources, model, view);
    UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), view);
    dialog.show(new CreateStatsHandler(model));
  }

  private void initPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                       EquipmentStatsView view) {
    switch (model.getEquipmentType()) {
      case CloseCombat:
        initCloseCombatPresentation(resources, model, view);
        break;
      case RangedCombat:
        initRangedCombatPresentation(resources, model, view);
        break;
      case Armor:
        initArmourPresentation(resources, model, view);
        break;
      case Artifact:
        initArtifactPresentation(resources, model, view);
        break;
      case TraitModifying:
        initTraitModifyingPresentation(resources, model, view);
        break;
      default:
        throw new IllegalArgumentException("Type must be defined to edit.");
    }
  }

  private void initCloseCombatPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                           EquipmentStatsView view) {
    ICloseCombatStatsticsModel closeModel = model.getCloseCombatStatsticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    new GeneralStatsPresenter(view, closeModel, model, resources).initPresentation();
    new CloseCombatStatisticsPresenter(closeModel, tagModel, view, resources).initPresentation();
  }

  private void initRangedCombatPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                            EquipmentStatsView view) {
    IRangedCombatStatisticsModel rangedModel = model.getRangedWeaponStatisticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    WeaponDamageProperties damageProperties = new WeaponDamageProperties(resources);
    new GeneralStatsPresenter(view, rangedModel, model, resources).initPresentation();
    new RangedStatisticsPresenter(rangedModel, tagModel, view, damageProperties, resources).initPresentation();
  }

  private void initArtifactPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                        EquipmentStatsView view) {
    IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
    new GeneralStatsPresenter(view, artifactModel, model, resources).initPresentation();
    new ArtifactStatisticsPresenter(artifactModel, view, resources).initPresentation();
  }

  private void initTraitModifyingPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                              EquipmentStatsView view) {
    ITraitModifyingStatisticsModel modModel = model.getTraitModifyingStatisticsModel();
    new GeneralStatsPresenter(view, modModel, model, resources).initPresentation();
    new ModifierStatisticsPresenter(modModel, view, resources).initPresentation();
  }

  private void initArmourPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                      EquipmentStatsView view) {
    IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
    new GeneralStatsPresenter(view, armourModel, model, resources).initPresentation();
    new ArmourStatisticsPresenter(armourModel, view, resources).initPresentation();
  }

  private class CreateStatsHandler implements OperationResultHandler {
    private final IEquipmentStatisticsCreationModel model;

    public CreateStatsHandler(IEquipmentStatisticsCreationModel model) {
      this.model = model;
    }

    @Override
    public void handleOperationResult(OperationResult result) {
      if (result.isCanceled()) {
        return;
      }
      IEquipmentStats stats = modelToStats.createStats(model);
      whenChangesAreFinished.execute(stats);
    }
  }
}