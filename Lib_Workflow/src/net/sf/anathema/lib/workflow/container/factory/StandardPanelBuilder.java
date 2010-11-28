package net.sf.anathema.lib.workflow.container.factory;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.disy.commons.swing.util.ToggleComponentEnabler;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.booleanvalue.CheckBoxBooleanView;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.lib.workflow.textualdescription.ICheckableTextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class StandardPanelBuilder {

  private final IGridDialogPanel dialogPanel = new DefaultGridDialogPanel();

  public void addDialogComponent(IDialogComponent component) {
    dialogPanel.add(component);
  }

  public ITextView addLineTextView(final String labelName, int columnCount) {
    return addLabelledTextView(labelName, new LineTextView(columnCount));
  }

  public ICheckableTextView addCheckableLineTextView(String labelName, int textFieldColumns) {
    final CheckBoxBooleanView checkBoxView = new CheckBoxBooleanView(labelName);
    final ITextView textView = new LineTextView(textFieldColumns);
    ToggleComponentEnabler.connect(checkBoxView.getComponent(), textView.getComponent());
    addDialogComponent(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(checkBoxView.getComponent(), GridDialogLayoutDataUtilities.createTopData());
        JComponent textContent = textView.getComponent();
        panel.add(textContent, GridDialogLayoutDataUtilities.createHorizontalSpanData(
            columnCount - 1,
            GridDialogLayoutData.FILL_HORIZONTAL));
      }
    });
    return new ICheckableTextView() {

      public IBooleanValueView getBooleanValueView() {
        return checkBoxView;
      }

      public ITextView getTextView() {
        return textView;
      }
    };
  }

  public ITextView addAreaTextView(final String labelName, int rowCount, int columnCount) {
    return addLabelledTextView(labelName, new AreaTextView(rowCount, columnCount));
  }

  private ITextView addLabelledTextView(final String labelText, final ITextView textView) {
    final LabelTextView labelTextView = new LabelTextView(labelText, textView);
    addDialogComponent(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        labelTextView.addToStandardPanel(panel, columnCount - 1);
      }
    });
    return textView;
  }

  public JPanel getTitledContent(String title) {
    return new TitledPanel(title, dialogPanel.getComponent());
  }

  public JPanel getUntitledContent() {
    return dialogPanel.getComponent();
  }

  public IntegerSpinner addIntegerSpinner(final String labelString, int startValue) {
    final IntegerSpinner spinner = new IntegerSpinner(startValue);
    addDialogComponent(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(new JLabel(labelString));
        panel.add(spinner.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
      }
    });
    return spinner;
  }

  public <V> IObjectSelectionView<V> addObjectSelectionView(String label, ListCellRenderer renderer, V[] objects) {
    return createObjectSelectionView(label, renderer, objects, false);
  }

  public <V> IObjectSelectionView<V> addEditableObjectSelectionView(String label, ListCellRenderer renderer, V[] objects) {
    return createObjectSelectionView(label, renderer, objects, true);
  }

  private <V> IObjectSelectionView<V> createObjectSelectionView(
      String label,
      ListCellRenderer renderer,
      V[] objects,
      boolean editable) {
    final ObjectSelectionView<V> view = new ObjectSelectionView<V>(label, renderer, editable, objects);
    addDialogComponent(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel layoutPanel, int columnCount) {
        view.addTo(layoutPanel, new GridDialogLayoutData());
      }
    });
    return view;
  }
}