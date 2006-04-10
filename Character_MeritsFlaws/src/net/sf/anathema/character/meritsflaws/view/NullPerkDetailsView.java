package net.sf.anathema.character.meritsflaws.view;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.character.meritsflaws.presenter.view.IPerkDetailsView;
import net.sf.anathema.lib.control.change.IChangeListener;

public class NullPerkDetailsView implements IPerkDetailsView {

  private final String labeltext;

  public NullPerkDetailsView(String labeltext) {
    this.labeltext = labeltext;
  }

  public JComponent getContent() {
    return new JLabel(labeltext);
  }

  public boolean isComplete() {
    return false;
  }

  public void addChangeListener(IChangeListener listener) {
    // Nothing to do
  }

  public int getSelectedValue() {
    throw new UnsupportedOperationException("Should not be called"); //$NON-NLS-1$
  }
}