package net.sf.anathema.character.reporting.text;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class CharacterDescriptionTextEncoder extends AbstractTextEncoder {

  public CharacterDescriptionTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericDescription description) throws DocumentException {
    columnText.addElement(createNameParagraph(description.getName()));
    if (!StringUtilities.isNullOrEmpty(description.getPeriphrase())) {
      Paragraph periphrasis = new Paragraph(description.getPeriphrase(), getUtils().createDefaultFont(8, Font.ITALIC));
      periphrasis.setAlignment(Element.ALIGN_CENTER);
      columnText.addElement(periphrasis);
    }
    if (StringUtilities.isNullOrEmpty(description.getCharacterization()) && StringUtilities.isNullOrEmpty(description.getPhysicalAppearance()) &&
        StringUtilities.isNullOrEmpty(description.getNotes())) {
      return;
    }
    Phrase descriptionPhrase = createTextParagraph(createBoldTitle(getString("TextDescription.Label.Description") + ": ")); //$NON-NLS-1$
    // //$NON-NLS-2$
    boolean isFirst = true;
    if (!StringUtilities.isNullOrEmpty(description.getCharacterization())) {
      descriptionPhrase.add(createTextChunk(description.getCharacterization()));
      columnText.addElement(descriptionPhrase);
      isFirst = false;
    }
    if (!StringUtilities.isNullOrEmpty(description.getPhysicalAppearance())) {
      Chunk descriptionChunk = createTextChunk(description.getPhysicalAppearance());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, descriptionChunk);
      isFirst = false;
    }
    if (!StringUtilities.isNullOrEmpty(description.getNotes())) {
      Chunk noteChunk = createTextChunk(description.getNotes());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, noteChunk);
    }
  }

  private Paragraph createNameParagraph(String name) {
    Font font = getUtils().createDefaultFont(11, Font.BOLD);
    Paragraph paragraph = new Paragraph(name, font);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.size() * 1.2f);
    return paragraph;
  }

  private void addTextualDescriptionPart(MultiColumnText columnText, Phrase potentialParentPhrase, boolean isFirst,
    Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParentPhrase.add(chunk);
      columnText.addElement(potentialParentPhrase);
    }
    else {
      Paragraph descriptionParagraph = createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      columnText.addElement(descriptionParagraph);
    }
  }
}
