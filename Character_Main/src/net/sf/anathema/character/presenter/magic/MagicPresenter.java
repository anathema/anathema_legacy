package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.magic.charm.CharacterCharmTreePresenter;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationModel;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicAndDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenterFactory;
import net.sf.anathema.character.presenter.magic.detail.NullMagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.RegisteredMagicDetailPresenterFactory;
import net.sf.anathema.character.presenter.magic.spells.SpellContentPresenter;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagicPresenter implements IContentPresenter {

  private final Logger logger = Logger.getLogger(MagicPresenter.class);
  private final List<IContentPresenter> subPresenters = new ArrayList<IContentPresenter>();
  private IAnathemaModel anathemaModel;
  private IResources resources;


  public MagicPresenter(ICharacterStatistics statistics, IMagicViewFactory factory, IResources resources,
          IAnathemaModel anathemaModel) {
    this.resources = resources;
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(anathemaModel).getTemplateRegistry();
    this.anathemaModel = anathemaModel;
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (charmTemplate.canLearnCharms()) {
      subPresenters.add(createCharmPresenter(statistics, factory, resources, templateRegistry));
      subPresenters.add(new ComboConfigurationPresenter(resources, createComboModel(statistics), factory));
    }
    addSpellPresenter(statistics, factory, resources);
  }

  private void addSpellPresenter(ICharacterStatistics statistics, IMagicViewFactory factory, IResources resources) {
    ISpellMagicTemplate spellMagic = statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.canLearnSorcery()) {
      subPresenters.add(SpellContentPresenter.ForSorcery(createMagicDetailPresenter(), statistics, resources, factory));
    }
    if (spellMagic.canLearnNecromancy()) {
      subPresenters
              .add(SpellContentPresenter.ForNecromancy(createMagicDetailPresenter(), statistics, resources, factory));
    }
  }

  private ComboConfigurationModel createComboModel(ICharacterStatistics statistics) {
    return new ComboConfigurationModel(statistics, getCharmDescriptionProvider());
  }

  private MagicDescriptionProvider getCharmDescriptionProvider() {
    return CharmDescriptionProviderExtractor.CreateFor(anathemaModel, resources);
  }

  private MagicAndDetailPresenter createCharmPresenter(ICharacterStatistics statistics, IMagicViewFactory factory,
          IResources resources, ITemplateRegistry templateRegistry) {
    CharacterCharmModel model = new CharacterCharmModel(statistics, getCharmDescriptionProvider());
    ITreePresentationProperties presentationProperties =
            statistics.getCharacterTemplate().getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    CharacterCharmTreePresenter treePresenter =
            new CharacterCharmTreePresenter(resources, factory, model, presentationProperties, propertiesMap);
    MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    String tabTitle = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    return new MagicAndDetailPresenter(tabTitle, detailPresenter, treePresenter);
  }

  private MagicDetailPresenter createMagicDetailPresenter() {
    try {
      Instantiater instantiater = getGenerics().getInstantiater();
      Collection<MagicDetailPresenterFactory> factories =
              instantiater.instantiateAll(RegisteredMagicDetailPresenterFactory.class);
      for (MagicDetailPresenterFactory factory : factories) {
        return factory.create(anathemaModel, resources);
      }
    } catch (Throwable e) {
      logger.error("Error initializing charm details.", e);
    }
    return new NullMagicDetailPresenter();
  }

  private ICharacterGenerics getGenerics() {
    return CharacterGenericsExtractor.getGenerics(anathemaModel);
  }

  @Override
  public void initPresentation() {
    for (IContentPresenter presenter : subPresenters) {
      presenter.initPresentation();
    }
  }

  @Override
  public IViewContent getTabContent() {
    return new IViewContent() {
      @Override
      public void addTo(IMultiContentView view) {
        for (IContentPresenter presenter : subPresenters) {
          presenter.getTabContent().addTo(view);
        }
      }

      @Override
      public IDisposable getDisposable() {
        final List<IDisposable> disposables = new ArrayList<IDisposable>();
        for (IContentPresenter presenter : subPresenters) {
          IDisposable disposable = presenter.getTabContent().getDisposable();
          if (disposable != null) {
            disposables.add(disposable);
          }
        }
        if (disposables.size() == 0) {
          return null;
        }
        return new IDisposable() {
          @Override
          public void dispose() {
            for (IDisposable disposable : disposables) {
              disposable.dispose();
            }
          }
        };
      }
    };
  }
}
