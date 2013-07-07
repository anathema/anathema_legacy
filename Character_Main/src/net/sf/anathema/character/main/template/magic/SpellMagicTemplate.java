package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ICircleTypeVisitor;
import net.sf.anathema.character.main.template.HeroTemplate;

public class SpellMagicTemplate implements ISpellMagicTemplate {

  private final CircleType[] sorceryCircles;
  private final CircleType[] necromancyCircles;
  private final HeroTemplate template;

  public SpellMagicTemplate(CircleType[] sorceryCircles, CircleType[] necromancyCircles, HeroTemplate template) {
    this.sorceryCircles = sorceryCircles;
    this.necromancyCircles = necromancyCircles;
    this.template = template;
  }

  @Override
  public CircleType[] getSorceryCircles() {
    return sorceryCircles;
  }

  @Override
  public CircleType[] getNecromancyCircles() {
    return necromancyCircles;
  }

  @Override
  public boolean canLearnSorcery() {
    return getSorceryCircles() != null && getSorceryCircles().length != 0;
  }

  @Override
  public boolean canLearnNecromancy() {
    return getNecromancyCircles() != null && getNecromancyCircles().length != 0;
  }

  @Override
  public boolean canLearnSpellMagic() {
    return canLearnSorcery() || canLearnNecromancy();
  }

  protected boolean knowsCharm(String charm, ICharm[] knownCharms) {
    for (ICharm knownCharm : knownCharms) {
      if (charm.equals(knownCharm.getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean knowsSorcery(ICharm[] knownCharms) {
    for (CircleType circle : sorceryCircles) {
      if (knowsSpellMagic(knownCharms, circle)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean knowsNecromancy(ICharm[] knownCharms) {
    for (CircleType circle : necromancyCircles) {
      if (knowsSpellMagic(knownCharms, circle)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean knowsSpellMagic(ICharm[] knownCharms) {
    return knowsSorcery(knownCharms) || knowsNecromancy(knownCharms);
  }

  @Override
  public boolean knowsSpellMagic(ICharm[] knownCharms, CircleType circle) {
    return knowsCharm(getInitiation(circle), knownCharms);
  }

  @Override
  public boolean canLearnSpell(ISpell spell, ICharm[] knownCharms) {
    return knowsCharm(getInitiation(spell.getCircleType()), knownCharms);
  }

  private String getInitiation(CircleType type) {
    final String[] initiation = new String[1];
    type.accept(new ICircleTypeVisitor() {
      @Override
      public void visitTerrestrial(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".TerrestrialCircleSorcery";
      }

      @Override
      public void visitCelestial(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".CelestialCircleSorcery";
      }

      @Override
      public void visitSolar(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".SolarCircleSorcery";
      }

      @Override
      public void visitShadowland(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".ShadowlandsCircleNecromancy";
      }

      @Override
      public void visitLabyrinth(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".LabyrinthCircleNecromancy";
      }

      @Override
      public void visitVoid(CircleType type) {
        initiation[0] = template.getTemplateType().getCharacterType().getId() + ".VoidCircleNecromancy";
      }
    });
    return initiation[0];
  }
}