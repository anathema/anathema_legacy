package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;

public class AdditionalCultDbRules extends DefaultAdditionalRules {

  public AdditionalCultDbRules() {
    super(DbCharacterModule.BACKGROUND_ID_BREEDING);
  }

  public void addSorceryRules(IBackgroundTemplate sorceryTemplate) {
    addMagicLearnPool(new CultSorceryLearnPool(sorceryTemplate));
  }
}