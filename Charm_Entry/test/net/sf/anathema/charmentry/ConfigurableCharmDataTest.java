package net.sf.anathema.charmentry;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.charmentry.model.data.ConfigurableCharmData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurableCharmDataTest {

	private ConfigurableCharmData data;

	@Before
	public void setUp() throws Exception {
		data = new ConfigurableCharmData();
	}

	@Test
	public void testChangePrerequisite() throws Exception {
		IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
		IGenericTrait expected = new ValuedTraitType(AbilityType.Athletics, 2);
		data.addPrerequisite(unwanted);
		data.addPrerequisite(expected);
		assertEquals(1, data.getPrerequisites().length);
		assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
		assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
	}

	@Test
	public void testChangePrimaryPrerequisiteType() throws Exception {
		IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
		IGenericTrait expected = new ValuedTraitType(AbilityType.Awareness, 4);
		data.setPrimaryPrerequisite(unwanted);
		data.setPrimaryPrerequisite(expected);
		assertEquals(1, data.getPrerequisites().length);
		assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
		assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
		assertEquals(expected.getType(), data.getPrimaryTraitType());
	}

	@Test
	public void testRemovePrerequisite() throws Exception {
		IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
		data.addPrerequisite(unwanted);
		data.removePrerequisite(unwanted);
		assertEquals(0, data.getPrerequisites().length);
		assertFalse(ArrayUtilities.contains(data.getPrerequisites(), unwanted));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCannotRemovePrimaryPrerequisite() throws Exception {
		IGenericTrait expected = new ValuedTraitType(AbilityType.Awareness, 4);
		data.setPrimaryPrerequisite(expected);
		data.removePrerequisite(expected);
		assertEquals(1, data.getPrerequisites().length);
		assertTrue(ArrayUtilities.contains(data.getPrerequisites(), expected));
	}

	@Test
	public void testClearPrimaryPrerequisite() throws Exception {
		IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
		data.setPrimaryPrerequisite(unwanted);
		data.clearPrimaryPrerequisite();
		assertEquals(0, data.getPrerequisites().length);
		assertNull(data.getPrimaryTraitType());
	}

	@Test
	public void testClearPrimaryBySettingToNull() throws Exception {
		IGenericTrait unwanted = new ValuedTraitType(AbilityType.Athletics, 4);
		data.setPrimaryPrerequisite(unwanted);
		IGenericTrait expected = new ValuedTraitType(null, 1);
		data.setPrimaryPrerequisite(expected);
		assertEquals(0, data.getPrerequisites().length);
		assertNull(data.getPrimaryTraitType());
	}

	@Test
	public void testPrimaryPrerequisteComesFirst() throws Exception {
		IGenericTrait prerequiste = new ValuedTraitType(AbilityType.Athletics,
				4);
		data.addPrerequisite(prerequiste);
		IGenericTrait primary = new ValuedTraitType(AbilityType.Awareness, 4);
		data.setPrimaryPrerequisite(primary);
		assertEquals(2, data.getPrerequisites().length);
		assertEquals(primary, data.getPrerequisites()[0]);
	}
}