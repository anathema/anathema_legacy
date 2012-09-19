package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

public class TerrestrialMartialArtsRules implements MartialArtsRules {
  private final IndirectCharmRequirement celestialAttributeRequirement = new CharmAttributeRequirement(
          new CharmAttribute(ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE.getId(), false), 1);

  private boolean highLevelAtCreation;

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    this.highLevelAtCreation = highLevelAtCreation;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.Terrestrial;
  }

  @Override
  public boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration,
                                boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (MartialArtsLevel.Terrestrial.compareTo(level) >= 0) {
      return true;
    }
    if (level == MartialArtsLevel.Celestial) {
      if (!highLevelAtCreation && !isExperienced) {
        return false;
      }
      if (martialArtsCharm.hasAttribute(ICharmData.UNRESTRICTED_ATTRIBUTE)) {
        return true;
      }
      if (celestialAttributeRequirement.isFulfilled(charmConfiguration.getLearnedCharms())) {
        String[] incompleteGroupIds = charmConfiguration.getIncompleteCelestialMartialArtsGroups();
        boolean hasMoreThanOneIncompleteGroup = incompleteGroupIds.length > 1;
        if (hasMoreThanOneIncompleteGroup) {
          String message = "The character has started learning more than one celestial style.";
          throw new IllegalStateException(message);
        }
        boolean hasNoIncompleteGroups = incompleteGroupIds.length == 0;
        if (hasNoIncompleteGroups) {
          return true;
        }
        boolean isFromCurrentIncompleteGroup = martialArtsCharm.getGroupId().equals(incompleteGroupIds[0]);
        return isFromCurrentIncompleteGroup;
      }
    }
    return false;
  }
}