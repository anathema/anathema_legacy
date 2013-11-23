package net.sf.anathema.hero.equipment.model;

import com.google.common.base.Functions;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMaterialRules implements MaterialRules {

  private final Map<String, CharacterTypeMaterialRules> rulesByCharacterType = new HashMap<>();

  public ReflectionMaterialRules(ObjectFactory objectFactory) {
    Collection<CharacterTypeMaterialRules> allRules = objectFactory.instantiateAllImplementers(CharacterTypeMaterialRules.class);
    for (CharacterTypeMaterialRules rules : allRules) {
      String applicableType = rules.getClass().getAnnotation(ForCharacterType.class).characterType();
      rulesByCharacterType.put(applicableType, rules);
    }
  }

  public MagicalMaterial getDefault(CharacterType characterType) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.getDefault();
  }

  public ArtifactAttuneType[] getAttunementTypes(CharacterType characterType,
                                                 MagicalMaterial material) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.getAttunementTypes(material);
  }

  public boolean canAttuneToMalfeanMaterials(CharacterType characterType) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.canAttuneToMalfeanMaterials();
  }

  private CharacterTypeMaterialRules getRulesForCharacter(CharacterType type) {
    return Functions.forMap(rulesByCharacterType, new DefaultMaterialRules()).apply(type.getId());
  }
}