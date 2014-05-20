package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface AttributeCreationData {

  int getAttributeCosts(ValuedTraitType trait);

  int[] getCounts();

  int getExtraFavoredDotCount();

  int getExtraGenericDotCount();
}
