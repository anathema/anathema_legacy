package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmIdMap;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.charm.ICharmLearnListener;
import net.sf.anathema.character.main.charm.ILearningCharmGroup;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

public class DummyCharmsModel implements CharmsModel {

  private ICharm[] charms = new ICharm[0];

  private ILearningCharmGroup[] groups;

  @Override
  public void addLearnableListener(ChangeListener listener) {
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
  public ILearningCharmGroup[] getCharmGroups(Identifier type) {
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
  public CharmIdMap getCharmIdMap() {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return new ISpecialCharm[0];
  }

  @Override
  public String getCharmTrueName(String charmName) {
    // Nothing to do
    return null;
  }

  public void setGroups(ILearningCharmGroup... groups) {
    this.groups = groups;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}