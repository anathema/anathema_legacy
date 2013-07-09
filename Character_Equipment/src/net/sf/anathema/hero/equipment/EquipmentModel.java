package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentItemCollection;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.main.essence.IEssencePoolModifier;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface EquipmentModel extends HeroModel, IEquipmentItemCollection, IEquipmentTemplateProvider, IEssencePoolModifier, StatsModifierFactory {

  Identifier ID = new SimpleIdentifier("Equipment");

  MagicalMaterial getDefaultMaterial();

  MaterialComposition getMaterialComposition(String templateId);

  MagicalMaterial getMagicalMaterial(String templateId);

  IEquipmentPrintModel getPrintModel();

  EquipmentHeroEvaluator getHeroEvaluator();

  void updateItem(IEquipmentItem item);

  void refreshItems();

  EquipmentOptionsProvider getOptionProvider();
}