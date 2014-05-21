package net.sf.anathema.hero.template.magic;

import net.sf.anathema.character.magic.spells.CircleType;

public interface ISpellMagicTemplate {

  CircleType[] getSorceryCircles();

  CircleType[] getNecromancyCircles();
}