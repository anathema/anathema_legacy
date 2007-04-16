package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class LearningCharmGroupArbitrator implements ILearningCharmGroupArbitrator {

  private final ICharmTemplate template;
  private final IBasicCharacterData data;

  public LearningCharmGroupArbitrator(ICharmTemplate template, IBasicCharacterData data) {
    this.template = template;
    this.data = data;
  }

  public ICharm[] getCharms(ICharmGroup charmGroup) {
    final ICharm[] allCharms = charmGroup.getAllCharms();
    if (template.isAllowedAlienCharms(data.getCasteType())) {
      return allCharms;
    }
    List<ICharm> charms = new ArrayList<ICharm>();
    for (ICharm charm : allCharms) {
      if (!charm.hasAttribute(IExtendedCharmData.EXCLUSIVE_ATTRIBUTE)
          || data.getCharacterType() == charm.getCharacterType()) {
        charms.add(charm);
      }
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  public String[] getUncompletedCelestialMartialArtsGroups(ILearningCharmGroup[] groups) {
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

  public final boolean isCelestialMartialArtsGroupCompleted(ILearningCharmGroup[] groups) {
    for (ILearningCharmGroup group : groups) {
      ICharm martialArtsCharm = group.getCoreCharms()[0];
      if (isCelestialStyle(martialArtsCharm) && isCompleted(group)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCelestialStyle(ICharm martialArtsCharm) {
    return MartialArtsUtilities.hasLevel(MartialArtsLevel.Celestial, martialArtsCharm) && !martialArtsCharm.hasAttribute(ICharmData.NO_STYLE_ATTRIBUTE);
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
      if (!group.isLearned(charm)) {
        return false;
      }
    }
    return true;
  }
}