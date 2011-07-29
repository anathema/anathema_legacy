package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.magic.spells.ICircleTypeVisitor;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;

public class SpellMagicTemplate implements ISpellMagicTemplate {

  private final CircleType[] sorceryCircles;
  private final CircleType[] necromancyCircles;
  private final ICharacterTemplate template;

  public SpellMagicTemplate(CircleType[] sorceryCircles,
                            CircleType[] necromancyCircles,
                            ICharacterTemplate template) {
    this.sorceryCircles = sorceryCircles;
    this.necromancyCircles = necromancyCircles;
    this.template = template;
  }

  public CircleType[] getSorceryCircles() {
    return sorceryCircles;
  }

  public CircleType[] getNecromancyCircles() {
    return necromancyCircles;
  }

  public boolean canLearnSorcery() {
    return getSorceryCircles() != null && getSorceryCircles().length != 0;
  }

  public boolean canLearnNecromancy() {
    return getNecromancyCircles() != null && getNecromancyCircles().length != 0;
  }

  public boolean canLearnSpellMagic() {
    return canLearnSorcery() || canLearnNecromancy();
  }

  protected boolean knowsCharm(String charm, ICharm[] knownCharms) {
    for (ICharm knownCharm : knownCharms)
        if (charm.equals(knownCharm.getId()))
          return true;
      return false;
  }
  
  public boolean knowsSorcery(ICharm[] knownCharms) {
    for (CircleType circle : sorceryCircles) {
      if (knowsCharm(getInitiation(circle), knownCharms)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean knowsNecromancy(ICharm[] knownCharms) {
    for (CircleType circle : necromancyCircles) {
      if (knowsCharm(getInitiation(circle), knownCharms)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean knowsSpellMagic(ICharm[] knownCharms) {
    return knowsSorcery(knownCharms) || knowsNecromancy(knownCharms);
  }

  public boolean canLearnSpell(ISpell spell, ICharm[] knownCharms) {
	  return knowsCharm(getInitiation(spell.getCircleType()), knownCharms);
  }
  
  public String getInitiation(CircleType type) {
    final String[] initiation = new String[1];
    type.accept(new ICircleTypeVisitor() {
      public void visitTerrestrial(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".TerrestrialCircleSorcery"; //$NON-NLS-1$
      }

      public void visitCelestial(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".CelestialCircleSorcery"; //$NON-NLS-1$
      }

      public void visitSolar(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".SolarCircleSorcery"; //$NON-NLS-1$
      }

      public void visitShadowland(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".ShadowlandsCircleNecromancy"; //$NON-NLS-1$        
      }

      public void visitLabyrinth(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".LabyrinthCircleNecromancy"; //$NON-NLS-1$        
      }

      public void visitVoid(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".VoidCircleNecromancy"; //$NON-NLS-1$        
      }
    });
    return initiation[0];
  }
}