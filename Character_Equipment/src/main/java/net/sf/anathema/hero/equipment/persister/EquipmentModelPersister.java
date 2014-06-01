package net.sf.anathema.hero.equipment.persister;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.model.MissingMaterialException;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class EquipmentModelPersister extends AbstractModelJsonPersister<EquipmentListPto, EquipmentModel> {

   private final Logger logger = Logger.getLogger(EquipmentModelPersister.class);

  public EquipmentModelPersister() {
    super("equipment", EquipmentListPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, EquipmentModel model, EquipmentListPto pto) {
    for (EquipmentPto equipment : pto.equipments) {
      fillEquipment(model, equipment);
    }
  }

  private void fillEquipment(EquipmentModel model, EquipmentPto equipment) {
    String templateId = equipment.templateId;
    String title = equipment.customTitle;
    String description = equipment.description;
    MagicalMaterial magicalMaterial = null;
    if (equipment.material != null) {
      magicalMaterial = MagicalMaterial.valueOf(equipment.material);
    }
    IEquipmentItem item;
    try {
      item = model.addEquipmentObjectFor(templateId, magicalMaterial);
    } catch (MissingMaterialException e) {
      logger.warn("No material found for item " + title);
      return;
    }
    if (item == null) {
      logger.warn("No template found for item " + title);
      return;
    }
    item.setPersonalization(title, description);
    item.setUnprinted();
    for (EquipmentStatsPto statsPto : equipment.printStats) {
      fillStats(model, item, statsPto);
    }
  }

  private void fillStats(EquipmentModel model, IEquipmentItem item, EquipmentStatsPto statsPto) {
    String printedStatId = statsPto.id;
    item.setPrinted(printedStatId);
    EquipmentHeroEvaluator provider = model.getHeroEvaluator();
    IEquipmentStats stats = item.getStat(printedStatId);
    for (EquipmentOptionPto optionPto : statsPto.options) {
      IEquipmentStatsOption option = provider.getCharacterSpecialtyOption(optionPto.name, optionPto.type);
      model.getOptionProvider().enableStatOption(item, stats, option);
    }
  }

  @Override
  protected EquipmentListPto saveModelToPto(EquipmentModel heroModel) {
    EquipmentListPto pto = new EquipmentListPto();
    ItemToPtoTransformer transformer = new ItemToPtoTransformer(heroModel);
    for (IEquipmentItem item : heroModel.getNaturalWeapons()) {
      pto.equipments.add(transformer.createPto(item));
    }
    for (IEquipmentItem item : heroModel.getEquipmentItems()) {
      pto.equipments.add(transformer.createPto(item));
    }
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return EquipmentModel.ID;
  }
}