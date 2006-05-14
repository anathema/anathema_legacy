package net.sf.anathema.character.reporting.sheet.second;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.TEXT_PADDING;

import java.awt.Point;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.reporting.sheet.page.AbstractPdfEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPersonalInfoEncoder extends AbstractPdfEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public SecondEditionPersonalInfoEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds) {
    int lineHeight = (infoBounds.height - TEXT_PADDING) / 3;
    int entryWidth = (infoBounds.width - TEXT_PADDING) / 2;
    int firstColumnX = infoBounds.x;
    int secondColumnX = infoBounds.x + entryWidth + TEXT_PADDING;

    int firstRowY = (int) (infoBounds.getMaxY() - lineHeight);
    IExaltedRuleSet rules = character.getRules();
    String rulesContent = rules == null ? null : resources.getString("Ruleset." + rules.getId()); //$NON-NLS-1$
    addLabelledContent(directContent, getLabel("Rules"), rulesContent, new Point(firstColumnX, firstRowY), entryWidth); //$NON-NLS-1$
    addLabelledContent(directContent, getLabel("Player"), null, new Point(secondColumnX, firstRowY), entryWidth); //$NON-NLS-1$

    int secondRowY = firstRowY - lineHeight;
    String conceptContent = character.getConcept().getConceptText();
    String conceptLabel = getLabel("Concept"); //$NON-NLS-1$
    addLabelledContent(directContent, conceptLabel, conceptContent, new Point(firstColumnX, secondRowY), entryWidth);
    String casteContent = getCasteString(character.getConcept().getCasteType());
    addLabelledContent(directContent, getLabel("Caste"), casteContent, new Point(secondColumnX, secondRowY), entryWidth); //$NON-NLS-1$

    int thirdRowY = secondRowY - lineHeight;
    String motivationContent = character.getConcept().getWillpowerRegainingConceptName();
    String motivationLabel = getLabel("Motivation"); //$NON-NLS-1$
    Point motivationPosition = new Point(firstColumnX, thirdRowY);
    addLabelledContent(directContent, motivationLabel, motivationContent, motivationPosition, infoBounds.width);
  }

  private String getCasteString(ICasteType casteType) {
    if (casteType == null || casteType == ICasteType.NULL_CASTE_TYPE) {
      return null;
    }
    return resources.getString("Caste." + casteType.getId()); //$NON-NLS-1$
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Label." + key); //$NON-NLS-1$
  }
}