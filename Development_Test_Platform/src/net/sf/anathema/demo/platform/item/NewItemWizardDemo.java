package net.sf.anathema.demo.platform.item;

import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionView;
import net.sf.anathema.framework.item.repository.creation.NewItemWizardModel;
import net.sf.anathema.framework.item.repository.creation.SelectItemTypePage;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.Registry;
import de.jdemo.extensions.SwingDemoCase;

public class NewItemWizardDemo extends SwingDemoCase {

  public void demo() {
    DemoResources resources = new DemoResources();
    Registry<IItemType, IWizardFactory> registry = new Registry<IItemType, IWizardFactory>();
    IAnathemaWizardPage page = new SelectItemTypePage(resources, registry, new NewItemWizardModel(
        new IItemType[] { new IItemType() {

          public IRepositoryConfiguration getRepositoryConfiguration() {
            return null;
          }

          public boolean supportsRepository() {
            return false;
          }

          public String getId() {
            return "TestItem";
          }
        } },
        registry), new ItemTypeSelectionView());
    show(new AnathemaWizardDialog(null, page).getConfiguredDialog().getWindow());
  }
}