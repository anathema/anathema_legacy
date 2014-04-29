package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public class RangedStatisticsPresenter {
  private final IRangedCombatStatisticsModel rangedModel;
  private final IWeaponTagsModel weaponTagsModel;
  private final EquipmentStatsView view;
  private final RangedCombatStatisticsProperties properties;
  private final WeaponDamageProperties damageProperties;
  private final TagPageProperties tagProperties;

  public RangedStatisticsPresenter(IRangedCombatStatisticsModel rangedModel, IWeaponTagsModel weaponTagsModel,
                                   EquipmentStatsView view, WeaponDamageProperties damageProperties,
                                   Resources resources) {
    this.rangedModel = rangedModel;
    this.weaponTagsModel = weaponTagsModel;
    this.view = view;
    this.damageProperties = damageProperties;
    this.properties = new RangedCombatStatisticsProperties(resources);
    this.tagProperties = new TagPageProperties(resources);
  }

  public void initPresentation() {
    String[] labels = new String[]{properties.getSpeedLabel(), properties.getRangeLabel()};
    view.addLabelledComponentRow(labels, new Component[]{view.initIntegerSpinner(
            rangedModel.getSpeedModel()).getComponent(), view.initIntegerSpinner(
            rangedModel.getRangeModel()).getComponent()});
    new AbstractWeaponPresenter(rangedModel, weaponTagsModel, view, properties, damageProperties,
            tagProperties).initPresentation();
  }
}
