package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.spells.CircleType;

public interface ISpellMagicTemplate {

  public CircleType[] getNecromancyCircles();

  public CircleType[] getSorceryCircles();

  public boolean knowsNecromancy();

  public boolean knowsSorcery();

  public boolean knowsSpellMagic();
}