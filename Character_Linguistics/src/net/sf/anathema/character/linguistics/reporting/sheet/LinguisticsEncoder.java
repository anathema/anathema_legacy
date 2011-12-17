package net.sf.anathema.character.linguistics.reporting.sheet;

import java.util.Iterator;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.linguistics.template.LinguisticsTemplate;
import net.sf.anathema.character.reporting.sheet.util.AbstractLineTextEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class LinguisticsEncoder extends AbstractLineTextEncoder {

  private final IResources resources;

  public LinguisticsEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  public String getHeaderKey() {
    return "Languages"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    ILinguisticsAdditionalModel additionalModel = (ILinguisticsAdditionalModel) character.getAdditionalModel(LinguisticsTemplate.ID);
    ILinguisticsModel model = additionalModel.getLinguisticsModel();
    for (Iterator<IIdentificate> languages = model.getEntries().iterator(); languages.hasNext();) {
      IIdentificate language = languages.next();
      String text = language.getId();
      if (model.isPredefinedLanguage(language)) {
        text = resources.getString("Language." + text); //$NON-NLS-1$
      }
      text += languages.hasNext() ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      phrase.add(new Chunk(text, font));
    }
  }
}