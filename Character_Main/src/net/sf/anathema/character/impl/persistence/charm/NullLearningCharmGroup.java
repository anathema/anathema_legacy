package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class NullLearningCharmGroup implements ILearningCharmGroup {
  @Override
  public void toggleLearned(ICharm charm) {
    //nothing to do
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    //nothing to do
  }

  @Override
  public ICharm[] getCreationLearnedCharms() {
    return new ICharm[0];
  }

  @Override
  public void learnCharm(ICharm charm, boolean experienced) {
    //nothing to do
  }

  @Override
  public void learnCharmNoParents(ICharm charm, boolean experienced, boolean announce) {
    //nothing to do
  }

  @Override
  public boolean isUnlearnable(ICharm charm) {
    return false;
  }

  @Override
  public boolean isUnlearnableWithoutConsequences(ICharm charm) {
    return false;
  }

  @Override
  public ICharm[] getExperienceLearnedCharms() {
    return new ICharm[0];
  }

  @Override
  public void forgetCharm(ICharm child, boolean experienced) {
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
  public ICharm[] getCoreCharms() {
    return new ICharm[0];
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
  public boolean isLearned(ICharm charm) {
    return false;
  }

  @Override
  public boolean isLearned(ICharm charm, boolean experienced) {
    return false;
  }

  @Override
  public void toggleLearnedOnCreation(ICharm charm) {
    //nothing to do
  }

  @Override
  public void toggleExperienceLearnedCharm(ICharm charm) {
    //nothing to do
  }

  @Override
  public ICharm[] getAllCharms() {
    return new ICharm[0];
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
  public String getId() {
    return null;
  }
}