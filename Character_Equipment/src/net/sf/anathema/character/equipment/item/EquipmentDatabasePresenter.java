package net.sf.anathema.character.equipment.item;

import com.google.common.base.Function;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class EquipmentDatabasePresenter implements Presenter {
  private final Resources resources;
  private final EquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;
  private final String[] defaultCostBackgrounds = {"Artifact", "Manse", "Resources"};

  public EquipmentDatabasePresenter(Resources resources, IEquipmentDatabaseManagement model,
                                    EquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    new EquipmentTemplateListPresenter(resources, model, view).initPresentation();
    addEditTemplateActions();
    initBasicDetailsView();
    new EquipmentEditStatsPresenter(resources, new WrappingStatsEditModel(model), view).initPresentation();
    model.getTemplateEditModel().setNewTemplate();
  }

  private String getColonString(String key) {
    return resources.getString(key) + ":";
  }

  private void addEditTemplateActions() {
    new NewEquipmentTemplateAction(resources, model).addToolTo(view);
    new SaveEquipmentTemplateAction(resources, model).addToolTo(view);
    new CopyEquipmentTemplateAction(resources, model).addToolTo(view);
    new RemoveEquipmentTemplateAction(resources, model).addToolTo(view);
  }

  private void initBasicDetailsView() {
    EquipmentDescriptionPanel descriptionPanel = view.addDescriptionPanel(resources.getString("Equipment.Creation.Basics"));
    ITextView nameView = descriptionPanel.addNameView(getColonString("Equipment.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = descriptionPanel.addDescriptionView(getColonString("Equipment.Creation.Basics.Description"));
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    final ObjectSelectionView<MaterialComposition> compositionView = descriptionPanel.addCompositionView(getColonString("Equipment.Creation.Basics.Composition"), new CompositionUi(resources));
    compositionView.setObjects(MaterialComposition.values());
    final ObjectSelectionView<MagicalMaterial> materialView = descriptionPanel.addMaterialView(getColonString("Equipment.Creation.Basics.Material"), new MaterialUi(resources));
    materialView.setObjects(MagicalMaterial.values());
    String[] backgrounds = transform(defaultCostBackgrounds, String.class, new Function<String, String>() {
      @Override
      public String apply(String arg0) {
        return resources.getString("BackgroundType.Name." + arg0);
      }
    });
    final CostSelectionView costView = descriptionPanel.addCostView(getColonString("Equipment.Creation.Basics.Cost"));
    costView.setSelectableBackgrounds(backgrounds);
    compositionView.addObjectSelectionChangedListener(new ObjectValueListener<MaterialComposition>() {
      @Override
      public void valueChanged(MaterialComposition newValue) {
        model.getTemplateEditModel().setMaterialComposition(newValue);
      }
    });
    model.getTemplateEditModel().addCompositionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        MaterialComposition materialComposition = model.getTemplateEditModel().getMaterialComposition();
        compositionView.setSelectedObject(materialComposition);
        materialView.setEnabled(materialComposition.requiresMaterial());
      }
    });
    materialView.addObjectSelectionChangedListener(new ObjectValueListener<MagicalMaterial>() {
      @Override
      public void valueChanged(MagicalMaterial newValue) {
        model.getTemplateEditModel().setMagicalMaterial(newValue);
      }
    });
    model.getTemplateEditModel().addMagicalMaterialChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        materialView.setSelectedObject(model.getTemplateEditModel().getMagicalMaterial());
      }
    });
    costView.addSelectionChangedListener(new ISelectionIntValueChangedListener<String>() {
      @Override
      public void valueChanged(String selection, int value) {
        ItemCost cost = selection == null ? null : new ItemCost(selection, value);
        ItemCost currentModelCost = model.getTemplateEditModel().getCost();
        if ((cost == null && currentModelCost != null) ||
                (cost != null && currentModelCost == null) ||
                (cost != null && !cost.equals(currentModelCost))) {
          model.getTemplateEditModel().setCost(cost);
        }
      }
    });
    model.getTemplateEditModel().addCostChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        costView.setValue(model.getTemplateEditModel().getCost());
      }
    });
  }
}