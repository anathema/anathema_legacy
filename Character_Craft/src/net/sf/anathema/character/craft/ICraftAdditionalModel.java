package net.sf.anathema.character.craft;

import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface ICraftAdditionalModel extends IAdditionalModel {

  public ICraftModel getCraftModel();
}
