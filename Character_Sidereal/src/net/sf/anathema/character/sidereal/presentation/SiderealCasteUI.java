package net.sf.anathema.character.sidereal.presentation;

import javax.swing.Icon;

import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCasteUI extends AbstractUI {

  public SiderealCasteUI(IResources resources) {
    super(resources);
  }

  public Icon getCasteIcon(IAstrologicalHouse house) {
    SiderealCaste caste = SiderealCaste.valueOf(house.getId());
    String iconName = "SiderealButton" + caste.name() + "16.png"; //$NON-NLS-1$ //$NON-NLS-2$
    return getIcon(iconName);
  }
}