package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.charmdatabase.management.filters.CharmNameFilter;
import net.sf.anathema.charmdatabase.view.CharmDatabaseView;
import net.sf.anathema.charmdatabase.view.CharmDescriptionPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class CharmDatabasePresenter implements Presenter {
  private final Resources resources;
  private final CharmDatabaseView view;
  private final ICharmDatabaseManagement model;

  public CharmDatabasePresenter(Resources resources, ICharmDatabaseManagement model,
                                    CharmDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    new CharmListPresenter(resources, model, view).initPresentation();
    addEditTemplateActions();
    initCharmListFilterView();
    initBasicDetailsView();
    model.getCharmEditModel().setNewTemplate();
  }
  
  private void addEditTemplateActions() {
    /*new NewEquipmentTemplateAction(resources, model).addToolTo(view);
    new SaveEquipmentTemplateAction(resources, model).addToolTo(view);
    new CopyEquipmentTemplateAction(resources, model).addToolTo(view);
    new RemoveEquipmentTemplateAction(resources, model).addToolTo(view);*/
  }
  
  private void initCharmListFilterView() {
	  final ITextView filterView = view.addTextualFilter(resources.getString("Charms.Filter"));
	  final CharmNameFilter filter = new CharmNameFilter(resources);
	  model.addFilter(filter);
	  filterView.addTextChangedListener(new ObjectValueListener<String>() {

		@Override
		public void valueChanged(String newValue) {
			filter.setCurrentText(newValue);
			model.notifyFiltersUpdated();
		}
		  
	  });
  }

  private void initBasicDetailsView() {
	  
    CharmDescriptionPanel descriptionPanel = view.addDescriptionPanel(resources.getString("Charms.Creation.Basics"));
    final ITextView nameView = descriptionPanel.addNameView(resources.getString("Charms.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getCharmEditModel().getName());
    
    final ObjectSelectionView<Identifier> typeView = descriptionPanel.addTypeView(resources.getString("Charms.Creation.Basics.CharmType"),
    		new DirectUi());
    typeView.setObjects(model.getCharmTypes());
    final ObjectSelectionView<Identifier> groupView = descriptionPanel.addGroupView(resources.getString("Charms.Creation.Basics.GroupType"),
    		new TypeUi(resources));
    model.getCharmEditModel().addCharmTypeChangedListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			typeView.setSelectedObject(model.getCharmEditModel().getCharmType());
			// TODO: We need a more generalized means to handle this
			if (model.getCharmEditModel().getCharmType() instanceof CharacterType) {
				groupView.setObjects(((CharacterType)model.getCharmEditModel().getCharmType()).getFavoringTraitType().getTraitTypesForGenericCharms());
				groupView.setSelectedObject(model.getCharmEditModel().getCharmGroup());
			} else {
				Identifier[] objects = new Identifier[1];
				objects[0] = model.getCharmEditModel().getCharmGroup();
				groupView.setObjects(objects);
				groupView.setEnabled(false);
			}
		}
    });
    typeView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
        @Override
        public void valueChanged(Identifier newValue) {
          model.getCharmEditModel().setCharmType(newValue);
        }
      });
    model.getCharmEditModel().addCharmGroupChangedListening(new ChangeListener() {
		@Override
		public void changeOccurred() {
			groupView.setSelectedObject(model.getCharmEditModel().getCharmGroup());
		}
    });
    groupView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {

		@Override
		public void valueChanged(Identifier newValue) {
			model.getCharmEditModel().setCharmGroup(newValue);
		}
    	
    });
    
    
    final ITextView descriptionView = descriptionPanel.addDescriptionView(resources.getString("Charms.Creation.Basics.Description"));
    new TextualPresentation().initView(descriptionView, model.getCharmEditModel().getDescription());
    
    model.getCharmEditModel().addCanonCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			nameView.setEnabled(false);
			typeView.setEnabled(false);
			groupView.setEnabled(false);
			descriptionView.setEnabled(false);
		}
    	
    });
    model.getCharmEditModel().addCustomCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			nameView.setEnabled(true);
			typeView.setEnabled(true);
			groupView.setEnabled(true);
			descriptionView.setEnabled(true);
		}
    	
    });
    
    /*final ObjectSelectionView<MaterialComposition> compositionView = descriptionPanel.addCompositionView(getColonString("Equipment.Creation.Basics.Composition"), new CompositionUi(resources));
    compositionView.setObjects(MaterialComposition.values());
    final ObjectSelectionView<MagicalMaterial> materialView = descriptionPanel.addMaterialView(getColonString("Equipment.Creation.Basics.Material"), new MaterialUi(resources));
    materialView.setObjects(MagicalMaterial.values());
    String[] backgrounds = transform(defaultCostBackgrounds, String.class, new Function<String, String>() {
      @Override
      public String apply(String arg0) {
        return resources.getString("Equipment.Cost.Type." + arg0);
      }
    });
    final CostSelectionView costView = descriptionPanel.addCostView(getColonString("Equipment.Creation.Basics.Cost"));
    costView.setSelectableBackgrounds(backgrounds);
    compositionView.addObjectSelectionChangedListener(new ObjectValueListener<MaterialComposition>() {
      @Override
      public void valueChanged(MaterialComposition newValue) {
        model.getCharmEditModel().setMaterialComposition(newValue);
      }
    });
    model.getCharmEditModel().addCompositionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        MaterialComposition materialComposition = model.getCharmEditModel().getMaterialComposition();
        compositionView.setSelectedObject(materialComposition);
        materialView.setEnabled(materialComposition.requiresMaterial());
      }
    });
    materialView.addObjectSelectionChangedListener(new ObjectValueListener<MagicalMaterial>() {
      @Override
      public void valueChanged(MagicalMaterial newValue) {
        model.getCharmEditModel().setMagicalMaterial(newValue);
      }
    });
    model.getCharmEditModel().addMagicalMaterialChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        materialView.setSelectedObject(model.getCharmEditModel().getMagicalMaterial());
      }
    });
    costView.addSelectionChangedListener(new ISelectionIntValueChangedListener<String>() {
      @Override
      public void valueChanged(String selection, int value) {
        ItemCost cost = selection == null ? null : new ItemCost(selection, value);
        ItemCost currentModelCost = model.getCharmEditModel().getCost();
        if ((cost == null && currentModelCost != null) ||
                (cost != null && currentModelCost == null) ||
                (cost != null && !cost.equals(currentModelCost))) {
          model.getCharmEditModel().setCost(cost);
        }
      }
    });
    model.getCharmEditModel().addCostChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        costView.setValue(model.getCharmEditModel().getCost());
      }
    });*/
  }
}