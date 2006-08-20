package net.sf.anathema.character.reporting.text;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;

public class ConceptTextEncoder extends AbstractTextEncoder {

  public ConceptTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter character) throws DocumentException {
    ICasteType< ? extends ICasteTypeVisitor> casteType = character.getCasteType();
    if (casteType != ICasteType.NULL_CASTE_TYPE) {
      Phrase castePhrase = createTextParagraph(createBoldTitle(getString("Sheet.Label.Caste") + " ")); //$NON-NLS-1$ //$NON-NLS-2$
      String casteId = casteType.getId();
      castePhrase.add(createTextChunk(casteId));
      columnText.addElement(castePhrase);
    }
    String motivationLabel = getString(character.getRules().getEdition() == ExaltedEdition.SecondEdition
        ? "Sheet.Label.Motivation" : "Sheet.Label.Nature"); //$NON-NLS-1$ //$NON-NLS-2$
    Phrase willpowerRegainPhrase = createTextParagraph(createBoldTitle(motivationLabel + " ")); //$NON-NLS-1$
    willpowerRegainPhrase.add(createTextChunk(character.getConcept().getWillpowerRegainingConceptName()));
    columnText.addElement(willpowerRegainPhrase);

    Phrase conceptPhrase = createTextParagraph(createBoldTitle(getString("Sheet.Label.Concept") + " ")); //$NON-NLS-1$ //$NON-NLS-2$
    if (!StringUtilities.isNullOrEmpty(character.getConcept().getConceptText())) {
      conceptPhrase.add(createTextChunk(character.getConcept().getConceptText()));
    }
    columnText.addElement(conceptPhrase);
  }
}