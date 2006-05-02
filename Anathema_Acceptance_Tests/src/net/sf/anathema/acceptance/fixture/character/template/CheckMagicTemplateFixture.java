package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.magic.spells.CircleType;

public class CheckMagicTemplateFixture extends AbstractTemplateColumnFixture {

  public String getFavoringTraitType() {
    return getTemplate().getMagicTemplate().getFavoringTraitType().getId();
  }

  public String getMaximumSorceryCircle() {
    return getMaximumCircle(getTemplate().getMagicTemplate().getSpellMagic().getSorceryCircles()).getId();
  }

  public String getMaximumNecromancyCircle() {
    return getMaximumCircle(getTemplate().getMagicTemplate().getSpellMagic().getNecromancyCircles()).getId();
  }

  private CircleType getMaximumCircle(CircleType[] circles) {
    CircleType highestCircle = circles[0];
    for (CircleType circle : circles) {
      highestCircle = circle.compareTo(highestCircle) > 0 ? circle : highestCircle;
    }
    return highestCircle;
  }

  public String getMartialArtsLevel() {
    return getTemplate().getMagicTemplate().getCharmTemplate().getMartialArtsLevel().getId();
  }

  public String getCharmType() {
    return getTemplate().getMagicTemplate()
        .getCharmTemplate()
        .getCharms(getTemplate().getEdition().getDefaultRuleset())[0].getCharacterType().getId();
  }
}