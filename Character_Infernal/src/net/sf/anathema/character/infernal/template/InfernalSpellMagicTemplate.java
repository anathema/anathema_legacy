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

public class InfernalSpellMagicTemplate extends SpellMagicTemplate {
  
  private static final String NECROMANCY_INITIATION = "Infernal.UltimateDarknessInternalization"; //$NON-NLS-1$
  
  public InfernalSpellMagicTemplate(CircleType[] sorceryCircles,
                                    CircleType[] necromancyCircles,
                                    ICharacterTemplate template) {
    super(sorceryCircles, necromancyCircles, template);
  }

  @Override
  public boolean canLearnSpell(final ISpell spell, final ICharm[] knownCharms) {
    final boolean canLearn[] = new boolean[1];

    // may be some special spell specific stuff here for demon summoning

    spell.getCircleType().accept(new ICircleTypeVisitor() {
      public void visitTerrestrial(CircleType type) {
        canLearn[0] = canLearnSorcerySpell(spell, knownCharms);
      }

      public void visitCelestial(CircleType type) {
        canLearn[0] = canLearnSorcerySpell(spell, knownCharms);
      }

      public void visitSolar(CircleType type) {
        canLearn[0] = canLearnSorcerySpell(spell, knownCharms);
      }

      public void visitShadowland(CircleType type) {
        canLearn[0] = canLearnNecromancySpell(spell, knownCharms);
      }

      public void visitLabyrinth(CircleType type) {
        canLearn[0] = canLearnNecromancySpell(spell, knownCharms);
      }

      public void visitVoid(CircleType type) {
        canLearn[0] = canLearnNecromancySpell(spell, knownCharms);
      }
    });

    return canLearn[0];
  }

  protected boolean knowsCharm(String charm, ICharm[] knownCharms) {
    for (ICharm knownCharm : knownCharms)
      if (charm.equals(knownCharm.getId()))
        return true;
    return false;
  }
  
  protected boolean knowsNecromancyInitiation(ICharm[] knownCharms) {
    return knowsCharm(NECROMANCY_INITIATION, knownCharms);
  }
  
  @Override
  public boolean knowsSorcery(ICharm[] knownCharms) {
    for (CircleType circle : getSorceryCircles())
      for (String initiation : getInitiations(circle))
        if (knowsCharm(initiation, knownCharms))
          return true;
    return false;
  }

  @Override
  public boolean knowsNecromancy(ICharm[] knownCharms) {
    return knowsNecromancyInitiation(knownCharms) && knowsSorcery(knownCharms);
  }

  private boolean canLearnSorcerySpell(ISpell spell, ICharm[] knownCharms) {
    String[] charmNames = getInitiations(spell.getCircleType());

    for (String charm : charmNames)
      if (knowsCharm(charm, knownCharms))
        return true;
    return false;
  }

  private boolean canLearnNecromancySpell(ISpell spell, ICharm[] knownCharms) {
    return knowsNecromancyInitiation(knownCharms)
           && canLearnSorcerySpell(spell, knownCharms);
  }

  public String[] getInitiations(CircleType circle) {
    final List<String> names = new ArrayList<String>();
    for (final YoziType yozi : YoziType.values()) {
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
