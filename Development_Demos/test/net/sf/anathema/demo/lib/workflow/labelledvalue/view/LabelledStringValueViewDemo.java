package net.sf.anathema.demo.lib.workflow.labelledvalue.view;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;
import org.junit.runner.RunWith;

import javax.swing.*;

@RunWith(DemoAsTestRunner.class)
public class LabelledStringValueViewDemo extends SwingDemoCase {

  public void demo() {
    LabelledStringValueView valueView = new LabelledStringValueView("Label:"); //$NON-NLS-1$
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    valueView.addToStandardPanel(panel);
    valueView.setValue("Content"); //$NON-NLS-1$
    show(panel);
  }
}