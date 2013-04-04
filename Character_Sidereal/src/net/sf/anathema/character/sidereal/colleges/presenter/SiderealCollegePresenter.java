package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.sidereal.SiderealCharacterModule;
import net.sf.anathema.character.sidereal.colleges.model.CollegeModelBonusPointCalculator;
import net.sf.anathema.character.sidereal.presentation.SiderealCasteUI;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.Icon;

public class SiderealCollegePresenter implements Presenter {

  private final Resources resources;
  private final ISiderealCollegeView view;
  private final ISiderealCollegeModel model;

  public SiderealCollegePresenter(Resources resources, ISiderealCollegeView view, ISiderealCollegeModel model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    final IOverviewCategory creationOverview = view.createOverview(resources.getString("Astrology.Overview.Title"));
    final ILabelledAlotmentView favoredView = creationOverview.addAlotmentView(
        resources.getString("Astrology.Overview.FavoredDots"), 2);
    final ILabelledAlotmentView generalView = creationOverview.addAlotmentView(
        resources.getString("Astrology.Overview.GeneralDots"), 2);
    final IValueView<Integer> bonusView = creationOverview.addIntegerValueView(
        resources.getString("Astrology.Overview.BonusPoints"), 2);
    final IOverviewCategory experienceOverview = view.createOverview(resources.getString("Overview.Title"));
    final IValueView<Integer> experienceView = experienceOverview.addIntegerValueView(
        resources.getString("Astrology.Overview.Experience"), 3);
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(
            SiderealCharacterModule.type);
    for (final IAstrologicalHouse house : model.getAllHouses()) {
      view.startGroup(resources.getString("AstrologicalHouses.GroupLabel." + house.getId()));
      for (final IFavorableDefaultTrait college : house.getColleges()) {
        String collegeName = resources.getString("AstrologicalCollege.Label." + college.getType().getId());
        IIconToggleButtonProperties properties = new IIconToggleButtonProperties() {
          @Override
          public Icon createUnselectedIcon() {
            return null;
          }

          @Override
          public Icon createStandardIcon() {
            return new SiderealCasteUI().getCasteIcon(house);
          }

          @Override
          public String getToolTipText() {
            return null;
          }
        };
        final IToggleButtonTraitView< ? > collegeView = view.addIntValueView(
            collegeName,
            factory,
            properties,
            college.getCurrentValue(),
            college.getMaximalValue(),
            college.getFavorization().isCasteOrFavored());
        new TraitPresenter(college, collegeView).initPresentation();
        college.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
          @Override
          public void favorableStateChanged(FavorableState state) {
            collegeView.setButtonState(college.getFavorization().isCasteOrFavored(), false);
          }
        });
        collegeView.setButtonState(college.getFavorization().isCasteOrFavored(), false);
      }
      house.addChangeListener(new IChangeListener() {
        @Override
        public void changeOccurred() {
          setOverviewData(favoredView, generalView, bonusView, experienceView);
        }
      });
    }
    view.setOverview(creationOverview);
    setOverviewData(favoredView, generalView, bonusView, experienceView);
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        if (experienced) {
          view.setOverview(experienceOverview);
        }
        else {
          view.setOverview(creationOverview);
        }
      }
    });
  }

  private void setOverviewData(
      ILabelledAlotmentView favoredView,
      ILabelledAlotmentView generalView,
      IValueView<Integer> bonusView,
      IValueView<Integer> experienceView) {
    CollegeModelBonusPointCalculator bonusPointCalculator = (CollegeModelBonusPointCalculator) model.getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    favoredView.setValue(bonusPointCalculator.getFavoredDotsSpent());
    favoredView.setAlotment(model.getTotalFavoredDotCount());
    generalView.setValue(bonusPointCalculator.getGeneralDotsSpent());
    generalView.setAlotment(model.getTotalGeneralDotCount());
    bonusView.setValue(bonusPointCalculator.getBonusPointCost());
    experienceView.setValue(model.getExperienceCalculator().calculateCost());
  }
}
