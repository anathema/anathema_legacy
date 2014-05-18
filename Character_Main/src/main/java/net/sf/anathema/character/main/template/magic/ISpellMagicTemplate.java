package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.character.main.magic.spells.Spell;

public interface ISpellMagicTemplate {

  CircleType[] getSorceryCircles();

  CircleType[] getNecromancyCircles();

  boolean canLearnSorcery();

  boolean canLearnNecromancy();
  
  boolean canLearnSpellMagic();

  boolean canLearnSpell(Spell spell, Charm[] knownCharms);
}