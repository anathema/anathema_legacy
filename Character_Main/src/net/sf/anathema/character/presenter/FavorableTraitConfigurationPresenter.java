package net.sf.anathema.character.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateVisitor;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.specialty.SpecialtyConfigurationPresenter;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public class FavorableTraitConfigurationPresenter extends AbstractTraitPresenter {

  private final IGroupedFavorableTraitConfigurationView configurationView;
  private final IdentityMapping<IFavorableTrait, IFavorableIntValueView> traitViewsByTrait = new IdentityMapping<IFavorableTrait, IFavorableIntValueView>();
  private final IResources resources;
  private final IIdentifiedTraitTypeGroup[] traitTypeGroups;
  private final ICoreTraitConfiguration traitConfiguration;
  private final IBasicCharacterData basicCharacterData;
  private final ICharacterListening characterListening;
  private final IPresentationProperties presentationProperties;

  public FavorableTraitConfigurationPresenter(
      IIdentifiedTraitTypeGroup[] traitTypeGroups,
      ICharacterStatistics statistics,
      final IGroupedFavorableTraitConfigurationView configurationView,
      IResources resources) {
    this.traitTypeGroups = traitTypeGroups;
    this.traitConfiguration = statistics.getTraitConfiguration();
    this.basicCharacterData = statistics.getCharacterContext().getBasicCharacterContext();
    this.characterListening = statistics.getCharacterContext().getCharacterListening();
    this.presentationProperties = statistics.getCharacterTemplate().getPresentationProperties();
    this.resources = resources;
    this.configurationView = configurationView;
  }

  public void init(String typePrefix, final boolean initSpecialties) {
    for (IIdentifiedTraitTypeGroup traitTypeGroup : traitTypeGroups) {
      configurationView.startNewTraitGroup(resources.getString(typePrefix + "." + traitTypeGroup.getGroupId().getId())); //$NON-NLS-1$
      addAbilityViews(traitConfiguration.getFavorableTraits(traitTypeGroup.getAllGroupTypes()));
    }
    if (initSpecialties) {
      new SpecialtyConfigurationPresenter(
          getAllTraits(),
          basicCharacterData,
          characterListening,
          configurationView,
          resources).initPresentation();
    }
    configurationView.initGui(null);
    characterListening.addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateButtons();
      }
    });
    updateButtons();
  }

  private void updateButtons() {
    for (IFavorableTrait trait : getAllTraits()) {
      IFavorableIntValueView view = traitViewsByTrait.get(trait);
      boolean disabled = basicCharacterData.isExperienced() || trait.getFavorization().isCaste();
      boolean favored = trait.getFavorization().isCasteOrFavored();
      view.setButtonState(favored, !disabled);
    }
  }

  private IFavorableTrait[] getAllTraits() {
    List<ITraitType> traitTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : traitTypeGroups) {
      Collections.addAll(traitTypes, group.getAllGroupTypes());
    }
    return traitConfiguration.getFavorableTraits(traitTypes.toArray(new ITraitType[traitTypes.size()]));
  }

  private void addAbilityViews(final IFavorableTrait[] abilityGroup) {
    for (IFavorableTrait ability : abilityGroup) {
      traitViewsByTrait.put(ability, addAbilityView(ability));
    }
  }

  private IFavorableIntValueView addAbilityView(final IFavorableTrait favorableTrait) {
    String id = favorableTrait.getType().getId();
    final IFavorableIntValueView abilityView = configurationView.addTraitView(
        resources.getString(id),
        favorableTrait.getCurrentValue(),
        favorableTrait.getMaximalValue(),
        favorableTrait.getFavorization().isFavored(),
        new FavorableTraitViewProperties(presentationProperties, basicCharacterData, favorableTrait, resources));
    addModelValueListener(favorableTrait, abilityView);
    addViewValueListener(abilityView, favorableTrait);
    abilityView.addButtonSelectedListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        favorableTrait.getFavorization().setFavored(newValue);
      }
    });
    favorableTrait.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
      public void favorableStateChanged(FavorableState state) {
        updateView(abilityView, state);
      }
    });
    updateView(abilityView, favorableTrait.getFavorization().getFavorableState());
    return abilityView;
  }

  private void updateView(final IFavorableIntValueView abilityView, FavorableState state) {
    state.accept(new IFavorableStateVisitor() {
      public void visitDefault(FavorableState visitedState) {
        abilityView.setButtonState(false, true);
      }

      public void visitFavored(FavorableState visitedState) {
        abilityView.setButtonState(true, true);
      }

      public void visitCaste(FavorableState visitedState) {
        abilityView.setButtonState(true, false);
      }
    });
  }
}