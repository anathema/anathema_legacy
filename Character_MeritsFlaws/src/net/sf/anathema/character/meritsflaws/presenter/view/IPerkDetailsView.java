package net.sf.anathema.character.meritsflaws.presenter.view;

import javax.swing.JComponent;

import net.sf.anathema.lib.control.IChangeListener;

public interface IPerkDetailsView {

  public JComponent getContent();

  public boolean isComplete();

  public void addChangeListener(IChangeListener listener);

  public int getSelectedValue();
}