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
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ICollectionListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Closure;

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
    equipmentTemplatePickList.setCellRenderer(new EquipmentItemUIConfiguration(model));
    setObjects(equipmentTemplatePickList);
    Tool addTool = view.addToolButton();
    createTemplateAddAction(equipmentTemplatePickList, magicMaterialView, addTool);
    Tool refreshTool = view.addToolButton();
    createRefreshAction(equipmentTemplatePickList, refreshTool);
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
    AgnosticUIConfiguration<MagicalMaterial> renderer = new MagicMaterialUIConfiguration(resources);
    magicMaterialView.initView(label, renderer, MagicalMaterial.values());
  }

  private void createRefreshAction(final IListObjectSelectionView<String> equipmentTemplatePickList,
                                   Tool refreshTool) {
    refreshTool.setTooltip(resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip"));
    refreshTool.setIcon(new EquipmentUI().getRefreshIconPath());
    refreshTool.setCommand(new Command() {
      @Override
      public void execute() {
        setObjects(equipmentTemplatePickList);
        model.refreshItems();
      }
    });
  }

  private void setObjects(IListObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private void createTemplateAddAction(final IListObjectSelectionView<String> equipmentTemplatePickList,
                                       final IMagicalMaterialView materialView, final Tool selectTool) {
    selectTool.setIcon(new BasicUi().getRightArrowIconPath());
    selectTool.setTooltip(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip"));
    selectTool.setCommand(new Command() {
      @Override
      public void execute() {
        model.addEquipmentObjectFor(equipmentTemplatePickList.getSelectedObject(), materialView.getSelectedMaterial());
      }
    });
    equipmentTemplatePickList.addObjectSelectionChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        setEnabled(equipmentTemplatePickList, selectTool);
      }
    });
    setEnabled(equipmentTemplatePickList, selectTool);
  }

  private void setEnabled(IListObjectSelectionView<String> equipmentTemplatePickList, Tool selectTool) {
    if (equipmentTemplatePickList.isObjectSelected()) {
      selectTool.enable();
    } else {
      selectTool.disable();
    }
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
    objectPresenter.initPresentation();
    enablePersonalization(selectedObject, objectPresenter);
    view.revalidateEquipmentViews();
  }

  private void enablePersonalization(IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    if (model.canBeRemoved(selectedObject)) {
      if (AnathemaEquipmentPreferences.getDefaultPreferences().getEnablePersonalization()) {
        createPersonalizeTool(selectedObject, objectPresenter);
      }
      createRemoveItemTool(selectedObject, objectPresenter);
    }
  }

  private void createPersonalizeTool(final IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    Tool personalize = objectPresenter.addContextTool();
    personalize.setIcon(new BasicUi().getEditIconPath());
    personalize.setText(resources.getString("AdditionalTemplateView.Personalize.Action.Name"));
    personalize.setCommand(new Command() {
      @Override
      public void execute() {
        personalizeItem(selectedObject);
      }
    });
  }

  private void createRemoveItemTool(final IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
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

  private void personalizeItem(final IEquipmentItem selectedObject) {
    PersonalizationEditView personalizationView = createView();
    final EquipmentPersonalizationModel personalizationModel = new EquipmentPersonalizationModel(selectedObject);
    personalizationView.setTitle(personalizationModel.getTitle());
    personalizationView.setDescription(personalizationModel.getDescription());
    personalizationView.whenTitleChanges(new Closure<String>() {
      @Override
      public void execute(String newValue) {
        personalizationModel.setTitle(newValue);
      }
    });
    personalizationView.whenDescriptionChanges(new Closure<String>() {
      @Override
      public void execute(String newValue) {
        personalizationModel.setDescription(newValue);
      }
    });
    personalizationView.whenChangeIsConfirmed(new Runnable() {
      @Override
      public void run() {
        selectedObject.setPersonalization(personalizationModel.getTitle(), personalizationModel.getDescription());
        initEquipmentObjectPresentation(selectedObject);
        model.updateItem(selectedObject);
      }
    });
    personalizationView.show();
  }

  private PersonalizationEditView createView() {
    EquipmentPersonalizationProperties properties = new EquipmentPersonalizationProperties(resources);
    return view.startEditingPersonalization(properties);
  }
}