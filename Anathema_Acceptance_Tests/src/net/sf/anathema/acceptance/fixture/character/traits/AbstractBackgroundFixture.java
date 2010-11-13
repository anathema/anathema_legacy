package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;

public abstract class AbstractBackgroundFixture extends AbstractCharacterRowEntryFixture {

  public String traitType;

  protected IBackgroundTemplate getTraitType() {
    IBackgroundTemplate registeredBackground = getCharacterGenerics().getBackgroundRegistry().getById(traitType);
    if (registeredBackground != null) {
      return registeredBackground;
    }
    return new CustomizedBackgroundTemplate(traitType);
  }
}
