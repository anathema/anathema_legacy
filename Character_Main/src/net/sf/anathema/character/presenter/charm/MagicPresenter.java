package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.NullCharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.tree.CharacterCharmTreePresenter;
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

  public MagicPresenter(
          ICharacterStatistics statistics,
          IMagicViewFactory factory,
          IResources resources,
          IAnathemaModel anathemaModel) {
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(anathemaModel).getTemplateRegistry();
    this.anathemaModel = anathemaModel;
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (charmTemplate.canLearnCharms(statistics.getRules())) {
      subPresenters.add(createCharmPresenter(statistics, factory, resources, templateRegistry));
      subPresenters.add(new ComboConfigurationPresenter(resources, statistics, factory));
    }
    ISpellMagicTemplate spellMagic = statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.canLearnSorcery()) {
      subPresenters.add(new SorcerySpellPresenter(statistics, resources, factory));
    }
    if (spellMagic.canLearnNecromancy()) {
      subPresenters.add(new NecromancyPresenter(statistics, resources, factory));
    }
  }

  private CharacterCharmPresenter createCharmPresenter(ICharacterStatistics statistics, IMagicViewFactory factory, IResources resources, ITemplateRegistry templateRegistry) {
    CharacterCharmModel model = new CharacterCharmModel(statistics);
    ITreePresentationProperties presentationProperties = statistics.getCharacterTemplate().getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, factory, model, presentationProperties,
            propertiesMap);
    CharmDetailPresenter detailPresenter = createCharmDetailPresenter();
    return new CharacterCharmPresenter(resources, detailPresenter, treePresenter);
  }

  private CharmDetailPresenter createCharmDetailPresenter() {
    try {
      Instantiater instantiater = CharacterGenericsExtractor.getGenerics(anathemaModel).getInstantiater();
      Collection<CharmDetailPresenterFactory> factories = instantiater.instantiateAll(RegisteredCharmDetailPresenterFactory.class);
      for (CharmDetailPresenterFactory factory : factories) {
        return factory.create(anathemaModel);
      }
    } catch (Throwable e) {
      logger.error("Error initializing charm details.", e);
    }
    return new NullCharmDetailPresenter();
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
