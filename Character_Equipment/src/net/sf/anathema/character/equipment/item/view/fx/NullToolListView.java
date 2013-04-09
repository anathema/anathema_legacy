package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.tool.FxButtonTool;

class NullToolListView implements ToolListView<IEquipmentStats> {
  @Override
  public void setObjects(IEquipmentStats[] items) {
    //Nothing to do
  }

  @Override
  public void addListSelectionListener(Runnable listener) {
    //Nothing to do
  }

  @Override
  public IEquipmentStats[] getSelectedItems() {
    return new IEquipmentStats[0];
  }

  @Override
  public Tool addTool() {
    return new FxButtonTool();
  }
}