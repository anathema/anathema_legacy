package net.sf.anathema.hero.abilities;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.abilities.model.AbilityTypeGroupFactory;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.ConfigurableCasteCollection;
import net.sf.anathema.hero.concept.template.caste.CasteTemplate;
import net.sf.anathema.hero.dummy.models.NullCasteCollection;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static net.sf.anathema.character.main.template.abilities.AbilityGroupType.Life;
import static net.sf.anathema.character.main.template.abilities.AbilityGroupType.War;
import static net.sf.anathema.character.main.traits.types.AbilityType.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class AbilityTraitTypeGroupFactoryTest {

  private static final String TEST_CASTE_ID = "Caste";
  private AbilityTypeGroupFactory factory = new AbilityTypeGroupFactory();

  @Test
  public void testOneGroup() throws Exception {
    CasteCollection casteCollection = new NullCasteCollection();
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, "War", Collections.<String>emptyList()),
            new GroupedTraitType(Melee, "War", Collections.<String>emptyList())
    };
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(1, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery, Melee));
    assertEquals(War, typeGroups[0].getGroupId());
  }

  @Test
  public void testDifferentGroups() throws Exception {
    CasteCollection casteCollection = new NullCasteCollection();
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, "War", Collections.<String>emptyList()),
            new GroupedTraitType(Medicine, "Life", Collections.<String>emptyList())
    };
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(2, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery));
    assertEquals(War, typeGroups[0].getGroupId());
    assertThat(typeGroups[1].getAll(), contains(Medicine));
    assertEquals(Life, typeGroups[1].getGroupId());
  }

  @Test
  public void usesCasteAsGroupIdentificate() throws Exception {
    CasteTemplate template = new CasteTemplate();
    template.castes.add(TEST_CASTE_ID);
    CasteCollection casteCollection = new ConfigurableCasteCollection(template);
    GroupedTraitType[] abilityTypes = new GroupedTraitType[]{
            new GroupedTraitType(Archery, TEST_CASTE_ID, Collections.singletonList(TEST_CASTE_ID)),
            new GroupedTraitType(Melee, TEST_CASTE_ID, Collections.singletonList(TEST_CASTE_ID))};
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(1, typeGroups.length);
    assertThat(typeGroups[0].getAll(), contains(Archery, Melee));
    assertSame(casteCollection.getById(TEST_CASTE_ID), typeGroups[0].getGroupId());
  }
}