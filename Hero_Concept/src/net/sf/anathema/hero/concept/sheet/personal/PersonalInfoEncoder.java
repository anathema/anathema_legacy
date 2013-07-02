package net.sf.anathema.hero.concept.sheet.personal;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class PersonalInfoEncoder implements IVariableContentEncoder {

  private final Resources resources;

  public PersonalInfoEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    ICharacterType characterType = reportSession.getHero().getTemplate().getTemplateType().getCharacterType();

    int lines = getNumberOfLines(characterType);

    float lineHeight = (bounds.height - TEXT_PADDING) / lines;
    float entryWidth = (bounds.width - TEXT_PADDING) / 2;
    float firstColumnX = bounds.x;
    float secondColumnX = firstColumnX + entryWidth + TEXT_PADDING;

    float firstRowY = (int) (bounds.getMaxY() - lineHeight);
    String conceptContent = reportSession.getDescription().getConceptText();
    String conceptLabel = getLabel("Concept");
    if (characterType.isExaltType()) {
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), entryWidth);
      String casteContent = getCasteString(HeroConceptFetcher.fetch(reportSession.getHero()).getCaste().getType());
      graphics.drawLabelledContent(getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX, firstRowY), entryWidth);
    } else {
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), 2 * entryWidth + TEXT_PADDING);
    }

    float secondRowY = firstRowY - lineHeight;

    int shortColumnCount = 3;
    float shortEntryWidth = (bounds.width - (shortColumnCount - 1) * TEXT_PADDING) / shortColumnCount;
    float[] shortColumnX = new float[shortColumnCount];
    for (int i = 0; i < shortColumnCount; i++) {
      shortColumnX[i] = bounds.x + i * (shortEntryWidth + TEXT_PADDING);
    }
    String ageContent = Integer.toString(HeroConceptFetcher.fetch(reportSession.getHero()).getAge().getValue());
    graphics.drawLabelledContent(getLabel("Age"), ageContent, new Position(shortColumnX[0], secondRowY), shortEntryWidth);
    String sexContent = reportSession.getDescription().getSex();
    graphics.drawLabelledContent(getLabel("Sex"), sexContent, new Position(shortColumnX[1], secondRowY), shortEntryWidth);

    float thirdRowY = secondRowY - lineHeight;
    String hairContent = reportSession.getDescription().getHair();
    graphics.drawLabelledContent(getLabel("Hair"), hairContent, new Position(shortColumnX[0], thirdRowY), shortEntryWidth);
    String skinContent = reportSession.getDescription().getSkin();
    graphics.drawLabelledContent(getLabel("Skin"), skinContent, new Position(shortColumnX[1], thirdRowY), shortEntryWidth);
    String eyesContent = reportSession.getDescription().getEyes();
    graphics.drawLabelledContent(getLabel("Eyes"), eyesContent, new Position(shortColumnX[2], thirdRowY), shortEntryWidth);

    if (characterType.isExaltType()) {
      float fourthRowY = thirdRowY - lineHeight;
      String animaContent = reportSession.getDescription().getAnima();
      graphics.drawLabelledContent(getLabel("Anima"), animaContent, new Position(firstColumnX, fourthRowY), bounds.width);
    }
  }

  private int getNumberOfLines(Hero hero) {
    return getNumberOfLines(hero.getTemplate().getTemplateType().getCharacterType());
  }

  private int getNumberOfLines(ICharacterType characterType) {
    return (characterType.isExaltType() ? 4 : 3);
  }

  private String getCasteString(CasteType casteType) {
    if (casteType == null || casteType == CasteType.NULL_CASTE_TYPE) {
      return null;
    }
    return resources.getString("Caste." + casteType.getId());
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Label." + key) + ":";
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    String name = session.getDescription().getName();
    return StringUtilities.isNullOrTrimmedEmpty(name) ? resources.getString("Sheet.Header.PersonalInfo") : name;
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width) {
    return getPreferredContentHeight(session);
  }

  public float getPreferredContentHeight(ReportSession session) {
    return getNumberOfLines(session.getHero()) * BARE_LINE_HEIGHT + TEXT_PADDING;
  }
}
