package net.sf.anathema.character.main.model.attributes;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AttributeModel extends TraitMap {

  Identifier ID = new SimpleIdentifier("Attributes");

  Trait[] getAll();

  TraitGroup[] getTraitGroups();

  IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();
}
