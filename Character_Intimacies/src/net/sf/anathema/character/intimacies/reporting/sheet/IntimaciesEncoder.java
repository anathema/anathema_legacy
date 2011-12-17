package net.sf.anathema.character.intimacies.reporting.sheet;

import java.util.Iterator;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.reporting.sheet.util.AbstractLineTextEncoder;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class IntimaciesEncoder extends AbstractLineTextEncoder {

  public IntimaciesEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  public String getHeaderKey() {
    return "Intimacies"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase intimacyPhrase) {
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getAdditionalModel(IntimaciesTemplate.ID);
    IIntimaciesModel model = additionalModel.getIntimaciesModel();
    for (Iterator<IIntimacy> intimacies = model.getEntries().iterator(); intimacies.hasNext();) {
      IIntimacy intimacy = intimacies.next();
      String text = intimacy.getName();
      if (!intimacy.isComplete()) {
        text += " (" + intimacy.getTrait().getCurrentValue() + "/" + intimacy.getTrait().getMaximalValue() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      text += intimacies.hasNext() ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      intimacyPhrase.add(new Chunk(text, font));
    }
  }
}