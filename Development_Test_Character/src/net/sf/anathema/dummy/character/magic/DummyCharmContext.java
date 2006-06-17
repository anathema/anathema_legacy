package net.sf.anathema.dummy.character.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.impl.magic.charm.GenericCharmConfiguration;
import net.sf.anathema.character.generic.magic.ICharm;

public final class DummyCharmContext extends GenericCharmConfiguration implements ICharmContext {
  
  private final IGenericCharacter character;
  private final ICharmLearnStrategy learnStrategy;

  public DummyCharmContext(IGenericCharacter character, ICharmLearnStrategy learnStrategy) {
    super(character);
    this.character = character;
    this.learnStrategy = learnStrategy;
  }
  
  public boolean isLearned(ICharm charm) {
    return character.isLearned(charm);
  }

  public ICharmLearnStrategy getCharmLearnStrategy() {
    return learnStrategy;
  }
}