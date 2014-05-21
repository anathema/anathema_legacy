package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AttributeModel extends TraitListModel {

  Identifier ID = new SimpleIdentifier("Attributes");

  TraitGroup[] getTraitGroups();

  GroupedTraitType[] getAttributeGroups();
}
