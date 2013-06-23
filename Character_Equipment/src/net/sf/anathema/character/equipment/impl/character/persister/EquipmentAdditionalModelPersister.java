package net.sf.anathema.character.equipment.impl.character.persister;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.EquipmentModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentModelImpl;
import net.sf.anathema.character.equipment.impl.character.model.MissingMaterialException;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class EquipmentAdditionalModelPersister implements IAdditionalPersister {

  private static final String TAG_ITEM = "item";
  private static final String TAG_TEMPLATE_ID = "templateId";
  private static final String TAG_PRINT_STATS = "printedStats";
  private static final String TAG_SPECIALTY_OPTION = "specialty";
  private static final String TAG_CUSTOM_TITLE = "customTitle";
  private static final String TAG_CUSTOM_DESCRIPTION = "customDescription";
  private static final String ATTRIB_NAME = "name";
  private static final String ATTRIB_TYPE = "type";
  private static final String TAG_MATERIAL = "material";
  private IMessaging messageIndicator;

  public EquipmentAdditionalModelPersister(IMessaging messageIndicator) {
    this.messageIndicator = messageIndicator;
  }

  @Override
  public void save(Element parent, IAdditionalModel model) {
    EquipmentModel equipmentModel = (EquipmentModel) model;
    saveItems(parent, equipmentModel.getNaturalWeapons(), equipmentModel.getCharacterOptionProvider());
    saveItems(parent, equipmentModel.getEquipmentItems(), equipmentModel.getCharacterOptionProvider());
  }

  private void saveItems(Element parent, IEquipmentItem[] equipmentItems, EquipmentOptionsProvider provider) {
    for (IEquipmentItem item : equipmentItems) {
      Element itemElement = parent.addElement(TAG_ITEM);
      itemElement.addElement(TAG_TEMPLATE_ID).addCDATA(item.getTemplateId());
      if (!item.getTemplateId().equals(item.getTitle())) {
        itemElement.addElement(TAG_CUSTOM_TITLE).addCDATA(item.getTitle());
      }
      if (!item.getBaseDescription().equals(item.getDescription())) {
        itemElement.addElement(TAG_CUSTOM_DESCRIPTION).addCDATA(item.getDescription());
      }
      if (item.getMaterialComposition() == MaterialComposition.Variable) {
        itemElement.addElement(TAG_MATERIAL).addCDATA(item.getMaterial().name());
      }
      for (IEquipmentStats stats : item.getStats()) {
        if (item.isPrintEnabled(stats)) {
          Element statsElement = itemElement.addElement(TAG_PRINT_STATS);
          statsElement.addCDATA(stats.getId());
          for (IEquipmentStatsOption option : provider.getEnabledStatOptions(item, item.getStat(stats.getId()))) {
            Element optionElement = statsElement.addElement(TAG_SPECIALTY_OPTION);
            optionElement.addAttribute(ATTRIB_NAME, option.getName());
            optionElement.addAttribute(ATTRIB_TYPE, option.getType());
          }
        }
      }
    }
  }

  @Override
  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    EquipmentModel equipmentModel = (EquipmentModelImpl) model;
    for (Element itemElement : ElementUtilities.elements(parent, TAG_ITEM)) {
      String templateId = itemElement.elementText(TAG_TEMPLATE_ID);
      String title = itemElement.elementText(TAG_CUSTOM_TITLE);
      String description = itemElement.elementText(TAG_CUSTOM_DESCRIPTION);
      MagicalMaterial magicalMaterial = null;
      Element magicalMaterialElement = itemElement.element(TAG_MATERIAL);
      if (magicalMaterialElement != null) {
        magicalMaterial = MagicalMaterial.valueOf(magicalMaterialElement.getTextTrim());
      }
      IEquipmentItem item;
      try {
        item = equipmentModel.addEquipmentObjectFor(templateId, magicalMaterial);
      } catch (MissingMaterialException e) {
        messageIndicator.addMessage("EquipmentPersistence.NoMaterialFound", MessageType.WARNING, templateId);
        continue;
      }
      if (item == null) {
        messageIndicator.addMessage("EquipmentPersistence.NoTemplateFound", MessageType.WARNING, templateId);
        continue;
      }
      item.setPersonalization(title, description);
      item.setUnprinted();
      for (Element statsElement : ElementUtilities.elements(itemElement, TAG_PRINT_STATS)) {
        String printedStatId = statsElement.getText().trim();
        item.setPrinted(printedStatId);

        EquipmentHeroEvaluator provider = equipmentModel.getCharacterDataProvider();
        IEquipmentStats stats = item.getStat(printedStatId);
        for (Element optionsElement : ElementUtilities.elements(statsElement, TAG_SPECIALTY_OPTION)) {
          IEquipmentStatsOption option =
                  provider.getCharacterSpecialtyOption(optionsElement.attributeValue(ATTRIB_NAME), optionsElement.attributeValue(ATTRIB_TYPE));
          equipmentModel.getCharacterOptionProvider().enableStatOption(item, stats, option);
        }
      }
    }
  }
}
