package net.sf.anathema.character.presenter;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class BasicAdvantagePresenter {
  private final List<IPresenter> subPresenters = new ArrayList<IPresenter>();
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

  public SimpleViewTabContent init() {
    for (IPresenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new IAdvantageViewProperties() {
      public String getVirtueTitle() {
        return resources.getString("AdvantagesView.Virtues.Title"); //$NON-NLS-1$
      }

      public String getWillpowerTitle() {
        return resources.getString("AdvantagesView.Willpower.Title"); //$NON-NLS-1$
      }

      public String getEssenceTitle() {
        return resources.getString("AdvantagesView.Essence.Title"); //$NON-NLS-1$
      }

      public String getBackgroundTitle() {
        return resources.getString("AdvantagesView.Backgrounds.Title"); //$NON-NLS-1$
      }
    });
    String basicAdvantageHeader = resources.getString("CardView.Advantages.Title"); //$NON-NLS-1$
    return new SimpleViewTabContent(basicAdvantageHeader, view);
  }
}