package net.sf.anathema.lib.workflow.textualdescription.view.demo;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import de.jdemo.extensions.SwingDemoCase;

public class LabelTextViewDemo extends SwingDemoCase {

  public void demoLabelledLineTextView() {
    LineTextView lineTextView = new LineTextView(18);
    lineTextView.setText("Content"); //$NON-NLS-1$
    LabelTextView labelTextView = new LabelTextView("Label:", lineTextView); //$NON-NLS-1$
    GridDialogPanel dialogPanel = new GridDialogPanel();
    labelTextView.addTo(dialogPanel);
    show(dialogPanel.getContent());
  }
}