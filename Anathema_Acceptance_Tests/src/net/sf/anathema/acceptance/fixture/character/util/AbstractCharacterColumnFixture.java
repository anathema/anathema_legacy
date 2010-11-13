package net.sf.anathema.acceptance.fixture.character.util;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import fit.ColumnFixture;

public abstract class AbstractCharacterColumnFixture extends ColumnFixture {

  protected final ICharacter getCharacter() {
    return getCharacterSummary().getCharacter();
  }

  protected final CharacterSummary getCharacterSummary() {
    return new CharacterSummary(summary);
  }

  protected final ICharacterStatistics getCharacterStatistics() {
    return getCharacter().getStatistics();
  }

  protected final ICharacterGenerics getCharacterGenerics() {
    return getCharacterSummary().getCharacterGenerics();
  }

  protected final ICharacterTemplate getTemplate() {
    return getCharacterSummary().getTemplate();
  }
}