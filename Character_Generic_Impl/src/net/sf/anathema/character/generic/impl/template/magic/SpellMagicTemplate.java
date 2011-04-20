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

  public SpellMagicTemplate(CircleType[] sorceryCircles, CircleType[] necromancyCircles,
		  ICharacterTemplate template) {
    this.sorceryCircles = sorceryCircles;
    this.necromancyCircles = necromancyCircles;
    this.template = template;
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

  @Override
  public boolean canLearnSpell(ISpell spell, ICharm[] knownCharms)
  {
	  final String[] circleCharmName = new String[1];
	  spell.getCircleType().accept(new ICircleTypeVisitor() {
	      public void visitTerrestrial(CircleType type) {
	        circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".TerrestrialCircleSorcery"; //$NON-NLS-1$
	      }

	      public void visitCelestial(CircleType type) {
	    	circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".CelestialCircleSorcery"; //$NON-NLS-1$
	      }

	      public void visitSolar(CircleType type) {
	    	 circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".SolarCircleSorcery"; //$NON-NLS-1$
	      }

	      public void visitShadowland(CircleType type) {
	    	 circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".ShadowlandsCircleNecromancy"; //$NON-NLS-1$        
	      }

	      public void visitLabyrinth(CircleType type) {
	    	circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".LabyrinthCircleNecromancy"; //$NON-NLS-1$        
	      }

	      public void visitVoid(CircleType type) {
	    	circleCharmName[0] = template.getTemplateType().getCharacterType().getId() + ".VoidCircleNecromancy"; //$NON-NLS-1$        
	      }
	    });
	  for (ICharm charm : knownCharms)
		  if (charm.getId().equals(circleCharmName[0]))
			  return true;
	  return false;
  }
  
}