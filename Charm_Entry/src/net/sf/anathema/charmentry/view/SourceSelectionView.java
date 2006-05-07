package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.util.IIdentificate;

public class SourceSelectionView implements ISourceSelectionView {

  private final IntegerSpinner spinner;
  private final String bookString;
  private final String pageString;
  private final ChangeableJComboBox bookBox;

  public SourceSelectionView(String bookString, String pageString, IIdentificate[] sources) {
    this.bookString = bookString;
    this.pageString = pageString;
    this.bookBox = new ChangeableJComboBox(sources, true);
    spinner = new IntegerSpinner(0);
    spinner.setPreferredWidth(100);

  }

  public void addSourceChangeListener(IObjectValueChangedListener listener) {
    bookBox.addObjectSelectionChangedListener(listener);
  }

  public void addPageChangeListener(IIntValueChangedListener listener) {
    spinner.addChangeListener(listener);
  }

  public void setPageSelectionEnabled(boolean enabled) {
    spinner.getComponent().setEnabled(enabled);
  }

  public void addTo(JComponent component) {
    component.add(new JLabel(bookString));
    component.add(bookBox.getComponent());
    component.add(new JLabel(pageString));
    component.add(spinner.getComponent());
  }
}