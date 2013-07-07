package net.sf.anathema.hero.concept.sheet.text.description;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.hero.sheet.text.AbstractTextEncoder;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class HeroDescriptionTextEncoder extends AbstractTextEncoder {

  public HeroDescriptionTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    TextDescriptionContentImpl content = new TextDescriptionContentImpl(HeroDescriptionFetcher.fetch(hero));
    columnText.addElement(createNameParagraph(content.getName()));
    Phrase descriptionPhrase = createTextParagraph(createBoldTitle(getString("TextDescription.Label.Description") + ": "));
    boolean isFirst = true;
    for (String descriptionPart : content.getDescription()) {
      Chunk chunk = createTextChunk(descriptionPart);
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, chunk);
      isFirst = false;
    }
  }

  private Paragraph createNameParagraph(String name) {
    Font font = getUtils().createDefaultFont(11, Font.BOLD);
    Paragraph paragraph = new Paragraph(name, font);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.getSize() * 1.2f);
    return paragraph;
  }

  private void addTextualDescriptionPart(MultiColumnText columnText, Phrase potentialParent, boolean isFirst, Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParent.add(chunk);
      columnText.addElement(potentialParent);
    } else {
      Paragraph descriptionParagraph = createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      columnText.addElement(descriptionParagraph);
    }
  }
}
