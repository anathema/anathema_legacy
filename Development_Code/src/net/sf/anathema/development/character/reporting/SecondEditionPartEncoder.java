package net.sf.anathema.development.character.reporting;

import static net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants.TEXT_PADDING;

import java.awt.Point;
import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources, int essenceMax) throws DocumentException, IOException {
    super(resources, essenceMax);
    this.boxEncoder = new PdfBoxEncoder(getBaseFont());
  }

  public void encodePersonalInfos(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription genericDescription,
      SmartRectangle infoBounds) {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    int lineHeight = (infoBounds.height - TEXT_PADDING) / 3;
    int entryWidth = (infoBounds.width - TEXT_PADDING) / 2;
    int firstColumnX = infoBounds.x;
    int secondColumnX = infoBounds.x + entryWidth + TEXT_PADDING;

    int firstRowY = (int) (infoBounds.getMaxY() - lineHeight);
    IExaltedRuleSet rules = character.getRules();
    String rulesContent = rules == null ? null : getResources().getString("Sheet.Content." + rules.getId());
    addLabelledContent(directContent, getLabel("Rules"), rulesContent, new Point(firstColumnX, firstRowY), entryWidth);
    addLabelledContent(directContent, getLabel("Player"), null, new Point(secondColumnX, firstRowY), entryWidth);

    int secondRowY = firstRowY - lineHeight;
    String conceptContent = character.getConcept().getConceptText();
    addLabelledContent(
        directContent,
        getLabel("Concept"),
        conceptContent,
        new Point(firstColumnX, secondRowY),
        entryWidth);
    String casteContent = getCasteString(character.getConcept().getCasteType());
    addLabelledContent(directContent, getLabel("Caste"), casteContent, new Point(secondColumnX, secondRowY), entryWidth);

    int thirdRowY = secondRowY - lineHeight;
    String motivationContent = null;
    addLabelledContent(
        directContent,
        getLabel("Motivation"),
        motivationContent,
        new Point(firstColumnX, thirdRowY),
        infoBounds.width);
  }

  private String getCasteString(ICasteType casteType) {
    if (casteType == null) {
      return null;
    }
    String resourceKey = "Sheet.Caste." + casteType.getId();
    return casteType == ICasteType.NULL_CASTE_TYPE ? null : getResources().getString(resourceKey);
  }

  public void encodeEditionSpecificFirstPagePart(PdfContentByte directContent, SmartRectangle restBounds) {
    boxEncoder.encodeBox(directContent, restBounds, "Rest");
  }
}