package net.sf.anathema.character.equipment.item;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
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
    model.getTemplateEditModel().setNewTemplate();
  }

  private String getColonString(String key) {
    return resources.getString(key) + ":"; //$NON-NLS-1$
  }

  private void addEditTemplateActions() {
    view.addEditTemplateAction(new NewEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new SaveEquipmentTemplateAction(resources, model));
    view.addEditTemplateAction(new RemoveEquipmentTemplateAction(resources, model, view));
  }

  private void initBasicDetailsView() {
    StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
    ITextView nameView = panelBuilder.addLineTextView(getColonString("Equipment.Creation.Basics.Name"), COLUMN_COUNT); //$NON-NLS-1$
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = panelBuilder.addAreaTextView(getColonString("Equipment.Creation.Basics.Description"), //$NON-NLS-1$
        5,
        COLUMN_COUNT);
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    final ObjectSelectionView<MaterialComposition> compositionView = new ObjectSelectionView<MaterialComposition>(
        getColonString("Equipment.Creation.Basics.Composition"), //$NON-NLS-1$
        new IdentificateSelectCellRenderer("MaterialComposition.", resources), //$NON-NLS-1$
        MaterialComposition.values());
    final ObjectSelectionView<MagicalMaterial> materialView = new ObjectSelectionView<MagicalMaterial>(
        getColonString("Equipment.Creation.Basics.Material"), //$NON-NLS-1$
        new IdentificateSelectCellRenderer("MagicMaterial.", resources), //$NON-NLS-1$
        MagicalMaterial.values());
    panelBuilder.addDialogComponent(new IDialogComponent() {

      public void fillInto(JPanel panel, int columnCount) {
        compositionView.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
        materialView.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
      }

      public int getColumnCount() {
        return 4;
      }
    });
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
    view.fillDescriptionPanel(panelBuilder.getTitledContent(resources.getString("Equipment.Creation.Basics"))); //$NON-NLS-1$
  }
}