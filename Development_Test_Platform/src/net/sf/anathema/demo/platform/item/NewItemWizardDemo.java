package net.sf.anathema.demo.platform.item;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import de.jdemo.extensions.SwingDemoCase;

public class NewItemWizardDemo extends SwingDemoCase {

  public void demo() {
   
    show(new AnathemaWizardDialog(null, page).getDialog().getWindow());
  }
}