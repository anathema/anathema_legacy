package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.HashMap;
import java.util.Map;

public class CascadePresenter extends AbstractCascadePresenter implements ICascadePresenter {

  private final IExaltedRuleSet selectedRuleSet = ExaltedRuleSet.SecondEdition;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();

  public CascadePresenter(IResources resources, ICharacterGenerics generics, ICascadeViewFactory factory,
                          MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    ICharmCache cache = CharmCache.getInstance();
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources,
            magicDescriptionProvider, generics, charmMapsByRules, selectedRuleSet, cache);
    ICascadeView view = factory.createCascadeView(viewProperties);
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    charmMapsByRules.put(selectedRuleSet, new CharmTreeIdentificateMap());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties,
            filterSet, new CharmDisplayPropertiesMap(templateRegistry), selectedRuleSet.getEdition());
    setCharmTypes(new CascadeCharmTypes(generics.getTemplateRegistry(), selectedRuleSet));
    setChangeListener(selectionListener);
    setView(view);
    setCharmDye(new CascadeCharmDye(view, selectionListener));
    setCharmGroups(new CascadeGroupCollection(templateRegistry, cache, charmMapsByRules));
  }

  @Override
  protected CascadeFilterContainer getFilterContainer() {
    return new CascadeFilterContainer(selectedRuleSet, (CascadeGroupCollection) charmGroups);
  }

  @Override
  protected GroupCharmTree getCharmTree(IIdentificate type) {
    CharmTreeIdentificateMap charmTreeMap = getCharmTreeMap(selectedRuleSet);
    return charmTreeMap.get(type);
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }
}
