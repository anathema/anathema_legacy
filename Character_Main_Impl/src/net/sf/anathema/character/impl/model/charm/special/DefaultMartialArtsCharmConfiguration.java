package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

import java.util.HashSet;
import java.util.Set;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public class DefaultMartialArtsCharmConfiguration implements MartialArtsCharmConfiguration {
  private final ICharmConfiguration configuration;
  private final IMagicCollection collection;
  private final IBasicCharacterData character;

  public DefaultMartialArtsCharmConfiguration(ICharmConfiguration configuration,
                                              IMagicCollection collection, IBasicCharacterData character) {
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
  public boolean isCelestialStyleCompleted() {
    return isCelestialMartialArtsGroupCompleted(getMartialArtsGroups());
  }

  private String[] getIncompleteCelestialMartialArtsGroups(ILearningCharmGroup[] groups) {
    Set<String> uncompletedGroups = new HashSet<String>();
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

  private boolean isCelestialMartialArtsGroupCompleted(ILearningCharmGroup[] groups) {
    for (ILearningCharmGroup group : groups) {
      ICharm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCelestialStyle(ICharm martialArtsCharm) {
    return MartialArtsUtilities.hasLevel(MartialArtsLevel.Celestial,
            martialArtsCharm) && !martialArtsCharm.hasAttribute(ICharmData.NO_STYLE_ATTRIBUTE);
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