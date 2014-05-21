package net.sf.anathema.character.magic.parser.dto;

import java.util.ArrayList;
import java.util.List;

public class ComboRestrictionDto {

  public List<String> charmIds = new ArrayList<>();
  public List<String> charmTypes = new ArrayList<>();
  public List<String> traitReferences = new ArrayList<>();
  public List<String> genericCharmReferences = new ArrayList<>();
}
