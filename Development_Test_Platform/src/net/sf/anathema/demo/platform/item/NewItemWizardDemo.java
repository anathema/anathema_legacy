package net.sf.anathema.demo.platform.item;

import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import de.jdemo.extensions.SwingDemoCase;

public class NewItemWizardDemo extends SwingDemoCase {

  public void demo() {
    DemoResources resources = new DemoResources();
    IAnathemaWizardPage page = new SelectItemTypePage(resources, new INewItemWizardModel() {
      
    }, new ISelectItemTypeView() {
      
    });
    show(new AnathemaWizardDialog(null, page).getDialog().getWindow());
  }
}