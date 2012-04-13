package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadePresenter extends AbstractCascadePresenter implements ICascadePresenter {

  private final CharmTreeIdentificateMap treeIdentificateMap = new CharmTreeIdentificateMap();

  public CascadePresenter(IResources resources, ICharacterGenerics generics, ICascadeViewFactory factory,
                          MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    ICharmCache cache = generics.getDataSet(ICharmCache.class);
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources,
            magicDescriptionProvider, generics, cache, treeIdentificateMap);
    ICascadeView view = factory.createCascadeView(viewProperties);
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties,
            filterSet, new CharmDisplayPropertiesMap(templateRegistry));
    setCharmTypes(new CascadeCharmTypes(generics.getTemplateRegistry()));
    setChangeListener(selectionListener);
    setView(view);
    setCharmDye(new CascadeCharmDye(view, selectionListener));
    setCharmGroups(new CascadeGroupCollection(templateRegistry, treeIdentificateMap));
  }

  @Override
  protected CascadeFilterContainer getFilterContainer() {
    return new CascadeFilterContainer((CascadeGroupCollection) charmGroups);
  }

  @Override
  protected GroupCharmTree getCharmTree(IIdentificate type) {
    return treeIdentificateMap.get(type);
  }
}