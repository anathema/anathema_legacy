package net.sf.anathema.character.abyssal.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.MultiLearnablePool;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class AdditionalAbyssalRules extends DefaultAdditionalRules {

  public AdditionalAbyssalRules() {
    this(new ArrayList<String>());
  }

  public AdditionalAbyssalRules(List<String> rejectedBackgrounds) {
    super(rejectedBackgrounds, new AbyssalAdditionalTraitRules());
  }

  public void addNecromancyRules(IBackgroundTemplate necromancyTemplate) {
    addMagicLearnPool(new NecromancyLearnPool(necromancyTemplate));
  }

  public void addEssenceEngorgementTechniqueRules(IMultiLearnableCharm technique) {
    addEssencePool(new MultiLearnablePool(technique, 0, 10));
  }
}