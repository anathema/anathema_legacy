package net.sf.anathema.demo.lib.workflow.textualdescription.view;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.junit.runner.RunWith;

import javax.swing.*;

@RunWith(DemoAsTestRunner.class)
public class LabelTextViewDemo extends SwingDemoCase {

  public void demoLabelledLineTextView() {
    LineTextView lineTextView = new LineTextView(18);
    lineTextView.setText("Content"); //$NON-NLS-1$
    LabelTextView labelTextView = new LabelTextView("Label:", lineTextView); //$NON-NLS-1$
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    labelTextView.addToStandardPanel(panel);
    show(panel);
  }
}