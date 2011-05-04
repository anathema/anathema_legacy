package net.sf.anathema.character.library.trait.visitor;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.IModifiableGenericTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IDefaultTrait extends ITrait, IModifiableBasicTrait, IModifiableGenericTrait, IModifiableCapTrait {

  public int getMinimalValue();

  public void resetCurrentValue();

  public void setModifiedCreationRange(int newInitialValue, int newUpperValue);

  public void addRangeListener(IChangeListener listener);
}