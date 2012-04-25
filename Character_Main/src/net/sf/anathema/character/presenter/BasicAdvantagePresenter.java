package net.sf.anathema.character.presenter;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;

public class BasicAdvantagePresenter implements IContentPresenter {
  private final List<Presenter> subPresenters = new ArrayList<Presenter>();
  private final IBasicAdvantageView view;
  private final IResources resources;

  public BasicAdvantagePresenter(
      IResources resources,
      ICharacterStatistics statistics,
      IAdvantageViewFactory factory,
      ICharacterGenerics generics) {
    this.resources = resources;
    this.view = factory.createBasicAdvantageView();
    ICoreTraitConfiguration traitConfiguration = statistics.getTraitConfiguration();
    subPresenters.add(new VirtueConfigurationPresenter(resources, traitConfiguration, view));
    subPresenters.add(new WillpowerConfigurationPresenter(
        resources,
        traitConfiguration.getTrait(OtherTraitType.Willpower),
        view));
    subPresenters.add(new BackgroundPresenter(
        resources,
        traitConfiguration.getBackgrounds(),
        statistics.getCharacterContext(),
        view,
        generics.getBackgroundRegistry()));
    subPresenters.add(new EssenceConfigurationPresenter(
        resources,
        statistics.getEssencePool(),
        traitConfiguration,
        view));
  }

  @Override
  public void initPresentation() {
    for (Presenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new IAdvantageViewProperties() {
      @Override
      public String getVirtueTitle() {
        return resources.getString("AdvantagesView.Virtues.Title"); //$NON-NLS-1$
      }

      @Override
      public String getWillpowerTitle() {
        return resources.getString("AdvantagesView.Willpower.Title"); //$NON-NLS-1$
      }

      @Override
      public String getEssenceTitle() {
        return resources.getString("AdvantagesView.Essence.Title"); //$NON-NLS-1$
      }

      @Override
      public String getBackgroundTitle() {
        return resources.getString("AdvantagesView.Backgrounds.Title"); //$NON-NLS-1$
      }
    });
  }

  @Override
  public IViewContent getTabContent() {
    String basicAdvantageHeader = resources.getString("CardView.Advantages.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(basicAdvantageHeader), view);
  }
}
