package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.library.trait.favorable.FavorableState.Caste;
import static net.sf.anathema.character.library.trait.favorable.FavorableState.Default;
import static net.sf.anathema.character.library.trait.favorable.FavorableState.Favored;

public class FavorableTraitConfigurationPresenter {

  private final IGroupedFavorableTraitConfigurationView configurationView;
  private final IdentityMapping<ITrait, IToggleButtonTraitView<?>> traitViewsByTrait = new IdentityMapping<>();
  private final Resources resources;
  private final IIdentifiedTraitTypeGroup[] traitTypeGroups;
  private final ICoreTraitConfiguration traitConfiguration;
  private final IBasicCharacterData basicCharacterData;
  private final ICharacterListening characterListening;
  private final IPresentationProperties presentationProperties;

  public FavorableTraitConfigurationPresenter(IIdentifiedTraitTypeGroup[] traitTypeGroups, ICharacter character,
                                              IGroupedFavorableTraitConfigurationView configurationView, Resources resources) {
    this.traitTypeGroups = traitTypeGroups;
    this.traitConfiguration = character.getTraitConfiguration();
    this.basicCharacterData = character.getCharacterContext().getBasicCharacterContext();
    this.characterListening = character.getCharacterContext().getCharacterListening();
    this.presentationProperties = character.getCharacterTemplate().getPresentationProperties();
    this.resources = resources;
    this.configurationView = configurationView;
  }

  public void init(String typePrefix) {
    for (IIdentifiedTraitTypeGroup traitTypeGroup : traitTypeGroups) {
      configurationView.startNewTraitGroup(resources.getString(typePrefix + "." + traitTypeGroup.getGroupId().getId()));
      addTraitViews(traitConfiguration.getTraits(traitTypeGroup.getAllGroupTypes()));
    }
    characterListening.addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateButtons();
      }
    });
    updateButtons();
  }

  private void updateButtons() {
    for (ITrait trait : getAllTraits()) {
      IToggleButtonTraitView<?> view = traitViewsByTrait.get(trait);
      boolean disabled = basicCharacterData.isExperienced() || trait.getFavorization().isCaste();
      boolean favored = trait.getFavorization().isCasteOrFavored();
      view.setButtonState(favored, !disabled);
    }
  }

  private ITrait[] getAllTraits() {
    return traitConfiguration.getTraits(TraitTypeGroup.getAllTraitTypes(traitTypeGroups));
  }

  private void addTraitViews(ITrait[] abilityGroup) {
    for (ITrait ability : abilityGroup) {
      traitViewsByTrait.put(ability, addTraitView(ability));
    }
  }

  private IToggleButtonTraitView<?> addTraitView(final ITrait favorableTrait) {
    final String id = favorableTrait.getType().getId();
    final IToggleButtonTraitView<?>[] view = new IToggleButtonTraitView<?>[1];
    favorableTrait.accept(new ITraitVisitor() {
      @Override
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        view[0] = configurationView
                .addMarkerLessTraitView(resources.getString(id), favorableTrait.getCurrentValue(), favorableTrait.getMaximalValue(),
                        visitedTrait.getFallbackTrait(), favorableTrait.getFavorization().isFavored(),
                        new FavorableTraitViewProperties(presentationProperties, basicCharacterData, favorableTrait));
      }

      @Override
      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        view[0] = configurationView.addTraitView(resources.getString(id), favorableTrait.getCurrentValue(), favorableTrait.getMaximalValue(),
                (IModifiableCapTrait) favorableTrait, favorableTrait.getFavorization().isFavored(),
                new FavorableTraitViewProperties(presentationProperties, basicCharacterData, favorableTrait));
      }
    });
    final IToggleButtonTraitView<?> traitView = view[0];
    new TraitPresenter(favorableTrait, traitView).initPresentation();
    traitView.addButtonSelectedListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        favorableTrait.getFavorization().setFavored(newValue);
      }
    });
    favorableTrait.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
      @Override
      public void favorableStateChanged(FavorableState state) {
        updateView(traitView, state);
      }
    });
    updateView(traitView, favorableTrait.getFavorization().getFavorableState());
    return traitView;
  }

  public static void updateView(final IToggleButtonTraitView<?> view, FavorableState state) {
    boolean select = state == Favored || state == Caste;
    boolean enable = state == Favored || state == Default;
    view.setButtonState(select, enable);
  }
}