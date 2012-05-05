package net.sf.anathema.charmtree.presenter.view.svg;

import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.platform.svgtree.document.CascadeFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadException;
import net.sf.anathema.platform.svgtree.presenter.view.ITreeView;

public class GenericCascadeRenderer<G> implements CharmTreeRenderer {
  public static <G> GenericCascadeRenderer<G> CreateFor(ITreeView<G> treeView, CascadeFactory<G> cascadeFactory) {
    return new GenericCascadeRenderer<G>(treeView, cascadeFactory);
  }

  private final CascadeFactory<G> provider;
  private final ITreeView<G> treeView;

  private GenericCascadeRenderer(ITreeView<G> treeView, CascadeFactory<G> cascadeFactory) {
    this.treeView = treeView;
    this.provider = cascadeFactory;
  }

  @Override
  public void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes) {
    G cascade = provider.createCascade(nodes, presentationProperties);
    loadCascade(cascade, resetView);
  }

  @Override
  public void clearView() {
    loadCascade(null, true);
  }

  private void loadCascade(G cascade, boolean resetView) {
    try {
      treeView.loadCascade(cascade, resetView);
    } catch (CascadeLoadException e) {
      Logger.getLogger(GenericCascadeRenderer.class).error(e);
    }
  }
}