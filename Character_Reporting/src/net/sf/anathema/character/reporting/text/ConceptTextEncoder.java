package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(PdfReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter character) throws DocumentException {
    ICasteType casteType = character.getCasteType();
    if (casteType != ICasteType.NULL_CASTE_TYPE) {
      Phrase castePhrase = createTextParagraph(createBoldTitle(getString("Sheet.Label.Caste." + character.getTemplate().getTemplateType()
              .getCharacterType().getId()) + ": ")); //$NON-NLS-1$ //$NON-NLS-2$
      String casteId = casteType.getId();
      castePhrase.add(createTextChunk(casteId));
      columnText.addElement(castePhrase);
    }
    String willpowerRegainingConceptName = character.getConcept().getWillpowerRegainingConceptName();
    if (!StringUtilities.isNullOrTrimmedEmpty(willpowerRegainingConceptName)) {
      String motivationLabel = getString(character.getRules().getEdition() == ExaltedEdition.SecondEdition
              ? "Sheet.Label.Motivation" : "Sheet.Label.Nature"); //$NON-NLS-1$ //$NON-NLS-2$
      Phrase willpowerRegainPhrase = createTextParagraph(createBoldTitle(motivationLabel + ": ")); //$NON-NLS-1$
      willpowerRegainPhrase.add(createTextChunk(willpowerRegainingConceptName));
      columnText.addElement(willpowerRegainPhrase);
    }
  }
}
