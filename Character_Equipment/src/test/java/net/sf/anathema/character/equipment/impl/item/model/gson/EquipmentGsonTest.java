package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.character.equipment.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.character.model.stats.MeleeWeaponStats;
import net.sf.anathema.character.equipment.character.model.stats.RangedWeaponStats;
import net.sf.anathema.character.equipment.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.equipment.item.model.gson.EquipmentGson;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import org.junit.Test;

import static net.sf.anathema.equipment.core.MagicalMaterial.Orichalcum;
import static net.sf.anathema.equipment.core.MaterialComposition.Fixed;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class EquipmentGsonTest {

  private EquipmentTemplate originalTemplate = new EquipmentTemplate("First Test", "The test used to shape Creation", Fixed, Orichalcum, null);
  private EquipmentGson gson = new EquipmentGson();

  @Test
  public void mentionsTypeInMeleeWeaponStats() throws Exception {
    MeleeWeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    assertThat(json.contains("Melee Weapon"), is(true));
  }

  @Test
  public void deserializesEmptyDescriptionToNull() throws Exception {
    EquipmentTemplate template = new EquipmentTemplate("Test", null, Fixed, Orichalcum, null);
    String json = gson.toJson(template);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(readTemplate.getDescription().isEmpty(), is(true));
  }

  @Test
  public void deserializesTemplateWithoutMaterial() throws Exception {
    EquipmentTemplate template = new EquipmentTemplate("Test", null, Fixed, null, null);
    String json = gson.toJson(template);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(readTemplate.getMaterial(), is(nullValue()));
  }

  @Test
  public void roundTripForMeleeWeapon() throws Exception {
    MeleeWeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInRangedWeaponStats() throws Exception {
    RangedWeaponStats rangedWeapon = GsonStatMother.createRangedWeapon();
    String json = serializeWithStats(rangedWeapon);
    assertThat(json.contains("Ranged Weapon"), is(true));
  }

  @Test
  public void roundTripForRangedWeapon() throws Exception {
    RangedWeaponStats rangedWeapon = GsonStatMother.createRangedWeapon();
    String json = serializeWithStats(rangedWeapon);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInArtifactStats() throws Exception {
    ArtifactStats artifact = GsonStatMother.createArtifact();
    String json = serializeWithStats(artifact);
    assertThat(json.contains("Artifact"), is(true));
  }

  @Test
  public void roundTripForArtifact() throws Exception {
    ArtifactStats artifact = GsonStatMother.createArtifact();
    String json = serializeWithStats(artifact);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInArmourStats() throws Exception {
    ArmourStats armour = GsonStatMother.createArmour();
    String json = serializeWithStats(armour);
    assertThat(json.contains("Armour"), is(true));
  }

  @Test
  public void roundTripForArmour() throws Exception {
    ArmourStats armour = GsonStatMother.createArmour();
    String json = serializeWithStats(armour);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  @Test
  public void mentionsTypeInTraitModifier() throws Exception {
    TraitModifyingStats modifier = GsonStatMother.createTraitModifier();
    String json = serializeWithStats(modifier);
    assertThat(json.contains("Trait Modifier"), is(true));
  }

  @Test
  public void roundTripForTraitModifier() throws Exception {
    TraitModifyingStats modifier = GsonStatMother.createTraitModifier();
    String json = serializeWithStats(modifier);
    IEquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  private IEquipmentTemplate deserialize(String json) {
    return gson.fromJson(json);
  }

  private String serializeWithStats(IEquipmentStats rangedWepaon) {
    originalTemplate.addStats(rangedWepaon);
    return gson.toJson(originalTemplate);
  }
}