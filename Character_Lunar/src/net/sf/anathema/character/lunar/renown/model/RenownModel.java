package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.lunar.LunarCharacterModule;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class RenownModel implements IRenownModel {

  private final RenownTraitCollection collection = new RenownTraitCollection();
  private final ICharacterModelContext context;
  private ITrait face;
  private final IntValueControl control = new IntValueControl();

  public RenownModel(final ICharacterModelContext context) {
    this.context = context;
    createRenownTraits();
    createFaceTrait();
    context.getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void traitChanged(ITraitType type) {
        if (LunarCharacterModule.RENOWN_BACKGROUND_TYPE.equals(type)) {
          control.fireValueChangedEvent(calculateAlottedRenownPoints());
        }
      }
    });
  }

  public ITrait getFace() {
    return face;
  }

  private void createFaceTrait() {
    ITraitContext traitContext = context.getTraitContext();
    ITraitTemplate renownTemplate = SimpleTraitTemplate.createStaticLimitedTemplate(0, 10, LowerableState.LowerableLoss);
    ITraitType faceType = new ITraitType() {
      public void accept(ITraitTypeVisitor visitor) {
        visitor.visitCustomTraitType(this);
      }

      public String getId() {
        return "Face"; //$NON-NLS-1$
      }
    };
    ITraitRules rules = new TraitRules(faceType, renownTemplate, traitContext.getLimitationContext());
    this.face = new DefaultTrait(rules, traitContext.getTraitValueStrategy(), new FaceValueChangeChecker(this));
    ConfigurableCharacterChangeListener attributeChangeListener = new ConfigurableCharacterChangeListener() {
      @Override
      public void configuredChangeOccured() {
        checkFaceTrait();
      }
    };
    for (AttributeType attributeType : AttributeType.values()) {
      attributeChangeListener.addTraitType(attributeType);
    }
    context.getCharacterListening().addChangeListener(attributeChangeListener);
  }

  private void checkFaceTrait() {
    int currentValue = face.getCurrentValue();
    int maximumAllowedFaceRank = getMaximumAllowedFaceRank();
    if (currentValue > maximumAllowedFaceRank) {
      face.setCurrentValue(maximumAllowedFaceRank);
    }
  }

  private void createRenownTraits() {
    ITraitContext traitContext = context.getTraitContext();
    ITraitTemplate renownTemplate = SimpleTraitTemplate.createStaticLimitedTemplate(
        0,
        999,
        LowerableState.LowerableLoss);
    for (int index = 0; index < RenownType.values().length; index++) {
      ITraitType type = RenownType.values()[index];
      ITraitRules rules = new TraitRules(type, renownTemplate, traitContext.getLimitationContext());
      ITrait trait = new DefaultTrait(rules, traitContext.getTraitValueStrategy(), new FriendlyValueChangeChecker());
      collection.addRenownTrait(trait);
      trait.addCurrentValueListener(new IIntValueChangedListener() {
        public void valueChanged(int newValue) {
          checkFaceTrait();
        }
      });
    }
  }

  public String getTemplateId() {
    return RenownTemplate.TEMPLATE_ID;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    // Nothing to do
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public ITrait getTrait(RenownType type) {
    return collection.getTrait(type);
  }

  public int calculateTotalRenown() {
    int total = 0;
    for (ITrait trait : getAllTraits()) {
      total += trait.getCurrentValue();
    }
    return total;
  }

  public ITrait[] getAllTraits() {
    return collection.getTraits(RenownType.values());
  }

  public int getMaximumAllowedFaceRank() {
    int totalRenown = calculateTotalRenown();
    if (totalRenown < 20) {
      return 0;
    }
    if (totalRenown < 50) {
      return 1;
    }
    if (totalRenown < 75) {
      return 2;
    }
    if (totalRenown < 100) {
      return 3;
    }
    if (totalRenown < 150 || hasAttributeBelow(3)) {
      return 4;
    }
    if (totalRenown < 200) {
      return 5;
    }
    if (totalRenown < 240) {
      return 6;
    }
    if (totalRenown < 280 || hasAttributeBelow(4)) {
      return 7;
    }
    if (totalRenown < 320) {
      return 8;
    }
    if (totalRenown < 360) {
      return 9;
    }
    return 10;
  }

  private boolean hasAttributeBelow(int minimalValue) {
    for (ITraitType type : AttributeType.values())
      if (context.getTraitCollection().getTrait(type).getCurrentValue() < minimalValue) {
        return true;
      }
    return false;
  }

  public int getUltimateFaceRank() {
    return 10;
  }

  public void addCharacterChangeListener(ICharacterChangeListener adapter) {
    context.getCharacterListening().addChangeListener(adapter);
  }

  public int calculateAlottedRenownPoints() {
    IGenericTrait trait = context.getTraitCollection().getTrait(LunarCharacterModule.RENOWN_BACKGROUND_TYPE);
    if (trait == null) {
      return 20;
    }
    int currentValue = trait.getCurrentValue();
    switch (currentValue) {
      case 0:
        return 20;
      case 1:
        return 25;
      case 2:
        return 30;
      case 3:
        return 35;
      case 4:
        return 45;
      case 5:
        return 60;
    }
    throw new IllegalStateException("Unexpected trait value for background Renown: " + currentValue); //$NON-NLS-1$
  }

  public void addRenownChangedListener(IIntValueChangedListener listener) {
    for (ITrait trait : getAllTraits()) {
      trait.addCurrentValueListener(listener);
    }
  }

  public void addFreeRenownPointsChangedListener(IIntValueChangedListener listener) {
    control.addIntValueChangeListener(listener);
  }

  public boolean isCharacterExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }
}