package net.sf.anathema.character.equipment.impl.character.persister;

import net.disy.commons.core.message.MessageType;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.MissingMaterialException;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class EquipmentAdditionalModelPersister implements IAdditionalPersister {

  private static final String TAG_ITEM = "item"; //$NON-NLS-1$
  private static final String TAG_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String TAG_PRINT_STATS = "printedStats"; //$NON-NLS-1$
  private static final String TAG_MATERIAL = "material"; //$NON-NLS-1$
  private IAnathemaMessaging messageIndicator;

  public EquipmentAdditionalModelPersister(IAnathemaMessaging messageIndicator) {
    this.messageIndicator = messageIndicator;
  }

  public void save(Element parent, IAdditionalModel model) {
    IEquipmentItem[] equipmentItems = ((IEquipmentAdditionalModel) model).getEquipmentItems();
    for (IEquipmentItem item : equipmentItems) {
      Element itemElement = parent.addElement(TAG_ITEM);
      itemElement.addElement(TAG_TEMPLATE_ID).addCDATA(item.getTemplateId());
      if (item.getMaterialComposition() == MaterialComposition.Variable) {
        itemElement.addElement(TAG_MATERIAL).addCDATA(item.getMaterial().name());
      }
      itemElement.addElement(TAG_TEMPLATE_ID).addCDATA(item.getTemplateId());
      for (IEquipmentStats stats : item.getStats()) {
        if (item.isPrintEnabled(stats)) {
          itemElement.addElement(TAG_PRINT_STATS).addCDATA(stats.getName().getId());
        }
      }
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    IEquipmentAdditionalModel equipmentModel = (EquipmentAdditionalModel) model;
    for (Element itemElement : ElementUtilities.elements(parent, TAG_ITEM)) {
      String templateId = itemElement.elementText(TAG_TEMPLATE_ID);
      MagicalMaterial magicalMaterial = null;
      Element magicalMaterialElement = itemElement.element(TAG_MATERIAL);
      if (magicalMaterialElement != null) {
        magicalMaterial = MagicalMaterial.valueOf(magicalMaterialElement.getTextTrim());
      }
      IEquipmentItem item;
      try {
        item = equipmentModel.addEquipmentObjectFor(templateId, magicalMaterial);
      }
      catch (MissingMaterialException e) {
        messageIndicator.addMessage("EquipmentPersistence.NoMaterialFound", //$NON-NLS-1$
            new Object[] { templateId },
            MessageType.WARNING);
        continue;
      }
      if (item == null) {
        messageIndicator.addMessage("EquipmentPersistence.NoTemplateFound", //$NON-NLS-1$
            new Object[] { templateId },
            MessageType.WARNING);
        continue;
      }
      item.setUnprinted();
      for (Element statsElement : ElementUtilities.elements(itemElement, TAG_PRINT_STATS)) {
        String printedStatId = statsElement.getText();
        item.setPrinted(printedStatId);
      }
    }
  }
}