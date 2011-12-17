package net.sf.anathema.character.reporting.extended.second;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.extended.util.AbstractPdfEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants.TEXT_PADDING;

public class SecondEditionPersonalInfoEncoder extends AbstractPdfEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public SecondEditionPersonalInfoEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds infoBounds) {
    float lineHeight = (infoBounds.height - TEXT_PADDING) / 3;
    float entryWidth = (infoBounds.width - TEXT_PADDING) / 2;
    float firstColumnX = infoBounds.x;
    float secondColumnX = infoBounds.x + entryWidth + TEXT_PADDING;

    float firstRowY = (int) (infoBounds.getMaxY() - lineHeight);
    IExaltedRuleSet rules = character.getRules();
    String rulesContent = rules == null ? null : resources.getString("Ruleset." + rules.getId()); //$NON-NLS-1$
    drawLabelledContent(directContent, getLabel("Rules"), rulesContent, new Position(firstColumnX, firstRowY), entryWidth); //$NON-NLS-1$
    drawLabelledContent(directContent, getLabel("Player"), description.getPlayer(), new Position(secondColumnX, firstRowY),
                        entryWidth); //$NON-NLS-1$

    float secondRowY = firstRowY - lineHeight;
    String conceptContent = description.getConceptText();
    String conceptLabel = getLabel("Concept"); //$NON-NLS-1$
    drawLabelledContent(directContent, conceptLabel, conceptContent, new Position(firstColumnX, secondRowY), entryWidth);
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    if (characterType.isExaltType()) {
      String casteContent = getCasteString(character.getConcept().getCasteType());
      drawLabelledContent(directContent, getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX, secondRowY),
                          entryWidth); //$NON-NLS-1$
    }

    float thirdRowY = secondRowY - lineHeight;
    String motivationContent = character.getConcept().getWillpowerRegainingComment(resources);
    String motivationLabel = character.getRules().getEdition() == ExaltedEdition.SecondEdition ? getLabel("Motivation") : getLabel("Nature");
    //$NON-NLS-1$ //$NON-NLS-2$
    Position motivationPosition = new Position(firstColumnX, thirdRowY);
    drawLabelledContent(directContent, motivationLabel, motivationContent, motivationPosition, infoBounds.width);
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
    return resources.getString("Sheet.Label." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}
