package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.ICharmGroup;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.PrintMagicProvider;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

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
  public void addCharmLearnListener(ICharmLearnListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(String charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public CharmSpecialsModel getSpecialCharmConfiguration(String charmId) {
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
  public CharmSpecialsModel getCharmSpecialsModel(Charm charm) {
    return null;
  }

  @Override
  public ILearningCharmGroup getGroup(Charm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
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
  public void addPrintProvider(PrintMagicProvider provider) {
    // nothing to do
  }

  @Override
  public void addLearnProvider(MagicLearner provider) {
    // nothing to do
  }

  @Override
  public MagicCreationCostEvaluator getMagicCostEvaluator() {
    return null;
  }

  @Override
  public MartialArtsLevel getStandardMartialArtsLevel() {
    return MartialArtsLevel.Mortal;
  }

  @Override
  public boolean isAlienCharmAllowed() {
    return false;
  }

  public void setGroups(ILearningCharmGroup... groups) {
    this.groups = groups;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    // nothing to do
  }
}