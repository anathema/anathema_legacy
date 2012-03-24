package net.sf.anathema.character.equipment.item;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public final class AddNewStatsAction extends SmartAction {
  private IExaltedRuleSet ruleset;
  private final IEquipmentStatsCreationFactory statsFactory;
  private final IResources resources;
  private final IEquipmentTemplateEditModel editModel;

  public AddNewStatsAction(IResources resources, IEquipmentTemplateEditModel editModel, IExaltedRuleSet ruleset,
                           IEquipmentStatsCreationFactory statsFactory) {
    super(new BasicUi(resources).getAddIcon());
    this.resources = resources;
    this.editModel = editModel;
    this.ruleset = ruleset;
    this.statsFactory = statsFactory;
    setToolTipText(resources.getString("Equipment.Creation.Stats.AddActionTooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    List<String> definedNames = new ArrayList<String>();
    for (IEquipmentStats stats : editModel.getStats(ruleset)) {
      definedNames.add(stats.getName().getId());
    }
    String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
    IEquipmentStats equipmentStats = statsFactory.createNewStats(parentComponent, resources, editModel, nameArray);
    if (equipmentStats == null) {
      return;
    }
    editModel.addStatistics(ruleset, equipmentStats);
  }
}