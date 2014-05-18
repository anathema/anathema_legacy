package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IdentifiedTraitTypeList;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AbilitiesModel extends TraitMap, HeroModel {

  Identifier ID = new SimpleIdentifier("Abilities");

  Trait getTrait(TraitType type);

  IdentifiedTraitTypeList[] getAbilityTypeGroups();
}
