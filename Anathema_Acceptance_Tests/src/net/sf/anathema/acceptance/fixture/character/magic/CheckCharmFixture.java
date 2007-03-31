package net.sf.anathema.acceptance.fixture.character.magic;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.lib.util.Identificate;

public class CheckCharmFixture extends AbstractCheckCharmFisture {

  public String attributeName;

  public boolean isLearnable() {
    return getCharacterStatistics().getCharms().isLearnable(id);
  }

  public boolean isLearned() {
    ICharm charm = getCharm();
    return GenericCharacterUtilities.createGenericCharacter(getCharacterStatistics()).isLearned(charm);
  }

  public boolean isLearnedOnCreation() {
    ICharm charm = getCharm();
    return ArrayUtilities.contains(getCharacterStatistics().getCharms().getCreationLearnedCharms(), charm);
  }

  public boolean hasAttribute() {
    ICharm charm = getCharm();
    return charm.hasAttribute(new Identificate(attributeName));
  }

  public boolean isLearnedOnExperience() {
    ICharm charm = getCharm();
    return ArrayUtilities.contains(getCharacterStatistics().getCharms().getExperienceLearnedCharms(), charm);
  }

  public int learnCount() {
    return GenericCharacterUtilities.createGenericCharacter(getCharacterStatistics()).getLearnCount(getCharm());
  }

  public boolean isFavored() {
    ICharm charm = getCharm();
    ICharacterModelContext characterContext = getCharacterStatistics().getCharacterContext();
    return charm.isFavored(characterContext.getBasicCharacterContext(), characterContext.getTraitCollection());
  }
}