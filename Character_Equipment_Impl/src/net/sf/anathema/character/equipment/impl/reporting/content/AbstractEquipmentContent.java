package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractEquipmentContent<STATS extends IEquipmentStats> extends AbstractSubContent
  implements FixedLineStatsContent<STATS> {

  private IGenericCharacter character;

  public AbstractEquipmentContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  protected IGenericCharacter getCharacter() {
    return character;
  }

  protected IEquipmentPrintModel getEquipmentModel() {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    return model.getPrintModel();
  }

  protected IEquipmentModifiers getEquipmentModifiers() {
    return character.getEquipmentModifiers();
  }
}
