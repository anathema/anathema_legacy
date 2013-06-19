package net.sf.anathema.character.main.testing.dummy.models;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;
import net.sf.anathema.lib.util.Identifier;

public class DummyOtherTraitModel extends DefaultTraitMap implements OtherTraitModel {

  public DummyOtherTraitModel() {
    addTraits(new DummyTrait(OtherTraitType.Essence, 2));
    addTraits(new DummyTrait(OtherTraitType.Willpower, 5));
    addTraits(new DummyTrait(VirtueType.Compassion, 1));
    addTraits(new DummyTrait(VirtueType.Conviction, 1));
    addTraits(new DummyTrait(VirtueType.Temperance, 1));
    addTraits(new DummyTrait(VirtueType.Valor, 1));
  }

  @Override
  public int getEssenceCap(boolean modified) {
    return 10;
  }

  @Override
  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(10);
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
