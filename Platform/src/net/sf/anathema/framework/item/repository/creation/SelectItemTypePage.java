package net.sf.anathema.framework.item.repository.creation;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.list.ListSelectionMode;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SelectItemTypePage extends AbstractAnathemaWizardPage {

  private final SelectedItemTypeProperties properties;
  private final IItemTypeSelectionView view;
  private final INewItemWizardModel model;
  private final IRegistry<IItemType, IWizardFactory> followUpRegistry;

  public SelectItemTypePage(
      IResources resources,
      IRegistry<IItemType, IWizardFactory> registry,
      INewItemWizardModel model,
      IItemTypeSelectionView view) {
    this.followUpRegistry = registry;
    this.model = model;
    this.view = view;
    this.properties = new SelectedItemTypeProperties(resources);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    for (final IItemType type : model.getRegisteredItemTypes()) {
      IAnathemaWizardPage followUpPage = followUpRegistry.get(type).createPage(model.getTemplate(type));
      if (followUpPage == null) {
        continue;
      }
      addFollowupPage(followUpPage, inputListener, new ICondition() {
        public boolean isFullfilled() {
          return model.getSelectedItemType() == type;
        }
      });
    }
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    model.addListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    IListObjectSelectionView<IItemType> listView = view.addSelectionView();
    listView.setObjects(model.getRegisteredItemTypes());
    listView.setSelectionType(ListSelectionMode.SingleSelection);
    listView.setCellRenderer(properties.getCellRenderer());
    listView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IItemType>() {
      public void valueChanged(IItemType newValue) {
        model.setSelectedValue(newValue);
      }
    });
  }

  public boolean canFinish() {
    IItemType type = model.getSelectedItemType();
    return type != null && followUpRegistry.get(type) == null;
  }

  public String getDescription() {
    return properties.getItemSelectionTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getSelectMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}