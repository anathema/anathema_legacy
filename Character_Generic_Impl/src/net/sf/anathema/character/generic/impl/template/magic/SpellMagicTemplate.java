package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;

public class SpellMagicTemplate implements ISpellMagicTemplate {

  private final CircleType[] sorceryCircles;
  private final CircleType[] necromancyCircles;

  public SpellMagicTemplate(CircleType[] sorceryCircles, CircleType[] necromancyCircles) {
    this.sorceryCircles = sorceryCircles;
    this.necromancyCircles = necromancyCircles;
  }

  public boolean knowsSorcery() {
    return getSorceryCircles() != null && getSorceryCircles().length != 0;
  }

  public boolean knowsNecromancy() {
    return getNecromancyCircles() != null && getNecromancyCircles().length != 0;
  }

  public CircleType[] getSorceryCircles() {
    return sorceryCircles;
  }

  public CircleType[] getNecromancyCircles() {
    return necromancyCircles;
  }

  public boolean knowsSpellMagic() {
    return knowsNecromancy() || knowsSorcery();
  }
}