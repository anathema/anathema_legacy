package net.sf.anathema.character.equipment.item.view.swing;

import net.sf.anathema.framework.value.IntValueDisplayGraphics;
import net.sf.anathema.lib.gui.image.ImageProvider;

import javax.swing.Icon;

public class EquipmentIntValueGraphics implements IntValueDisplayGraphics {

  private final ImageProvider imageProvider = new ImageProvider(".");

  @Override
  public Icon getActiveIcon() {
    return imageProvider.getImageIcon("icons/BorderEquipmentButton16.png");
  }

  @Override
  public Icon getPassiveIcon() {
    return imageProvider.getImageIcon("icons/BorderUnselectedButton16.png");
  }

  @Override
  public Icon getBlockedIcon() {
    return imageProvider.getImageIcon("icons/BorderUnselectableButton16.png");
  }
}