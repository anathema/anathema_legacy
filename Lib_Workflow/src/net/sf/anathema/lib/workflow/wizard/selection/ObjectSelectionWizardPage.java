package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.list.ListSelectionMode;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;

public class ObjectSelectionWizardPage<V> extends AbstractAnathemaWizardPage {
  private final IRegistry<V, IWizardFactory> followUpRegistry;
  private final IObjectSelectionWizardModel<V> model;
  private final IObjectSelectionView<V> view;
  private final IObjectSelectionProperties properties;
  private final Registry<V, IAnathemaWizardModelTemplate> modelTemplateRegistry;

  public ObjectSelectionWizardPage(
      IRegistry<V, IWizardFactory> followUpRegistry,
      Registry<V, IAnathemaWizardModelTemplate> modelTemplateRegistry,
      IObjectSelectionWizardModel<V> model,
      IObjectSelectionView<V> view,
      IObjectSelectionProperties properties) {
    this.followUpRegistry = followUpRegistry;
    this.modelTemplateRegistry = modelTemplateRegistry;
    this.model = model;
    this.view = view;
    this.properties = properties;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    for (final V type : model.getRegisteredObjects()) {
      IWizardFactory wizardFactory = followUpRegistry.get(type);
      if (!wizardFactory.needsFurtherDetails()) {
        continue;
      }
      IAnathemaWizardPage followUpPage = wizardFactory.createPage(modelTemplateRegistry.get(type));
      addFollowupPage(followUpPage, inputListener, new ICondition() {
        @Override
        public boolean isFulfilled() {
          return model.getSelectedObject() == type;
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
    final IListObjectSelectionView<V> listView = view.addSelectionView();
    listView.setObjects(model.getRegisteredObjects());
    listView.setSelectionType(ListSelectionMode.SingleSelection);
    listView.setCellRenderer(properties.getCellRenderer());
    listView.addObjectSelectionChangedListener(new ObjectValueListener<V>() {
      @Override
      public void valueChanged(V newValue) {
        if (newValue == null) {
          return;
        }
        model.setSelectedObject(newValue);
      }
    });
    model.addListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        listView.setSelectedObject(model.getSelectedObject());
      }
    });
  }

  @Override
  public boolean canFinish() {
    V value = model.getSelectedObject();
    return value != null && !followUpRegistry.get(value).needsFurtherDetails();
  }

  @Override
  public String getDescription() {
    return properties.getSelectionTitle();
  }

  @Override
  public IBasicMessage getMessage() {
    return properties.getSelectMessage();
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }
}