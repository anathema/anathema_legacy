package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class DefaultTerrestrialCharmTemplate extends CharmTemplate {

  private ICharmAttributeRequirement celestialAttributeRequirement = new CharmAttributeRequirement(new CharmAttribute(
      ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE.getId(),
      false), 1);

  public DefaultTerrestrialCharmTemplate(ICharmCache charmProvider) throws PersistenceException {
    super(MartialArtsLevel.Terrestrial, charmProvider, CharacterType.DB, ExaltedEdition.FirstEdition);
  }

  @Override
  public boolean isMartialArtsCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (level == MartialArtsLevel.Terrestrial) {
      return true;
    }
    if (level == MartialArtsLevel.Celestial) {
      if (!mayLearnHighLevelAtCreation() && !isExperienced) {
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

  @Override
  protected boolean mayLearnHighLevelAtCreation() {
    return false;
  }
}