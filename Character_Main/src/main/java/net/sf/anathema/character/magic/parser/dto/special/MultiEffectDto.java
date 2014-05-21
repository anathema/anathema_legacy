package net.sf.anathema.character.magic.parser.dto.special;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiEffectDto {
  public List<String> effects = new ArrayList<>();
  public Map<String, String> prerequisiteEffectMap = new HashMap<>();
}
