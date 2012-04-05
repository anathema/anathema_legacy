package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.stats.*;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.equipment.MagicalMaterial.Orichalcum;
import static net.sf.anathema.character.equipment.MaterialComposition.Fixed;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Gson_SerializationTest {

  private GsonCollectionFactory collectionFactory = new GsonCollectionFactory();
  private EquipmentTemplate originalTemplate = new EquipmentTemplate("First Test", "The test used to shape Creation", Fixed, Orichalcum, collectionFactory);
  private Gson gson;

  @Before
  public void registerTypeAdapters() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(IEquipmentStats.class, new StatsAdapter());
    gsonBuilder.registerTypeAdapter(ICollectionFactory.class, new CollectionFactoryFactory());
    gsonBuilder.registerTypeAdapter(IIdentificate.class, new IdentificateAdapter());
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  @Test
  public void mentionsTypeInMeleeWeaponStats() throws Exception {
    MeleeWeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    assertThat(json.contains("Melee Weapon"), is(true));
  }

  @Test
  public void roundTripForMeleeWeapon() throws Exception {
    MeleeWeaponStats meleeWeapon = GsonStatMother.createMeleeWeapon();
    String json = serializeWithStats(meleeWeapon);
    EquipmentTemplate readTemplate = deserialize(json);
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
    EquipmentTemplate readTemplate = deserialize(json);
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
    EquipmentTemplate readTemplate = deserialize(json);
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
    EquipmentTemplate readTemplate = deserialize(json);
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
    EquipmentTemplate readTemplate = deserialize(json);
    assertThat(gson.toJson(readTemplate), is(json));
  }

  private EquipmentTemplate deserialize(String json) {
    return gson.fromJson(json, EquipmentTemplate.class);
  }

  private String serializeWithStats(IEquipmentStats rangedWepaon) {
    originalTemplate.addStats(rangedWepaon);
    return gson.toJson(originalTemplate);
  }
}