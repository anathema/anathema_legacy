package net.sf.anathema.character.meritsflaws.presenter.view;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IView;

public interface IPerkDetailsView extends IView {

  public boolean isComplete();

  public void addChangeListener(IChangeListener listener);

  public int getSelectedValue();
}