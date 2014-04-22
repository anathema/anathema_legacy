package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.RangedCombatStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;

public class RangedCombatStatisticsPresenterPage extends
        WeaponPresenterPage<IRangedCombatStatisticsModel, RangedCombatStatisticsProperties> {

  public RangedCombatStatisticsPresenterPage(
          Resources resources,
          IEquipmentStatisticsCreationModel model, IWeaponStatisticsView view) {
    super(
            new RangedCombatStatisticsProperties(resources),
            model,
            model.getRangedWeaponStatisticsModel(), view
    );
  }
}