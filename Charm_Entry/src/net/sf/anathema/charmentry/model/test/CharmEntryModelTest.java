package net.sf.anathema.charmentry.model.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.presenter.ITraitSelectionChangedListener;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmEntryModelTest extends BasicTestCase {

  private CharmEntryModel model;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    model = new CharmEntryModel();
  }

  public void testSetCharmName() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    String name = "name"; //$NON-NLS-1$
    model.setCharmName(name);
    assertEquals("Solar.name", model.getCharmData().getId()); //$NON-NLS-1$
  }

  public void testSetCharmNameWithBlanks() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    String name = "name with blanks"; //$NON-NLS-1$
    model.setCharmName(name);
    assertEquals("Solar.namewithblanks", model.getCharmData().getId()); //$NON-NLS-1$
    assertEquals(name, model.getCharmData().getName());
  }

  public void testSetCharmNameBeforeCharacterType() throws Exception {
    String name = "name with blanks"; //$NON-NLS-1$
    model.setCharmName(name);
    model.setCharacterType(CharacterType.SOLAR);
    assertEquals("Solar.namewithblanks", model.getCharmData().getId()); //$NON-NLS-1$
  }

  public void testClearCharmData() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    model.clearCharmData();
    assertEquals(null, model.getCharmData().getCharacterType());
  }

  public void testSetCharacterType() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    assertEquals(CharacterType.SOLAR, model.getCharmData().getCharacterType());
  }

  public void testGetLegalCharacterTypes() throws Exception {
    final CharacterType[] types = model.getLegalCharacterTypes();
    for (CharacterType type : CharacterType.values()) {
      type.accept(new ICharacterTypeVisitor() {
        public void visitSolar(CharacterType visitedType) {
          assertTrue(ArrayUtilities.contains(types, visitedType));
        }

        public void visitMortal(CharacterType visitedType) {
          assertFalse(ArrayUtilities.contains(types, visitedType));
        }

        public void visitSidereal(CharacterType visitedType) {
          assertFalse(ArrayUtilities.contains(types, visitedType));
        }

        public void visitDB(CharacterType visitedType) {
          assertTrue(ArrayUtilities.contains(types, visitedType));

        }

        public void visitAbyssal(CharacterType visitedType) {
          assertTrue(ArrayUtilities.contains(types, visitedType));
        }

        public void visitLunar(CharacterType visitedType) {
          assertTrue(ArrayUtilities.contains(types, visitedType));
        }

        public void visitDragonKing(CharacterType visitedType) {
          assertFalse(ArrayUtilities.contains(types, visitedType));
        }
      });
    }
  }

  public void testSetIllegalCharacterType() throws Exception {
    model.setCharacterType(CharacterType.SIDEREAL);
    assertNull(model.getCharmData().getCharacterType());
  }

  public void testKeepLegalCharacterType() throws Exception {
    model.setCharacterType(CharacterType.ABYSSAL);
    model.setCharacterType(CharacterType.SIDEREAL);
    assertEquals(CharacterType.ABYSSAL, model.getCharmData().getCharacterType());

  }

  public void testSetCharmType() throws Exception {
    model.setCharmType(CharmType.ExtraAction);
    assertEquals(CharmType.ExtraAction, model.getCharmData().getCharmType());
  }

  public void testSetDuration() throws Exception {
    Duration expected = Duration.getDuration("Any Duration"); //$NON-NLS-1$
    model.setDuration(expected);
    assertEquals(expected, model.getCharmData().getDuration());
  }

  public void testSetPrerequisite() throws Exception {
    IGenericTrait expected = new ValuedTraitType(AbilityType.Athletics, 4);
    model.setPrerequisite(expected);
    assertTrue(ArrayUtilities.contains(model.getCharmData().getPrerequisites(), expected));
  }

  public void testSetNullPrerequisite() throws Exception {
    IGenericTrait unexpected = new ValuedTraitType(null, 4);
    model.setPrerequisite(unexpected);
    assertEquals(0, model.getCharmData().getPrerequisites().length);
  }

  public void testSetEssencePrerequisite() throws Exception {
    IGenericTrait expected = new ValuedTraitType(OtherTraitType.Essence, 3);
    model.setPrerequisite(expected);
    assertEquals(expected, model.getCharmData().getEssence());
  }

  public void testSetNullPrimaryPrerequisite() throws Exception {
    IGenericTrait unexpected = new ValuedTraitType(null, 4);
    model.setPrimaryPrerequisite(unexpected);
    assertEquals(0, model.getCharmData().getPrerequisites().length);
  }

  public void testSetPrimaryPrerequisiteSendsEvent() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addPrimaryPrerequisiteListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object object, int value) {
        eventReceived[0] = true;
      }
    });
    IGenericTrait prerequisite = new ValuedTraitType(AbilityType.Athletics, 3);
    model.setPrimaryPrerequisite(prerequisite);
    assertTrue(eventReceived[0]);
  }

  public void testSetPrimaryPrerequisiteSendsNoEventIfUnchanged() throws Exception {
    model.setPrimaryPrerequisite(new ValuedTraitType(AbilityType.Athletics, 3));
    final boolean[] eventReceived = new boolean[] { false };
    model.addPrimaryPrerequisiteListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object object, int value) {
        eventReceived[0] = true;
      }
    });
    model.setPrimaryPrerequisite(new ValuedTraitType(AbilityType.Athletics, 3));
    assertFalse(eventReceived[0]);
  }

  public void testAnyPrerequisiteAcceptedWhileCharacterTypeIsNull() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addPrimaryPrerequisiteListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object object, int value) {
        eventReceived[0] = true;
      }
    });
    IGenericTrait prerequisite = new ValuedTraitType(AttributeType.Appearance, 3);
    model.setPrimaryPrerequisite(prerequisite);
    assertTrue(eventReceived[0]);
  }

  public void testSetCharacterTypeChecksPrimaryPrerequisite() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(AbilityType.Athletics, 3);
    model.setPrimaryPrerequisite(prerequisite);
    model.addPrimaryPrerequisiteListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object object, int value) {
        assertNull(object);
        assertEquals(1, value);
      }
    });
    model.setCharacterType(CharacterType.LUNAR);
  }

  public void testGetLegalPrimaryPrerequisitesForAbilityBasedExalt() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    assertEquals(AbilityType.values(), model.getLegalPrimaryPrerequisiteTypes());
  }

  public void testGetLegalPrimaryPrerequisitesForAttributeBasedExalt() throws Exception {
    model.setCharacterType(CharacterType.LUNAR);
    assertEquals(AbilityType.getAbilityTypes(ExaltedRuleSet.CoreRules), model.getLegalPrimaryPrerequisiteTypes());
  }

  public void testSetPrimaryPrerequisiteSetsGroupId() throws Exception {
    model.setCharacterType(CharacterType.SOLAR);
    IGenericTrait prerequisite = new ValuedTraitType(AbilityType.Athletics, 3);
    model.setPrimaryPrerequisite(prerequisite);
    assertTrue(ArrayUtilities.contains(model.getCharmData().getPrerequisites(), prerequisite));
    assertEquals("Athletics", model.getCharmData().getGroupId()); //$NON-NLS-1$
  }

  public void testPrimaryPrerequisiteMustBeAbility() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(AttributeType.Charisma, 3);
    model.setCharacterType(CharacterType.SOLAR);
    model.setPrimaryPrerequisite(prerequisite);
    assertEquals(0, model.getCharmData().getPrerequisites().length);
  }

  public void testSetLunarPrerequisite() {
    model.setCharacterType(CharacterType.LUNAR);
    IGenericTrait prerequisite = new ValuedTraitType(AttributeType.Wits, 3);
    model.setPrimaryPrerequisite(prerequisite);
    assertTrue(ArrayUtilities.contains(model.getCharmData().getPrerequisites(), prerequisite));
    assertTrue(StringUtilities.isNullOrEmpty(model.getCharmData().getGroupId()));
  }

  public void testLunarPrerequisiteMustBeAttribute() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(AttributeType.Charisma, 3);
    model.setCharacterType(CharacterType.SOLAR);
    model.setPrimaryPrerequisite(prerequisite);
    assertEquals(0, model.getCharmData().getPrerequisites().length);
  }

  public void testCannotRemoveEssencePrerequisite() throws Exception {
    assertThrowsException(IllegalArgumentException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        IGenericTrait prerequisite = new ValuedTraitType(OtherTraitType.Essence, 3);
        model.setPrerequisite(prerequisite);
        model.removePrerequisite(prerequisite);
      }
    });
  }

  public void testCannotRemovePrimaryPrerequisite() throws Exception {
    assertThrowsException(IllegalArgumentException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        IGenericTrait prerequisite = new ValuedTraitType(AbilityType.Athletics, 3);
        model.setCharacterType(CharacterType.SOLAR);
        model.setPrimaryPrerequisite(prerequisite);
        model.removePrerequisite(prerequisite);
      }
    });
    assertThrowsException(IllegalArgumentException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        IGenericTrait prerequisite = new ValuedTraitType(AttributeType.Intelligence, 3);
        model.setCharacterType(CharacterType.LUNAR);
        model.setPrimaryPrerequisite(prerequisite);
        model.removePrerequisite(prerequisite);
      }
    });
  }

  public void testNoParentCharms() throws Exception {
    assertEquals(0, model.getCharmData().getParentCharms().size());
  }

  public void testSetParentCharms() throws Exception {
    DummyCharm expected = new DummyCharm("Dummy"); //$NON-NLS-1$
    model.setPrerequisiteCharms(new ICharm[] { expected });
    assertTrue(model.getCharmData().getParentCharms().contains(expected));
  }

  public void testRemoveParentCharms() throws Exception {
    DummyCharm unwanted = new DummyCharm("Dummy"); //$NON-NLS-1$
    model.setPrerequisiteCharms(new ICharm[] { unwanted });
    model.setPrerequisiteCharms(new ICharm[0]);
    assertEquals(0, model.getCharmData().getParentCharms().size());
    assertFalse(model.getCharmData().getParentCharms().contains(unwanted));
  }

  public void testSetBookSource() throws Exception {
    String expectedBook = "Book"; //$NON-NLS-1$
    Integer expectedPage = 123;
    model.setSource(expectedBook, expectedPage);
    assertEquals(expectedBook, model.getCharmData().getSource().getSource());
    assertEquals(String.valueOf(expectedPage), model.getCharmData().getSource().getPage());
  }

  public void testSetCustomSource() throws Exception {
    model.setSource("Custom", null); //$NON-NLS-1$
    assertEquals("Custom", model.getCharmData().getSource().getSource()); //$NON-NLS-1$
    assertEquals(null, model.getCharmData().getSource().getPage());
  }

  public void testSetNullSource() throws Exception {
    model.setSource(null, null);
    assertEquals("Custom", model.getCharmData().getSource().getSource()); //$NON-NLS-1$
    assertEquals(null, model.getCharmData().getSource().getPage());
  }

  public void testSetEssenceCostValueAsString() throws Exception {
    model.getCharmData().getTemporaryCost().getEssenceCost().setText("Too Many"); //$NON-NLS-1$
    model.setEssenceCostValue("One"); //$NON-NLS-1$
    assertEquals("One", model.getCharmData().getTemporaryCost().getEssenceCost().getCost()); //$NON-NLS-1$
    assertEquals("Too Many", model.getCharmData().getTemporaryCost().getEssenceCost().getText()); //$NON-NLS-1$
  }
}