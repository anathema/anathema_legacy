package net.sf.anathema.character.generic.impl.bootjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class TemplateTransformer_RulesTest {
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
            {"Abyssal", "RevisedLoyalAbyssal", "TemplateType.Default"},
            {"Abyssal", "RevisedRenegadeAbyssal", "RenegadeAbyssal"},
            {"Dragon-Blooded", "CherakRevised", "Cherak"},
            {"Dragon-Blooded", "DreamsRevisedSubtype", "DreamsSubtype"},
            {"Dragon-Blooded", "DynasticRevisedSubtype", "TemplateType.Default"},
            {"Dragon-Blooded", "ImmaculateRevisedSubtype", "ImmaculateSubtype"},
            {"Dragon-Blooded", "LookshyRevisedSubtype", "LookshySubtype"},
            {"Dragon-Blooded", "LookshyOutcasteRevisedSubtype", "LookshyOutcasteSubtype"},
            {"Dragon-Blooded", "LookshyRealmRevisedSubtype", "LookshyRealmSubtype"},
            {"Dragon-Blooded", "ThresholdOutcasteRevisedSubtype", "ThresholdOutcasteSubtype"},
            {"Infernal", "RevisedInfernal", "TemplateType.Default"},
            {"Lunar", "CastelessRevised", "Casteless"},
            {"Lunar", "DreamsRevised", "Dreams"},
            {"Lunar", "SilverPactRevised", "TemplateType.Default"},
            {"Sidereal", "RevisedDreams", "Dreams"},
            {"Sidereal", "RevisedRonin", "Ronin"},
            {"Sidereal", "Revised", "TemplateType.Default"},
            {"Solar", "RevisedSolar", "TemplateType.Default"},
            {"Solar", "DreamsRevised", "Dreams"},
            {"Solar", "DreamsRevisedEstablished", "DreamsEstablished"},
            {"Solar", "DreamsRevisedInfluential", "DreamsInfluential"},
            {"Solar", "DreamsRevisedLegendary", "DreamsLegendary"},
            {"Solar", "DreamsDawn", "Dreams"},
            {"Solar", "DreamsDawnRevised", "Dreams"},
            {"Solar", "DreamsZenith", "Dreams"},
            {"Solar", "DreamsZenithRevised", "Dreams"},
            {"Solar", "DreamsTwilight", "Dreams"},
            {"Solar", "DreamsTwilightRevised", "Dreams"},
            {"Solar", "DreamsNight", "Dreams"},
            {"Solar", "DreamsNightRevised", "Dreams"},
            {"Solar", "DreamsEclipse", "Dreams"},
            {"Solar", "DreamsEclipseRevised", "Dreams"},
    });
  }

  private final String characterType;
  private final String originalTemplate;
  private final String newTemplate;
  private TemplateTransformer transformer = new TemplateTransformer();

  public TemplateTransformer_RulesTest(String characterType, String originalTemplate, String newTemplate) {
    this.characterType = characterType;
    this.originalTemplate = originalTemplate;
    this.newTemplate = newTemplate;
  }

  @Test
  public void test() {
    assertCharacterTransformationResultsIn(characterType, originalTemplate, newTemplate);
  }

  private void assertCharacterTransformationResultsIn(String type, String inputTemplate, String expectedTemplate) {
    String input = createCharacterWithTemplate(type, inputTemplate);
    String expectation = createCharacterWithTemplate(type, expectedTemplate);
    assertTransformationResultsIn(input, expectation);
  }

  private void assertTransformationResultsIn(String input, String expectation) {
    String result = transformer.transform(input);
    assertThat(result, is(expectation));
  }

  private String createCharacterWithTemplate(String charactertype, String template) {
    return MessageFormat.format(CHARACTER_TYPE_PATTERN, charactertype, template);
  }

  private static final String CHARACTER_TYPE_PATTERN = "<ExaltedCharacter repositoryId=\"MrNonDefault\" repositoryPrintName=\"Mr. NonDefault\">\n" +
          "  <Description>\n" +
          "    <CharacterName><![CDATA[Mr. NonDefault]]></CharacterName>\n" +
          "  </Description>\n" +
          "  <Statistics experienced=\"false\">\n" +
          "    <Rules>\n" +
          "      <RuleSet name=\"SecondEdition\"/>\n" +
          "    </Rules>\n" +
          "    <CharacterType subtype=\"{1}\">{0}</CharacterType>\n" +
          "  </Statistics>\n" +
          "</ExaltedCharacter>";
}
 