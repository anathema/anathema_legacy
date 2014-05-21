package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.hero.charms.display.node.CharmGraphNodeBuilder;
import net.sf.anathema.hero.charms.display.view.ICharmGroupChangeListener;
import net.sf.anathema.hero.charms.model.ICharmGroup;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.document.GenericCascadeFactory;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import net.sf.anathema.platform.tree.view.AgnosticCascadeStrategy;
import net.sf.anathema.platform.tree.view.container.Cascade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener, CharmGroupInformer {

  private final CharmGroupArbitrator arbitrator;
  private ICharmGroup currentGroup;
  private Identifier currentType;
  private final CharmDisplayPropertiesMap displayPropertiesMap;
  private TreeView treeView;

  public AbstractCharmGroupChangeListener(CharmGroupArbitrator arbitrator, CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    this.arbitrator = arbitrator;
    this.displayPropertiesMap = charmDisplayPropertiesMap;
  }

  @Override
  public final void valueChanged(Object cascade, Object type) {
    loadCharmTree((ICharmGroup) cascade, (Identifier) type);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }

  private void loadCharmTree(ICharmGroup charmGroup, Identifier type) {
    boolean resetView = !(currentGroup != null && currentGroup.equals(
            charmGroup) && currentType != null && currentType.equals(type));
    this.currentGroup = charmGroup;
    this.currentType = type;
    modifyCharmVisuals(type);
    if (charmGroup == null) {
      treeView.clear();
    } else {
      Set<Charm> charms = getDisplayCharms(charmGroup);
      IRegularNode[] nodesToShow = prepareNodes(charms);
      GenericCascadeFactory cascadeFactory = new GenericCascadeFactory(new AgnosticCascadeStrategy());
      TreePresentationProperties presentationProperties = getDisplayProperties(charmGroup);
      Cascade cascade = cascadeFactory.createCascade(nodesToShow, presentationProperties);
      treeView.loadCascade(cascade, resetView);
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
  public boolean hasGroupSelected() {
    return getCurrentGroup() != null;
  }

  protected TreeView getTreeView() {
    return treeView;
  }
}