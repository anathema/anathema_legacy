package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.traits.model.TraitGroup;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AttributeModel extends TraitListModel {

  Identifier ID = new SimpleIdentifier("Attributes");

  TraitGroup[] getTraitGroups();

  GroupedTraitType[] getAttributeGroups();
}
