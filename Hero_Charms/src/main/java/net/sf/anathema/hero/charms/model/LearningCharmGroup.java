package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmAttributeList;
import net.sf.anathema.character.main.magic.charm.CharmGroup;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroupContainer;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.character.main.magic.charm.special.MultipleEffectCharmSpecials;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LearningCharmGroup extends CharmGroup implements ILearningCharmGroup {

  private final Set<Charm> charmsLearnedOnCreation = new HashSet<>();
  private final Set<Charm> charmsLearnedWithExperience = new HashSet<>();
  private final Announcer<ICharmLearnListener> control = Announcer.to(ICharmLearnListener.class);
  private final IExtendedCharmLearnableArbitrator learnArbitrator;
  private final ICharmLearnStrategy learnStrategy;
  private final ILearningCharmGroupContainer charmGroupContainer;
  private final CharmsModel charmConfig;

  public LearningCharmGroup(ICharmLearnStrategy learnStrategy, ICharmGroup simpleCharmGroup, IExtendedCharmLearnableArbitrator arbitrator,
                            ILearningCharmGroupContainer charmGroupContainer) {
    this(learnStrategy, simpleCharmGroup, arbitrator, charmGroupContainer, null);
  }

  public LearningCharmGroup(ICharmLearnStrategy learnStrategy, ICharmGroup simpleCharmGroup, IExtendedCharmLearnableArbitrator arbitrator,
                            ILearningCharmGroupContainer charmGroupContainer, CharmsModel charmConfig) {
    super(simpleCharmGroup.getCharacterType(), simpleCharmGroup.getId(), simpleCharmGroup.getAllCharms(), simpleCharmGroup.isMartialArtsGroup());
    this.learnStrategy = learnStrategy;
    this.learnArbitrator = arbitrator;
    this.charmGroupContainer = charmGroupContainer;
    this.charmConfig = charmConfig;
  }

  @Override
  public void toggleLearned(Charm charm) {
    learnStrategy.toggleLearned(this, charm);
  }

  @Override
  public void toggleLearnedOnCreation(Charm charm) {
    if (charmsLearnedOnCreation.contains(charm)) {
      forgetCharm(charm, false);
      return;
    }
    if (!learnArbitrator.isLearnable(charm)) {
      boolean mergedLearned = false;
      for (Charm merged : charm.getMergedCharms()) {
        mergedLearned = mergedLearned || learnArbitrator.isLearned(merged);
      }
      if (!mergedLearned) {
        fireNotLearnableEvent(charm);
      }
      return;
    }
    learnCharm(charm, false);
  }

  @Override
  public void toggleExperienceLearnedCharm(Charm charm) {
    if (charmsLearnedOnCreation.contains(charm)) {
      fireNotUnlearnableEvent(charm);
      return;
    }
    if (charmsLearnedWithExperience.contains(charm)) {
      forgetCharm(charm, true);
      return;
    }
    if (!learnArbitrator.isLearnable(charm)) {
      fireNotLearnableEvent(charm);
      return;
    }
    learnCharm(charm, true);
  }

  @Override
  public void forgetCharm(Charm charm, boolean experienced) {
    if (isUnlearnable(charm)) {
      if (experienced) {
        charmsLearnedWithExperience.remove(charm);
      } else {
        charmsLearnedOnCreation.remove(charm);
      }
      fireCharmForgotten(charm);
      forgetChildren(charm, experienced);
    }
  }

  @Override
  public void learnCharm(Charm charm, boolean experienced) {
    learnParents(charm, experienced);
    learnCharmNoParents(charm, experienced, true);
  }

  @Override
  public void learnCharmNoParents(Charm charm, boolean experienced, boolean announce) {
    if (experienced) {
      charmsLearnedWithExperience.add(charm);
    } else {
      charmsLearnedOnCreation.add(charm);
    }
    if (announce) {
      fireCharmLearned(charm);
    }
  }

  private void forgetChildren(Charm charm, boolean experienced) {
    for (Charm child : charm.getLearnFollowUpCharms(learnArbitrator)) {
      ILearningCharmGroup childGroup = charmGroupContainer.getLearningCharmGroup(child);
      childGroup.forgetCharm(child, experienced);
    }
  }

  private void learnParents(Charm charm, boolean experienced) {
    for (Charm parent : charm.getLearnPrerequisitesCharms(learnArbitrator)) {
      ILearningCharmGroup parentGroup = charmGroupContainer.getLearningCharmGroup(parent);
      boolean subeffectHandled = false;
      for (String subeffectRequirement : charm.getParentSubEffects()) {
        if (getSubeffectParent(subeffectRequirement).equals(parent.getId())) {
          CharmSpecialsModel config = charmConfig.getSpecialCharmConfiguration(getSubeffectParent(subeffectRequirement));
          if (config instanceof MultipleEffectCharmSpecials) {
            subeffectHandled = true;
            MultipleEffectCharmSpecials mConfig = (MultipleEffectCharmSpecials) config;
            mConfig.getEffectById(getSubeffect(subeffectRequirement)).setLearned(true);
          }
          if (config instanceof MultiLearnCharmSpecials) {
            subeffectHandled = true;
            MultiLearnCharmSpecials mConfig = (MultiLearnCharmSpecials) config;
            String effect = getSubeffect(subeffectRequirement);
            int requiredCount = Integer.parseInt(effect.replace("Repurchase", ""));
            if (mConfig.getCurrentLearnCount() < requiredCount) {
              mConfig.setCurrentLearnCount(requiredCount);
            }
          }
        }
      }
      if (!subeffectHandled && !parentGroup.isLearned(parent)) {
        parentGroup.learnCharm(parent, experienced);
      }
    }
  }

  private String getSubeffectParent(String subeffect) {
    String[] split = subeffect.split("\\.");
    return split[0] + "." + split[1] + (split.length == 5 ? "." + split[4] : "");
  }

  private String getSubeffect(String subeffect) {
    return subeffect.split("\\.")[3];
  }

  private void fireCharmLearned(Charm charm) {
    control.announce().charmLearned(charm);
  }

  private void fireCharmForgotten(Charm charm) {
    control.announce().charmForgotten(charm);
  }

  private void fireNotLearnableEvent(Charm charm) {
    control.announce().charmNotLearnable(charm);
  }

  private void fireNotUnlearnableEvent(Charm charm) {
    control.announce().charmNotUnlearnable(charm);
  }

  @Override
  public void fireRecalculateRequested() {
    control.announce().recalculateRequested();
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    control.addListener(listener);
  }

  @Override
  public Charm[] getCreationLearnedCharms() {
    return charmsLearnedOnCreation.toArray(new Charm[charmsLearnedOnCreation.size()]);
  }

  @Override
  public Charm[] getExperienceLearnedCharms() {
    return charmsLearnedWithExperience.toArray(new Charm[charmsLearnedWithExperience.size()]);
  }

  @Override
  public boolean isLearned(Charm charm) {
    return learnStrategy.isLearned(this, charm);
  }

  /**
   * @param experienced true to learn whether the charm is learned on experience, false if interested in creation
   *                    learning.
   */
  @Override
  public boolean isLearned(Charm charm, boolean experienced) {
    if (experienced) {
      return charmsLearnedWithExperience.contains(charm);
    }
    return charmsLearnedOnCreation.contains(charm);
  }

  @Override
  public boolean isUnlearnable(Charm charm) {
    return !learnArbitrator.isCompulsiveCharm(charm) && learnStrategy.isUnlearnable(this, charm);
  }

  @Override
  public boolean isUnlearnableWithoutConsequences(Charm charm) {
    if (!isUnlearnable(charm)) {
      return false;
    }
    for (Charm child : charm.getLearnFollowUpCharms(learnArbitrator)) {
      ILearningCharmGroup childGroup = charmGroupContainer.getLearningCharmGroup(child);
      if (childGroup.isLearned(child)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void forgetAll() {
    Set<Charm> forgetCloneCharms = new HashSet<>(charmsLearnedWithExperience);
    for (Charm charm : forgetCloneCharms) {
      forgetCharm(charm, true);
    }
    forgetCloneCharms = new HashSet<>(charmsLearnedOnCreation);
    for (Charm charm : forgetCloneCharms) {
      forgetCharm(charm, false);
    }
  }

  @Override
  public boolean hasLearnedCharms() {
    return charmsLearnedOnCreation.size() + charmsLearnedWithExperience.size() > 0;
  }

  @Override
  public Charm[] getCoreCharms() {
    Charm[] allCharms = getAllCharms();
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(CharmAttributeList.EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  @Override
  public void unlearnExclusives() {
    List<Charm> exclusiveCharms = new ArrayList<>();
    Collections.addAll(exclusiveCharms, getAllCharms());
    exclusiveCharms.removeAll(Arrays.asList(getCoreCharms()));
    for (Charm charm : exclusiveCharms) {
      forgetCharm(charm, isLearned(charm, true));
    }
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}