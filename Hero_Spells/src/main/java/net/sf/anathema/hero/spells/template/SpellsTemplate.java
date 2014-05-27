package net.sf.anathema.hero.spells.template;

import net.sf.anathema.character.magic.spells.CircleType;

import java.util.Map;
import java.util.TreeMap;

public class SpellsTemplate {

  public Map<CircleType, String> sorcery = new TreeMap<>();
  public Map<CircleType, String> necromancy = new TreeMap<>();
  public String favoringTrait;
}
