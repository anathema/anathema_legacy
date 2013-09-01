package net.sf.anathema.hero.traits.display;

import net.sf.anathema.character.main.library.ITraitFavorization;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.TraitTypeGroup;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.character.main.library.trait.favorable.FavorableState.Caste;
import static net.sf.anathema.character.main.library.trait.favorable.FavorableState.Default;
import static net.sf.anathema.character.main.library.trait.favorable.FavorableState.Favored;

public class FavorableTraitConfigurationPresenter {

  private final GroupedFavorableTraitConfigurationView view;
  private final IdentityMapping<Trait, ToggleTool> traitViewsByTrait = new IdentityMapping<>();
  private final Resources resources;
  private final IIdentifiedTraitTypeGroup[] traitTypeGroups;
  private final TraitMap traitConfiguration;
  private final IPresentationProperties presentationProperties;
  private Hero hero;

  public FavorableTraitConfigurationPresenter(IIdentifiedTraitTypeGroup[] traitTypeGroups, Hero hero, GroupedFavorableTraitConfigurationView view,
                                              Resources resources) {
    this.hero = hero;
    this.traitTypeGroups = traitTypeGroups;
    this.traitConfiguration = TraitModelFetcher.fetch(hero);
    this.presentationProperties = hero.getTemplate().getPresentationProperties();
    this.resources = resources;
    this.view = view;
  }

  public void init(String typePrefix) {
    for (IIdentifiedTraitTypeGroup traitTypeGroup : traitTypeGroups) {
      view.startNewTraitGroup(resources.getString(typePrefix + "." + traitTypeGroup.getGroupId().getId()));
      addTraitViews(traitConfiguration.getTraits(traitTypeGroup.getAllGroupTypes()));
    }
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          updateButtons();
        }
      }
    });
    updateButtons();
  }

  private void updateButtons() {
    for (Trait trait : getAllTraits()) {
      ToggleTool view = traitViewsByTrait.get(trait);
      boolean disabled = ExperienceModelFetcher.fetch(hero).isExperienced() || trait.getFavorization().isCaste();
      boolean favored = trait.getFavorization().isCasteOrFavored();
      setButtonState(view, favored, !disabled);
    }
  }

  private Trait[] getAllTraits() {
    return traitConfiguration.getTraits(TraitTypeGroup.getAllTraitTypes(traitTypeGroups));
  }

  private void addTraitViews(Trait[] traits) {
    for (Trait trait : traits) {
      addTraitView(trait);
    }
  }

  private void addTraitView(Trait favorableTrait) {
    ExtensibleTraitView traitView = createTraitView(favorableTrait);
    addCasteAndFavoredToggle(favorableTrait, traitView);
  }

  private ExtensibleTraitView createTraitView(Trait favorableTrait) {
    String traitName = resources.getString(favorableTrait.getType().getId());
    ExtensibleTraitView traitView = view.addExtensibleTraitView(traitName, favorableTrait.getMaximalValue());
    new TraitPresenter(favorableTrait, traitView.getIntValueView()).initPresentation();
    return traitView;
  }

  private void addCasteAndFavoredToggle(final Trait favorableTrait, ExtensibleTraitView traitView) {
    final ToggleTool casteTool = traitView.addToggleInFront();
    casteTool.setCommand(new Command() {
      @Override
      public void execute() {
        ITraitFavorization favorization = favorableTrait.getFavorization();
        favorization.setFavored(!favorization.isFavored());
      }
    });
    favorableTrait.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
      @Override
      public void favorableStateChanged(FavorableState state) {
        updateView(casteTool, state);
      }
    });
    updateView(casteTool, favorableTrait.getFavorization().getFavorableState());
    traitViewsByTrait.put(favorableTrait, casteTool);
  }

  private void updateView(final ToggleTool view, FavorableState state) {
    boolean select = state == Favored || state == Caste;
    boolean enable = state == Favored || state == Default;
    setButtonState(view, select, enable);
    new FavoredIconSelector(view, presentationProperties).setIconFor(hero, state);
  }

  private void setButtonState(ToggleTool view, boolean select, boolean enable) {
    select(view, select);
    enable(view, enable);
  }

  private void select(ToggleTool view, boolean select) {
    if (select) {
      view.select();
    } else {
      view.deselect();
    }
  }

  private void enable(ToggleTool view, boolean enable) {
    if (enable) {
      view.enable();
    } else {
      view.disable();
    }
  }
}