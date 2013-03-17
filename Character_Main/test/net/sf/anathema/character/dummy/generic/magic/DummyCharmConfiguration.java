package net.sf.anathema.character.dummy.generic.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identified;

import java.util.List;

public class DummyCharmConfiguration implements ICharmConfiguration {

  private ICharm[] charms = new ICharm[0];

  private ILearningCharmGroup[] groups;

  @Override
  public void addLearnableListener(IChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(ICharm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public ICharm[] getCreationLearnedCharms() {
    return charms;
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(String charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId) {
    return null;
  }

  @Override
  public ILearningCharmGroup[] getCharmGroups(Identified type) {
    return groups;
  }

  @Override
  public ICharm[] getLearnedCharms(boolean experienced) {
    return charms;
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public ICharm getCharmById(String charmId) {
    throw new NotYetImplementedException();
  }

  public ICharacterType getCharacterType() {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isCompulsiveCharm(ICharm charm) {
    return false;
  }

  @Override
  public ILearningCharmGroup[] getAllGroups() {
    throw new NotYetImplementedException();
  }

  @Override
  public void unlearnAllAlienCharms() {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isAlienCharm(ICharm charm) {
    for (ICharm currentCharm : charms) {
      if (charm == currentCharm) {
        return true;
      }
    }
    return false;
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm) {
    return null;
  }

  @Override
  public ILearningCharmGroup getGroup(ICharm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    throw new NotYetImplementedException();
  }

  @Override
  public ILearningCharmGroup getGroup(String characterType, String groupName) {
    throw new NotYetImplementedException();
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }

  @Override
  public ICharmIdMap getCharmIdMap() {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return new ISpecialCharm[0];
  }

  @Override
  public List<ICharmFilter> getCharmFilters() {
    return null;
  }

  @Override
  public String getCharmTrueName(String charmName) {
    // Nothing to do
    return null;
  }

  public void setGroups(ILearningCharmGroup... groups) {
    this.groups = groups;
  }
}