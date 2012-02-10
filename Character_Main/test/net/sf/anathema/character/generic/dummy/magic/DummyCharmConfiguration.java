package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.List;

public class DummyCharmConfiguration implements ICharmConfiguration {

  private ICharm[] charms = new ICharm[0];

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
    return null;
  }

  public boolean isLearnable(String charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public ILearningCharmGroup[] getCharmGroups(IIdentificate type) {
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

  public ICharacterType getCharacterType() {
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

  public ILearningCharmGroup[] getAllGroups() {
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

  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }

  @Override
  public ICharmIdMap getCharmIdMap() {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    throw new NotYetImplementedException();
  }

	@Override
	public List<ICharmFilter> getCharmFilters() {
		return null;
	}

	@Override
	public void setCharmFilters(List<ICharmFilter> filters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCharmTrueName(String charmName) {
		// TODO Auto-generated method stub
		return null;
	}
}