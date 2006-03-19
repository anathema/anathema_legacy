package net.sf.anathema.character.view;

import java.awt.Component;

import net.sf.anathema.character.model.traits.IDeleteListener;
import net.sf.anathema.framework.value.IIntValueView;

public interface IBackgroundView extends IIntValueView {

  public void addDeleteListener(IDeleteListener listener);

  public void delete();

  public Component getComponent();

  public void setDeleteEnabled(boolean enabled);
}