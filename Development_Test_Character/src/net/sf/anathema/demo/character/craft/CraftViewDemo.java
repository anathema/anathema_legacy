package net.sf.anathema.demo.character.craft;

import net.sf.anathema.character.craft.model.CraftModel;
import net.sf.anathema.character.craft.presenter.CraftPresenter;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.craft.view.CraftView;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.aggregated.AggregatedTrait;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.dummy.character.DummyCharacterModelContext;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.resources.IResources;
import de.jdemo.extensions.SwingDemoCase;

public class CraftViewDemo extends SwingDemoCase {

  public void demo() {
    IResources resources = new AnathemaResources();
    DummyCharacterModelContext context = new DummyCharacterModelContext();
    AggregatedTrait craftTrait = createCraft(context);
    context.getCharacter().addTrait(craftTrait);
    ICraftModel craftModel = new CraftModel(context);
    IIntValueDisplayFactory displayFactory = new MarkerIntValueDisplayFactory(resources, CharacterType.SIDEREAL);
    CraftView view = new CraftView(displayFactory, 7);
    new CraftPresenter(craftModel, view, resources).initPresentation();
    show(view.getComponent());
  }

  private AggregatedTrait createCraft(DummyCharacterModelContext context) {
    String[] unremovableTraits = new String[] { "Fire", "Ice" };
    ITraitTemplate template = SimpleTraitTemplate.createStaticLimitedTemplate(0, 7);
    ILimitationContext limitiation = context.getTraitContext().getLimitationContext();
    IFavorableTraitRules traitRules = new FavorableTraitRules(AbilityType.Craft, template, limitiation);
    IValueChangeChecker valueChecker = new IValueChangeChecker() {
      public boolean isValidNewValue(int value) {
        return true;
      }
    };
    return new AggregatedTrait(
        traitRules,
        context.getBasicCharacterContext(),
        context.getCharacterListening(),
        context.getTraitContext(),
        valueChecker,
        ICasteType.NULL_CASTE_TYPE,
        new FriendlyIncrementChecker(),
        unremovableTraits);
  }
}