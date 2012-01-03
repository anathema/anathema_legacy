package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ProxyTraitValueStrategy;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.test.character.BasicCharacterTestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FavorableTraitTest extends BasicCharacterTestCase {

  private IIncrementChecker incrementChecker;
  private IFavorableStateChangedListener abilityStateListener;
  private ProxyTraitValueStrategy valueStrategy;
  private DefaultTrait first;
  private DummyCharacterModelContext modelContext;
  private DefaultTrait objectUnderTest;
  private IIntValueChangedListener intListener;

  @Before
  public void setUp() throws Exception {
    objectUnderTest = createObjectUnderTest();
    this.intListener = EasyMock.createStrictMock(IIntValueChangedListener.class);
    objectUnderTest.addCreationPointListener(intListener);
    this.valueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
    this.incrementChecker = EasyMock.createStrictMock(IIncrementChecker.class);
    this.modelContext = createModelContextWithEssence2(valueStrategy);
    first = createObjectUnderTest(modelContext);
    this.abilityStateListener = EasyMock.createStrictMock(IFavorableStateChangedListener.class);
  }

  protected DefaultTrait createObjectUnderTest() {
    ICharacterModelContext context = createModelContextWithEssence2(valueStrategy);
    return createObjectUnderTest(context);
  }

  @Test
  public void testSetAbilityToFavored() throws Exception {
    allowOneFavoredIncrement();
    first.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    assertEquals(0, first.getCreationValue());
    abilityStateListener.favorableStateChanged(FavorableState.Favored);
    EasyMock.replay(abilityStateListener);
    first.getFavorization().setFavorableState(FavorableState.Favored);
    EasyMock.verify(abilityStateListener);
    assertEquals(1, first.getCreationValue());
  }

  private void allowOneFavoredIncrement() {
    EasyMock.expect(incrementChecker.isValidIncrement(1)).andReturn(true);
    EasyMock.replay(incrementChecker);
  }

  @Test
  public void testSetAbiltyToFavoredUnallowed() throws Exception {
    EasyMock.expect(incrementChecker.isValidIncrement(1)).andReturn(false);
    EasyMock.replay(incrementChecker);
    first.getFavorization().setFavorableState(FavorableState.Favored);
    EasyMock.verify(incrementChecker);
    assertSame(FavorableState.Default, first.getFavorization().getFavorableState());
    assertEquals(0, first.getCreationValue());
  }

  @Test
  public void testSetFavoredAbiltyCreationValueBelow1() throws Exception {
    allowOneFavoredIncrement();
    first.getFavorization().setFavorableState(FavorableState.Favored);
    assertTrue(first.getFavorization().isFavored());
    first.setCurrentValue(0);
    assertEquals(1, first.getCreationValue());
  }

  @Test
  public void testCasteAbilityNotSetToFavored() throws Exception {
    first.getFavorization().setFavorableState(FavorableState.Caste);
    EasyMock.replay(abilityStateListener);
    first.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    first.getFavorization().setFavorableState(FavorableState.Favored);
    assertSame(FavorableState.Caste, first.getFavorization().getFavorableState());
    EasyMock.verify(abilityStateListener);
  }

  @Test
  public void testExceedCreationValueMaximum() throws Exception {
    first.setCurrentValue(6);
    assertEquals(5, first.getCreationValue());
  }

  @Test
  public void testUnderrunCreationValueMinimum() throws Exception {
    first.setCurrentValue(-1);
    assertEquals(0, first.getCreationValue());
  }

  private DefaultTrait createObjectUnderTest(ICharacterModelContext context) {
    ITraitTemplate archeryTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(0);
    ITraitContext traitContext = context.getTraitContext();
    FavorableTraitRules rules = new FavorableTraitRules(
            AbilityType.Archery,
            archeryTemplate,
            traitContext.getLimitationContext());
    return new DefaultTrait(
            rules,
            new ICasteType[]{new DummyCasteType()},
            traitContext,
            context.getBasicCharacterContext(),
            context.getCharacterListening(),
            new FriendlyValueChangeChecker(),
            incrementChecker);
  }

  @Test
  public void testSetExperiencedToCreationValue() throws Exception {
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    first.setCurrentValue(2);
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    first.setCurrentValue(3);
    final int[] holder = new int[1];
    first.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        holder[0] = newValue;
      }
    });
    first.setCurrentValue(0);
    assertEquals(2, holder[0]);
    assertEquals(ITraitRules.UNEXPERIENCED, first.getExperiencedValue());
  }

  @Test
  public void testSetValueTo6OnExperiencedCharacterWithoutHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    first.setCurrentValue(6);
    assertEquals(5, first.getCurrentValue());
  }

  @Test
  public void testSetValueTo6OnExperiencedCharacterWithHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 6));
    first.setCurrentValue(6);
    assertEquals(6, first.getCurrentValue());
  }

  // TODO Test for the SpecialtyContainer
  @Test
  public void testExperienceSpecialtyCount() throws Exception {
    ISubTraitContainer container = new SpecialtiesContainer(
            new DefaultTraitReference(first),
            modelContext.getTraitContext());
    ISubTrait specialty = container.addSubTrait("TestSpecialty"); //$NON-NLS-1$
    specialty.setCreationValue(1);
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    specialty.setExperiencedValue(2);
    assertEquals(2, specialty.getCurrentValue());
    assertEquals(1, container.getCreationDotTotal());
    assertEquals(1, container.getExperienceDotTotal());
  }

  @Test
  public void testCreationSpecialtyDuringExperienced() throws Exception {
    ICharacterModelContext context = createModelContextWithEssence2(new ExperiencedTraitValueStrategy());
    ISubTraitContainer container = new SpecialtiesContainer(new DefaultTraitReference(first), context.getTraitContext());
    ISubTrait specialty = container.addSubTrait("TestSpecialty"); //$NON-NLS-1$
    specialty.setCreationValue(2);
    assertEquals(2, specialty.getCreationValue());
    assertEquals(-1, specialty.getExperiencedValue());
    assertEquals(2, specialty.getCurrentValue());
    assertEquals(2, container.getCreationDotTotal());
    assertEquals(0, container.getExperienceDotTotal());
  }
}