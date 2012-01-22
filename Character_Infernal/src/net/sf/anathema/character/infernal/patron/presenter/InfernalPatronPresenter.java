package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateVisitor;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.presenter.FavorableTraitViewProperties;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class InfernalPatronPresenter implements IPresenter {

  private final IResources resources;
  private final IInfernalPatronView view;
  private final IInfernalPatronModel model;
  private final ICharacterModelContext context;
  private final ICharacterListening characterListening;
  private final IdentityMapping<IFavorableTrait, IToggleButtonTraitView< ? >> traitViewsByTrait = new IdentityMapping<IFavorableTrait, IToggleButtonTraitView< ? >>();

  public InfernalPatronPresenter(IResources resources, IInfernalPatronView view, IInfernalPatronModel model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
    this.context = model.getContext();
    this.characterListening = context.getCharacterListening();
  }

  public void initPresentation() {
    final IOverviewCategory overview = view.createOverview(resources.getString("Astrology.Overview.Title")); //$NON-NLS-1$
    final ILabelledAlotmentView favoredView = overview.addAlotmentView(
        resources.getString("Infernal.Overview.FavoredYozis"), 1); //$NON-NLS-1$
    IIntValueDisplayFactory factory = new MarkerIntValueDisplayFactory(resources, CharacterType.INFERNAL);
    view.startGroup(resources.getString("Yozis.Yozis")); //$NON-NLS-1$
    for (final IFavorableDefaultTrait yozi : model.getAllYozis()) {
        String yoziName = resources.getString(yozi.getType().getId()); //$NON-NLS-1$
        IIconToggleButtonProperties properties =
        	new FavorableTraitViewProperties(context.getPresentationProperties(),
        			context.getBasicCharacterContext(), yozi, resources);
        final IToggleButtonTraitView< ? > yoziView = view.addIntValueView(
            yoziName,
            factory,
            properties,
            yozi.getFavorization().isCasteOrFavored());
        new TraitPresenter(yozi, yoziView).initPresentation();
        yoziView.addButtonSelectedListener(new IBooleanValueChangedListener() {
            public void valueChanged(boolean newValue) {
              yozi.getFavorization().setFavored(newValue);
            }
          });
        yozi.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
            public void favorableStateChanged(FavorableState state) {
              updateView(yoziView, state);
              setOverviewData(favoredView);
            }
          });
        traitViewsByTrait.put(yozi, yoziView);
        updateView(yoziView, yozi.getFavorization().getFavorableState());
      }
      /*yozi.addChangeListener(new IChangeListener() {
        public void changeOccurred() {
          setOverviewData(favoredView, generalView, bonusView, experienceView);
        }
      });*/
    view.setOverview(overview);
    setOverviewData(favoredView);
    
    characterListening.addChangeListener(new DedicatedCharacterChangeAdapter() {
        @Override
        public void experiencedChanged(boolean experienced) {
          updateButtons();
        }
      });
      updateButtons();
  }
  
  private void updateButtons() {
	    for (final IFavorableDefaultTrait yozi : model.getAllYozis()) {
	      IToggleButtonTraitView< ? > view = traitViewsByTrait.get(yozi);
	      boolean disabled = context.getBasicCharacterContext().isExperienced() || yozi.getFavorization().isCaste();
	      boolean favored = yozi.getFavorization().isCasteOrFavored();
	      view.setButtonState(favored, !disabled);
	    }
	  }
  
  private void updateView(final IToggleButtonTraitView< ? > patronView, FavorableState state) {
	    state.accept(new IFavorableStateVisitor() {
	      public void visitDefault(FavorableState visitedState) {
	        patronView.setButtonState(false, true);
	      }

	      public void visitFavored(FavorableState visitedState) {
	        patronView.setButtonState(true, true);
	      }

	      public void visitCaste(FavorableState visitedState) {
	        patronView.setButtonState(true, false);
	      }
	    });
	  }

  private void setOverviewData(ILabelledAlotmentView favoredView) {
    favoredView.setValue(model.getFavoredYozi() == null ? 0 : 1);
    favoredView.setAlotment(1);
  }
}