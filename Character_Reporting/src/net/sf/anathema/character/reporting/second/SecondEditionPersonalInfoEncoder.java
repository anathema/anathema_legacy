package net.sf.anathema.character.reporting.second;

import static net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants.TEXT_PADDING;

import java.awt.Point;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.reporting.encoder.AbstractPdfEncoder;
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
    String rulesContent = rules == null ? null : resources.getString("Sheet.Content." + rules.getId());
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
    return casteType == ICasteType.NULL_CASTE_TYPE ? null : resources.getString(resourceKey);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Label." + key);
  }
}