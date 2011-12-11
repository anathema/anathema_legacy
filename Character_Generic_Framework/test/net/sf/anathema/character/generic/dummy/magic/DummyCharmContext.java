package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public final class DummyCharmContext implements ICharmContext {

  private final IGenericCharacter character;
  private final ICharmLearnStrategy learnStrategy;

  public DummyCharmContext(IGenericCharacter character, ICharmLearnStrategy learnStrategy) {
    this.character = character;
    this.learnStrategy = learnStrategy;
  }

  public boolean isLearned(ICharm charm) {
    return character.isLearned(charm);
  }

  @Override
  public IGenericCharmConfiguration getCharmConfiguration() {
    return character;
  }

  public ICharmLearnStrategy getCharmLearnStrategy() {
    return learnStrategy;
  }

  public final String[] getUncompletedCelestialMartialArtsGroups() {
    return character.getUncompletedCelestialMartialArtsGroups();
  }
}