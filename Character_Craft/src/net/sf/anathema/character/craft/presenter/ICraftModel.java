package net.sf.anathema.character.craft.presenter;

import net.sf.anathema.character.library.selection.IStringEntryTraitModel;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public interface ICraftModel extends IStringEntryTraitModel<ISubTrait> {

  public int getAbsoluteMaximum();

  public boolean isRemovable(ISubTrait craft);

  public boolean isExperienced();

}