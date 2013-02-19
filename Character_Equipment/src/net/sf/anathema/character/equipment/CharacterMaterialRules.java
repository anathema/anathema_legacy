package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;

public class CharacterMaterialRules {

  public MagicalMaterial getDefault(ICharacterType characterType) {
    MaterialRules rules = getRulesForCharacter(characterType);
    return rules.getDefault();
  }

  public ArtifactAttuneType[] getAttunementTypes(ICharacterType characterType,
                                                 MagicalMaterial material) {
    MaterialRules rules = getRulesForCharacter(characterType);
    return rules.getAttunementTypes(material);
  }

  public boolean canAttuneToMalfeanMaterials(ICharacterType characterType) {
    MaterialRules rules = getRulesForCharacter(characterType);
    return rules.canAttuneToMalfeanMaterials();
  }

  private MaterialRules getRulesForCharacter(ICharacterType type) {
    final MaterialRules[] rules = new MaterialRules[1];
    type.accept(new ICharacterTypeVisitor() {
      @Override
      public void visitSolar() {
        rules[0] = new SolarMaterialRules();
      }

      @Override
      public void visitMortal() {
        rules[0] = new MortalMaterialRules();
      }

      @Override
      public void visitSidereal() {
        rules[0] = new SiderealMaterialRules();
      }

      @Override
      public void visitInfernal() {
        rules[0] = new InfernalMaterialRules();
      }

      @Override
      public void visitDB() {
        rules[0] = new DbMaterialRules();
      }

      @Override
      public void visitAbyssal() {
        rules[0] = new AbyssalMaterialRules();
      }

      @Override
      public void visitSpirit() {
        rules[0] = new SpiritMaterialRules();
      }

      @Override
      public void visitGhost() {
        rules[0] = new GhostMaterialRules();
      }

      @Override
      public void visitLunar() {
        rules[0] = new LunarMaterialRules();
      }
    });
    return rules[0];
  }
}