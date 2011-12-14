package net.sf.anathema.demo.lib.workflow.textualdescription.view;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import org.junit.runner.RunWith;

import javax.swing.*;

@RunWith(DemoAsTestRunner.class)
public class AreaTextViewDemo extends SwingDemoCase {

  public void demoDisabled() {
    AreaTextView textView = new AreaTextView(3, 18);
    textView.setText("Ich bin ein Testtext."); //$NON-NLS-1$
    textView.setEnabled(false);
    show(textView.getComponent());
  }

  public void demoLabelledAreaTextView() {
    AreaTextView areaTextView = new AreaTextView(3, 18);
    areaTextView.setText("Content"); //$NON-NLS-1$
    LabelTextView labelTextView = new LabelTextView("Label:", areaTextView); //$NON-NLS-1$
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    labelTextView.addToStandardPanel(panel);
    show(panel);
  }
}