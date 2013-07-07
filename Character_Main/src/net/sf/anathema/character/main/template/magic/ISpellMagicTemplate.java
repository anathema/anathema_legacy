package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.spells.CircleType;

public interface ISpellMagicTemplate {

  CircleType[] getSorceryCircles();

  CircleType[] getNecromancyCircles();

  boolean canLearnSorcery();

  boolean canLearnNecromancy();
  
  boolean canLearnSpellMagic();

  boolean knowsSorcery(ICharm[] knownCharms);

  boolean knowsNecromancy(ICharm[] knownCharms);
  
  boolean knowsSpellMagic(ICharm[] knownCharms);
  
  boolean knowsSpellMagic(ICharm[] knownCharms, CircleType circle);
  
  boolean canLearnSpell(ISpell spell, ICharm[] knownCharms);
}