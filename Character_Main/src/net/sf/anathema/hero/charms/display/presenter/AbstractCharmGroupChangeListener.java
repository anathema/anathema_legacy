package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.builder.CharmGraphNodeBuilder;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.hero.charms.display.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.view.ICharmGroupChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeRenderer;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener, CharmGroupInformer {

  private final CharmGroupArbitrator arbitrator;
  private final TreeRenderer treeRenderer;
  private ICharmGroup currentGroup;
  private Identifier currentType;
  private final CharmDisplayPropertiesMap displayPropertiesMap;

  public AbstractCharmGroupChangeListener(CharmGroupArbitrator arbitrator, TreeRenderer treeRenderer,
                                          CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    this.treeRenderer = treeRenderer;
    this.arbitrator = arbitrator;
    this.displayPropertiesMap = charmDisplayPropertiesMap;
  }

  @Override
  public final void valueChanged(Object cascade, Object type) {
    loadCharmTree((ICharmGroup) cascade, (Identifier) type);
  }

  private void loadCharmTree(ICharmGroup charmGroup, Identifier type) {
    boolean resetView = !(currentGroup != null && currentGroup.equals(charmGroup) && currentType != null && currentType.equals(type));
    this.currentGroup = charmGroup;
    this.currentType = type;
    modifyCharmVisuals(type);
    if (charmGroup == null) {
      treeRenderer.clearView();
    } else {
      TreePresentationProperties presentationProperties = getDisplayProperties(charmGroup);
      Set<Charm> charms = getDisplayCharms(charmGroup);
      IRegularNode[] nodesToShow = prepareNodes(charms);
      treeRenderer.renderTree(resetView, presentationProperties, nodesToShow);
    }
  }

  private IRegularNode[] prepareNodes(Set<Charm> charms) {
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

  private Set<Charm> getDisplayCharms(ICharmGroup charmGroup) {
    Set<Charm> charmsToDisplay = new LinkedHashSet<>();
    for (Charm charm : arbitrator.getCharms(charmGroup)) {
      charmsToDisplay.add(charm);
      for (Charm prerequisite : charm.getRenderingPrerequisiteCharms()) {
        if (charmGroup.getId().equals(prerequisite.getGroupId())) {
          charmsToDisplay.add(prerequisite);
        }
      }
    }
    return charmsToDisplay;
  }

  private TreePresentationProperties getDisplayProperties(ICharmGroup charmGroup) {
    CharacterType characterType = charmGroup.getCharacterType();
    return getDisplayProperties(characterType);
  }

  protected TreePresentationProperties getDisplayProperties(CharacterType characterType) {
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