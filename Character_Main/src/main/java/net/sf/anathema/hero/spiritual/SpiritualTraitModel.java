package net.sf.anathema.hero.spiritual;

import net.sf.anathema.hero.traits.model.limitation.TraitLimitation;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface SpiritualTraitModel extends TraitMap, HeroModel {

  Identifier ID = new SimpleIdentifier("SpiritualTraits");

  int getEssenceCap(boolean modified);

  TraitLimitation getEssenceLimitation();
}
