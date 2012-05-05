package net.sf.anathema.charmtree.presenter.view.svg;

import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.platform.svgtree.document.CascadeDocumentFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadException;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import org.dom4j.Document;

public class SvgCharmTreeRenderer implements CharmTreeRenderer {
  private final CascadeDocumentFactory provider = new CascadeDocumentFactory();
  private final ISvgTreeView charmTreeView;

  public SvgCharmTreeRenderer(ISvgTreeView charmTreeView) {
    this.charmTreeView = charmTreeView;
  }

  @Override
  public void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes) {
    Document document = provider.createCascadeDocument(nodes, presentationProperties);
    loadCascade(document, resetView);
  }

  @Override
  public void clearView() {
    loadCascade(null, true);
  }

  private void loadCascade(Document document, boolean resetView) {
    try {
      charmTreeView.loadCascade(document, resetView);
    } catch (CascadeLoadException e) {
      Logger.getLogger(SvgCharmTreeRenderer.class).error(e);
    }
  }
}