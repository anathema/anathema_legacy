package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.abilities.AbilityGroupType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.lib.testing.BasicTestCase;

public class AbilityTraitTypeGroupFactoryTest extends BasicTestCase {

  private AbilityTypeGroupFactory factory;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.factory = new AbilityTypeGroupFactory();
  }

  public void testOneGroup() throws Exception {
    ICasteCollection casteCollection = new CasteCollection(new ICasteType[0]);
    IGroupedTraitType[] abilityTypes = new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, "War", null), //$NON-NLS-1$
        new GroupedTraitType(AbilityType.Melee, "War", null) //$NON-NLS-1$
    };
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(1, typeGroups.length);
    assertEquals(new ITraitType[] { AbilityType.Archery, AbilityType.Melee }, typeGroups[0].getAllGroupTypes());
    assertEquals(AbilityGroupType.War, typeGroups[0].getGroupId());
  }

  public void testDifferentGroups() throws Exception {
    ICasteCollection casteCollection = new CasteCollection(new ICasteType[0]);
    IGroupedTraitType[] abilityTypes = new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, "War", null), //$NON-NLS-1$
        new GroupedTraitType(AbilityType.Medicine, "Life", null) //$NON-NLS-1$
    };
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(2, typeGroups.length);
    assertEquals(new ITraitType[] { AbilityType.Archery }, typeGroups[0].getAllGroupTypes());
    assertEquals(new ITraitType[] { AbilityType.Medicine }, typeGroups[1].getAllGroupTypes());
    assertEquals(AbilityGroupType.War, typeGroups[0].getGroupId());
    assertEquals(AbilityGroupType.Life, typeGroups[1].getGroupId());
  }

  public void testCasteAsGroupIdentificate() throws Exception {
    DummyCasteType caste = new DummyCasteType("Caste"); //$NON-NLS-1$
    ICasteCollection casteCollection = new CasteCollection(new ICasteType[] { caste });
    IGroupedTraitType[] abilityTypes = new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, caste.getId(), null),
        new GroupedTraitType(AbilityType.Melee, caste.getId(), null) };
    IIdentifiedTraitTypeGroup[] typeGroups = factory.createTraitGroups(casteCollection, abilityTypes);
    assertEquals(1, typeGroups.length);
    assertEquals(new ITraitType[] { AbilityType.Archery, AbilityType.Melee }, typeGroups[0].getAllGroupTypes());
    assertSame(caste, typeGroups[0].getGroupId());
  }
}