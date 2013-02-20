package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;

public class EquipmentUI extends AbstractUI {
  public EquipmentUI(IResources resources) {
    super(resources);
  }

  public Icon getActiveIcon() {
    return getIcon("BorderEquipmentButton16.png"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Icon getPassiveIcon() {
    return getIcon("BorderUnselectedButton16.png"); //$NON-NLS-1$
  }

  public Icon getBlockedIcon() {
    return getIcon("BorderUnselectableButton16.png"); //$NON-NLS-1$
  }
}
