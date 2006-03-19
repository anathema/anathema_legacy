package net.sf.anathema.development.reporting.encoder;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.MortalBasicsCharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.util.HeaderData;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.framework.resources.AnathemaResources;

import org.dom4j.Element;

public class BasicMortalSheetEncoder extends AbstractBasicCharacterSheetEncoder {

  @Override
  protected void addParameterClasses(Map<String, String> parameterClassesByName) {
    new MortalBasicsCharacterTemplate(new AnathemaResources()).addParameterClasses(parameterClassesByName);
  }

  @Override
  protected String getReportName() {
    return "BasicMortalCharacterSheet"; //$NON-NLS-1$
  }

  @Override
  protected int getSpecialtyLineCount() {
    return 3;
  }

  @Override
  protected CharacterType getCharacterType() {
    return CharacterType.MORTAL;
  }

  @Override
  protected HeaderData[] getHeaderData() {
    return new HeaderData[] {
        new HeaderData("Name", "characterName"),
        new HeaderData("Concept", "concept"),
        new HeaderData("Nature", "nature"),
        new HeaderData("Player") };
  }

  @Override
  protected ReportDataSourceEncoder createAdvantageDataSourceEncoder() {
    return ReportDataSourceEncoder.createMeritAndFlawEncoder(LINE_HEIGHT);
  }

  @Override
  protected String getAdvantageDataSourceTitle() {
    return "Merits / Flaws";
  }

  @Override
  protected int encodeLeftRestColumn(Element parent, int y, int x, int spacing, int restColumnWidth) {
    int yOffsetLeft = 0;
    yOffsetLeft += encodeTitledEmptyLines(parent, "Weapons", 8, y, x, restColumnWidth, spacing);
    yOffsetLeft += encodeTitledEmptyLines(parent, "Notes", 8, y + yOffsetLeft, x, restColumnWidth, spacing);
    return yOffsetLeft;
  }
}