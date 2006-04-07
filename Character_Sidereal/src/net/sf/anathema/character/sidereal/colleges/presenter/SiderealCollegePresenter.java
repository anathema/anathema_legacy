package net.sf.anathema.character.sidereal.colleges.presenter;

import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.sidereal.colleges.model.CollegeModelBonusPointCalculator;
import net.sf.anathema.character.sidereal.presentation.SiderealPresentationProperties;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCollegePresenter extends AbstractTraitPresenter {

  private final IResources resources;
  private final ISiderealCollegeView view;
  private final ISiderealCollegeModel model;
  private final ISiderealCollegeCreationOverview creationOverview;
  private final ISiderealCollegeExperiencedOverview experiencedOverview;

  public SiderealCollegePresenter(
      IResources resources,
      ISiderealCollegeView view,
      ISiderealCollegeCreationOverview creationOverview,
      ISiderealCollegeExperiencedOverview experiencedOverview,
      ISiderealCollegeModel model) {
    this.resources = resources;
    this.view = view;
    this.creationOverview = creationOverview;
    this.experiencedOverview = experiencedOverview;
    this.model = model;
  }

  public void initPresentation() {
    view.setOverview(creationOverview);
    Icon siderealBall = resources.getImageIcon(IIconConstants.SIDEREAL_BALL);
    IIntValueDisplayFactory factory = new IntValueDisplayFactory(resources, siderealBall);
    for (final IAstrologicalHouse house : model.getAllHouses()) {
      view.startGroup(resources.getString("AstrologicalHouses.GroupLabel." + house.getId())); //$NON-NLS-1$
      for (final IFavorableTrait college : house.getColleges()) {
        String collegeName = resources.getString("AstrologicalCollege.Label." + college.getType().getId()); //$NON-NLS-1$
        IIconToggleButtonProperties properties = new IIconToggleButtonProperties() {
          public Icon createUnselectedIcon() {
            return null;
          }

          public Icon createStandardIcon() {
            return resources.getImageIcon(SiderealPresentationProperties.getSideralCasteIconResource(house.getId()));
          }
        };
        final IFavorableIntValueView collegeView = view.addIntValueView(
            collegeName,
            factory,
            properties,
            college.getCurrentValue(),
            college.getMaximalValue(),
            college.getFavorization().isCasteOrFavored());
        addModelValueListener(college, collegeView);
        addViewValueListener(collegeView, college);
        college.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
          public void favorableStateChanged(FavorableState state) {
            collegeView.setButtonState(college.getFavorization().isCasteOrFavored(), false);
          }
        });
        collegeView.setButtonState(college.getFavorization().isCasteOrFavored(), false);
      }
      house.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          setOverviewData();
        }
      });
    }
    setOverviewData();
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      public void experiencedChanged(boolean experienced) {
        if (experienced) {
          view.setOverview(experiencedOverview);
        }
        else {
          view.setOverview(creationOverview);
        }
      }
    });
  }

  private void setOverviewData() {
    CollegeModelBonusPointCalculator bonusPointCalculator = (CollegeModelBonusPointCalculator) model.getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    creationOverview.setFavoredDotsOverview(bonusPointCalculator.getFavoredDotsSpent(), model.getTotalFavoredDotCount());
    creationOverview.setGeneralDotsOverview(bonusPointCalculator.getGeneralDotsSpent(), model.getTotalGeneralDotCount());
    creationOverview.setBonusPointsOverview(bonusPointCalculator.getBonusPointCost());
    experiencedOverview.setExperiencePointsSpent(model.getExperienceCalculator().calculateCost());
  }
}