package net.sf.anathema.lib.workflow.textualdescription.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.RevalidatingScrollPane;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class LabelTextView implements ILabelTextView {

  private final ITextView textView;
  private final String labelText;
  private final boolean scrollPane;

  public LabelTextView(String labelText, ITextView textView) {
    this(labelText, textView, false);
  }

  public LabelTextView(String labelText, ITextView textView, boolean scrollPane) {
    this.labelText = labelText;
    this.textView = textView;
    this.scrollPane = scrollPane;
  }

  public ITextView addTo(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(new JLabel(labelText), labelLayoutData);
        if (scrollPane) {
          panel.add(new RevalidatingScrollPane(textView.getComponent()), GridDialogLayoutData.FILL_BOTH);
        }
        else {
          panel.add(textView.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
        }
      }
    });
    return textView;
  }

  public void setText(String text) {
    textView.setText(text);
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    textView.addTextChangedListener(listener);
  }
}