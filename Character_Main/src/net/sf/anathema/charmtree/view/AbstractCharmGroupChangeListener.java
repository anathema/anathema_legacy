package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.builder.CharmGraphNodeBuilder;
import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener, CharmGroupInformer {

  private final ICharmGroupArbitrator arbitrator;
  private final CharmTreeRenderer charmTreeRenderer;
  private final CharmFilterSet charmFilterSet;
  private ICharmGroup currentGroup;
  private Identifier currentType;
  private final CharmDisplayPropertiesMap displayPropertiesMap;

  public AbstractCharmGroupChangeListener(ICharmGroupArbitrator arbitrator, CharmFilterSet charmFilterSet, CharmTreeRenderer treeRenderer,
                                          CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    this.charmTreeRenderer = treeRenderer;
    this.arbitrator = arbitrator;
    this.charmFilterSet = charmFilterSet;
    this.displayPropertiesMap = charmDisplayPropertiesMap;
  }

  @Override
  public final void valueChanged(Object cascade, Object type) {
    loadCharmTree((ICharmGroup) cascade, (Identifier) type);
  }

  protected boolean filterAncestors(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (!charmFilterSet.filterCharm(prerequisite, true) || !filterAncestors(prerequisite)) {
        return false;
      }
    }
    return true;
  }

  private void loadCharmTree(ICharmGroup charmGroup, Identifier type) {
    boolean resetView = !(currentGroup != null && currentGroup.equals(charmGroup) && currentType != null && currentType.equals(type));
    this.currentGroup = charmGroup;
    this.currentType = type;
    modifyCharmVisuals(type);
    if (charmGroup == null) {
      charmTreeRenderer.clearView();
    } else {
      ITreePresentationProperties presentationProperties = getDisplayProperties(charmGroup);
      Set<ICharm> charms = getDisplayCharms(charmGroup);
      IRegularNode[] nodesToShow = prepareNodes(charms);
      charmTreeRenderer.renderTree(resetView, presentationProperties, nodesToShow);
    }
  }

  private IRegularNode[] prepareNodes(Set<ICharm> charms) {
    Collection<IIdentifiedRegularNode> nodes = CharmGraphNodeBuilder.createNodesFromCharms(charms);
    List<IIdentifiedRegularNode> sortedNodes = new ArrayList<>(nodes);
    Collections.sort(sortedNodes, new Comparator<IIdentifiedRegularNode>() {
      @Override
      public int compare(IIdentifiedRegularNode o1, IIdentifiedRegularNode o2) {
        return o1.getId().compareTo(o2.getId());
      }
    });
    return sortedNodes.toArray(new IRegularNode[sortedNodes.size()]);
  }

  private Set<ICharm> getDisplayCharms(ICharmGroup charmGroup) {
    Set<ICharm> charmsToDisplay = new LinkedHashSet<>();
    for (ICharm charm : arbitrator.getCharms(charmGroup)) {
      if (!charmFilterSet.filterCharm(charm, false) || !filterAncestors(charm)) {
        continue;
      }
      charmsToDisplay.add(charm);
      for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
        if (charmGroup.getId().equals(prerequisite.getGroupId())) {
          charmsToDisplay.add(prerequisite);
        }
      }
    }
    return charmsToDisplay;
  }

  private ITreePresentationProperties getDisplayProperties(ICharmGroup charmGroup) {
    ICharacterType characterType = charmGroup.getCharacterType();
    return getDisplayProperties(characterType);
  }

  protected ITreePresentationProperties getDisplayProperties(ICharacterType characterType) {
    return displayPropertiesMap.getDisplayProperties(characterType);
  }

  @Override
  public ICharmGroup getCurrentGroup() {
    return currentGroup;
  }

  protected abstract void modifyCharmVisuals(Identifier type);

  @Override
  public void reselect() {
    valueChanged(getCurrentGroup(), currentType);
  }

  @Override
  public boolean hasGroupSelected() {
    return getCurrentGroup() != null;
  }
}