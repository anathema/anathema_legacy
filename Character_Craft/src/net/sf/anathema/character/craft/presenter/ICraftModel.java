package net.sf.anathema.character.craft.presenter;

import net.sf.anathema.character.library.selection.IStringEntryTraitModel;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public interface ICraftModel extends IStringEntryTraitModel<ISubTrait> {

  int getAbsoluteMaximum();

  boolean isRemovable(ISubTrait craft);

  boolean isExperienced();

}