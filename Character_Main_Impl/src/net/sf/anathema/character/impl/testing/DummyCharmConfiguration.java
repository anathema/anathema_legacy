package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyCharmConfiguration implements ICharmConfiguration {

  private ICharm[] charms = new ICharm[0];
  private ISpecialCharmConfiguration specialCharmConfiguration;

  public void addLearnableListener(IChangeListener listener) {
    throw new NotYetImplementedException();
  }

  public boolean isLearned(ICharm charm) {
    throw new NotYetImplementedException();
  }

  public ICharm[] getCreationLearnedCharms() {
    return charms;
  }

  public void addCharmLearnListener(ICharmLearnListener listener) {
    throw new NotYetImplementedException();
  }

  public boolean isLearned(String charmId) {
    throw new NotYetImplementedException();
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId) {
    if (specialCharmConfiguration != null && charmId.equals(specialCharmConfiguration.getCharm().getId())) {
      return specialCharmConfiguration;
    }
    return null;
  }

  public boolean isLearnable(String charmId) {
    throw new NotYetImplementedException();
  }

  public ILearningCharmGroup[] getNonMartialArtsGroups(CharacterType characterType) {
    throw new NotYetImplementedException();
  }

  public ILearningCharmGroup[] getMartialArtsGroups() {
    throw new NotYetImplementedException();
  }

  public boolean isUnlearnable(String charmId) {
    throw new NotYetImplementedException();
  }

  public ICharm[] getExperienceLearnedCharms() {
    return new ICharm[0];
  }

  public ICharm[] getLearnedCharms(boolean experienced) {
    return charms;
  }

  public boolean isLearnable(ICharm charm) {
    throw new NotYetImplementedException();
  }

  public ICharm getCharmById(String charmId) {
    throw new NotYetImplementedException();
  }

  public CharacterType getCharacterType() {
    throw new NotYetImplementedException();
  }

  public boolean isCelestialMartialArtsGroupCompleted() {
    return false;
  }

  public boolean isCompulsiveCharm(ICharm charm) {
    return false;
  }

  public boolean isFullfilled(ICharmAttributeRequirement requirement) {
    return true;
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
  }

  public CharacterType[] getCharacterTypes() {
    throw new NotYetImplementedException();
  }

  public ILearningCharmGroup[] getAllGroups() {
    throw new NotYetImplementedException();
  }

  public ICharmTree getCharmTree(CharacterType type) {
    throw new NotYetImplementedException();
  }

  public void unlearnAllAlienCharms() {
    throw new NotYetImplementedException();
  }

  public boolean isAlienCharm(ICharm charm) {
    for (ICharm currentCharm : charms) {
      if (charm == currentCharm) {
        return true;
      }
    }
    return false;
  }

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    if (specialCharmConfiguration != null && charm.getId().equals(specialCharmConfiguration.getCharm().getId())) {
      return specialCharmConfiguration;
    }
    return null;
  }

  public ILearningCharmGroup getGroup(ICharm charm) {
    throw new NotYetImplementedException();
  }

  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    throw new NotYetImplementedException();
  }

  public ILearningCharmGroup getGroup(String characterType, String groupName) {
    throw new NotYetImplementedException();
  }

  public ICharmProvider getCharmProvider() {
    throw new NotYetImplementedException();
  }
}