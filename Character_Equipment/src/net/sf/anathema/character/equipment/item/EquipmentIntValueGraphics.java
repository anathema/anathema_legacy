package net.sf.anathema.character.equipment.item;

import net.sf.anathema.framework.value.IntValueDisplayGraphics;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public class EquipmentIntValueGraphics implements IntValueDisplayGraphics {

  private IResources resources;

  public EquipmentIntValueGraphics(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Icon getActiveIcon() {
    return new EquipmentUI(resources).getActiveIcon();
  }

  @Override
  public Icon getPassiveIcon() {
    return new EquipmentUI(resources).getPassiveIcon();
  }

  @Override
  public Icon getBlockedIcon() {
    return new EquipmentUI(resources).getBlockedIcon();
  }
}
