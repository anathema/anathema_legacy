package net.sf.anathema.hero.concept.sheet.text.concept;

import com.google.common.base.Strings;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.TextPartFactory;
import net.sf.anathema.framework.environment.Resources;

public class ConceptTextEncoder implements HeroTextEncoder {

  private final TextPartFactory partFactory;
  private Resources resources;

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    this.resources = resources;
    this.partFactory = new TextPartFactory(utils);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    createCasteParagraph(columnText, hero);
    createConceptParagraph(columnText, hero);
  }

  private void createCasteParagraph(MultiColumnText columnText, Hero hero) throws DocumentException {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    CharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    String labelKey = "Sheet.Label.Caste." + characterType.getId();
    addLabeledText(columnText, labelKey, casteType.getId());
  }

  private void createConceptParagraph(MultiColumnText columnText, Hero hero) throws DocumentException {
    String conceptText = HeroDescriptionFetcher.fetch(hero).getConcept().getText();
    if (!Strings.isNullOrEmpty(conceptText)) {
      String labelKey = "Sheet.Label.Concept";
      addLabeledText(columnText, labelKey, conceptText);
    }
  }

  private void addLabeledText(MultiColumnText columnText, String labelKey, String text) throws DocumentException {
    Phrase phrase = createLabelPhrase(labelKey);
    phrase.add(partFactory.createTextChunk(text));
    columnText.addElement(phrase);
  }

  private Phrase createLabelPhrase(String key) {
    String label = resources.getString(key) + ": ";
    Chunk labelChunk = partFactory.createBoldChunk(label);
    return partFactory.createTextParagraph(labelChunk);
  }
}
