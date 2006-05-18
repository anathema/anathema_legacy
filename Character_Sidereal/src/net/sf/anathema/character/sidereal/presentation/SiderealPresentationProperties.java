package net.sf.anathema.character.sidereal.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.sidereal.caste.ISiderealCasteVisitor;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;

public class SiderealPresentationProperties extends AbstractPresentationProperties {

  public static String getSideralCasteIconResource(String casteId) {
    final String[] iconName = new String[1];
    SiderealCaste caste = SiderealCaste.valueOf(casteId);
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
    return iconName[0];
  }

  private final ICharmPresentationProperties charmPresentationProperties = new SiderealCharmPresentationProperties();

  public SiderealPresentationProperties() {
    super(new TemplateType(CharacterType.SIDEREAL));
  }

  public String getBallResource() {
    return IIconConstants.SIDEREAL_BALL;
  }

  public String getSmallCasteIconResource(String casteId) {
    final String[] iconName = new String[1];
    SiderealCaste caste = SiderealCaste.valueOf(casteId);
    caste.accept(new ISiderealCasteVisitor() {
      public void visitJourneys(SiderealCaste journeys) {
        iconName[0] = IIconConstants.SIDEREAL_JOURNEYSCASTE_SMALL;
      }

      public void visitSerenity(SiderealCaste serenity) {
        iconName[0] = IIconConstants.SIDEREAL_SERENITYCASTE_SMALL;
      }

      public void visitBattles(SiderealCaste battles) {
        iconName[0] = IIconConstants.SIDEREAL_BATTLESCASTE_SMALL;
      }

      public void visitSecrets(SiderealCaste secrets) {
        iconName[0] = IIconConstants.SIDEREAL_SECRETSCASTE_SMALL;
      }

      public void visitEndings(SiderealCaste endings) {
        iconName[0] = IIconConstants.SIDEREAL_ENDINGSCASTE_SMALL;
      }
    });
    return iconName[0];
  }

  public String getMediumCasteIconResource(String groupId) {
    return getSideralCasteIconResource(groupId);
  }

  public Color getColor() {
    return new Color(147, 112, 219);
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }
}