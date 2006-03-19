package net.sf.anathema.character.meritsflaws.presenter.view;

import javax.swing.JComponent;
import javax.swing.event.ChangeListener;

public interface IPerkDetailsView {

  public JComponent getContent();

  public boolean isComplete();

  public void addChangeListener(ChangeListener listener);

  public int getSelectedValue();
}