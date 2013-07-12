package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.main.equipment.ArtifactStats;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.equipment.model.EquipmentItemPresentationModel;
import net.sf.anathema.hero.equipment.model.EquipmentSpecialtyOption;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;

import java.text.MessageFormat;

public class EquipmentObjectPresenter {

  public static final String EQUIPMENT_NAME_PREFIX = "Equipment.Name.";
  private static final String DESCRIPTION_PREFIX = "Equipment.Description.";
  private final EquipmentItemPresentationModel presentationModel = new EquipmentItemPresentationModel();
  private final IEquipmentItem model;
  private final EquipmentObjectView view;
  private final IEquipmentStringBuilder stringBuilder;
  private final EquipmentOptionsProvider characterOptionProvider;
  private final EquipmentHeroEvaluator dataProvider;
  private final Resources resources;

  public EquipmentObjectPresenter(IEquipmentItem model, EquipmentObjectView view,
                                  IEquipmentStringBuilder stringBuilder, EquipmentHeroEvaluator dataProvider,
                                  EquipmentOptionsProvider characterOptionProvider, Resources resources) {
    this.model = model;
    this.view = view;
    this.stringBuilder = stringBuilder;
    this.characterOptionProvider = characterOptionProvider;
    this.resources = resources;
    this.dataProvider = dataProvider;
  }

  public Tool addContextTool() {
    return view.addAction();
  }

  public void initPresentation() {
    showItemTitle();
    showItemDescription();
    refreshView();
  }

  private void showItemTitle() {
    String itemTitle = model.getTitle();
    boolean customTitle = !model.getTemplateId().equals(itemTitle);
    if (resources.supportsKey(EQUIPMENT_NAME_PREFIX + itemTitle)) {
      itemTitle = resources.getString(EQUIPMENT_NAME_PREFIX + itemTitle);
    }
    if (!customTitle && model.getMaterialComposition() == MaterialComposition.Variable) {
      String materialString = resources.getString("MagicMaterial." + model.getMaterial().name());
      itemTitle += " (" + materialString + ")";
    }
    view.setItemTitle(itemTitle);
  }

  private void showItemDescription() {
    String description = model.getDescription();
    if (resources.supportsKey(DESCRIPTION_PREFIX + description)) {
      description = resources.getString(DESCRIPTION_PREFIX + description);
    }
    if (description != null) {
      view.setItemDescription(description);
    }
  }

  private void refreshView() {
    view.clear();
    presentationModel.clear();
    for (final IEquipmentStats stats : model.getStats()) {
      StatsPresentationStrategy strategy = choosePresentationStrategy(stats);
      if (!strategy.shouldStatsBeShown()) {
        continue;
      }
      final StatsView statsView = view.addStats(createEquipmentDescription(model, stats));
      statsView.setSelected(model.isPrintEnabled(stats));
      statsView.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          model.setPrintEnabled(stats, statsView.getSelected());
          if (stats instanceof ArtifactStats) {
            boolean userHasEnabledAttunementStats = statsView.getSelected();
            if (userHasEnabledAttunementStats) {
              disableAllOtherAttunementStats(stats);
            }
            refreshView();
          }
        }
      });
      if (stats instanceof ArtifactStats) {
        presentationModel.registerViewForAttunementStats(stats, statsView);
      } else {
        presentationModel.registerViewForDefaultStats(stats, statsView);
      }
      showEligibleSpecialties(stats, statsView);
    }
    disableAllStatsIfAttunementRequiredButNotGiven();
  }

  private void disableAllOtherAttunementStats(IEquipmentStats equipment) {
    for (IEquipmentStats stats : presentationModel.getAttunementStats()) {
      if (!equipment.equals(stats) && model.isPrintEnabled(stats)) {
        model.setPrintEnabled(stats, false);
      }
    }
  }

  private void disableAllStatsIfAttunementRequiredButNotGiven() {
    boolean isAttunementRequired = isAttunementRequired();
    boolean isCurrentlyAttuned = isCurrentlyAttuned();
    if (isAttunementRequired && !isCurrentlyAttuned) {
      for (StatsView statsView : presentationModel.getDefaultStatViews()) {
        statsView.disable();
        statsView.setSelected(false);
      }
    }
  }

  private boolean isCurrentlyAttuned() {
    for (IEquipmentStats stats : model.getStats()) {
      if (stats instanceof ArtifactStats) {
        if (model.isPrintEnabled(stats)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isAttunementRequired() {
    for (IEquipmentStats stats : model.getStats()) {
      if (stats instanceof ArtifactStats) {
        if (((ArtifactStats) stats).requireAttunementToUse()) {
          return true;
        }
      }
    }
    return false;
  }

  private StatsPresentationStrategy choosePresentationStrategy(IEquipmentStats equipment) {
    if (equipment instanceof ArtifactStats) {
      return new ArtifactPresentationStrategy((ArtifactStats) equipment, dataProvider, model);
    } else {
      return new DefaultPresentationStrategy(equipment);
    }
  }

  private void showEligibleSpecialties(IEquipmentStats equipment, StatsView statsView) {
    if (!(equipment instanceof IWeaponStats)) {
      return;
    }
    addSpecialtiesForWeaponStats(statsView, (IWeaponStats) equipment);
  }

  private void addSpecialtiesForWeaponStats(StatsView baseView, IWeaponStats weaponStats) {
    Specialty[] specialties = dataProvider.getSpecialties(weaponStats.getTraitType());
    for (Specialty specialty : specialties) {
      String label = MessageFormat.format(resources.getString("Equipment.Specialty"), specialty.getName());
      final StatsView statsView = baseView.addOptionFlag(label);
      final IEquipmentStatsOption specialtyOption = new EquipmentSpecialtyOption(specialty, weaponStats.getTraitType());
      final IEquipmentStats baseStat = model.getStat(weaponStats.getId());
      statsView.setSelected(characterOptionProvider.isStatOptionEnabled(model, baseStat, specialtyOption));
      statsView.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          if (statsView.getSelected()) characterOptionProvider.enableStatOption(model, baseStat, specialtyOption);
          else characterOptionProvider.disableStatOption(model, baseStat, specialtyOption);
        }
      });
    }
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}