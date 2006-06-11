package net.sf.anathema.character.sidereal.additionalrules;

import java.util.ArrayList;

import net.sf.anathema.character.generic.framework.module.IBackgroundIds;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;

public class AdditionalSiderealRules extends DefaultAdditionalRules {

  public AdditionalSiderealRules() {
    super(new ArrayList<String>() {
      {
        add(IBackgroundIds.BACKGROUND_ID_CONTACTS);
        add(IBackgroundIds.BACKGROUND_ID_FOLLOWERS);
        add(IBackgroundIds.BACKGROUND_ID_INFLUENCE);
        add(IBackgroundIds.BACKGROUND_ID_MENTOR);
        add(IBackgroundIds.BACKGROUND_ID_RESOURCES);
      }
    });
  }
}