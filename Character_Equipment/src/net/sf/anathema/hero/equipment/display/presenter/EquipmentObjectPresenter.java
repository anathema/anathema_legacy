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
import net.sf.anathema.lib.model.BooleanModel;
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
    prepareContents();
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

  private void prepareContents() {
    view.clear();
    presentationModel.clear();
    for (final IEquipmentStats equipment : model.getStats()) {
      StatsPresentationStrategy strategy = choosePresentationStrategy(equipment);
      if (!strategy.shouldStatsBeShown()) {
        continue;
      }
      final BooleanModel booleanModel = view.addStats(createEquipmentDescription(model, equipment));
      if (equipment instanceof ArtifactStats) {
        presentationModel.registerViewForAttunementStats(equipment, booleanModel);
      } else {
        presentationModel.registerViewForDefaultStats(equipment, booleanModel);
      }
      booleanModel.setValue(model.isPrintEnabled(equipment));
      booleanModel.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          model.setPrintEnabled(equipment, booleanModel.getValue());
          if (equipment instanceof ArtifactStats) {
            // if we are enabling an attunement stats ...
            if (booleanModel.getValue()) {
              // disable all other attunement stats
              for (IEquipmentStats stats : presentationModel.getAttunementStats()) {
                if (!equipment.equals(stats) && model.isPrintEnabled(stats)) {
                  model.setPrintEnabled(stats, false);
                }
              }
            }
            prepareContents();
          }
        }
      });
      showEligibleSpecialties(equipment, booleanModel);
    }
    disableAllStatsIfAttunementRequiredButNotGiven();
  }

  private void disableAllStatsIfAttunementRequiredButNotGiven() {
    boolean isAttunementRequired = isAttunementRequired();
    boolean isCurrentlyAttuned = isCurrentlyAttuned();
    if (isAttunementRequired && !isCurrentlyAttuned) {
      for (BooleanModel bool : presentationModel.getDefaultStatViews()) {
        view.setEnabled(bool, false);
        bool.setValue(false);
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

  private void showEligibleSpecialties(IEquipmentStats equipment, BooleanModel booleanModel) {
    if (!(equipment instanceof IWeaponStats)) {
      return;
    }
    addSpecialtiesForWeaponStats(booleanModel, (IWeaponStats) equipment);
  }

  private void addSpecialtiesForWeaponStats(BooleanModel baseModel, IWeaponStats weaponStats) {
    Specialty[] specialties = dataProvider.getSpecialties(weaponStats.getTraitType());
    for (Specialty specialty : specialties) {
      String label = MessageFormat.format(resources.getString("Equipment.Specialty"), specialty.getName());
      final BooleanModel booleanModel = view.addOptionFlag(baseModel, label);
      final IEquipmentStatsOption specialtyOption = new EquipmentSpecialtyOption(specialty, weaponStats.getTraitType());
      final IEquipmentStats baseStat = model.getStat(weaponStats.getId());
      booleanModel.setValue(characterOptionProvider.isStatOptionEnabled(model, baseStat, specialtyOption));
      booleanModel.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          if (booleanModel.getValue()) characterOptionProvider.enableStatOption(model, baseStat, specialtyOption);
          else characterOptionProvider.disableStatOption(model, baseStat, specialtyOption);
        }
      });
    }
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}