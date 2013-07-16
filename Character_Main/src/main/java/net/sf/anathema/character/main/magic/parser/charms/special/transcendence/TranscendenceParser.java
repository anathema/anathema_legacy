package net.sf.anathema.character.main.magic.parser.charms.special.transcendence;

import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.TranscendenceDto;
import org.dom4j.Element;

@RegisteredSpecialCharmParser
public class TranscendenceParser implements SpecialCharmParser {

  private static final String TAG_TRANSCENDENCE = "transcendence";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    overallDto.transcendence = createTranscendenceDto(charmElement, overallDto);
  }

  private TranscendenceDto createTranscendenceDto(Element charmElement, SpecialCharmDto overallDto) {
    TranscendenceDto dto = new TranscendenceDto();
    Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
    dto.trait = getGenericTraitType(overallDto.charmId);
    dto.modifier = Integer.parseInt(transcendenceElement.attributeValue(ATTRIB_MODIFIER));
    return dto;
  }

  private String getGenericTraitType(String value) {
    String[] split = value.split("\\.");
    return split[split.length - 1];
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_TRANSCENDENCE) != null;
  }
}