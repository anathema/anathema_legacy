package net.sf.anathema.character.equipment.character;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.collection.ICollectionListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentAdditionalPresenter implements IPresenter {

  private final IResources resources;
  private final IEquipmentAdditionalModel model;
  private final IEquipmentAdditionalView view;
  private final Map<IEquipmentItem, IEquipmentObjectView> viewsByItem = new HashMap<IEquipmentItem, IEquipmentObjectView>();

  public EquipmentAdditionalPresenter(
      IResources resources,
      IEquipmentAdditionalModel model,
      IEquipmentAdditionalView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    for (IEquipmentItem item : model.getEquipmentItems()) {
      initEquipmentObjectPresentation(item);
    }
    final IListObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    model.addEquipmentObjectListener(new ICollectionListener<IEquipmentItem>() {
      public void itemAdded(IEquipmentItem item) {
        initEquipmentObjectPresentation(item);
      }

      public void itemRemoved(IEquipmentItem item) {
        IEquipmentObjectView objectView = viewsByItem.remove(item);
        view.removeEquipmentObjectView(objectView);
      }
    });
    equipmentTemplatePickList.setCellRenderer(new EquipmentObjectCellRenderer());
    setObjects(equipmentTemplatePickList);
    view.setSelectButtonAction(createTemplateAddAction(equipmentTemplatePickList));
    view.setRefreshButtonAction(createRefreshAction(equipmentTemplatePickList));
    String label = resources.getString("MagicMaterial.Label") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    DefaultListCellRenderer renderer = new MagicMaterialCellRenderer(resources);
    view.getMagicMaterialView().initView(label, renderer, MagicalMaterial.values(), null);
    equipmentTemplatePickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String templateId) {
        MaterialComposition composition = templateId == null
            ? MaterialComposition.None
            : model.getMaterialComposition(templateId);
        MagicalMaterial defaultMagicMaterial = composition != MaterialComposition.Variable
            ? null
            : model.getDefaultMaterial();
        view.getMagicMaterialView().setSelectedMaterial(defaultMagicMaterial);
      }
    });
  }

  private SmartAction createRefreshAction(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    SmartAction refreshAction = new SmartAction(new EquipmentUI(resources).getRefreshIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        setObjects(equipmentTemplatePickList);
      }
    };
    refreshAction.setToolTipText(resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip")); //$NON-NLS-1$
    return refreshAction;
  }

  private void setObjects(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    equipmentTemplatePickList.setObjects(model.getAvailableTemplateIds());
  }

  private SmartAction createTemplateAddAction(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    final SmartAction addAction = new SmartAction(new BasicUi(resources).getRightArrowIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        model.addEquipmentObjectFor(equipmentTemplatePickList.getSelectedObject());
      }
    };
    addAction.setToolTipText(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip")); //$NON-NLS-1$
    equipmentTemplatePickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
      }
    });
    addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
    return addAction;
  }

  private void initEquipmentObjectPresentation(final IEquipmentItem selectedObject) {
    IEquipmentObjectView objectView = view.addEquipmentObjectView();
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    Icon removeIcon = new BasicUi(resources).getRemoveIcon();
    viewsByItem.put(selectedObject, objectView);
    new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder).initPresentation();
    if (model.canBeRemoved(selectedObject)) {
      objectView.addAction(new SmartAction(resources.getString("AdditionalTemplateView.RemoveTemplate.Action.Name"), //$NON-NLS-1$
          removeIcon) {
        @Override
        protected void execute(Component parentComponent) {
          model.removeItem(selectedObject);
        }
      });
    }
  }
}