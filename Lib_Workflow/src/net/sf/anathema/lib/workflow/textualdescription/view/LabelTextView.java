package net.sf.anathema.lib.workflow.textualdescription.view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.RevalidatingScrollPane;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class LabelTextView implements ITextView {

  private final ITextView textView;
  private final boolean scrollPane;
  private JComponent content;
  private Color disabledLabelColor = SystemColor.textInactiveText;
  private final JLabel label;

  public LabelTextView(String labelText, ITextView textView) {
    this(labelText, textView, false);
  }

  public LabelTextView(String labelText, ITextView textView, boolean scrollPane) {
    this.label = new JLabel(labelText);
    this.textView = textView;
    this.scrollPane = scrollPane;
  }

  @Deprecated
  public ITextView addTo(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(label, labelLayoutData);
        JComponent initializedContent = getInitializedContent();
        GridDialogLayoutData contentData = new GridDialogLayoutData((scrollPane
            ? GridDialogLayoutData.FILL_BOTH
            : GridDialogLayoutData.FILL_HORIZONTAL));
        contentData.setHorizontalSpan(columnCount - 1);
        panel.add(initializedContent, contentData);
      }
    });
    return textView;
  }

  @Deprecated
  public ITextView addTo(IGridDialogPanel dialogPanel, final boolean fillAllColumns) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(label, labelLayoutData);
        JComponent initializedContent = getInitializedContent();
        GridDialogLayoutData contentData = new GridDialogLayoutData((scrollPane
            ? GridDialogLayoutData.FILL_BOTH
            : GridDialogLayoutData.FILL_HORIZONTAL));
        contentData.setHorizontalSpan(fillAllColumns ? columnCount - 1 : 1);
        panel.add(initializedContent, contentData);
      }
    });
    return textView;
  }

  private JComponent getInitializedContent() {
    if (content == null) {
      content = scrollPane ? new RevalidatingScrollPane(textView.getComponent()) : textView.getComponent();
    }
    return content;
  }

  public void setText(String text) {
    textView.setText(text);
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    textView.addTextChangedListener(listener);
  }

  @Deprecated
  public JComponent getComponent() {
    return getInitializedContent();
  }

  public void setEnabled(boolean enabled) {
    textView.setEnabled(enabled);
    if (enabled) {
      label.setForeground(SystemColor.textText);
    }
    adjustLabelToDisabledColor();
  }

  public void setDisabledLabelColor(Color color) {
    this.disabledLabelColor = color;
    adjustLabelToDisabledColor();
  }

  private void adjustLabelToDisabledColor() {
    if (!label.isEnabled()) {
      label.setForeground(disabledLabelColor);
    }
  }

  public void addToStandardPanel(JPanel panel) {
    panel.add(label, GridDialogLayoutDataUtilities.createTopData());
    panel.add(textView.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
  }
}