package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class SourceSelectionView implements ISourceSelectionView, IDialogComponent {

  private final IntegerSpinner spinner;
  private final String bookString;
  private final String pageString;
  private final ChangeableJComboBox<IExaltedSourceBook> bookBox;

  public SourceSelectionView(String bookString, String pageString, IExaltedSourceBook[] sources) {
    this.bookString = bookString;
    this.pageString = pageString;
    this.bookBox = new ChangeableJComboBox<IExaltedSourceBook>(sources, false);
    spinner = new IntegerSpinner(0);
    spinner.setPreferredWidth(100);

  }

  @Override
  public void addSourceChangeListener(ObjectValueListener<IExaltedSourceBook> listener) {
    bookBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void addPageChangeListener(IIntValueChangedListener listener) {
    spinner.addChangeListener(listener);
  }

  @Override
  public void setPageSelectionEnabled(boolean enabled) {
    spinner.getComponent().setEnabled(enabled);
  }

  @Override
  public int getColumnCount() {
    return 4;
  }

  @Override
  public void fillInto(JPanel panel, int columnCount) {
    addTo(panel);
  }

  public void addTo(JComponent component) {
    component.add(new JLabel(bookString));
    component.add(bookBox.getComponent());
    component.add(new JLabel(pageString));
    component.add(spinner.getComponent());
  }

  public void setRenderer(ListCellRenderer renderer) {
    bookBox.setRenderer(renderer);
  }

  @Override
  public void setObjects(IExaltedSourceBook[] legalSources) {
    bookBox.setObjects(legalSources);
  }
}