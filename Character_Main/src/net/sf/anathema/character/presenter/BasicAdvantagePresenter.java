package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class BasicAdvantagePresenter implements IContentPresenter {
  private final List<Presenter> subPresenters = new ArrayList<>();
  private final IBasicAdvantageView view;
  private final Resources resources;

  public BasicAdvantagePresenter(Resources resources, ICharacter character, IAdvantageViewFactory factory) {
    this.resources = resources;
    this.view = factory.createBasicAdvantageView();
    ICoreTraitConfiguration traitConfiguration = character.getTraitConfiguration();
    subPresenters.add(new VirtueConfigurationPresenter(resources, traitConfiguration, view));
    subPresenters.add(new WillpowerConfigurationPresenter(resources, traitConfiguration.getTrait(OtherTraitType.Willpower), view));
    subPresenters.add(new EssenceConfigurationPresenter(resources, character.getEssencePool(), traitConfiguration, view));
  }

  @Override
  public void initPresentation() {
    for (Presenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new BasicAdvantageViewProperties());
  }

  @Override
  public ContentView getTabContent() {
    BasicAdvantageViewProperties properties = new BasicAdvantageViewProperties();
    String basicAdvantageHeader = properties.getVirtueTitle() + ", " + properties.getWillpowerTitle() + " & " + properties.getEssenceTitle();
    return new SimpleViewContentView(new ContentProperties(basicAdvantageHeader), view);
  }

  private class BasicAdvantageViewProperties implements IAdvantageViewProperties {
    @Override
    public String getVirtueTitle() {
      return resources.getString("AdvantagesView.Virtues.Title");
    }

    @Override
    public String getWillpowerTitle() {
      return resources.getString("AdvantagesView.Willpower.Title");
    }

    @Override
    public String getEssenceTitle() {
      return resources.getString("AdvantagesView.Essence.Title");
    }
  }
}