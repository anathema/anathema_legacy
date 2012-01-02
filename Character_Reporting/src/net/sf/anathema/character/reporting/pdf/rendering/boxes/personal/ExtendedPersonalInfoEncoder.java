package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class ExtendedPersonalInfoEncoder {

  private final IResources resources;

  public ExtendedPersonalInfoEncoder(IResources resources) {
    this.resources = resources;
  }

  public void encodePersonalInfo(SheetGraphics graphics, ReportContent content, Bounds infoBounds) {
    IGenericCharacter character = content.getCharacter();
    IGenericDescription description = content.getDescription();
    float lineHeight = (infoBounds.height - TEXT_PADDING) / 3;
    float entryWidth = (infoBounds.width - TEXT_PADDING) / 2;
    float firstColumnX = infoBounds.x;
    float secondColumnX = infoBounds.x + entryWidth + TEXT_PADDING;

    float firstRowY = (int) (infoBounds.getMaxY() - lineHeight);
    IExaltedRuleSet rules = character.getRules();
    String rulesContent = rules == null ? null : resources.getString("Ruleset." + rules.getId()); //$NON-NLS-1$
    graphics.drawLabelledContent(getLabel("Rules"), rulesContent, new Position(firstColumnX, firstRowY), entryWidth); //$NON-NLS-1$
    graphics.drawLabelledContent(getLabel("Player"), description.getPlayer(), new Position(secondColumnX, firstRowY), entryWidth); //$NON-NLS-1$

    float secondRowY = firstRowY - lineHeight;
    String conceptContent = description.getConceptText();
    String conceptLabel = getLabel("Concept"); //$NON-NLS-1$
    graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, secondRowY), entryWidth);
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    if (characterType.isExaltType()) {
      String casteContent = getCasteString(character.getConcept().getCasteType());
      graphics.drawLabelledContent(getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX, secondRowY),
        entryWidth); //$NON-NLS-1$
    }

    float thirdRowY = secondRowY - lineHeight;
    String motivationContent = character.getConcept().getWillpowerRegainingComment(resources);
    String motivationLabel = character.getRules().getEdition() == ExaltedEdition.SecondEdition ? getLabel("Motivation") : getLabel("Nature");
    //$NON-NLS-1$ //$NON-NLS-2$
    Position motivationPosition = new Position(firstColumnX, thirdRowY);
    graphics.drawLabelledContent(motivationLabel, motivationContent, motivationPosition, infoBounds.width);
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
}
