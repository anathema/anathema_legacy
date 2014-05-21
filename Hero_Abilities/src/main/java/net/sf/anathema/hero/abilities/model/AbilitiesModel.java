package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AbilitiesModel extends TraitListModel {

  Identifier ID = new SimpleIdentifier("Abilities");
}
