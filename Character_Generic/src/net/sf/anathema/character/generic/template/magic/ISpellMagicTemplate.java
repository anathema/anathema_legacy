package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;

public interface ISpellMagicTemplate {

  public CircleType[] getSorceryCircles();

  public CircleType[] getNecromancyCircles();

  public boolean canLearnSorcery();

  public boolean canLearnNecromancy();
  
  public boolean canLearnSpellMagic();

  public boolean knowsSorcery(ICharm[] knownCharms);

  public boolean knowsNecromancy(ICharm[] knownCharms);
  
  public boolean knowsSpellMagic(ICharm[] knownCharms);
  
  public boolean canLearnSpell(ISpell spell, ICharm[] knownCharms);
}