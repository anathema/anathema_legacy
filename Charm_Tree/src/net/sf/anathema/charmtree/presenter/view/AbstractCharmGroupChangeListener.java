package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.framework.magic.CharmGraphNodeBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.util.List;
import java.util.Set;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener, CharmGroupInformer {

  private final ITemplateRegistry templateRegistry;
  private final ICharmGroupArbitrator arbitrator;
  private final CharmTreeRenderer charmTreeRenderer;
  protected final List<ICharmFilter> charmFilterSet;
  private IExaltedEdition edition;
  private ICharmGroup currentGroup;
  private IIdentificate currentType;

  public AbstractCharmGroupChangeListener(
          ITemplateRegistry templateRegistry,
          ICharmGroupArbitrator arbitrator,
          List<ICharmFilter> charmFilterSet, IExaltedEdition edition, CharmTreeRenderer treeRenderer) {
    this.charmTreeRenderer = treeRenderer;
    this.templateRegistry = templateRegistry;
    this.arbitrator = arbitrator;
    this.charmFilterSet = charmFilterSet;
    this.edition = edition;
  }

  public final void valueChanged(Object cascade, Object type) {
    loadCharmTree((ICharmGroup) cascade, (IIdentificate) type);
  }

  private Set<ICharm> getDisplayCharms(ICharmGroup charmGroup) {
    ICharm[] allCharms = arbitrator.getCharms(charmGroup);
    Set<ICharm> charmsToDisplay = new ListOrderedSet<ICharm>();
    for (ICharm charm : allCharms) {
      if (!filterCharm(charm, false) || !filterAncestors(charm))
        continue;
      charmsToDisplay.add(charm);
      for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
        if (charmGroup.getId().equals(prerequisite.getGroupId())) {
          charmsToDisplay.add(prerequisite);
        }
      }
    }
    return charmsToDisplay;
  }

  protected boolean filterAncestors(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (!filterCharm(prerequisite, true) || !filterAncestors(prerequisite))
        return false;
    }
    return true;
  }

  private boolean filterCharm(ICharm charm, boolean isAncestor) {
    for (ICharmFilter filter : charmFilterSet)
      if (!filter.acceptsCharm(charm, isAncestor))
        return false;
    return true;
  }

  protected IExaltedEdition getEdition() {
    return edition;
  }

  public void setEdition(IExaltedEdition edition) {
    this.edition = edition;
  }

  private void loadCharmTree(ICharmGroup charmGroup, IIdentificate type) {
    boolean resetView = !(currentGroup == charmGroup && currentType != null && currentType.getId().equals(type.getId()));
    this.currentGroup = charmGroup;
    this.currentType = type;
    modifyCharmVisuals(type);
    if (charmGroup == null) {
      charmTreeRenderer.clearView();
    } else {
      ITreePresentationProperties presentationProperties = templateRegistry.getDefaultTemplate(
              charmGroup.getCharacterType(),
              getEdition()).getPresentationProperties().getCharmPresentationProperties();
      IRegularNode[] nodes = CharmGraphNodeBuilder.createNodesFromCharms(getDisplayCharms(charmGroup));
      charmTreeRenderer.renderTree(resetView, presentationProperties, nodes);
    }
  }

  @Override
  public ICharmGroup getCurrentGroup() {
    return currentGroup;
  }

  protected abstract void modifyCharmVisuals(IIdentificate type);

  public void reselect() {
    valueChanged(getCurrentGroup(), currentType);
  }
}