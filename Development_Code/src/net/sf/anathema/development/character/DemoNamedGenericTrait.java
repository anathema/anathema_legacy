package net.sf.anathema.development.character;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.dummy.character.trait.DummyGenericTrait;

public class DemoNamedGenericTrait extends DummyGenericTrait implements INamedGenericTrait {

  private final String postfix;

  public DemoNamedGenericTrait(ITraitType type, int currentValue, String postfix) {
    super(type, currentValue);
    this.postfix = postfix;
  }

  @Override
  public String getName() {
    return getType().getId() + postfix;
  }

  public IGenericTrait getBasicTrait() {
    return new ValuedTraitType(getType(), 1);
  }
}