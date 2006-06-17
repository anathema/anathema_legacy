package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ProxyTraitValueStrategy;
import net.sf.anathema.character.library.trait.FavorableTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.specialty.ISpecialty;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.test.character.dummy.DummyCasteType;
import net.sf.anathema.test.character.dummy.DummyCharacterModelContext;
import net.sf.anathema.test.character.dummy.DummyGenericTrait;

import org.easymock.MockControl;

public class FavorableTraitTest extends AbstractTraitTest {

  private MockControl incrementCheckerControl;
  private IIncrementChecker incrementChecker;
  private MockControl abilityStateListenerControl;
  private IFavorableStateChangedListener abilityStateListener;
  private ProxyTraitValueStrategy valueStrategy;
  private FavorableTrait first;
  private DummyCharacterModelContext modelContext;

  @Override
  protected void setUp() throws Exception {
    this.valueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
    super.setUp();
    this.incrementCheckerControl = MockControl.createStrictControl(IIncrementChecker.class);
    this.incrementChecker = (IIncrementChecker) incrementCheckerControl.getMock();
    this.modelContext = createModelContextWithEssence2(valueStrategy);
    first = createObjectUnderTest(modelContext);
    this.abilityStateListenerControl = MockControl.createStrictControl(IFavorableStateChangedListener.class);
    this.abilityStateListener = (IFavorableStateChangedListener) abilityStateListenerControl.getMock();
  }

  public void testSetAbilityToFavored() throws Exception {
    allowOneFavoredIncrement();
    first.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    assertEquals(0, first.getCreationValue());
    abilityStateListener.favorableStateChanged(FavorableState.Favored);
    abilityStateListenerControl.replay();
    first.getFavorization().setFavorableState(FavorableState.Favored);
    abilityStateListenerControl.verify();
    assertEquals(1, first.getCreationValue());
  }

  private void allowOneFavoredIncrement() {
    incrementChecker.isValidIncrement(1);
    incrementCheckerControl.setDefaultReturnValue(true);
    incrementCheckerControl.replay();
  }

  public void testSetAbiltyToFavoredUnallowed() throws Exception {
    incrementChecker.isValidIncrement(1);
    incrementCheckerControl.setReturnValue(false);
    incrementCheckerControl.replay();
    first.getFavorization().setFavorableState(FavorableState.Favored);
    incrementCheckerControl.verify();
    assertSame(FavorableState.Default, first.getFavorization().getFavorableState());
    assertEquals(0, first.getCreationValue());
  }

  public void testSetFavoredAbiltyCreationValueBelow1() throws Exception {
    allowOneFavoredIncrement();
    first.getFavorization().setFavorableState(FavorableState.Favored);
    assertTrue(first.getFavorization().isFavored());
    first.setCurrentValue(0);
    assertEquals(1, first.getCreationValue());
  }

  public void testCasteAbilityNotSetToFavored() throws Exception {
    first.getFavorization().setFavorableState(FavorableState.Caste);
    abilityStateListenerControl.replay();
    first.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    first.getFavorization().setFavorableState(FavorableState.Favored);
    assertSame(FavorableState.Caste, first.getFavorization().getFavorableState());
    abilityStateListenerControl.verify();
  }

  @Override
  public void testExceedCreationValueMaximum() throws Exception {
    first.setCurrentValue(6);
    assertEquals(5, first.getCreationValue());
  }

  @Override
  public void testUnderrunCreationValueMinimum() throws Exception {
    first.setCurrentValue(-1);
    assertEquals(0, first.getCreationValue());
  }

  @Override
  protected FavorableTrait createObjectUnderTest() {
    ICharacterModelContext context = createModelContextWithEssence2(valueStrategy);
    return createObjectUnderTest(context);
  }

  private FavorableTrait createObjectUnderTest(ICharacterModelContext context) {
    ITraitTemplate archeryTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(0);
    ITraitContext traitContext = context.getTraitContext();
    FavorableTraitRules rules = new FavorableTraitRules(
        AbilityType.Archery,
        archeryTemplate,
        traitContext.getLimitationContext());
    return new FavorableTrait(
        rules,
        new DummyCasteType(),
        traitContext.getTraitValueStrategy(),
        context.getBasicCharacterContext(),
        context.getCharacterListening(),
        new FriendlyValueChangeChecker(),
        incrementChecker);
  }

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

  public void testSetValueTo6OnExperiencedCharacterWithoutHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    first.setCurrentValue(6);
    assertEquals(5, first.getCurrentValue());
  }

  public void testSetValueTo6OnExperiencedCharacterWithHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 6));
    first.setCurrentValue(6);
    assertEquals(6, first.getCurrentValue());
  }

  public void testExperienceSpecialtyCount() throws Exception {
    ISpecialty specialty = first.getSpecialtiesContainer().addSpecialty("TestSpecialty"); //$NON-NLS-1$
    specialty.setCreationValue(1);
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    specialty.setExperiencedValue(2);
    assertEquals(2, specialty.getCurrentValue());
    assertEquals(1, first.getSpecialtiesContainer().getCreationSpecialtyCount());
    assertEquals(1, first.getSpecialtiesContainer().getExperienceLearnedSpecialtyCount());
  }
}