package net.sf.anathema.campaign.perspective;

import net.sf.anathema.campaign.item.ClosedFileCollector;
import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.campaign.load.selection.IObjectSelectionProperties;
import net.sf.anathema.campaign.load.selection.IObjectSelectionView;
import net.sf.anathema.campaign.load.selection.IObjectSelectionWizardModel;
import net.sf.anathema.campaign.load.selection.LenientLegalityProvider;
import net.sf.anathema.campaign.load.selection.ListObjectSelectionPageView;
import net.sf.anathema.campaign.load.selection.ObjectSelectionDialogPage;
import net.sf.anathema.campaign.load.selection.ObjectSelectionWizardModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.ConfigurableFileProvider;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

import java.util.Collection;

public class ItemSelectionTemplateFactory implements DialogBasedTemplateFactory {

  private PlotItemManagement itemManagement;
  private final IItemType type;
  private final IPrintNameFileAccess access;
  private final IObjectSelectionProperties selectionProperties;

  public ItemSelectionTemplateFactory(
          PlotItemManagement itemManagement,
          IItemType type,
          IPrintNameFileAccess access,
          IObjectSelectionProperties selectionProperties) {
    this.itemManagement = itemManagement;
    this.type = type;
    this.access = access;
    this.selectionProperties = selectionProperties;
  }

  @Override
  public IDialogPage createPage(final IDialogModelTemplate template) {
    if (!(template instanceof ConfigurableFileProvider)) {
      throw new IllegalArgumentException("Bad template type.");
    }
    ClosedFileCollector collector = new ClosedFileCollector(itemManagement, access);
    Collection<PrintNameFile> printNameFiles = collector.collectClosedPrintNameFiles(type);
    PrintNameFile[] fileArray = printNameFiles.toArray(new PrintNameFile[printNameFiles.size()]);
    final IObjectSelectionWizardModel<PrintNameFile> model = new ObjectSelectionWizardModel<>(
        fileArray,
        new LenientLegalityProvider<PrintNameFile>());
    model.addListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        ((ConfigurableFileProvider) template).setFile(model.getSelectedObject().getFile());
      }
    });
    IObjectSelectionView<PrintNameFile> view = new ListObjectSelectionPageView<>(PrintNameFile.class);
    return new ObjectSelectionDialogPage<>(
        model,
        view,
        selectionProperties);
  }

  @Override
  public IDialogModelTemplate createTemplate() {
    return new ConfigurableFileProvider();
  }

  @Override
  public boolean needsFurtherDetails() {
    return true;
  }
}