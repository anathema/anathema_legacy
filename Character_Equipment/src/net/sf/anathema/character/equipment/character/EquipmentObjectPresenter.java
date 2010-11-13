package net.sf.anathema.character.equipment.character;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentObjectPresenter implements IPresenter {

  public static final String EQUIPMENT_NAME_PREFIX = "Equipment.Name."; //$NON-NLS-1$
  private static final String DESCRIPTION_PREFIX = "Equipment.Description."; //$NON-NLS-1$
  private final IEquipmentItem model;
  private final IEquipmentObjectView view;
  private final IEquipmentStringBuilder stringBuilder;
  private final IResources resources;

  public EquipmentObjectPresenter(
      IEquipmentItem model,
      IEquipmentObjectView view,
      IEquipmentStringBuilder stringBuilder,
      IResources resources) {
    this.model = model;
    this.view = view;
    this.stringBuilder = stringBuilder;
    this.resources = resources;
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
      final BooleanModel booleanModel = view.addStats(createEquipmentDescription(model, equipment));
      booleanModel.setValue(model.isPrintEnabled(equipment));
      booleanModel.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          model.setPrintEnabled(equipment, booleanModel.getValue());
        }
      });
    }
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}