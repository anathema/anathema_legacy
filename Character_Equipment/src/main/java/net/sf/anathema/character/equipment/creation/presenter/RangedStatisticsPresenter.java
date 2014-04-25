package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.creation.view.swing.EquipmentStatsView;

import java.awt.Component;

public class RangedStatisticsPresenter {
  private final IRangedCombatStatisticsModel rangedModel;
  private final IWeaponTagsModel weaponTagsModel;
  private final EquipmentStatsView view;
  private final RangedCombatStatisticsProperties properties;
  private final WeaponDamageProperties damageProperties;
  private final TagPageProperties tagProperties;

  public RangedStatisticsPresenter(IRangedCombatStatisticsModel rangedModel, IWeaponTagsModel weaponTagsModel, EquipmentStatsView view, RangedCombatStatisticsProperties properties, TagPageProperties tagProperties, WeaponDamageProperties damageProperties) {
    this.rangedModel = rangedModel;
    this.weaponTagsModel = weaponTagsModel;
    this.view = view;
    this.properties = properties;
    this.damageProperties = damageProperties;
    this.tagProperties= tagProperties;
  }

  public void initPresentation() {
    String[] labels = new String[]{properties.getSpeedLabel(), properties.getRangeLabel()};
    view.addLabelledComponentRow(labels, new Component[]{
            view.initIntegerSpinner(rangedModel.getSpeedModel()).getComponent(),
            view.initIntegerSpinner(rangedModel.getRangeModel()).getComponent()});
    new AbstractWeaponPresenter(rangedModel, weaponTagsModel, view, properties, damageProperties, tagProperties).initPresentation();
  }
}
