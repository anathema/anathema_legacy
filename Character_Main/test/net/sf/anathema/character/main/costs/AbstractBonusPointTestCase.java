package net.sf.anathema.character.main.costs;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.traits.EssenceTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.template.DummyTraitTemplateFactory;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.SimpleIdentifier;

public abstract class AbstractBonusPointTestCase {

  protected static void addAbilityAndEssence(TraitModel traitModel, DummyHero hero) {
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(hero, new EssenceTemplateFactory(new DummyTraitTemplateFactory()));
    traitModel.addTraits(traitFactory.createTrait(OtherTraitType.Essence));
    FavorableTraitFactory favorableTraitFactory = new FavorableTraitFactory(hero);
    IncrementChecker friendlyIncrementChecker = new FriendlyIncrementChecker();
    for (final AbilityType traitType : AbilityType.values()) {
      IIdentifiedCasteTraitTypeGroup typeGroup =
              new IdentifiedCasteTraitTypeGroup(new TraitType[]{traitType}, new SimpleIdentifier("Test"), new MultiEntryMap<TraitType, CasteType>());
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