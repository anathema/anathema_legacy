package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;
import net.sf.anathema.lib.workflow.wizard.selection.LenientLegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.ListObjectSelectionPageView;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardPage;

public class LoadItemWizardPageFactory implements IWizardFactory {

  private final IItemType type;
  private final IItemMangementModel itemManagement;
  private final IPrintNameFileAccess access;
  private final IResources resources;

  public LoadItemWizardPageFactory(
      IItemType type,
      IPrintNameFileAccess access,
      IItemMangementModel itemManagement,
      IResources resources) {
    this.type = type;
    this.access = access;
    this.itemManagement = itemManagement;
    this.resources = resources;
  }

  public IAnathemaWizardPage createPage(final IAnathemaWizardModelTemplate template) {
    if (!(template instanceof ConfigurableFileProvider)) {
      throw new IllegalArgumentException("Bad template type."); //$NON-NLS-1$
    }
    PrintNameFile[] printNameFiles = access.collectPrintNameFiles(type, itemManagement);
    final IObjectSelectionWizardModel<PrintNameFile> model = new ObjectSelectionWizardModel<PrintNameFile>(
        printNameFiles,
        new LenientLegalityProvider<PrintNameFile>());
    model.addListener(new IChangeListener() {
      public void changeOccured() {
        ((ConfigurableFileProvider) template).setFile(model.getSelectedObject().getFile());
      }
    });
    IObjectSelectionView<PrintNameFile> view = new ListObjectSelectionPageView<PrintNameFile>(PrintNameFile.class);
    IRegistry<PrintNameFile, IWizardFactory> followUpRegistry = new Registry<PrintNameFile, IWizardFactory>() {
      @Override
      public IWizardFactory get(PrintNameFile id) {
        return new NullWizardPageFactory();
      }
    };
    Registry<PrintNameFile, IAnathemaWizardModelTemplate> modelTemplateRegistry = new Registry<PrintNameFile, IAnathemaWizardModelTemplate>();
    IObjectSelectionProperties properties = new LoadItemWizardProperties(resources);
    return new ObjectSelectionWizardPage<PrintNameFile>(
        followUpRegistry,
        modelTemplateRegistry,
        model,
        view,
        properties);
  }

  public IAnathemaWizardModelTemplate createTemplate() {
    return new ConfigurableFileProvider();
  }

  public boolean needsFurtherDetails() {
    return true;
  }
}