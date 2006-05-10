package net.sf.anathema.charmentry.demo.page;

import javax.swing.DefaultListCellRenderer;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.demo.ICharmEntryModel;
import net.sf.anathema.charmentry.demo.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.demo.IDurationEntryModel;
import net.sf.anathema.charmentry.demo.IDurationEntryView;
import net.sf.anathema.charmentry.demo.IDurationPageProperties;
import net.sf.anathema.charmentry.demo.page.properties.DurationPageProperties;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class DurationEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private IDurationPageProperties properties;
  private IDurationEntryView view;

  public DurationEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new DurationPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return getPageModel().getDuration() != null;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createDurationView();
    IObjectSelectionView selectionView = view.addObjectSelectionView(
        properties.getDurationLabel(),
        new DefaultListCellRenderer(),
        getPageModel().getDurations());
    selectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setDuration(newValue);
      }
    });
  }

  private IDurationEntryModel getPageModel() {
    return model.getDurationModel();
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getDurationPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getBasicMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}