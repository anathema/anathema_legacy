package net.sf.anathema.character.main.magic.parser.dto;

import java.util.List;

public class PrerequisiteListDto {

  public List<TraitPrerequisiteDto> traits;
  public List<String> charmReferences;
  public List<SelectiveCharmGroupDto> selectiveCharmGroups;
  public List<IndirectCharmPrerequisiteDto> indirectCharmPrerequisites;
}
