package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.EquipmentTemplateNameComparator;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.model.EquipmentPersonalizationModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ICollectionListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Closure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EquipmentPresenter implements Presenter {

  private final Resources resources;
  private final EquipmentModel model;
  private final EquipmentView view;
  private final Map<IEquipmentItem, EquipmentObjectView> viewsByItem = new HashMap<>();

  public EquipmentPresenter(Resources resources, final EquipmentModel model, EquipmentView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;

    model.getHeroEvaluator().addCharacterSpecialtyListChangeListener(new ChangeListener() {
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
    VetoableObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
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
    MagicalMaterialView magicMaterialView = view.getMagicMaterialView();
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

  private void initMaterialView(MagicalMaterialView magicMaterialView) {
    String label = resources.getString("MagicMaterial.Label") + ":";
    AgnosticUIConfiguration<MagicalMaterial> renderer = new MagicMaterialUIConfiguration(resources);
    magicMaterialView.initView(label, renderer, MagicalMaterial.values());
  }

  private void createRefreshAction(final VetoableObjectSelectionView<String> equipmentTemplatePickList,
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

  private void setObjects(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private void createTemplateAddAction(final VetoableObjectSelectionView<String> equipmentTemplatePickList,
                                       final MagicalMaterialView materialView, final Tool selectTool) {
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

  private void setEnabled(VetoableObjectSelectionView<String> equipmentTemplatePickList, Tool selectTool) {
    if (equipmentTemplatePickList.isObjectSelected()) {
      selectTool.enable();
    } else {
      selectTool.disable();
    }
  }

  private void removeItemView(IEquipmentItem item) {
    EquipmentObjectView objectView = viewsByItem.remove(item);
    view.removeEquipmentObjectView(objectView);
  }

  private void initEquipmentObjectPresentation(final IEquipmentItem selectedObject) {
    EquipmentObjectView objectView = viewsByItem.get(selectedObject);
    objectView = objectView == null ? view.addEquipmentObjectView() : objectView;
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    viewsByItem.put(selectedObject, objectView);
    EquipmentObjectPresenter objectPresenter = new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder,
            model.getHeroEvaluator(), model.getOptionProvider(), resources);
    objectPresenter.initPresentation();
    enablePersonalization(selectedObject, objectPresenter);
    view.revalidateEquipmentViews();
  }

  private void enablePersonalization(IEquipmentItem selectedObject, EquipmentObjectPresenter objectPresenter) {
    if (model.canBeRemoved(selectedObject)) {
      createPersonalizeTool(selectedObject, objectPresenter);
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