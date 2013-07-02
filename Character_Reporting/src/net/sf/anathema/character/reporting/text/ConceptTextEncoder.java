package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    if (casteType != CasteType.NULL_CASTE_TYPE) {
      ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
      Phrase castePhrase = createTextParagraph(createBoldTitle(getString("Sheet.Label.Caste." + characterType.getId()) + ": "));
      String casteId = casteType.getId();
      castePhrase.add(createTextChunk(casteId));
      columnText.addElement(castePhrase);
    }
  }
}
