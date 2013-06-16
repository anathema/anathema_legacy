package net.sf.anathema.character.impl.costs;

import net.sf.anathema.character.dummy.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.character.dummy.trait.DummyTraitContext;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.traits.DummyTraitTemplateFactory;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.traits.EssenceTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.SimpleIdentifier;

public abstract class AbstractBonusPointTestCase {

  protected static void addAbilityAndEssence(DummyCoreTraitConfiguration coreTraits) {
    NullAdditionalRules additionalRules = new NullAdditionalRules();
    DummyTraitContext traitContext = new DummyTraitContext(coreTraits);
    DefaultTraitFactory traitFactory =
            new DefaultTraitFactory(traitContext, additionalRules, new EssenceTemplateFactory(new DummyTraitTemplateFactory()));
    coreTraits.addTestTrait(traitFactory.createTrait(OtherTraitType.Essence));
    FavorableTraitFactory favorableTraitFactory = new FavorableTraitFactory(traitContext, new NullAdditionalRules(), new IBasicCharacterData() {
      @Override
      public DummyCasteType getCasteType() {
        return new DummyCasteType();
      }

      @Override
      public ICharacterType getCharacterType() {
        return getTemplateType().getCharacterType();
      }

      @Override
      public boolean isExperienced() {
        return false;
      }

      @Override
      public ITemplateType getTemplateType() {
        return new TemplateType(new DummyExaltCharacterType());
      }
    }, new ICharacterListening() {
      @Override
      public void addChangeListener(ICharacterChangeListener changeListener) {
        // Nothing to do
      }
    }
    );
    IIncrementChecker friendlyIncrementChecker = new FriendlyIncrementChecker();
    for (final AbilityType traitType : AbilityType.values()) {
      DummyCasteType[] casteType = {new DummyCasteType()};
      IIdentifiedCasteTraitTypeGroup typeGroup = new IdentifiedCasteTraitTypeGroup(new ITraitType[]{traitType}, new SimpleIdentifier("Test"),
              new MultiEntryMap<ITraitType, ICasteType>());
      IDefaultTrait trait = favorableTraitFactory.createTraits(typeGroup, friendlyIncrementChecker, new DummyTypedTraitTemplateFactory(traitType))[0];
      coreTraits.addTestTrait(trait);
      coreTraits.addAbilityTypeToGroup(traitType, casteType[0].getId());
    }
  }

  private static class DummyTypedTraitTemplateFactory implements TypedTraitTemplateFactory {
    private final AbilityType traitType;

    public DummyTypedTraitTemplateFactory(AbilityType traitType) {
      this.traitType = traitType;
    }

    @Override
    public ITraitTemplate create(ITraitType type) {
      return new DummyTraitTemplateFactory().createAbilityTemplate(traitType);
    }
  }
}