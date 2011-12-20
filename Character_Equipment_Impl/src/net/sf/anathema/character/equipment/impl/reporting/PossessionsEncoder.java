package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractLineTextEncoder;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class PossessionsEncoder extends AbstractLineTextEncoder {

  public PossessionsEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  public String getHeaderKey(ReportContent content) {
    return "Possessions"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    for (int index = 0; index < model.getEquipmentItems().length; index++) {
      IEquipmentItem item = model.getEquipmentItems()[index];
      if (item.getStats().length > 0) {
        continue;
      }
      String text = item.getTemplateId();
      text += index + 1 < model.getEquipmentItems().length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      phrase.add(new Chunk(text, font));
    }
  }
}
