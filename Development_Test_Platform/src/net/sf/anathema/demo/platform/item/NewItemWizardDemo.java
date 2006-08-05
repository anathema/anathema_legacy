package net.sf.anathema.demo.platform.item;

import net.sf.anathema.demo.platform.item.model.NewItemWizardModel;
import net.sf.anathema.demo.platform.item.view.ItemTypeSelectionView;
import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.Registry;
import de.jdemo.extensions.SwingDemoCase;

public class NewItemWizardDemo extends SwingDemoCase {

  public void demo() {
    DemoResources resources = new DemoResources();
    IAnathemaWizardPage page = new SelectItemTypePage(
        resources,
        new Registry<CreationItemType, IAnathemaWizardPage>(),
        new NewItemWizardModel(),
        new ItemTypeSelectionView());
    show(new AnathemaWizardDialog(null, page).getConfiguredDialog().getWindow());
  }
}