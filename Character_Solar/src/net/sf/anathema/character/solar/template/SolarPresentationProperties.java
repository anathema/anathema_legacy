package net.sf.anathema.character.solar.template;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.solar.caste.ISolarCasteVisitor;
import net.sf.anathema.character.solar.caste.SolarCaste;

public final class SolarPresentationProperties extends AbstractPresentationProperties {
  private ICharmPresentationProperties charmProperties = new SolarCharmPresentationProperties();

  public SolarPresentationProperties() {
    super(new TemplateType(CharacterType.SOLAR));
  }

  public String getCasteIconResource(String groupId) {
    return getSolarIcon(groupId);
  }

  public String getBallResource() {
    return IIconConstants.SOLAR_BALL;
  }

  public Color getColor() {
    return new Color(255, 215, 0);
  }

  private String getSolarIcon(String groupId) {
    final String[] iconName = new String[1];
    SolarCaste solarCaste = SolarCaste.valueOf(groupId);
    solarCaste.accept(new ISolarCasteVisitor() {
      public void visitDawn(SolarCaste caste) {
        iconName[0] = IIconConstants.SOLAR_DAWNCASTE;
      }

      public void visitZenith(SolarCaste caste) {
        iconName[0] = IIconConstants.SOLAR_ZENITHCASTE;
      }

      public void visitTwilight(SolarCaste caste) {
        iconName[0] = IIconConstants.SOLAR_TWILIGHTCASTE;
      }

      public void visitNight(SolarCaste caste) {
        iconName[0] = IIconConstants.SOLAR_NIGHTCASTE;
      }

      public void visitEclipse(SolarCaste caste) {
        iconName[0] = IIconConstants.SOLAR_ECLIPSECASTE;
      }
    });
    return iconName[0];
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return charmProperties;
  }
}