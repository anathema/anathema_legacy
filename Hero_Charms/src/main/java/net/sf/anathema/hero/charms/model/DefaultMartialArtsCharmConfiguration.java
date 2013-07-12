package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.hero.experience.ExperienceModel;

import java.util.HashSet;
import java.util.Set;

import static net.sf.anathema.character.main.magic.model.charm.CharmAttributeList.NO_STYLE_ATTRIBUTE;
import static net.sf.anathema.hero.magic.model.martial.MartialArtsLevel.Celestial;
import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.hasLevel;

public class DefaultMartialArtsCharmConfiguration implements MartialArtsCharmConfiguration {
  private final CharmsModel charmModel;
  private ExperienceModel experience;

  public DefaultMartialArtsCharmConfiguration(CharmsModel charmModel, ExperienceModel experience) {
    this.charmModel = charmModel;
    this.experience = experience;
  }

  @Override
  public Charm[] getLearnedCharms() {
    return charmModel.getLearnedCharms(experience.isExperienced());
  }

  @Override
  public String[] getIncompleteCelestialMartialArtsGroups() {
    return getIncompleteCelestialMartialArtsGroups(getMartialArtsGroups());
  }

  @Override
  public String[] getCompleteCelestialMartialArtsGroups() {
    Set<String> completedGroups = new HashSet<>();
    for (ILearningCharmGroup group : getMartialArtsGroups()) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        completedGroups.add(group.getId());
      }
    }
    return completedGroups.toArray(new String[completedGroups.size()]);
  }

  @Override
  public boolean isAnyCelestialStyleCompleted() {
    return isAnyCelestialMartialArtsGroupCompleted(getMartialArtsGroups());
  }

  private String[] getIncompleteCelestialMartialArtsGroups(ILearningCharmGroup[] groups) {
    Set<String> uncompletedGroups = new HashSet<>();
    for (ILearningCharmGroup group : groups) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (!isCelestialStyle(martialArtsCharm) || isCompleted(group)) {
        continue;
      }
      if (isBegun(group)) {
        uncompletedGroups.add(group.getId());
      }
    }
    return uncompletedGroups.toArray(new String[uncompletedGroups.size()]);
  }

  private boolean isAnyCelestialMartialArtsGroupCompleted(ILearningCharmGroup[] groups) {
    for (ILearningCharmGroup group : groups) {
      Charm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCelestialStyle(Charm martialArtsCharm) {
    return hasLevel(Celestial, martialArtsCharm) && !martialArtsCharm.hasAttribute(NO_STYLE_ATTRIBUTE);
  }

  private boolean isBegun(ILearningCharmGroup group) {
    for (Charm charm : group.getAllCharms()) {
      if (group.isLearned(charm)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCompleted(ILearningCharmGroup group) {
    for (Charm charm : group.getCoreCharms()) {
      if (!group.isLearned(charm) && !charm.isBlockedByAlternative(charmModel)) {
        return false;
      }
    }
    return true;
  }

  private ILearningCharmGroup[] getMartialArtsGroups() {
    return charmModel.getCharmGroups(MARTIAL_ARTS);
  }
}