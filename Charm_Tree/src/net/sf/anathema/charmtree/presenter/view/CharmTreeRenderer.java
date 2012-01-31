package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.platform.svgtree.document.CascadeDocumentFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import org.dom4j.Document;
import org.dom4j.DocumentException;

public class CharmTreeRenderer {
  private final CascadeDocumentFactory provider = new CascadeDocumentFactory();
  private final ISvgTreeView charmTreeView;

  public CharmTreeRenderer(ISvgTreeView charmTreeView) {
    this.charmTreeView = charmTreeView;
  }

  public void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes) {
    Document document = provider.createCascadeDocument(nodes, presentationProperties);
    loadCascade(document, resetView);
  }

  public void clearView() {
    loadCascade(null, true);
  }

  private void loadCascade(Document document, boolean resetView) {
    try {
      charmTreeView.loadCascade(document, resetView);
    } catch (DocumentException e) {
      Logger.getLogger(CharmTreeRenderer.class).error(e);
    }
  }
}