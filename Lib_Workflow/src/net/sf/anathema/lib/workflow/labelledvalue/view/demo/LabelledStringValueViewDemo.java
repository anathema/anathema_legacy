package net.sf.anathema.lib.workflow.labelledvalue.view.demo;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;
import de.jdemo.extensions.SwingDemoCase;

public class LabelledStringValueViewDemo extends SwingDemoCase {

  public void demo() {
    LabelledStringValueView valueView = new LabelledStringValueView("Label:"); //$NON-NLS-1$
    GridDialogPanel gridDialogPanel = new GridDialogPanel();
    valueView.addComponents(gridDialogPanel);
    valueView.setValue("Content"); //$NON-NLS-1$
    show(gridDialogPanel.getContent());
  }
}