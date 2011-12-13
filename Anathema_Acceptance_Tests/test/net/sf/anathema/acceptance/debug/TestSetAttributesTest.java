package net.sf.anathema.acceptance.debug;

import net.sf.anathema.acceptance.fixture.character.CreateCharacterFixture;
import net.sf.anathema.acceptance.fixture.character.SetupCharacterPlatformFixture;
import net.sf.anathema.acceptance.fixture.character.costs.CheckBonusPointsFixture;
import net.sf.anathema.acceptance.fixture.character.miscellaneous.SetConceptFixture;
import net.sf.anathema.acceptance.fixture.character.traits.SetAttributesFixture;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TestSetAttributesTest extends BasicTestCase {

  public void testIt() throws Exception {
    SetupCharacterPlatformFixture platformFixture = new SetupCharacterPlatformFixture();
    platformFixture.doTable(null);

    CreateCharacterFixture createCharacterFixture = new CreateCharacterFixture();
    createCharacterFixture.summary = platformFixture.summary;
    createCharacterFixture.characterType = "Lunar"; //$NON-NLS-1$
    createCharacterFixture.enterRow();

    SetConceptFixture setConceptFixture = new SetConceptFixture();
    setConceptFixture.summary = createCharacterFixture.summary;
    setConceptFixture.caste = "NoMoon"; //$NON-NLS-1$
    setConceptFixture.enterRow();

    SetAttributesFixture attributeFixtures = new SetAttributesFixture();
    attributeFixtures.summary = setConceptFixture.summary;
    attributeFixtures.traitType = "Strength"; //$NON-NLS-1$
    attributeFixtures.value = 5;
    attributeFixtures.enterRow();
    attributeFixtures.traitType = "Dexterity"; //$NON-NLS-1$
    attributeFixtures.value = 5;
    attributeFixtures.enterRow();
    attributeFixtures.traitType = "Stamina"; //$NON-NLS-1$
    attributeFixtures.value = 3;
    attributeFixtures.enterRow();

    attributeFixtures.traitType = "Wits"; //$NON-NLS-1$
    attributeFixtures.value = 5;
    attributeFixtures.enterRow();
    attributeFixtures.traitType = "Intelligence"; //$NON-NLS-1$
    attributeFixtures.value = 5;
    attributeFixtures.enterRow();

    CheckBonusPointsFixture checkBonusPointsFixture = new CheckBonusPointsFixture();
    checkBonusPointsFixture.summary = setConceptFixture.summary;
    assertEquals(7, checkBonusPointsFixture.regularSpent());
  }
}