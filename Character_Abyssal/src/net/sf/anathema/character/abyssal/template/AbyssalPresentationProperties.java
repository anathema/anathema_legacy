package net.sf.anathema.character.abyssal.template;

import java.awt.Color;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;

public class AbyssalPresentationProperties extends AbstractPresentationProperties {

  private final String newActionResoure;
  private final AbyssalCharmPresentationProperties abyssalCharmPresentationProperties = new AbyssalCharmPresentationProperties();

  public AbyssalPresentationProperties(String newActionResoure) {
    super(new TemplateType(CharacterType.ABYSSAL));
    this.newActionResoure = newActionResoure;
  }

  @Override
  public String getNewActionResource() {
    return newActionResoure;
  }

  public String getBallResource() {
    return IIconConstants.ABYSSAL_BALL;
  }

  public String getSmallCasteIconResource(String casteId, String editionId) {
    final String[] iconName = new String[1];
    AbyssalCaste abyssalCaste = AbyssalCaste.valueOf(casteId);
    abyssalCaste.accept(new IAbyssalCasteVisitor() {
      public void visitDusk(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DUSKCASTE_SMALL;
      }

      public void visitMidnight(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_MIDNIGHTCASTE_SMALL;
      }

      public void visitDaybreak(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DAYBREAKCASTE_SMALL;
      }

      public void visitDay(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DAYCASTE_SMALL;
      }

      public void visitMoonshadow(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_MOONSHADOWCASTE_SMALL;
      }
    });
    return iconName[0];
  }

  public String getMediumCasteIconResource(String casteId, String editionId) {
    final String[] iconName = new String[1];
    AbyssalCaste abyssalCaste = AbyssalCaste.valueOf(casteId);
    abyssalCaste.accept(new IAbyssalCasteVisitor() {
      public void visitDusk(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DUSKCASTE;
      }

      public void visitMidnight(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_MIDNIGHTCASTE;
      }

      public void visitDaybreak(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DAYBREAKCASTE;
      }

      public void visitDay(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_DAYCASTE;
      }

      public void visitMoonshadow(AbyssalCaste caste) {
        iconName[0] = IIconConstants.ABYSSAL_MOONSHADOWCASTE;
      }
    });
    return iconName[0];
  }

  public Color getColor() {
    return new Color(169, 169, 169);
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return abyssalCharmPresentationProperties;
  }
}