package net.sf.anathema.character.equipment.character;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentObjectPresenter implements Presenter {

  public static final String EQUIPMENT_NAME_PREFIX = "Equipment.Name."; //$NON-NLS-1$
  private static final String DESCRIPTION_PREFIX = "Equipment.Description."; //$NON-NLS-1$
  private final Map<IEquipmentStats, BooleanModel> attuneStatFlags = new HashMap<IEquipmentStats, BooleanModel>();
  private final Map<IEquipmentStats, BooleanModel> otherStatFlags = new HashMap<IEquipmentStats, BooleanModel>();
  private final IEquipmentItem model;
  private final IEquipmentObjectView view;
  private final IEquipmentStringBuilder stringBuilder;
  private final IEquipmentCharacterOptionProvider characterOptionProvider;
  private final IEquipmentCharacterDataProvider dataProvider;
  private final IResources resources;
  private final Action[] additionalActions;
  
  public EquipmentObjectPresenter(IEquipmentItem model,
		  						  IEquipmentObjectView view,
		  						  IEquipmentStringBuilder stringBuilder,
                                  IEquipmentCharacterDataProvider dataProvider,
                                  IEquipmentCharacterOptionProvider characterOptionProvider,
                                  IResources resources,
                                  Action... additionalActions) {
    this.model = model;
    this.view = view;
    this.stringBuilder = stringBuilder;
    this.characterOptionProvider = characterOptionProvider;
    this.resources = resources;
    this.dataProvider = dataProvider;
    this.additionalActions = additionalActions;
  }

  @Override
  public void initPresentation() {
    String itemTitle = model.getTitle();
    boolean customTitle = !model.getTemplateId().equals(itemTitle);
    if (resources.supportsKey(EQUIPMENT_NAME_PREFIX + itemTitle)) {
      itemTitle = resources.getString(EQUIPMENT_NAME_PREFIX + itemTitle);
    }
    if (!customTitle && model.getMaterialComposition() == MaterialComposition.Variable) {
      String materialString = resources.getString("MagicMaterial." + model.getMaterial().name()); //$NON-NLS-1$
      itemTitle += " (" + materialString + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    view.setItemTitle(itemTitle);
    String description = model.getDescription();
    if (resources.supportsKey(DESCRIPTION_PREFIX + description)) {
      description = resources.getString(DESCRIPTION_PREFIX + description);
    }
    if (description != null) {
      view.setItemDescription(description);
    }

    prepareContents();
  }
  
  public void prepareContents()
  {
	  view.clearContents();
	  attuneStatFlags.clear();
	  otherStatFlags.clear();
	  
	  boolean isRequireAttuneArtifact = false;
	  boolean isAttuned = false;
	  for (final IEquipmentStats equipment : model.getStats()) {
	    if (equipment instanceof IArtifactStats)
	  	  isRequireAttuneArtifact = isRequireAttuneArtifact || ((IArtifactStats)equipment).requireAttunementToUse();
	    if (!viewFilter(equipment))
	  		  continue;
	    final BooleanModel booleanModel = view.addStats(createEquipmentDescription(model, equipment));
	    if (equipment instanceof IArtifactStats) {
	    	attuneStatFlags.put(equipment, booleanModel);
	    	if (model.isPrintEnabled(equipment)) {
	    		isAttuned = true;
	    	}
	    }
	    else {
	  	  otherStatFlags.put(equipment, booleanModel);
	    }
	    booleanModel.setValue(model.isPrintEnabled(equipment));
	    booleanModel.addChangeListener(new IChangeListener() {
	      @Override
          public void stateChanged() {
	        model.setPrintEnabled(equipment, booleanModel.getValue());
	        if (equipment instanceof IArtifactStats)
	        {
	        	// if we are enabling an attunement stats ...
		        if (booleanModel.getValue())
		        {
		          // disable all other attunement stats
		      	  for (IEquipmentStats stats : attuneStatFlags.keySet()) {
		      		  if (!equipment.equals(stats) && model.isPrintEnabled(stats)) {
		      			  model.setPrintEnabled(stats, false);
		      		  }
		      	  }
		        }
		        prepareContents();
	        }
	      }
	    });
	      
	    addOptionalModels(booleanModel, equipment);
	  }
	  // if we require attunement, but we are not, clear and disable all other stats
	  if (isRequireAttuneArtifact && !isAttuned) {		  
	   	for (BooleanModel bool : otherStatFlags.values()) {
	    	view.setEnabled(bool, false);
	    	bool.setValue(false);
	    }
	  }
	  
	  for (Action action : additionalActions) {
		  view.addAction(action);
	  }
  }
  
  private void addOptionalModels(BooleanModel baseModel, final IEquipmentStats stats)
  {
	  if (stats instanceof IWeaponStats)
	  {
		  final IWeaponStats weaponStats = (IWeaponStats)stats;
		  INamedGenericTrait[] specialties = dataProvider.getSpecialties(((IWeaponStats)stats).getTraitType());
		  for (final INamedGenericTrait specialty : specialties)
		  {
			  String label = MessageFormat.format(resources.getString("Equipment.Specialty"), specialty.getName());
			  final BooleanModel booleanModel = view.addOptionFlag(baseModel, label);
			  final IEquipmentStatsOption specialtyOption = new EquipmentSpecialtyOption(specialty, weaponStats.getTraitType());
			  final IEquipmentStats baseStat = model.getStat(stats.getId());
			  booleanModel.setValue(characterOptionProvider.isStatOptionEnabled(model, baseStat, specialtyOption));
		      booleanModel.addChangeListener(new IChangeListener() {
		        @Override
                public void stateChanged()
		        {
		        	if (booleanModel.getValue())
                      characterOptionProvider.enableStatOption(model, baseStat, specialtyOption);
		        	else
                      characterOptionProvider.disableStatOption(model, baseStat, specialtyOption);
		        }
		      });
		  }
	  }
  }
  
  private boolean viewFilter(IEquipmentStats equipment)
  {
	  boolean match;
	  if (equipment instanceof IArtifactStats)
	  {
		  IArtifactStats stats = (IArtifactStats)equipment;
		  match = false;
		  if (dataProvider.getAttuneTypes(model) != null)
			  for (ArtifactAttuneType type : dataProvider.getAttuneTypes(model))
				  if (stats.getAttuneType() == type)
					  match = true;
		  if (!match) return false;
	  }
	  return true;
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}
