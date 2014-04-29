package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public class CloseCombatStatisticsPresenter {
  private final ICloseCombatStatsticsModel closeModel;
  private final IWeaponTagsModel tagModel;
  private final EquipmentStatsView page;
  private final CloseCombatStatisticsProperties properties;
  private final TagPageProperties tagProperties;
  private final WeaponDamageProperties damageProperties;

  public CloseCombatStatisticsPresenter(ICloseCombatStatsticsModel closeModel, IWeaponTagsModel tagModel,
                                        EquipmentStatsView page, Resources resources) {
    this.closeModel = closeModel;
    this.tagModel = tagModel;
    this.page = page;
    this.properties = new CloseCombatStatisticsProperties(resources);
    this.tagProperties = new TagPageProperties(resources);
    this.damageProperties = new WeaponDamageProperties(resources);
  }

  public void initPresentation() {
    String[] labels = new String[]{properties.getSpeedLabel(), properties.getDefenseLabel()};
    page.addLabelledComponentRow(labels,
            new Component[]{page.initIntegerSpinner(closeModel.getSpeedModel()).getComponent(), page.initIntegerSpinner(
                    closeModel.getDefenseModel()).getComponent()}
    );
    new AbstractWeaponPresenter(closeModel, tagModel, page, properties, damageProperties,
            tagProperties).initPresentation();
  }
}
