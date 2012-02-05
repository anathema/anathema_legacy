package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;

import com.db4o.query.Predicate;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.ExpensivePartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ICharacterType characterType;
  private final MagicalMaterial defaultMaterial;
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<IEquipmentItem>();

  public EquipmentAdditionalModel(
          ICharacterType characterType,
          IArmourStats naturalArmour,
          IEquipmentTemplateProvider equipmentTemplateProvider,
          IExaltedRuleSet ruleSet,
          IEquipmentTemplate... naturalWeapons) {
    super(ruleSet, naturalArmour);
    this.characterType = characterType;
    this.defaultMaterial = evaluateDefaultMaterial();
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    for (IEquipmentTemplate template : naturalWeapons) {
      if (template == null) {
        continue;
      }
      final IEquipmentItem item = new EquipmentItem(template, ruleSet, null);
      naturalWeaponItems.add(initItem(item));
    }
  }

  private MagicalMaterial evaluateDefaultMaterial() {
    MagicalMaterial defaultMaterial = MagicalMaterial.getDefault(characterType);
    if (defaultMaterial == null) {
      return MagicalMaterial.Orichalcum;
    }
    return defaultMaterial;
  }

  public IEquipmentItem[] getNaturalWeapons() {
    return naturalWeaponItems.toArray(new IEquipmentItem[naturalWeaponItems.size()]);
  }

  public boolean canBeRemoved(IEquipmentItem item) {
    return !naturalWeaponItems.contains(item);
  }

  public String[] getAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    equipmentTemplateProvider.queryContainer(new Predicate<IEquipmentTemplate>() {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean match(IEquipmentTemplate candidate) {
        if (candidate.getStats(getRuleSet()).length > 0) {
          idSet.add(candidate.getName());
        } else {
          for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
            if (candidate.getStats(rules).length > 0) {
              return false;
            }
          }
          idSet.add(candidate.getName());
        }
        return false;
      }
    });
    return idSet.toArray(new String[idSet.size()]);
  }

  @Override
  protected IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }

  @Override
  protected IEquipmentItem getSpecialManagedItem(String templateId) {
    for (IEquipmentItem item : naturalWeaponItems) {
      if (templateId.equals(item.getTemplateId())) {
        return item;
      }
    }
    return null;
  }

  public MagicalMaterial getDefaultMaterial() {
    return defaultMaterial;
  }

  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    MagicalMaterial material = item.getMaterial();
    switch (item.getMaterialComposition()) {
      default:
      case None:
        return null;
      case Fixed:
      case Variable:
        return MagicalMaterial.getAttunementTypes(characterType, material);
      case Compound:
        return new ArtifactAttuneType[]
                {Unattuned, ArtifactAttuneType.FullyAttuned};
      case MalfeanMaterials:
        return createMalfeanMaterialsAttunementOptions();
    }
  }

  private ArtifactAttuneType[] createMalfeanMaterialsAttunementOptions() {
    if (characterType != CharacterType.INFERNAL) {
      return new ArtifactAttuneType[]{Unattuned, ExpensivePartiallyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, PartiallyAttuned};
  }
}