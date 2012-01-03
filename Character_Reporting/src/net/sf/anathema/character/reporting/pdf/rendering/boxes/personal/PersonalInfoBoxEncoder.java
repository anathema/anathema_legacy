package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class PersonalInfoBoxEncoder implements IVariableContentEncoder {

  private final IResources resources;

  public PersonalInfoBoxEncoder(IResources resources) {
    this.resources = resources;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
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
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), entryWidth);
      String casteContent = getCasteString(reportContent.getCharacter().getConcept().getCasteType());
      graphics.drawLabelledContent(getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX, firstRowY),
        entryWidth); //$NON-NLS-1$
    }
    else {
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), 2 * entryWidth + TEXT_PADDING);
    }
    IExaltedRuleSet rules = reportContent.getCharacter().getRules();
    String rulesContent = rules == null ? null : resources.getString("Ruleset." + rules.getId()); //$NON-NLS-1$
    graphics.drawLabelledContent(getLabel("Rules"), rulesContent, new Position(thirdColumnX, firstRowY), entryWidth); //$NON-NLS-1$

    float secondRowY = firstRowY - lineHeight;
    String motivationContent = reportContent.getCharacter().getConcept().getWillpowerRegainingComment(resources);
    String motivationLabel =
      reportContent.getCharacter().getRules().getEdition() == ExaltedEdition.SecondEdition ? getLabel("Motivation") : getLabel("Nature");
    graphics.drawLabelledContent(motivationLabel, motivationContent, new Position(firstColumnX, secondRowY), bounds.width);

    float thirdRowY = secondRowY - lineHeight;
    float[] shortColumnX = new float[5];
    for (int i = 0; i < 5; i++) {
      shortColumnX[i] = bounds.x + i * (shortEntryWidth + TEXT_PADDING);
    }
    String ageContent = Integer.toString(reportContent.getCharacter().getAge());
    graphics.drawLabelledContent(getLabel("Age"), ageContent, new Position(shortColumnX[0], thirdRowY), shortEntryWidth); //$NON-NLS-1$
    String sexContent = reportContent.getDescription().getSex();
    graphics.drawLabelledContent(getLabel("Sex"), sexContent, new Position(shortColumnX[1], thirdRowY), shortEntryWidth); //$NON-NLS-1$
    String hairContent = reportContent.getDescription().getHair();
    graphics.drawLabelledContent(getLabel("Hair"), hairContent, new Position(shortColumnX[2], thirdRowY), shortEntryWidth); //$NON-NLS-1$
    String skinContent = reportContent.getDescription().getSkin();
    graphics.drawLabelledContent(getLabel("Skin"), skinContent, new Position(shortColumnX[3], thirdRowY), shortEntryWidth); //$NON-NLS-1$
    String eyesContent = reportContent.getDescription().getEyes();
    graphics.drawLabelledContent(getLabel("Eyes"), eyesContent, new Position(shortColumnX[4], thirdRowY), shortEntryWidth); //$NON-NLS-1$

    if (characterType.isExaltType()) {
      float fourthRowY = thirdRowY - lineHeight;
      String animaContent = null;
      graphics.drawLabelledContent(getLabel("Anima"), animaContent, new Position(firstColumnX, fourthRowY), bounds.width); //$NON-NLS-1$
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
    if (StringUtilities.isNullOrTrimmedEmpty(name)) {
      return "PersonalInfo"; //$NON-NLS-1$
    }
    else {
      return name + ".Literal"; //$NON-NLS-1$
    }
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    return getNumberOfLines(content.getCharacter()) * BARE_LINE_HEIGHT + TEXT_PADDING;
  }
}
