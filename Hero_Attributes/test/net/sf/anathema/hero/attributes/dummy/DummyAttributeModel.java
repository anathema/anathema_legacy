package net.sf.anathema.hero.attributes.dummy;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.lib.util.Identifier;

public class DummyAttributeModel extends DefaultTraitMap implements AttributeModel {
  @Override
  public TraitGroup[] getTraitGroups() {
    return new TraitGroup[0];
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedTraitTypeGroup[0];
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
