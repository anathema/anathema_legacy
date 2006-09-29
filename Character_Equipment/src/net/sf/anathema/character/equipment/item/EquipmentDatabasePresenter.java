package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentDatabasePresenter implements IPresenter {

  private static final int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentDatabasePresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
      IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    new EquipmentTemplateListPresenter(resources, model, view).initPresentation();
    addEditTemplateActions();
    initBasicDetailsView();
    new EquipmentEditStatsPresenter(resources, model, view).initPresentation();
    view.setTemplateListHeader("Available Templates");
    view.setEditTemplateHeader("Edit Template");
  }

  private void addEditTemplateActions() {
    view.addEditTemplateAction(new NewEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new SaveEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new RemoveEquipmentTemplateAction(resources, model, view));
  }

  //TODO: Create ObjectSelectionViews externally and add them to PanelBuilder (they should be in the same row)
  private void initBasicDetailsView() {
    StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
    ITextView nameView = panelBuilder.addLineTextView("Name:", COLUMN_COUNT);
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = panelBuilder.addAreaTextView("Description:", 5, COLUMN_COUNT);
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    final IObjectSelectionView<MaterialComposition> compositionView = panelBuilder.addObjectSelectionView(
        "Composition:",
        new IdentificateListCellRenderer(resources),
        MaterialComposition.values());
    final IObjectSelectionView<MagicalMaterial> materialView = panelBuilder.addObjectSelectionView(
        "Material:",
        new IdentificateListCellRenderer(resources),
        MagicalMaterial.values());
    compositionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<MaterialComposition>() {
      public void valueChanged(MaterialComposition newValue) {
        model.getTemplateEditModel().setMaterialComposition(newValue);
      }
    });
    model.getTemplateEditModel().addCompositionChangeListener(new IChangeListener() {
      public void changeOccured() {
        MaterialComposition materialComposition = model.getTemplateEditModel().getMaterialComposition();
        compositionView.setSelectedObject(materialComposition);
        materialView.setEnabled(materialComposition.requiresMaterial());
      }
    });
    materialView.addObjectSelectionChangedListener(new IObjectValueChangedListener<MagicalMaterial>() {
      public void valueChanged(MagicalMaterial newValue) {
        model.getTemplateEditModel().setMagicalMaterial(newValue);
      }
    });
    model.getTemplateEditModel().addMagicalMaterialChangeListener(new IChangeListener() {
      public void changeOccured() {
        materialView.setSelectedObject(model.getTemplateEditModel().getMagicalMaterial());
      }
    });
    view.fillDescriptionPanel(panelBuilder.getTitledContent("Basics"));
  }
}