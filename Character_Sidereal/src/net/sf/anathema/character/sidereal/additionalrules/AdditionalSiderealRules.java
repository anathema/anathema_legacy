package net.sf.anathema.character.sidereal.additionalrules;

import net.sf.anathema.character.generic.framework.module.IBackgroundIds;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;

public class AdditionalSiderealRules extends DefaultAdditionalRules {

  public AdditionalSiderealRules() {
    super(
        IBackgroundIds.BACKGROUND_ID_CONTACTS,
        IBackgroundIds.BACKGROUND_ID_FOLLOWERS,
        IBackgroundIds.BACKGROUND_ID_INFLUENCE,
        IBackgroundIds.BACKGROUND_ID_MENTOR,
        IBackgroundIds.BACKGROUND_ID_RESOURCES);
  }
}