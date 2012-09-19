package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

import static net.sf.anathema.character.generic.magic.ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE;
import static net.sf.anathema.character.generic.magic.ICharmData.UNRESTRICTED_ATTRIBUTE;
import static net.sf.anathema.character.generic.magic.charms.MartialArtsLevel.Terrestrial;

public class TerrestrialMartialArtsRules implements MartialArtsRules {
  private final IndirectCharmRequirement celestialAttributeRequirement = new CharmAttributeRequirement(
          new CharmAttribute(ALLOWS_CELESTIAL_ATTRIBUTE.getId(), false), 1);

  private boolean highLevelAtCreation;

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    this.highLevelAtCreation = highLevelAtCreation;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return Terrestrial;
  }

  @Override
  public boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration,
                                boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (Terrestrial.compareTo(level) >= 0) {
      return true;
    }
    if (level == MartialArtsLevel.Celestial) {
      if (!highLevelAtCreation && !isExperienced) {
        return false;
      }
      if (martialArtsCharm.hasAttribute(UNRESTRICTED_ATTRIBUTE)) {
        return true;
      }
      if (celestialAttributeRequirement.isFulfilled(charmConfiguration.getLearnedCharms())) {
        if (isFromAnyCompletedStyle(martialArtsCharm, charmConfiguration)) {
          return true;
        }
        String[] incompleteStyleIds = charmConfiguration.getIncompleteCelestialMartialArtsGroups();
        boolean hasMoreThanOneIncompleteStyle = incompleteStyleIds.length > 1;
        if (hasMoreThanOneIncompleteStyle) {
          String message = "The character has started learning more than one celestial style.";
          throw new IllegalStateException(message);
        }
        boolean hasNoIncompleteStyle = incompleteStyleIds.length == 0;
        if (hasNoIncompleteStyle) {
          return true;
        }
        boolean isFromCurrentIncompleteStyle = martialArtsCharm.getGroupId().equals(incompleteStyleIds[0]);
        return isFromCurrentIncompleteStyle;
      }
    }
    return false;
  }

  private boolean isFromAnyCompletedStyle(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration) {
    String[] completeStyleIds = charmConfiguration.getCompleteCelestialMartialArtsGroups();
    for (String styleId : completeStyleIds) {
      if (martialArtsCharm.getGroupId().equals(styleId)) {
        return true;
      }
    }
    return false;
  }
}