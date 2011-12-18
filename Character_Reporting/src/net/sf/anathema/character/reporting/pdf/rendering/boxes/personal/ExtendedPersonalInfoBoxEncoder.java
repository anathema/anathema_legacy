package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import com.lowagie.text.pdf.BaseFont;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class ExtendedPersonalInfoBoxEncoder extends AbstractPdfEncoder implements IVariableBoxContentEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public ExtendedPersonalInfoBoxEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent, Bounds bounds) {
    ICharacterType characterType = reportContent.getCharacter().getTemplate().getTemplateType().getCharacterType();

    int lines = getNumberOfLines(characterType);

    float lineHeight = (bounds.height - TEXT_PADDING) / lines;
    float entryWidth = (bounds.width - 2 * TEXT_PADDING) / 3;
    float shortEntryWidth = (bounds.width - 4 * TEXT_PADDING) / 5;
    float firstColumnX = bounds.x;
    float secondColumnX = firstColumnX + entryWidth + TEXT_PADDING;
    float thirdColumnX = secondColumnX + entryWidth + TEXT_PADDING;

    float firstRowY = (int) (bounds.getMaxY() - lineHeight);
    String conceptContent = reportContent.getDescription().getConceptText();
    String conceptLabel = getLabel("Concept"); //$NON-NLS-1$
    if (characterType.isExaltType()) {
      drawLabelledContent(graphics.getDirectContent(), conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), entryWidth);
      String casteContent = getCasteString(reportContent.getCharacter().getConcept().getCasteType());
      drawLabelledContent(graphics.getDirectContent(), getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX,
        firstRowY), entryWidth); //$NON-NLS-1$
    }
    else {
      drawLabelledContent(graphics.getDirectContent(), conceptLabel, conceptContent, new Position(firstColumnX, firstRowY),
        2 * entryWidth + TEXT_PADDING);
    }
    IExaltedRuleSet rules = reportContent.getCharacter().getRules();
    String rulesContent = rules == null ? null : resources.getString("Ruleset." + rules.getId()); //$NON-NLS-1$
    drawLabelledContent(graphics.getDirectContent(), getLabel("Rules"), rulesContent, new Position(thirdColumnX, firstRowY),
      entryWidth); //$NON-NLS-1$
    /*drawLabelledContent(
        directContent,
        getLabel("Player"), description.getPlayer(), new Position(secondColumnX, firstRowY), entryWidth); //$NON-NLS-1$*/

    float secondRowY = firstRowY - lineHeight;
    String motivationContent = reportContent.getCharacter().getConcept().getWillpowerRegainingComment(resources);
    String motivationLabel = reportContent.getCharacter().getRules().getEdition() == ExaltedEdition.SecondEdition ? getLabel("Motivation") :
                             getLabel("Nature");
    //$NON-NLS-1$ //$NON-NLS-2$
    drawLabelledContent(graphics.getDirectContent(), motivationLabel, motivationContent, new Position(firstColumnX, secondRowY),
      bounds.width);

    float thirdRowY = secondRowY - lineHeight;
    float[] shortColumnX = new float[5];
    for (int i = 0; i < 5; i++) {
      shortColumnX[i] = bounds.x + i * (shortEntryWidth + TEXT_PADDING);
    }
    String ageContent = Integer.toString(reportContent.getCharacter().getAge());
    drawLabelledContent(graphics.getDirectContent(), getLabel("Age"), ageContent, new Position(shortColumnX[0], thirdRowY),
      shortEntryWidth); //$NON-NLS-1$
    String sexContent = reportContent.getDescription().getSex();
    drawLabelledContent(graphics.getDirectContent(), getLabel("Sex"), sexContent, new Position(shortColumnX[1], thirdRowY),
      shortEntryWidth); //$NON-NLS-1$
    String hairContent = reportContent.getDescription().getHair();
    drawLabelledContent(graphics.getDirectContent(), getLabel("Hair"), hairContent, new Position(shortColumnX[2], thirdRowY),
      shortEntryWidth); //$NON-NLS-1$
    String skinContent = reportContent.getDescription().getSkin();
    drawLabelledContent(graphics.getDirectContent(), getLabel("Skin"), skinContent, new Position(shortColumnX[3], thirdRowY),
      shortEntryWidth); //$NON-NLS-1$
    String eyesContent = reportContent.getDescription().getEyes();
    drawLabelledContent(graphics.getDirectContent(), getLabel("Eyes"), eyesContent, new Position(shortColumnX[4], thirdRowY),
      shortEntryWidth); //$NON-NLS-1$

    if (characterType.isExaltType()) {
      float fourthRowY = thirdRowY - lineHeight;
      String animaContent = null;
      drawLabelledContent(graphics.getDirectContent(), getLabel("Anima"), animaContent, new Position(firstColumnX, fourthRowY),
        bounds.width); //$NON-NLS-1$
    }
  }

  private int getNumberOfLines(IGenericCharacter character) {
    return getNumberOfLines(character.getTemplate().getTemplateType().getCharacterType());
  }

  private int getNumberOfLines(ICharacterType characterType) {
    return (characterType.isExaltType() ? 4 : 3);
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

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }

  @Override
  public String getHeaderKey(ReportContent content) {
    String name = content.getDescription().getName();
    if (StringUtilities.isNullOrTrimEmpty(name)) {
      return "PersonalInfo"; //$NON-NLS-1$
    }
    else {
      return name + ".Literal"; //$NON-NLS-1$
    }
  }

  @Override
  public float getRequestedHeight(ReportContent content, float width) {
    return getNumberOfLines(content.getCharacter()) * IVoidStateFormatConstants.BARE_LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
  }
}
