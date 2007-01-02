package net.sf.anathema.character.db.additional;

import java.util.ArrayList;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;

public class AdditionalCultDbRules extends DefaultAdditionalRules {

  public AdditionalCultDbRules() {
    super(new ArrayList<String>());
  }

  public void addSorceryRules(IBackgroundTemplate sorceryTemplate) {
    addMagicLearnPool(new CultSorceryLearnPool(sorceryTemplate));
  }
}