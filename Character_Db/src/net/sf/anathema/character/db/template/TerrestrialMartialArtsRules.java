package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;

public class TerrestrialMartialArtsRules implements IMartialArtsRules {
  private final ICharmAttributeRequirement celestialAttributeRequirement = new CharmAttributeRequirement(
      new CharmAttribute(ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE.getId(), false),
      1);

  private boolean highLevelAtCreation;

  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    this.highLevelAtCreation = highLevelAtCreation;
  }

  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.Terrestrial;
  }

  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (level == MartialArtsLevel.Terrestrial) {
      return true;
    }
    if (level == MartialArtsLevel.Celestial) {
      if (!highLevelAtCreation && !isExperienced) {
        return false;
      }
      if (martialArtsCharm.hasAttribute(ICharmData.UNRESTRICTED_ATTRIBUTE)) {
        return true;
      }
      if (charmConfiguration.isRequirementFulfilled(celestialAttributeRequirement)) {
        String[] uncompletedGroupIds = charmConfiguration.getUncompletedCelestialMartialArtsGroups();
        if (uncompletedGroupIds.length > 1) {
          throw new IllegalStateException("The character has started learning more than one celestial style."); //$NON-NLS-1$
        }
        if (uncompletedGroupIds.length == 0) {
          return true;
        }
        return martialArtsCharm.getGroupId().equals(uncompletedGroupIds[0]);
      }
    }
    return false;
  }
}