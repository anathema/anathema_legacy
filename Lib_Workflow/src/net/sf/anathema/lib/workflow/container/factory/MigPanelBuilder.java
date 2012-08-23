package net.sf.anathema.lib.workflow.container.factory;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JPanel;

public class MigPanelBuilder {
  private final IGridDialogPanel dialogPanel = new DefaultGridDialogPanel();

  public ITextView addLineTextView(String labelName, int columnCount) {
    return addLabelledTextView(labelName, new LineTextView(columnCount));
  }

  private ITextView addLabelledTextView(String labelText, ITextView textView) {
    final LabelTextView labelTextView = new LabelTextView(labelText, textView);
    addDialogComponent(new IDialogComponent() {
      @Override
      public int getColumnCount() {
        return 2;
      }

      @Override
      public void fillInto(JPanel panel, int columnCount) {
        labelTextView.addToStandardPanel(panel, columnCount - 1);
      }
    });
    return textView;
  }

  public void addDialogComponent(IDialogComponent component) {
    dialogPanel.add(component);
  }
}
