package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.item.model.ModelToStats;
import net.sf.anathema.character.equipment.item.model.NullClosure;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.dialog.core.OperationResult;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.util.Closure;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Armor;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.CloseCombat;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.RangedCombat;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.TraitModifying;

public class AgnosticStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  @Override
  public void editStats(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    initPresentation(resources, model, dialog);
    dialog.show(new CreateStatsHandler(model));
  }

  private void initPresentation(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    if (CloseCombat == model.getEquipmentType()) {
      initCloseCombatPresentation(resources, model, dialog);
    } else if (RangedCombat == model.getEquipmentType()) {
      initRangedCombatPresentation(resources, model, dialog);
    } else if (Armor == model.getEquipmentType()) {
      initArmourPresentation(resources, model, dialog);
    } else if (Artifact == model.getEquipmentType()) {
      initArtifactPresentation(resources, model, dialog);
    } else if (TraitModifying == model.getEquipmentType()) {
      initTraitModifyingPresentation(resources, model, dialog);
    }
  }

  private void initCloseCombatPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                           EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    ICloseCombatStatsticsModel closeModel = model.getCloseCombatStatsticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    new GeneralStatsPresenter(view, dialog, closeModel, model, resources).initPresentation();
    new CloseCombatStatisticsPresenter(closeModel, tagModel, view, resources).initPresentation();
  }

  private void initRangedCombatPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                            EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IRangedCombatStatisticsModel rangedModel = model.getRangedWeaponStatisticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    WeaponDamageProperties damageProperties = new WeaponDamageProperties(resources);
    new GeneralStatsPresenter(view, dialog, rangedModel, model, resources).initPresentation();
    new RangedStatisticsPresenter(rangedModel, tagModel, view, damageProperties, resources).initPresentation();
  }

  private void initArtifactPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                        EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
    new GeneralStatsPresenter(view, dialog, artifactModel, model, resources).initPresentation();
    new ArtifactStatisticsPresenter(artifactModel, view, resources).initPresentation();
  }

  private void initTraitModifyingPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                              EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    ITraitModifyingStatisticsModel modModel = model.getTraitModifyingStatisticsModel();
    new GeneralStatsPresenter(view, dialog, modModel, model, resources).initPresentation();
    new ModifierStatisticsPresenter(modModel, view, resources).initPresentation();
  }

  private void initArmourPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                      EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
    new GeneralStatsPresenter(view, dialog, armourModel, model, resources).initPresentation();
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