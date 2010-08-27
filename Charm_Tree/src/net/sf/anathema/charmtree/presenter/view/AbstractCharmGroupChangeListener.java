package net.sf.anathema.charmtree.presenter.view;

import java.util.Set;

import net.sf.anathema.character.generic.framework.magic.CharmGraphNodeBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.CascadeDocumentFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener {

  private final CascadeDocumentFactory provider = new CascadeDocumentFactory();
  private final ITemplateRegistry templateRegistry;
  private final ISvgTreeView charmTreeView;
  private final ICharmGroupArbitrator arbitrator;
  private ICharmGroup currentGroup;

  public AbstractCharmGroupChangeListener(
      final ISvgTreeView charmTreeView,
      final ITemplateRegistry templateRegistry,
      final ICharmGroupArbitrator arbitrator) {
    this.charmTreeView = charmTreeView;
    this.templateRegistry = templateRegistry;
    this.arbitrator = arbitrator;
  }

  public final void valueChanged(final Object cascade, final Object type) {
    try {
      loadCharmTree((ICharmGroup) cascade, (IIdentificate) type);
      return;
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private Set<ICharm> getDisplayCharms(final ICharmGroup charmGroup) {
    ICharm[] allCharms = arbitrator.getCharms(charmGroup);
    Set<ICharm> charmsToDisplay = new ListOrderedSet<ICharm>();
    for (ICharm charm : allCharms) {
      charmsToDisplay.add(charm);
      for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
        if (charmGroup.getId().equals(prerequisite.getGroupId())) {
          charmsToDisplay.add(prerequisite);
        }
      }
    }
    return charmsToDisplay;
  }

  protected abstract IExaltedEdition getEdition();

  private void loadCharmTree(final ICharmGroup charmGroup, final IIdentificate type) throws DocumentException {
    currentGroup = charmGroup;
    modifyCharmVisuals(type);
    if (charmGroup == null) {
      charmTreeView.loadCascade(null);
    }
    else {
      ITreePresentationProperties presentationProperties = templateRegistry.getDefaultTemplate(
          charmGroup.getCharacterType(),
          getEdition()).getPresentationProperties().getCharmPresentationProperties();
      IRegularNode[] nodes = CharmGraphNodeBuilder.createNodesFromCharms(getDisplayCharms(charmGroup));
      Document document = provider.createCascadeDocument(nodes, presentationProperties);
      charmTreeView.loadCascade(document);
    }
  }

  public ICharmGroup getCurrentGroup() {
    return currentGroup;
  }

  protected abstract void modifyCharmVisuals(IIdentificate type);
}