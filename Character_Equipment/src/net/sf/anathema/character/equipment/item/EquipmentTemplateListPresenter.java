package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.resources.IResources;

import java.util.Arrays;

public class EquipmentTemplateListPresenter implements Presenter {

  private final class EquipmentTemplateLoadListener implements ObjectValueListener<String> {
    @Override
    public void valueChanged(String newValue) {
      if (newValue == null) {
        return;
      }
      IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
      editModel.setEditTemplate(newValue);
    }
  }

  private final IResources resources;
  private final EquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentTemplateListPresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
      EquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    model.getDatabase().addAvailableTemplateChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateAvailableTemplates();
      }
    });
    updateAvailableTemplates();
    view.getTemplateListView().addSelectionVetor(new DiscardChangesVetor(resources, new ICondition() {
      @Override
      public boolean isFulfilled() {
        IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
        return editModel.isDirty();
      }
    }, SwingApplicationFrame.getParentComponent()));
    view.getTemplateListView().addObjectSelectionChangedListener(new EquipmentTemplateLoadListener());
  }

  private void updateAvailableTemplates() {
    String[] templates = model.getDatabase().getAllAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    view.getTemplateListView().setObjects(templates);
  }
}
