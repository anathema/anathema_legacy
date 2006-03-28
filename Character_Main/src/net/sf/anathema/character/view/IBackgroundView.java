package net.sf.anathema.character.view;

import java.awt.Component;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;

public interface IBackgroundView extends IIntValueView {

  public void addDeleteListener(IChangeListener listener);

  public void delete();

  public Component getComponent();

  public void setDeleteEnabled(boolean enabled);
}