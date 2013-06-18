package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public final class DummyCharmContext implements ICharmContext {

  private final IGenericCharacter character;

  public DummyCharmContext(IGenericCharacter character) {
    this.character = character;
  }

  @Override
  public IGenericCharmConfiguration getCharmConfiguration() {
    return character;
  }
}