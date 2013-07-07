package net.sf.anathema.character.main.magic.parser.dto;

import java.util.ArrayList;
import java.util.List;

public class CharmDto {

  public String charmId;
  public String characterType;
  public CostListDto costList = new CostListDto();
  public DurationDto duration = new DurationDto();
  public List<String> sources = new ArrayList<>();
  public PrerequisiteListDto prerequisiteList;
}
