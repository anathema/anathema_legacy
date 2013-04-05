package net.sf.anathema.character.equipment.item.view.swing;

import net.sf.anathema.framework.value.IntValueDisplayGraphics;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;

public class EquipmentIntValueGraphics implements IntValueDisplayGraphics {

  private final ImageProvider imageProvider = new ImageProvider();

  @Override
  public Icon getActiveIcon() {
    return imageProvider.getImageIcon(new RelativePath("icons/BorderEquipmentButton16.png"));
  }

  @Override
  public Icon getPassiveIcon() {
    return imageProvider.getImageIcon(new RelativePath("icons/BorderUnselectedButton16.png"));
  }

  @Override
  public Icon getBlockedIcon() {
    return imageProvider.getImageIcon(new RelativePath("icons/BorderUnselectableButton16.png"));
  }
}