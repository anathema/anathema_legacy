package net.sf.anathema.character.presenter.charm;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.SimpleViewTabContent;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.lib.resources.IResources;

public class MagicPresenter {

  private final IMagicViewFactory factory;
  private final List<IMagicSubPresenter> subPresenters = new ArrayList<IMagicSubPresenter>();

  public MagicPresenter(
      ICharacterStatistics statistics,
      IMagicViewFactory factory,
      IResources resources,
      ITemplateRegistry templateRegistry,
      ICharmProvider provider) {
    this.factory = factory;
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (charmTemplate.knowsCharms(statistics.getRules())) {
      subPresenters.add(new CharacterCharmSelectionPresenter(statistics, resources, templateRegistry, provider));
      subPresenters.add(new ComboConfigurationPresenter(resources, statistics));
    }
    ISpellMagicTemplate spellMagic = statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.knowsSorcery()) {
      subPresenters.add(new SorcerySpellPresenter(statistics, resources));
    }
    if (spellMagic.knowsNecromancy()) {
      subPresenters.add(new NecromancyPresenter(statistics, resources));
    }
  }

  public SimpleViewTabContent[] init() {
    List<SimpleViewTabContent> basicMagicViews = new ArrayList<SimpleViewTabContent>();
    for (IMagicSubPresenter presenter : subPresenters) {
      basicMagicViews.add(presenter.init(factory));
    }
    return basicMagicViews.toArray(new SimpleViewTabContent[basicMagicViews.size()]);
  }
}