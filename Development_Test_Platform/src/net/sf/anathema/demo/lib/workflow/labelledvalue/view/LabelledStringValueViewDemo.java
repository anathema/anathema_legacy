package net.sf.anathema.demo.lib.workflow.labelledvalue.view;

import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;
import de.jdemo.extensions.SwingDemoCase;

public class LabelledStringValueViewDemo extends SwingDemoCase {

  public void demo() {
    LabelledStringValueView valueView = new LabelledStringValueView("Label:"); //$NON-NLS-1$
    IGridDialogPanel gridDialogPanel = new DefaultGridDialogPanel();
    valueView.addComponents(gridDialogPanel);
    valueView.setValue("Content"); //$NON-NLS-1$
    show(gridDialogPanel.getContent());
  }
}