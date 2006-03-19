package net.sf.anathema.acceptance.fixture.character.lunar.renown;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.lunar.renown.model.RenownType;

public class SetRenownFixture extends AbstractRenownRowEntryFixture {

  public String id;
  public int value;

  @Override
  public void enterRow() throws Exception {
    ITrait trait = getTrait();
    trait.setCurrentValue(value);
  }

  protected final ITrait getTrait() {
    return getModel().getTrait(RenownType.valueOf(id));
  }
}