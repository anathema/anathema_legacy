package net.sf.anathema.hero.spiritual;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface SpiritualTraitModel extends TraitMap, HeroModel {

  Identifier ID = new SimpleIdentifier("SpiritualTraits");

  int getEssenceCap(boolean modified);

  ITraitLimitation getEssenceLimitation();
}
