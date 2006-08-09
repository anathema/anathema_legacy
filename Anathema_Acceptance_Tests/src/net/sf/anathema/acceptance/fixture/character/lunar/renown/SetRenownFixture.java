package net.sf.anathema.acceptance.fixture.character.lunar.renown;

import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.character.lunar.renown.model.RenownType;

public class SetRenownFixture extends AbstractRenownRowEntryFixture {

  public String id;
  public int value;

  @Override
  public void enterRow() throws Exception {
    IDefaultTrait trait = getTrait();
    trait.setCurrentValue(value);
  }

  protected final IDefaultTrait getTrait() {
    return (IDefaultTrait) getModel().getTrait(RenownType.valueOf(id));
  }
}