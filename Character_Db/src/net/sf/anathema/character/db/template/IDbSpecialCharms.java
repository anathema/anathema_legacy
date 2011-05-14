package net.sf.anathema.character.db.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.db.magic.ElementalMultipleEffectCharm;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.MultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public interface IDbSpecialCharms {
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Dragon-Blooded.Ox-BodyTechnique", AbilityType.Endurance, //$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
		private static final long serialVersionUID = 4907232349336731364L;

		{
          put("Category.-1-2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE_SECOND_EDITION = new OxBodyTechniqueCharm(
      "Dragon-Blooded.Ox-BodyTechnique", AbilityType.Resistance, //$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
		private static final long serialVersionUID = 4618293067517007189L;

		{
          put("Category.-1-2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
  public static final IMultipleEffectCharm DRAGON_CLAW_ELEMENTAL_STRIKE = new ElementalMultipleEffectCharm(
      "Terrestrial.Dragon-ClawElementalStrike"); //$NON-NLS-1$
  public static final IMultipleEffectCharm ELEMENT_PROTECTION_FORM = new MultipleEffectCharm(
      "Dragon-Blooded.ElementProtectionForm", //$NON-NLS-1$
      new String[] { "Air", "Earth", "Fire", "Water", "Wood" }); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
  public static final IMultipleEffectCharm ELEMENTAL_DEFENSE_TECHNIQUE = new ElementalMultipleEffectCharm(
      "Dragon-Blooded.ElementalDefenseTechnique"); //$NON-NLS-1$
  public static final IMultipleEffectCharm UNASSAILABLE_BODY_OF_ELEMENT_DEFENSE = new ElementalMultipleEffectCharm(
      "Dragon-Blooded.UnassailableBodyElementDefense"); //$NON-NLS-1$
  public static final IMultipleEffectCharm TERRIFYING_ELEMENT_DRAGON_ROAR = new ElementalMultipleEffectCharm(
      "Dragon-Blooded.TerrifyingElementDragonRoar"); //$NON-NLS-1$
  public static final IMultipleEffectCharm ELEMENT_SHELTER_CREATION_TECHNIQUE = new ElementalMultipleEffectCharm(
      "Dragon-Blooded.ElementShelterCreationTechnique"); //$NON-NLS-1$
  public static final IMultipleEffectCharm EXTENSION_ELEMENT_DRAGONS_BLESSING = new ElementalMultipleEffectCharm(
      "Dragon-Blooded.ExtensionElementDragon'sBlessing"); //$NON-NLS-1$
}