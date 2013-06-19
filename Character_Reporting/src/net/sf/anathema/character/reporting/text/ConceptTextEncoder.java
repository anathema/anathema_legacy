package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.Resources;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter character) throws DocumentException {
    CasteType casteType = character.getCasteType();
    if (casteType != CasteType.NULL_CASTE_TYPE) {
      Phrase castePhrase = createTextParagraph(
              createBoldTitle(getString("Sheet.Label.Caste." + character.getTemplate().getTemplateType().getCharacterType().getId()) + ": "));
      String casteId = casteType.getId();
      castePhrase.add(createTextChunk(casteId));
      columnText.addElement(castePhrase);
    }
  }
}
