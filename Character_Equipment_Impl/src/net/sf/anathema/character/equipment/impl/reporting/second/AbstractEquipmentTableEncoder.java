package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractEquipmentTableEncoder<T extends IEquipmentStats> extends
    AbstractFixedLineStatsTableEncoder<T> {

  public AbstractEquipmentTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  protected final IEquipmentPrintModel getEquipmentModel(IGenericCharacter character) {
    return (IEquipmentPrintModel) character.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
  }
}