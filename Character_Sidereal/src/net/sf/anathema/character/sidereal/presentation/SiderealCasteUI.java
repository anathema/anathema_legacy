package net.sf.anathema.character.sidereal.presentation;

import javax.swing.Icon;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.sidereal.caste.ISiderealCasteVisitor;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCasteUI extends AbstractUI {

  public SiderealCasteUI(IResources resources) {
    super(resources);
  }

  public Icon getCasteIcon(IAstrologicalHouse house) {
    final String[] iconName = new String[1];
    SiderealCaste caste = SiderealCaste.valueOf(house.getId());
    caste.accept(new ISiderealCasteVisitor() {
      public void visitJourneys(SiderealCaste journeys) {
        iconName[0] = IIconConstants.SIDEREAL_JOURNEYSCASTE;
      }

      public void visitSerenity(SiderealCaste serenity) {
        iconName[0] = IIconConstants.SIDEREAL_SERENITYCASTE;
      }

      public void visitBattles(SiderealCaste battles) {
        iconName[0] = IIconConstants.SIDEREAL_BATTLESCASTE;
      }

      public void visitSecrets(SiderealCaste secrets) {
        iconName[0] = IIconConstants.SIDEREAL_SECRETSCASTE;
      }

      public void visitEndings(SiderealCaste endings) {
        iconName[0] = IIconConstants.SIDEREAL_ENDINGSCASTE;
      }
    });
    return getIcon(iconName[0]);
  }
}