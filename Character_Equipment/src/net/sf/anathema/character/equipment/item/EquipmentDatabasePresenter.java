package net.sf.anathema.character.equipment.item;

import javax.swing.JPanel;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.framework.resources.CharacterIntValueGraphics;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentDatabasePresenter implements Presenter {

  private static final int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;
  
  private final String[] defaultCostBackgrounds = { "Artifact", "Manse", "Resources" };

  public EquipmentDatabasePresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
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

      @Override
      public void fillInto(JPanel panel, int columnCount) {
        compositionView.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
        materialView.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
      }

      @Override
      public int getColumnCount() {
        return 4;
      }
    });
    String[] backgrounds = ArrayUtilities.transform(defaultCostBackgrounds, String.class,
    		new ITransformer<String, String>() {

				@Override
				public String transform(String arg0) {
					return resources.getString("BackgroundType.Name." + arg0);
				}
    });
    final CostSelectionView costView = new CostSelectionView(
            getColonString("Equipment.Creation.Basics.Cost"), //$NON-NLS-1$
            backgrounds, getIntValueDisplayFactory());
    panelBuilder.addDialogComponent(new IDialogComponent() {

        @Override
        public void fillInto(JPanel panel, int columnCount) {
          costView.addTo(panel, GridDialogLayoutData.FILL_HORIZONTAL);
        }

        @Override
        public int getColumnCount() {
          return 4;
        }
      });
    compositionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<MaterialComposition>() {
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
    materialView.addObjectSelectionChangedListener(new IObjectValueChangedListener<MagicalMaterial>() {
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
    view.fillDescriptionPanel(panelBuilder.getTitledContent(resources.getString("Equipment.Creation.Basics"))); //$NON-NLS-1$
  }
  
  private IIntValueDisplayFactory getIntValueDisplayFactory() {
	  return new MarkerIntValueDisplayFactory(new CharacterIntValueGraphics(resources, CharacterType.MORTAL));
  }
}
