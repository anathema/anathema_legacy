package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.infernal.InfernalCharacterModule;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.presenter.FavorableTraitViewProperties;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

import static net.sf.anathema.character.presenter.FavorableTraitConfigurationPresenter.updateView;

public class InfernalPatronPresenter implements Presenter {

  private final Resources resources;
  private final IInfernalPatronView view;
  private final IInfernalPatronModel model;
  private final ICharacterModelContext context;
  private final ICharacterListening characterListening;
  private final IdentityMapping<IFavorableTrait, IToggleButtonTraitView<?>> viewsByYozi = new IdentityMapping<>();

  public InfernalPatronPresenter(Resources resources, IInfernalPatronView view, IInfernalPatronModel model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
    this.context = model.getContext();
    this.characterListening = context.getCharacterListening();
  }

  @Override
  public void initPresentation() {
    IOverviewCategory overview = view.createOverview(resources.getString("Astrology.Overview.Title"));
    view.setOverview(overview);
    final ILabelledAlotmentView favoredView = overview.addAlotmentView(
            resources.getString("Infernal.Overview.FavoredYozis"), 1);
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(
            InfernalCharacterModule.type);
    view.startGroup(resources.getString("Yozis.Yozis"));
    for (final IFavorableDefaultTrait yozi : model.getAllYozis()) {
      String yoziName = resources.getString(yozi.getType().getId());
      IIconToggleButtonProperties properties = new FavorableTraitViewProperties(context.getPresentationProperties(),
              context.getBasicCharacterContext(), yozi);
      final IToggleButtonTraitView<?> yoziView = view.addYoziSelectionView(yoziName, factory, properties,
              yozi.getFavorization().isCasteOrFavored());
      new TraitPresenter(yozi, yoziView).initPresentation();
      yoziView.addButtonSelectedListener(new IBooleanValueChangedListener() {
        @Override
        public void valueChanged(boolean newValue) {
          model.setPatronYozi(yozi.getType(), newValue);
        }
      });
      yozi.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
        @Override
        public void favorableStateChanged(FavorableState state) {
          updateView(yoziView, state);
          setOverviewData(favoredView);
        }
      });
      viewsByYozi.put(yozi, yoziView);
      updateView(yoziView, model.getFavorableState(yozi.getType()));
    }
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
    boolean characterIsExperienced = context.getBasicCharacterContext().isExperienced();
    for (IFavorableDefaultTrait yozi : model.getAllYozis()) {
      boolean isPatronYozi = model.isPatronYozi(yozi.getType());
      boolean isCasteYozi = model.isCasteYozi(yozi.getType());
      boolean favored = isPatronYozi || isCasteYozi;
      boolean disabled = characterIsExperienced || isCasteYozi;
      IToggleButtonTraitView<?> view = viewsByYozi.get(yozi);
      view.setButtonState(favored, !disabled);
    }
  }

  private void setOverviewData(ILabelledAlotmentView favoredView) {
    favoredView.setValue(model.getPatronYozi() == null ? 0 : 1);
    favoredView.setAlotment(1);
  }
}