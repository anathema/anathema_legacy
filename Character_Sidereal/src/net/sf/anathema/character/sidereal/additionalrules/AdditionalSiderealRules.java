package net.sf.anathema.character.sidereal.additionalrules;

import java.util.ArrayList;

import net.sf.anathema.character.generic.framework.module.MortalCharacterModule;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;

public class AdditionalSiderealRules extends DefaultAdditionalRules {

  public AdditionalSiderealRules() {
    super(new ArrayList<String>() {
      {
        add(MortalCharacterModule.BACKGROUND_ID_CONTACTS);
        add(MortalCharacterModule.BACKGROUND_ID_FOLLOWERS);
        add(MortalCharacterModule.BACKGROUND_ID_INFLUENCE);
        add(MortalCharacterModule.BACKGROUND_ID_MENTOR);
        add(MortalCharacterModule.BACKGROUND_ID_RESOURCES);
      }
    });
  }
}