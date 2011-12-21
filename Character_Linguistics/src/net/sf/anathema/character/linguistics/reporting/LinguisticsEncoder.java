package net.sf.anathema.character.linguistics.reporting;

import java.util.Iterator;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.linguistics.template.LinguisticsTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class LinguisticsEncoder extends LineFillingBoxContentEncoder {

  private final IResources resources;

  public LinguisticsEncoder(IResources resources, BaseFont baseFont) {
    super();
    this.resources = resources;
  }

  public String getHeaderKey(ReportContent content) {
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
