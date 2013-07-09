package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;

public class NullLearningCharmGroup implements ILearningCharmGroup {
  @Override
  public void toggleLearned(Charm charm) {
    //nothing to do
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    //nothing to do
  }

  @Override
  public Charm[] getCreationLearnedCharms() {
    return new Charm[0];
  }

  @Override
  public void learnCharm(Charm charm, boolean experienced) {
    //nothing to do
  }

  @Override
  public void learnCharmNoParents(Charm charm, boolean experienced, boolean announce) {
    //nothing to do
  }

  @Override
  public boolean isUnlearnable(Charm charm) {
    return false;
  }

  @Override
  public boolean isUnlearnableWithoutConsequences(Charm charm) {
    return false;
  }

  @Override
  public Charm[] getExperienceLearnedCharms() {
    return new Charm[0];
  }

  @Override
  public void forgetCharm(Charm child, boolean experienced) {
    //nothing to do
  }

  @Override
  public void forgetAll() {
    //nothing to do
  }

  @Override
  public boolean hasLearnedCharms() {
    return false;
  }

  @Override
  public Charm[] getCoreCharms() {
    return new Charm[0];
  }

  @Override
  public void unlearnExclusives() {
    //nothing to do
  }

  @Override
  public void fireRecalculateRequested() {
    //nothing to do
  }

  @Override
  public boolean isLearned(Charm charm) {
    return false;
  }

  @Override
  public boolean isLearned(Charm charm, boolean experienced) {
    return false;
  }

  @Override
  public void toggleLearnedOnCreation(Charm charm) {
    //nothing to do
  }

  @Override
  public void toggleExperienceLearnedCharm(Charm charm) {
    //nothing to do
  }

  @Override
  public Charm[] getAllCharms() {
    return new Charm[0];
  }

  @Override
  public ICharacterType getCharacterType() {
    return null;
  }

  @Override
  public boolean isMartialArtsGroup() {
    return false;
  }

  @Override
  public boolean isCharmFromGroup(Charm charm) {
    return false;
  }

  @Override
  public String getId() {
    return null;
  }
}