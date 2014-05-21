package net.sf.anathema.character.magic.parser.charms.special.oxbody;

import net.sf.anathema.character.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.magic.parser.dto.special.OxBodyPickDto;
import net.sf.anathema.character.magic.parser.dto.special.OxBodyTechniqueDto;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public class OxBodyTechniqueParser implements SpecialCharmParser {

  private static final String TAG_OX_BODY_CHARM = "oxbody";
  private static final String TAG_OX_BODY_PICK = "pick";
  private static final String ATTRIB_TRAIT = "trait";
  private static final String ATTRIB_NAME = "name";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element oxBodyElement = charmElement.element(TAG_OX_BODY_CHARM);
    overallDto.oxBodyTechnique = createOxBodyTechniqueDto(oxBodyElement);
  }

  private OxBodyTechniqueDto createOxBodyTechniqueDto(Element oxBodyElement) {
    OxBodyTechniqueDto dto = new OxBodyTechniqueDto();
    dto.traits.addAll(Arrays.asList(oxBodyElement.attributeValue(ATTRIB_TRAIT).split(",")));
    for (Object pickObj : oxBodyElement.elements(TAG_OX_BODY_PICK)) {
      Element pick = (Element) pickObj;
      createPickDto(dto, pick);
    }
    return dto;
  }

  private void createPickDto(OxBodyTechniqueDto dto, Element pick) {
    OxBodyPickDto category = new OxBodyPickDto();
    category.id = pick.attributeValue(ATTRIB_NAME);
    for (Object levelObj : pick.elements()) {
      Element levelElement = (Element) levelObj;
      category.healthLevels.add(levelElement.getName());
    }
    dto.picks.add(category);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_OX_BODY_CHARM) != null;
  }
}