package net.sf.anathema.character.presenter.charm;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.resources.IResources;

public class MagicPresenter implements IContentPresenter {

  private final List<IContentPresenter> subPresenters = new ArrayList<IContentPresenter>();

  public MagicPresenter(
      ICharacterStatistics statistics,
      IMagicViewFactory factory,
      IResources resources,
      ITemplateRegistry templateRegistry) {
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (charmTemplate.knowsCharms(statistics.getRules())) {
      subPresenters.add(new CharacterCharmSelectionPresenter(statistics, resources, templateRegistry, factory));
      subPresenters.add(new ComboConfigurationPresenter(resources, statistics, factory));
    }
    ISpellMagicTemplate spellMagic = statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.knowsSorcery()) {
      subPresenters.add(new SorcerySpellPresenter(statistics, resources, factory));
    }
    if (spellMagic.knowsNecromancy()) {
      subPresenters.add(new NecromancyPresenter(statistics, resources, factory));
    }
  }

  public void initPresentation() {
    for (IContentPresenter presenter : subPresenters) {
      presenter.initPresentation();
    }
  }

  public IViewContent getTabContent() {
    return new IViewContent() {
      public void addTo(IMultiContentView view) {
        for (IContentPresenter presenter : subPresenters) {
          presenter.getTabContent().addTo(view);
        }
      }

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