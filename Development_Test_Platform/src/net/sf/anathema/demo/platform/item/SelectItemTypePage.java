package net.sf.anathema.demo.platform.item;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
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
  private final IRegistry<CreationItemType, IAnathemaWizardPage> followUpRegistry;

  public SelectItemTypePage(
      IResources resources,
      IRegistry<CreationItemType, IAnathemaWizardPage> followUpRegistry,
      INewItemWizardModel model,
      IItemTypeSelectionView view) {
    this.followUpRegistry = followUpRegistry;
    this.model = model;
    this.view = view;
    this.properties = new SelectedItemTypeProperties(resources);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    for (final CreationItemType type : model.getRegisteredItemTypes()) {
      IAnathemaWizardPage followUpPage = followUpRegistry.get(type);
      if (followUpPage == null) {
        continue;
      }
      addFollowupPage(followUpPage, inputListener, new ICondition() {
        public boolean isFullfilled() {
          return model.getSelectedValue() == type;
        }
      });
    }
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initPageContent() {
    IListObjectSelectionView<CreationItemType> listView = view.addSelectionView();
    listView.setObjects(model.getRegisteredItemTypes());
    listView.setSelectionType(ListSelectionMode.SingleSelection);
    listView.setCellRenderer(properties.getCellRenderer());
    listView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CreationItemType>() {
      public void valueChanged(CreationItemType newValue) {
        model.setSelectedValue(newValue);
      }
    });
  }

  public boolean canFinish() {
    CreationItemType type = model.getSelectedValue();
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