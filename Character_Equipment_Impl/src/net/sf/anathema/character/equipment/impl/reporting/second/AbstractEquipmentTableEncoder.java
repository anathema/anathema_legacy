package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.character.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditonalModelTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractEquipmentTableEncoder<T extends IEquipment> extends AbstractFixedLineStatsTableEncoder<T> {

  public AbstractEquipmentTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  protected final IEquipmentAdditionalModel getEquipmentModel(IGenericCharacter character) {
    return (IEquipmentAdditionalModel) character.getAdditionalModel(EquipmentAdditonalModelTemplate.ID);
  }
}