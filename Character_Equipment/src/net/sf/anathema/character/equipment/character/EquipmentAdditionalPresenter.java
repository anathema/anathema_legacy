package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.preference.AnathemaEquipmentPreferences;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.equipment.character.view.IMagicalMaterialView;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.EquipmentTemplateNameComparator;
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationModel;
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationPresenterPage;
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ICollectionListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EquipmentAdditionalPresenter implements Presenter {

  private final Resources resources;
  private final IEquipmentAdditionalModel model;
  private final IEquipmentAdditionalView view;
  private final Map<IEquipmentItem, IEquipmentObjectView> viewsByItem = new HashMap<>();

  public EquipmentAdditionalPresenter(Resources resources, final IEquipmentAdditionalModel model,
                                      IEquipmentAdditionalView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;

    model.getCharacterDataProvider().addCharacterSpecialtyListChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        for (IEquipmentItem item : model.getNaturalWeapons()) {
          initEquipmentObjectPresentation(item);
        }
        for (IEquipmentItem item : model.getEquipmentItems()) {
          initEquipmentObjectPresentation(item);
        }
      }
    });
  }


  @Override
  public void initPresentation() {
    for (IEquipmentItem item : model.getNaturalWeapons()) {
      initEquipmentObjectPresentation(item);
    }
    for (IEquipmentItem item : model.getEquipmentItems()) {
      initEquipmentObjectPresentation(item);
    }
    IListObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    model.addEquipmentObjectListener(new ICollectionListener<IEquipmentItem>() {
      @Override
      public void itemAdded(IEquipmentItem item) {
        initEquipmentObjectPresentation(item);
      }

      @Override
      public void itemRemoved(IEquipmentItem item) {
        removeItemView(item);
      }
    });
    IMagicalMaterialView magicMaterialView = view.getMagicMaterialView();
    initMaterialView(magicMaterialView);
    equipmentTemplatePickList.setCellRenderer(new EquipmentObjectCellRenderer(model));
    setObjects(equipmentTemplatePickList);
    view.setSelectButtonAction(createTemplateAddAction(equipmentTemplatePickList, magicMaterialView));
    view.setRefreshButtonAction(createRefreshAction(equipmentTemplatePickList));
    equipmentTemplatePickList.addObjectSelectionChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String templateId) {
        MaterialComposition composition = templateId == null ? MaterialComposition.None : model.getMaterialComposition(
                templateId);
        MagicalMaterial magicMaterial = null;
        if (composition == MaterialComposition.Variable) {
          magicMaterial = model.getDefaultMaterial();
        } else if (composition == MaterialComposition.Fixed) {
          magicMaterial = model.getMagicalMaterial(templateId);
        }
        view.getMagicMaterialView().setSelectedMaterial(magicMaterial, composition == MaterialComposition.Variable);
      }
    });
  }

  private void initMaterialView(IMagicalMaterialView magicMaterialView) {
    String label = resources.getString("MagicMaterial.Label") + ":";
    TechnologyAgnosticUIConfiguration<MagicalMaterial> renderer = new MagicMaterialCellRenderer(resources);
    magicMaterialView.initView(label, renderer, MagicalMaterial.values());
  }

  private SmartAction createRefreshAction(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    SmartAction refreshAction = new SmartAction(new EquipmentUI().getRefreshIcon()) {

      @Override
      protected void execute(Component parentComponent) {
        setObjects(equipmentTemplatePickList);
        model.refreshItems();
      }
    };
    refreshAction.setToolTipText(resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip"));
    return refreshAction;
  }

  private void setObjects(IListObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private SmartAction createTemplateAddAction(final IListObjectSelectionView<String> equipmentTemplatePickList,
                                              final IMagicalMaterialView materialView) {
    final SmartAction addAction = new SmartAction(new BasicUi().getRightArrowIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        model.addEquipmentObjectFor(equipmentTemplatePickList.getSelectedObject(), materialView.getSelectedMaterial());
      }
    };
    addAction.setToolTipText(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip"));
    equipmentTemplatePickList.addObjectSelectionChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
      }
    });
    addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
    return addAction;
  }

  private void removeItemView(IEquipmentItem item) {
    IEquipmentObjectView objectView = viewsByItem.remove(item);
    view.removeEquipmentObjectView(objectView);
  }

  private void initEquipmentObjectPresentation(final IEquipmentItem selectedObject) {
    IEquipmentObjectView objectView = viewsByItem.get(selectedObject);
    objectView = objectView == null ? view.addEquipmentObjectView() : objectView;
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    viewsByItem.put(selectedObject, objectView);
    EquipmentObjectPresenter objectPresenter = new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder,
            model.getCharacterDataProvider(), model.getCharacterOptionProvider(), resources);
    enablePersonalization(selectedObject, objectPresenter);
    objectPresenter.initPresentation();
    view.revalidateEquipmentViews();
  }

  private void enablePersonalization(IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    if (model.canBeRemoved(selectedObject)) {
      if (AnathemaEquipmentPreferences.getDefaultPreferences().getEnablePersonalization()) {
        createPersonalizeTool(selectedObject, objectPresenter);
      }
      createUnpersonalizeTool(selectedObject, objectPresenter);
    }
  }

  private void createPersonalizeTool(final IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    Tool personalize = objectPresenter.addContextTool();
    personalize.setIcon(new BasicUi().getEditIconPath());
    personalize.setText(resources.getString("AdditionalTemplateView.Personalize.Action.Name"));
    personalize.setCommand(new Command() {
      @Override
      public void execute() {
        EquipmentPersonalizationModel personalizationModel = new EquipmentPersonalizationModel(selectedObject);
        EquipmentPersonalizationProperties properties = new EquipmentPersonalizationProperties(resources);
        EquipmentPersonalizationPresenterPage page = new EquipmentPersonalizationPresenterPage(personalizationModel,
                properties);
        UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), page);
        IDialogResult result = dialog.show();
        if (!result.isCanceled()) {
          selectedObject.setPersonalization(personalizationModel.getTitle(), personalizationModel.getDescription());
          initEquipmentObjectPresentation(selectedObject);
          EquipmentAdditionalPresenter.this.model.updateItem(selectedObject);
        }
      }
    });
  }

  private void createUnpersonalizeTool(final IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    Tool unpersonalize = objectPresenter.addContextTool();
    unpersonalize.setIcon(new BasicUi().getRemoveIconPath());
    unpersonalize.setText(resources.getString("AdditionalTemplateView.RemoveTemplate.Action.Name"));
    unpersonalize.setCommand(new Command() {
      @Override
      public void execute() {
        model.removeItem(selectedObject);
      }
    });
  }
}