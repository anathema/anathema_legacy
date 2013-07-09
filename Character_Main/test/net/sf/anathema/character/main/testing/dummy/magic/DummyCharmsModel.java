package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

public class DummyCharmsModel implements CharmsModel {

  private Charm[] charms = new Charm[0];

  private ILearningCharmGroup[] groups;

  @Override
  public void addLearnableListener(ChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(Charm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public Charm[] getCreationLearnedCharms() {
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
  public Charm[] getLearnedCharms(boolean experienced) {
    return charms;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public Charm getCharmById(String charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isCompulsiveCharm(Charm charm) {
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
  public boolean isAlienCharm(Charm charm) {
    for (Charm currentCharm : charms) {
      if (charm == currentCharm) {
        return true;
      }
    }
    return false;
  }

  @Override
  public ISpecialCharmConfiguration getSpecialCharmConfiguration(Charm charm) {
    return null;
  }

  @Override
  public ILearningCharmGroup getGroup(Charm charm) {
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
  public Charm[] getCharms(ICharmGroup charmGroup) {
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