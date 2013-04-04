package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter character) throws DocumentException {
    ICasteType casteType = character.getCasteType();
    if (casteType != ICasteType.NULL_CASTE_TYPE) {
      Phrase castePhrase = createTextParagraph(createBoldTitle(getString(
              "Sheet.Label.Caste." + character.getTemplate().getTemplateType().getCharacterType().getId()) + ": "));
      String casteId = casteType.getId();
      castePhrase.add(createTextChunk(casteId));
      columnText.addElement(castePhrase);
    }
    String willpowerRegainingConceptName = character.getConcept().getWillpowerRegainingConceptName();
    if (!StringUtilities.isNullOrTrimmedEmpty(willpowerRegainingConceptName)) {
      String motivationLabel = getString("Sheet.Label.Motivation");
      Phrase willpowerRegainPhrase = createTextParagraph(createBoldTitle(motivationLabel + ": "));
      willpowerRegainPhrase.add(createTextChunk(willpowerRegainingConceptName));
      columnText.addElement(willpowerRegainPhrase);
    }
  }
}
