package net.sf.anathema.character.equipment.item;

import com.google.common.base.Function;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.framework.resources.CharacterIntValueGraphics;
import net.sf.anathema.character.generic.type.MortalCharacterType;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.*;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class EquipmentDatabasePresenter implements Presenter {
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;
  private final String[] defaultCostBackgrounds = {"Artifact", "Manse", "Resources"};

  public EquipmentDatabasePresenter(IResources resources, IEquipmentDatabaseManagement model,
                                    IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    new EquipmentTemplateListPresenter(resources, model, view).initPresentation();
    addEditTemplateActions();
    initBasicDetailsView();
    new EquipmentEditStatsPresenter(resources, model, view).initPresentation();
    model.getTemplateEditModel().setNewTemplate();
  }

  private String getColonString(String key) {
    return resources.getString(key) + ":"; //$NON-NLS-1$
  }

  private void addEditTemplateActions() {
    view.addEditTemplateAction(new NewEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new CopyEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new SaveEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new RemoveEquipmentTemplateAction(resources, model, view));
  }

  private void initBasicDetailsView() {
    MigPanelBuilder panelBuilder = new MigPanelBuilder();
    ITextView nameView = panelBuilder.addLineTextView(getColonString("Equipment.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = panelBuilder.addAreaTextView(getColonString("Equipment.Creation.Basics.Description"), 5);
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    final ObjectSelectionView<MaterialComposition> compositionView = new ObjectSelectionView<>(
            getColonString("Equipment.Creation.Basics.Composition"), //$NON-NLS-1$
            new IdentificateSelectCellRenderer("MaterialComposition.", resources), //$NON-NLS-1$
            MaterialComposition.values());
    final ObjectSelectionView<MagicalMaterial> materialView = new ObjectSelectionView<>(
            getColonString("Equipment.Creation.Basics.Material"), //$NON-NLS-1$
            new IdentificateSelectCellRenderer("MagicMaterial.", resources), //$NON-NLS-1$
            MagicalMaterial.values());
    panelBuilder.addView(compositionView, new CC().split(3).gapAfter("15"));
    panelBuilder.addView(materialView, new CC());
    String[] backgrounds = transform(defaultCostBackgrounds, String.class, new Function<String, String>() {
      @Override
      public String apply(String arg0) {
        return resources.getString("BackgroundType.Name." + arg0);
      }
    });
    final CostSelectionView costView = new CostSelectionView(getColonString("Equipment.Creation.Basics.Cost"),
            backgrounds, getIntValueDisplayFactory());
    panelBuilder.addView(costView, new CC().split(2));
    compositionView.addObjectSelectionChangedListener(new ObjectValueListener<MaterialComposition>() {
      @Override
      public void valueChanged(MaterialComposition newValue) {
        model.getTemplateEditModel().setMaterialComposition(newValue);
      }
    });
    model.getTemplateEditModel().addCompositionChangeListener(new IChangeListener() {
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
    model.getTemplateEditModel().addMagicalMaterialChangeListener(new IChangeListener() {
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
    model.getTemplateEditModel().addCostChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        costView.setValue(model.getTemplateEditModel().getCost());
      }
    });
    JComponent titledContent = panelBuilder.getTitledContent(resources.getString("Equipment.Creation.Basics"));
    view.fillDescriptionPanel(titledContent);
  }

  private IntegerViewFactory getIntValueDisplayFactory() {
    return new MarkerIntValueDisplayFactory(new CharacterIntValueGraphics(resources, new MortalCharacterType()));
  }
}