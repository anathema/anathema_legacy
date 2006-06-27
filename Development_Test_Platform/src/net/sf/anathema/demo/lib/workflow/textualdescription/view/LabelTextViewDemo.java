package net.sf.anathema.demo.lib.workflow.textualdescription.view;

import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import de.jdemo.extensions.SwingDemoCase;

public class LabelTextViewDemo extends SwingDemoCase {

  public void demoLabelledLineTextView() {
    LineTextView lineTextView = new LineTextView(18);
    lineTextView.setText("Content"); //$NON-NLS-1$
    LabelTextView labelTextView = new LabelTextView("Label:", lineTextView); //$NON-NLS-1$
    IGridDialogPanel dialogPanel = new DefaultGridDialogPanel();
    labelTextView.addTo(dialogPanel);
    show(dialogPanel.getContent());
  }
}