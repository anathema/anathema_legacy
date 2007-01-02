package net.sf.anathema.character.abyssal.additional;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.MultiLearnablePool;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class AdditionalAbyssalRules extends DefaultAdditionalRules {

  public AdditionalAbyssalRules(String... rejectedBackgrounds) {
    super(new AbyssalAdditionalTraitRules(), rejectedBackgrounds);
  }

  public void addNecromancyRules(IBackgroundTemplate necromancyTemplate) {
    addMagicLearnPool(new NecromancyLearnPool(necromancyTemplate));
  }

  public void addEssenceEngorgementTechniqueRules(IMultiLearnableCharm technique) {
    addEssencePool(new MultiLearnablePool(technique, 0, 10));
  }
}