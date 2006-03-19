package net.sf.anathema.character.solar.additional;

import java.util.ArrayList;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.solar.caste.SolarCaste;

public class AdditionalSolarRules extends DefaultAdditionalRules {

  public AdditionalSolarRules() {
    super(new ArrayList<String>());
  }

  @Override
  public boolean isAllowedAlienCharms(ICasteType type) {
    if (type == SolarCaste.Eclipse) {
      return true;
    }
    return super.isAllowedAlienCharms(type);
  }
}