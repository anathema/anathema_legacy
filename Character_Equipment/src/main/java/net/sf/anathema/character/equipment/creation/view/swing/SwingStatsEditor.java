package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.ArmourStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.ArtifactStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.CloseCombatStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.ModifierStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.RangedStatisticsPresenter;
import net.sf.anathema.character.equipment.creation.presenter.StatsNamePresenter;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.item.model.ModelToStats;
import net.sf.anathema.character.equipment.item.model.NullClosure;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.DialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.util.Closure;

import javax.swing.SwingUtilities;

public class SwingStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void editStats(Resources resources, IEquipmentStatisticsCreationModel model) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        runDialog(resources, model);
      }
    });
  }

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  private void runDialog(Resources resources, final IEquipmentStatisticsCreationModel model) {
    IDialogPage startPage = chooseStartPage(resources, model);
    UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), startPage);
    dialog.show(new CreateStatsHandler(model));
  }

  private IDialogPage chooseStartPage(Resources resources, IEquipmentStatisticsCreationModel model) {
    WeaponStatisticsView view = new WeaponStatisticsView();
    switch (model.getEquipmentType()) {
      case CloseCombat:
        return createCloseCombatPage(resources, model, view);
      case RangedCombat:
        return createRangedCombatPage(resources, model, view);
      case Armor:
        return createArmourPage(resources, model, view);
      case Artifact:
        return createArtifactPage(resources, model, view);
      case TraitModifying:
        return createTraitModifyingPage(resources, model, view);
      default:
        throw new IllegalArgumentException("Type must be defined to edit.");
    }
  }

  private IDialogPage createCloseCombatPage(Resources resources, IEquipmentStatisticsCreationModel model, WeaponStatisticsView view) {
    CloseCombatStatisticsProperties properties = new CloseCombatStatisticsProperties(resources);
    ICloseCombatStatsticsModel closeModel = model.getCloseCombatStatsticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    CloseCombatStatisticsPresenterPage page = new CloseCombatStatisticsPresenterPage(resources, model, view);
    TagPageProperties tagProperties = new TagPageProperties(resources);
    WeaponDamageProperties damageProperties = new WeaponDamageProperties(resources);
    new StatsNamePresenter(view, closeModel, properties).initPresentation();
    new CloseCombatStatisticsPresenter(closeModel, tagModel,  page, properties, tagProperties, damageProperties).initPresentation();
    return page;
  }

  private IDialogPage createRangedCombatPage(Resources resources, IEquipmentStatisticsCreationModel model, WeaponStatisticsView view) {
    RangedCombatStatisticsProperties properties = new RangedCombatStatisticsProperties(resources);
    IRangedCombatStatisticsModel rangedModel = model.getRangedWeaponStatisticsModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    RangedCombatStatisticsPresenterPage page = new RangedCombatStatisticsPresenterPage(resources, model, view);
    TagPageProperties tagProperties = new TagPageProperties(resources);
    WeaponDamageProperties damageProperties = new WeaponDamageProperties(resources);
    new StatsNamePresenter(view, rangedModel, properties).initPresentation();
    new RangedStatisticsPresenter(rangedModel, tagModel,  page, properties, tagProperties, damageProperties).initPresentation();
    return page;
  }

  private IDialogPage createArtifactPage(Resources resources, IEquipmentStatisticsCreationModel model, WeaponStatisticsView view) {
    ArtifactStatisticsProperties properties = new ArtifactStatisticsProperties(resources);
    IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
    EquipmentStatisticsPresenterPage<IArtifactStatisticsModel, ArtifactStatisticsProperties> page = new EquipmentStatisticsPresenterPage<>(properties, model, artifactModel, view);
    new StatsNamePresenter(view, artifactModel, properties).initPresentation();
    new ArtifactStatisticsPresenter(artifactModel, page, properties).initPresentation();
    return page;
  }

  private IDialogPage createTraitModifyingPage(Resources resources, IEquipmentStatisticsCreationModel model, WeaponStatisticsView view) {
    TraitBoostStatisticsProperties properties = new TraitBoostStatisticsProperties(resources);
    ITraitModifyingStatisticsModel modModel = model.getTraitModifyingStatisticsModel();
    EquipmentStatisticsPresenterPage<ITraitModifyingStatisticsModel, TraitBoostStatisticsProperties> page = new EquipmentStatisticsPresenterPage<>(properties, model, modModel, view);
    new StatsNamePresenter(view, modModel, properties).initPresentation();
    new ModifierStatisticsPresenter(modModel, page, properties).initPresentation();
    return page;
  }

  private IDialogPage createArmourPage(Resources resources, IEquipmentStatisticsCreationModel model, WeaponStatisticsView view) {
    ArmourStatisticsProperties armourStatisticsProperties = new ArmourStatisticsProperties(resources);
    IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
    EquipmentStatisticsPresenterPage<IArmourStatisticsModel, ArmourStatisticsProperties> page = new EquipmentStatisticsPresenterPage<>(armourStatisticsProperties, model, armourModel, view);
    new StatsNamePresenter(view, armourModel, armourStatisticsProperties).initPresentation();
    new ArmourStatisticsPresenter(armourModel, page, armourStatisticsProperties).initPresentation();
    return page;
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
      IEquipmentStats stats = modelToStats.createStats(model);
      whenChangesAreFinished.execute(stats);
    }
  }
}