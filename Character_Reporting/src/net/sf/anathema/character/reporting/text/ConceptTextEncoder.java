package net.sf.anathema.character.reporting.text;

import com.google.common.base.Strings;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.reporting.text.description.TextDescriptionContentImpl;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    createCasteParagraph(columnText, hero);
    createConceptParagraph(columnText, hero);
  }

  private void createCasteParagraph(MultiColumnText columnText, Hero hero) throws DocumentException {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    Phrase castePhrase = createTextParagraph(createBoldTitle(getString("Sheet.Label.Caste." + characterType.getId()) + ": "));
    String casteId = casteType.getId();
    castePhrase.add(createTextChunk(casteId));
    columnText.addElement(castePhrase);
  }

  private void createConceptParagraph(MultiColumnText columnText, Hero hero) throws DocumentException {
    TextPartFactory factory = new TextPartFactory(getUtils());
    String conceptText = HeroDescriptionFetcher.fetch(hero).getConcept().getText();
    if (!Strings.isNullOrEmpty(conceptText)) {
      Phrase conceptPhrase = factory.createTextParagraph(factory.createBoldTitle(getString("Sheet.Label.Concept") + " "));
      conceptPhrase.add(factory.createTextChunk(conceptText));
      columnText.addElement(conceptPhrase);
    }
  }
}
