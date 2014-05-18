package net.sf.anathema.hero.advance;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.traits.EssenceTemplateFactory;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.main.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.main.traits.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.character.main.traits.lists.IdentifiedCasteTraitTypeList;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.template.DummyTraitTemplateFactory;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.SimpleIdentifier;

public abstract class AbstractBonusPointTestCase {

  protected static void addAbilityAndEssence(TraitModel traitModel, DummyHero hero) {
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(hero, new EssenceTemplateFactory(new DummyTraitTemplateFactory()));
    traitModel.addTraits(traitFactory.createTrait(OtherTraitType.Essence));
    FavorableTraitFactory favorableTraitFactory = new FavorableTraitFactory(hero);
    IncrementChecker friendlyIncrementChecker = new FriendlyIncrementChecker();
    for (final AbilityType traitType : AbilityType.values()) {
      IIdentifiedCasteTraitTypeList typeGroup =
              new IdentifiedCasteTraitTypeList(new TraitType[]{traitType}, new SimpleIdentifier("Test"), new MultiEntryMap<TraitType, CasteType>());
      Trait trait = favorableTraitFactory.createTraits(typeGroup, friendlyIncrementChecker, new DummyTypedTraitTemplateFactory(traitType))[0];
      traitModel.addTraits(trait);
    }
  }

  private static class DummyTypedTraitTemplateFactory implements TypedTraitTemplateFactory {
    private final AbilityType traitType;

    public DummyTypedTraitTemplateFactory(AbilityType traitType) {
      this.traitType = traitType;
    }

    @Override
    public ITraitTemplate create(TraitType type) {
      return new DummyTraitTemplateFactory().createAbilityTemplate(traitType);
    }
  }
}