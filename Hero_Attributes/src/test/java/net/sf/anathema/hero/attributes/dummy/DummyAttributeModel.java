package net.sf.anathema.hero.attributes.dummy;

import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.DefaultTraitMap;
import net.sf.anathema.lib.util.Identifier;

public class DummyAttributeModel extends DefaultTraitMap implements AttributeModel {
  @Override
  public TraitGroup[] getTraitGroups() {
    return new TraitGroup[0];
  }

  @Override
  public IdentifiedTraitTypeList[] getAttributeTypeGroups() {
    return new IdentifiedTraitTypeList[0];
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
