package net.sf.anathema.campaign.load.selection;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public class ObjectSelectionDialogPage<V> extends AbstractDialogPage {
  private final IObjectSelectionWizardModel<V> model;
  private final IObjectSelectionView<V> view;
  private final IObjectSelectionProperties properties;

  public ObjectSelectionDialogPage(
          IObjectSelectionWizardModel<V> model,
          IObjectSelectionView<V> view,
          IObjectSelectionProperties properties) {
    super(properties.getSelectMessage().getText());
    this.model = model;
    this.view = view;
    this.properties = properties;
  }

  @Override
  public void setInputValidListener(IChangeListener inputValidListener) {
    model.addListener(inputValidListener);
  }

  @Override
  public boolean canFinish() {
    V value = model.getSelectedObject();
    return value != null;
  }

  @Override
  public String getTitle() {
    return properties.getSelectionTitle();
  }

  @Override
  public String getDescription() {
    return properties.getSelectionTitle();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return properties.getSelectMessage();
  }

  @Override
  public JComponent createContent() {
    final IListObjectSelectionView<V> listView = view.addSelectionView();
    listView.setObjects(model.getRegisteredObjects());
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
    return view.getContent();
  }
}