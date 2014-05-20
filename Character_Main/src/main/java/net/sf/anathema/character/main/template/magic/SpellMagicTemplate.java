package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.spells.CircleType;

public class SpellMagicTemplate implements ISpellMagicTemplate {

  private final CircleType[] sorceryCircles;
  private final CircleType[] necromancyCircles;

  public SpellMagicTemplate(CircleType[] sorceryCircles, CircleType[] necromancyCircles) {
    this.sorceryCircles = sorceryCircles;
    this.necromancyCircles = necromancyCircles;
  }

  @Override
  public CircleType[] getSorceryCircles() {
    return sorceryCircles;
  }

  @Override
  public CircleType[] getNecromancyCircles() {
    return necromancyCircles;
  }
}