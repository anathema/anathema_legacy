package net.sf.anathema.character.main.model.othertraits;

import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface OtherTraitModel extends TraitMap {

  Identifier ID = new SimpleIdentifier("OtherTraits");

  int getEssenceCap(boolean modified);
}
