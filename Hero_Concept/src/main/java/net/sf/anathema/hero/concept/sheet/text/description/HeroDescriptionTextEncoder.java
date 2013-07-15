package net.sf.anathema.hero.concept.sheet.text.description;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.TextPartFactory;
import net.sf.anathema.lib.resources.Resources;

public class HeroDescriptionTextEncoder implements HeroTextEncoder {

  private final TextPartFactory partFactory;
  private final Resources resources;

  public HeroDescriptionTextEncoder(PdfReportUtils utils, Resources resources) {
    this.resources = resources;
    this.partFactory = new TextPartFactory(utils);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    TextDescriptionContentImpl content = new TextDescriptionContentImpl(HeroDescriptionFetcher.fetch(hero));
    columnText.addElement(partFactory.createTitleParagraph(content.getName()));
    Chunk labelChunk = createDescriptionTitleChunk();
    Phrase descriptionPhrase = partFactory.createTextParagraph(labelChunk);
    boolean isFirst = true;
    for (String descriptionPart : content.getDescription()) {
      Chunk chunk = partFactory.createTextChunk(descriptionPart);
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, chunk);
      isFirst = false;
    }
  }

  private Chunk createDescriptionTitleChunk() {
    String label = resources.getString("TextDescription.Label.Description") + ": ";
    return partFactory.createBoldChunk(label);
  }

  private void addTextualDescriptionPart(MultiColumnText columnText, Phrase potentialParent, boolean isFirst, Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParent.add(chunk);
      columnText.addElement(potentialParent);
    } else {
      Paragraph descriptionParagraph = partFactory.createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      columnText.addElement(descriptionParagraph);
    }
  }
}
