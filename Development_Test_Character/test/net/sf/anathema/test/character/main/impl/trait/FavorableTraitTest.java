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

  private IIncrementChecker incrementChecker = EasyMock.createStrictMock(IIncrementChecker.class);
  private IFavorableStateChangedListener abilityStateListener = EasyMock.createStrictMock(IFavorableStateChangedListener.class);
  private ProxyTraitValueStrategy valueStrategy;
  private DefaultTrait trait;
  private DummyCharacterModelContext modelContext;

  @Before
  public void createTrait() throws Exception {
    this.valueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
    this.modelContext = createModelContextWithEssence2(valueStrategy);
    this.trait = createObjectUnderTest(modelContext);
  }

  @Test
  public void testSetAbilityToFavored() throws Exception {
    allowOneFavoredIncrement();
    trait.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    assertEquals(0, trait.getCreationValue());
    abilityStateListener.favorableStateChanged(FavorableState.Favored);
    EasyMock.replay(abilityStateListener);
    trait.getFavorization().setFavorableState(FavorableState.Favored);
    EasyMock.verify(abilityStateListener);
    assertEquals(1, trait.getCreationValue());
  }

  private void allowOneFavoredIncrement() {
    EasyMock.expect(incrementChecker.isValidIncrement(1)).andReturn(true);
    EasyMock.replay(incrementChecker);
  }

  @Test
  public void testSetAbiltyToFavoredUnallowed() throws Exception {
    EasyMock.expect(incrementChecker.isValidIncrement(1)).andReturn(false);
    EasyMock.replay(incrementChecker);
    trait.getFavorization().setFavorableState(FavorableState.Favored);
    EasyMock.verify(incrementChecker);
    assertSame(FavorableState.Default, trait.getFavorization().getFavorableState());
    assertEquals(0, trait.getCreationValue());
  }

  @Test
  public void testSetFavoredAbiltyCreationValueBelow1() throws Exception {
    allowOneFavoredIncrement();
    trait.getFavorization().setFavorableState(FavorableState.Favored);
    assertTrue(trait.getFavorization().isFavored());
    trait.setCurrentValue(0);
    assertEquals(1, trait.getCreationValue());
  }

  @Test
  public void testCasteAbilityNotSetToFavored() throws Exception {
    trait.getFavorization().setFavorableState(FavorableState.Caste);
    EasyMock.replay(abilityStateListener);
    trait.getFavorization().addFavorableStateChangedListener(abilityStateListener);
    trait.getFavorization().setFavorableState(FavorableState.Favored);
    assertSame(FavorableState.Caste, trait.getFavorization().getFavorableState());
    EasyMock.verify(abilityStateListener);
  }

  @Test
  public void testExceedCreationValueMaximum() throws Exception {
    trait.setCurrentValue(6);
    assertEquals(5, trait.getCreationValue());
  }

  @Test
  public void testUnderrunCreationValueMinimum() throws Exception {
    trait.setCurrentValue(-1);
    assertEquals(0, trait.getCreationValue());
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
    trait.setCurrentValue(2);
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    trait.setCurrentValue(3);
    final int[] holder = new int[1];
    trait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        holder[0] = newValue;
      }
    });
    trait.setCurrentValue(0);
    assertEquals(2, holder[0]);
    assertEquals(ITraitRules.UNEXPERIENCED, trait.getExperiencedValue());
  }

  @Test
  public void testSetValueTo6OnExperiencedCharacterWithoutHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 2));
    trait.setCurrentValue(6);
    assertEquals(5, trait.getCurrentValue());
  }

  @Test
  public void testSetValueTo6OnExperiencedCharacterWithHighEssence() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    modelContext.getCharacter().addTrait(new DummyGenericTrait(OtherTraitType.Essence, 6));
    trait.setCurrentValue(6);
    assertEquals(6, trait.getCurrentValue());
  }

  @Test
  public void testExperienceSpecialtyCount() throws Exception {
    ISubTraitContainer container = new SpecialtiesContainer(
            new DefaultTraitReference(trait),
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
    ISubTraitContainer container = new SpecialtiesContainer(new DefaultTraitReference(trait), context.getTraitContext());
    ISubTrait specialty = container.addSubTrait("TestSpecialty"); //$NON-NLS-1$
    specialty.setCreationValue(2);
    assertEquals(2, specialty.getCreationValue());
    assertEquals(-1, specialty.getExperiencedValue());
    assertEquals(2, specialty.getCurrentValue());
    assertEquals(2, container.getCreationDotTotal());
    assertEquals(0, container.getExperienceDotTotal());
  }
}