package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.character.main.magic.spells.ICircleTypeVisitor;
import net.sf.anathema.character.main.magic.spells.Spell;
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

  protected boolean knowsCharm(String charm, Charm[] knownCharms) {
    for (Charm knownCharm : knownCharms) {
      if (charm.equals(knownCharm.getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean canLearnSpell(Spell spell, Charm[] knownCharms) {
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