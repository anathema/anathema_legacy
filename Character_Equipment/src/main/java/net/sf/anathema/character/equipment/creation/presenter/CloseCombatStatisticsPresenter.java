package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

public class CloseCombatStatisticsPresenter {
  private final ICloseCombatStatsticsModel closeModel;
  private final IWeaponTagsModel tagModel;
  private final EquipmentStatsView view;
  private final CloseCombatStatisticsProperties properties;
  private final TagPageProperties tagProperties;
  private final WeaponDamageProperties damageProperties;

  public CloseCombatStatisticsPresenter(ICloseCombatStatsticsModel closeModel, IWeaponTagsModel tagModel,
                                        EquipmentStatsView view, Resources resources) {
    this.closeModel = closeModel;
    this.tagModel = tagModel;
    this.view = view;
    this.properties = new CloseCombatStatisticsProperties(resources);
    this.tagProperties = new TagPageProperties(resources);
    this.damageProperties = new WeaponDamageProperties(resources);
  }

  public void initPresentation() {
    addSpinner(properties.getSpeedLabel(), closeModel.getSpeedModel());
    addSpinner(properties.getDefenseLabel(), closeModel.getDefenseModel());
    new BasicWeaponPresenter(closeModel, tagModel, view, properties, damageProperties,
            tagProperties).initPresentation();
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label, model.getValue());
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
