package net.sf.anathema.character.infernal.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.magic.spells.ICircleTypeVisitor;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class InfernalSpellMagicTemplate extends SpellMagicTemplate
{
	public InfernalSpellMagicTemplate(CircleType[] sorceryCircles, CircleType[] necromancyCircles,
			  ICharacterTemplate template)
	{
		super(sorceryCircles, necromancyCircles, template);
	}
	
	@Override
	public boolean canLearnSpell(final ISpell spell, final ICharm[] knownCharms)
	{
	     final Boolean canLearn[] = new Boolean[1];
	     
	     //may be some special spell specific stuff here for demon summoning
	     
		 spell.getCircleType().accept(new ICircleTypeVisitor() {
		      public void visitTerrestrial(CircleType type) {
		        canLearn[0] = canLearnSorcery(spell, knownCharms);
		      }

		      public void visitCelestial(CircleType type) {
		    	canLearn[0] = canLearnSorcery(spell, knownCharms);
		      }

		      public void visitSolar(CircleType type) {
		    	canLearn[0] = canLearnSorcery(spell, knownCharms);
		      }

		      public void visitShadowland(CircleType type) {
		    	canLearn[0] = canLearnNecromancy(spell, knownCharms);        
		      }

		      public void visitLabyrinth(CircleType type) {
		    	  canLearn[0] = canLearnNecromancy(spell, knownCharms);        
		      }

		      public void visitVoid(CircleType type) {
		    	  canLearn[0] = canLearnNecromancy(spell, knownCharms);        
		      }
		    });

		 return canLearn[0];
	  }
	
	private boolean canLearnSorcery(ISpell spell, ICharm[] knownCharms)
	{
		String[] charmNames = getCharmNames(spell.getCircleType());
		
		for (String charm : charmNames)
			if (knowsCharm(charm, knownCharms))
				return true;
		return false;
	}
	
	private boolean canLearnNecromancy(ISpell spell, ICharm[] knownCharms)
	{
		boolean canLearnNecromancy = knowsCharm("Infernal.UltimateDarknessInternalization", knownCharms);
		return canLearnNecromancy && canLearnSorcery(spell, knownCharms);
	}
	
	private boolean knowsCharm(String charm, ICharm[] knownCharms)
	{
		for (ICharm knownCharm : knownCharms)
			  if (charm.equals(knownCharm.getId()))
				  return true;
		  return false;
	}
	
	public String[] getCharmNames(CircleType circle)
	{
		final List<String> names = new ArrayList<String>();
		for (final YoziType yozi : YoziType.values())
		{
		  circle.accept(new ICircleTypeVisitor() {
		      public void visitTerrestrial(CircleType type) {
		        names.add("Infernal.SorcerousEnlightenment." + yozi.getId());
		      }

		      public void visitCelestial(CircleType type) {
		    	  names.add("Infernal.SorcerousEnlightenment2." + yozi.getId());
		      }

		      public void visitSolar(CircleType type) {
		    	  names.add("Infernal.SorcerousEnlightenment3." + yozi.getId());
		      }

		      public void visitShadowland(CircleType type) {
		    	  names.add("Infernal.SorcerousEnlightenment." + yozi.getId());        
		      }

		      public void visitLabyrinth(CircleType type) {
		    	  names.add("Infernal.SorcerousEnlightenment2." + yozi.getId());        
		      }

		      public void visitVoid(CircleType type) {
		    	  names.add("Infernal.SorcerousEnlightenment3." + yozi.getId());        
		      }
		    });
		}
		String[] charms = new String[names.size()];
		  names.toArray(charms);
		  return charms;
	}

}
