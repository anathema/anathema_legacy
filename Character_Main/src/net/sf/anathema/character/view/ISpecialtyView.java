package net.sf.anathema.character.view;

import net.sf.anathema.character.model.traits.IDeleteListener;
import net.sf.anathema.framework.value.IIntValueView;

public interface ISpecialtyView extends IIntValueView {

  public void addDeleteListener(IDeleteListener listener);

  public void delete();

  public void setDeleteButtonEnabled(boolean enabled);
}