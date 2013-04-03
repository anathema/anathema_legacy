package net.sf.anathema.character.equipment.item.view.swing;

import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.framework.value.IntValueDisplayGraphics;

import javax.swing.Icon;

public class EquipmentIntValueGraphics implements IntValueDisplayGraphics {

  private final ImageProvider imageProvider = new ImageProvider(".");

  @Override
  public Icon getActiveIcon() {
    return imageProvider.getImageIcon(EquipmentIntValueGraphics.class, "icons/BorderEquipmentButton16.png");
  }

  @Override
  public Icon getPassiveIcon() {
    return imageProvider.getImageIcon(EquipmentIntValueGraphics.class, "icons/BorderUnselectedButton16.png");
  }

  @Override
  public Icon getBlockedIcon() {
    return imageProvider.getImageIcon(EquipmentIntValueGraphics.class, "icons/BorderUnselectableButton16.png");
  }
}