package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

import java.util.HashSet;
import java.util.Set;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.hasLevel;
import static net.sf.anathema.character.generic.magic.ICharmData.NO_STYLE_ATTRIBUTE;
import static net.sf.anathema.character.generic.magic.charms.MartialArtsLevel.Celestial;

public class DefaultMartialArtsCharmConfiguration implements MartialArtsCharmConfiguration {
  private final ICharmConfiguration configuration;
  private final IMagicCollection collection;
  private final IBasicCharacterData character;

  public DefaultMartialArtsCharmConfiguration(ICharmConfiguration configuration, IMagicCollection collection,
                                              IBasicCharacterData character) {
    this.configuration = configuration;
    this.collection = collection;
    this.character = character;
  }

  @Override
  public ICharm[] getLearnedCharms() {
    return configuration.getLearnedCharms(character.isExperienced());
  }

  @Override
  public String[] getIncompleteCelestialMartialArtsGroups() {
    return getIncompleteCelestialMartialArtsGroups(getMartialArtsGroups());
  }

  @Override
  public String[] getCompleteCelestialMartialArtsGroups() {
    Set<String> completedGroups = new HashSet<>();
    for (ILearningCharmGroup group : getMartialArtsGroups()) {
      ICharm martialArtsCharm = group.getCoreCharms()[0];
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
      ICharm martialArtsCharm = group.getCoreCharms()[0];
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
      ICharm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCelestialStyle(ICharm martialArtsCharm) {
    return hasLevel(Celestial, martialArtsCharm) && !martialArtsCharm.hasAttribute(NO_STYLE_ATTRIBUTE);
  }

  private boolean isBegun(ILearningCharmGroup group) {
    for (ICharm charm : group.getAllCharms()) {
      if (group.isLearned(charm)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCompleted(ILearningCharmGroup group) {
    for (ICharm charm : group.getCoreCharms()) {
      if (!group.isLearned(charm) && !charm.isBlockedByAlternative(collection)) {
        return false;
      }
    }
    return true;
  }

  private ILearningCharmGroup[] getMartialArtsGroups() {
    return configuration.getCharmGroups(MARTIAL_ARTS);
  }
}