package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;

public interface AttributeCreationData extends TraitListCreationData {

  int getAttributeCosts(ValuedTraitType trait);

  int[] getCounts();

  int getExtraFavoredDotCount();

  int getExtraGenericDotCount();
}
