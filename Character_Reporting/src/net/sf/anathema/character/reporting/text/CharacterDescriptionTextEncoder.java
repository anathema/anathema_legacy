package net.sf.anathema.character.reporting.text;

import com.google.common.base.Strings;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.main.IGenericDescription;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.Resources;

public class CharacterDescriptionTextEncoder extends AbstractTextEncoder {

  public CharacterDescriptionTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericDescription description) throws DocumentException {
    columnText.addElement(createNameParagraph(description.getName()));
    if (!Strings.isNullOrEmpty(description.getPeriphrase())) {
      Paragraph periphrasis = new Paragraph(description.getPeriphrase(), getUtils().createDefaultFont(8, Font.ITALIC));
      periphrasis.setAlignment(Element.ALIGN_CENTER);
      columnText.addElement(periphrasis);
    }
    if (Strings.isNullOrEmpty(description.getCharacterization()) && Strings.isNullOrEmpty(
            description.getPhysicalAppearance()) &&
            Strings.isNullOrEmpty(description.getNotes())) {
      return;
    }
    Phrase descriptionPhrase = createTextParagraph(createBoldTitle(getString("TextDescription.Label.Description") + ": "));
    //
    boolean isFirst = true;
    if (!Strings.isNullOrEmpty(description.getCharacterization())) {
      descriptionPhrase.add(createTextChunk(description.getCharacterization()));
      columnText.addElement(descriptionPhrase);
      isFirst = false;
    }
    if (!Strings.isNullOrEmpty(description.getPhysicalAppearance())) {
      Chunk descriptionChunk = createTextChunk(description.getPhysicalAppearance());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, descriptionChunk);
      isFirst = false;
    }
    if (!Strings.isNullOrEmpty(description.getNotes())) {
      Chunk noteChunk = createTextChunk(description.getNotes());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, noteChunk);
    }
  }

  private Paragraph createNameParagraph(String name) {
    Font font = getUtils().createDefaultFont(11, Font.BOLD);
    Paragraph paragraph = new Paragraph(name, font);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.getSize() * 1.2f);
    return paragraph;
  }

  private void addTextualDescriptionPart(MultiColumnText columnText, Phrase potentialParentPhrase, boolean isFirst,
                                         Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParentPhrase.add(chunk);
      columnText.addElement(potentialParentPhrase);
    } else {
      Paragraph descriptionParagraph = createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      columnText.addElement(descriptionParagraph);
    }
  }
}
