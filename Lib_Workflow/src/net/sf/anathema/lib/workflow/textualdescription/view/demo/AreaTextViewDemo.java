package net.sf.anathema.lib.workflow.textualdescription.view.demo;

import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;

import de.jdemo.extensions.SwingDemoCase;

public class AreaTextViewDemo extends SwingDemoCase {

  public void demoDisabled() {
    AreaTextView textView = new AreaTextView(3, 18);
    textView.setText("Ich bin ein Testtext."); //$NON-NLS-1$
    textView.setEnabled(false);
    show(textView.getComponent());
  }
}