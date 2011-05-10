package net.sf.anathema.character.equipment.character;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentObjectPresenter implements IPresenter {

  public static final String EQUIPMENT_NAME_PREFIX = "Equipment.Name."; //$NON-NLS-1$
  private static final String DESCRIPTION_PREFIX = "Equipment.Description."; //$NON-NLS-1$
  private final Map<IEquipmentStats, BooleanModel> attuneStatFlags = new HashMap<IEquipmentStats, BooleanModel>();
  private final IEquipmentItem model;
  private final IEquipmentObjectView view;
  private final IEquipmentStringBuilder stringBuilder;
  private final IResources resources;
  private final ArtifactAttuneType[] attuneTypes;

  public EquipmentObjectPresenter(
      IEquipmentItem model,
      IEquipmentObjectView view,
      IEquipmentStringBuilder stringBuilder,
      IResources resources,
      ArtifactAttuneType[] attuneTypes) {
    this.model = model;
    this.view = view;
    this.stringBuilder = stringBuilder;
    this.resources = resources;
    this.attuneTypes = attuneTypes;
  }

  public void initPresentation() {
    String itemTitle = model.getTemplateId();
    if (resources.supportsKey(EQUIPMENT_NAME_PREFIX + itemTitle)) {
      itemTitle = resources.getString(EQUIPMENT_NAME_PREFIX + itemTitle);
    }
    if (model.getMaterialComposition() == MaterialComposition.Variable) {
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
    for (final IEquipmentStats equipment : model.getStats()) {
      if (!viewFilter(equipment))
    		  continue;
      final BooleanModel booleanModel = view.addStats(createEquipmentDescription(model, equipment));
      if (equipment instanceof IArtifactStats)
    	  attuneStatFlags.put(equipment, booleanModel);
      booleanModel.setValue(model.isPrintEnabled(equipment));
      booleanModel.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          model.setPrintEnabled(equipment, booleanModel.getValue());
          if (booleanModel.getValue() == true && equipment instanceof IArtifactStats)
        	  for (IEquipmentStats stats : attuneStatFlags.keySet())
        		  if (equipment != stats)
        			  attuneStatFlags.get(stats).setValue(false);
        }
      });
    }
  }
  
  private boolean viewFilter(IEquipmentStats equipment)
  {
	  if (equipment instanceof IArtifactStats)
	  {
		  IArtifactStats stats = (IArtifactStats)equipment;
		  if (attuneTypes != null)
			  for (ArtifactAttuneType type : attuneTypes)
				  if (stats.getAttuneType() == type)
					  return true;
		  return false;
	  }
	  return true;
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}