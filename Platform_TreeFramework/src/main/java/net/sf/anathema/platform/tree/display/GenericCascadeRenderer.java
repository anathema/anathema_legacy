package net.sf.anathema.platform.tree.display;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.platform.tree.document.CascadeFactory;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;

public class GenericCascadeRenderer implements TreeRenderer {
  public static GenericCascadeRenderer CreateFor(ITreeView treeView, CascadeFactory cascadeFactory) {
    return new GenericCascadeRenderer(treeView, cascadeFactory);
  }

  private final CascadeFactory provider;
  private final ITreeView treeView;

  private GenericCascadeRenderer(ITreeView treeView, CascadeFactory cascadeFactory) {
    this.treeView = treeView;
    this.provider = cascadeFactory;
  }

  @Override
  public void renderTree(boolean resetView, TreePresentationProperties presentationProperties, IRegularNode[] nodes) {
    Cascade cascade = provider.createCascade(nodes, presentationProperties);
    loadCascade(cascade, resetView);
  }

  @Override
  public void clearView() {
    treeView.clear();
  }

  private void loadCascade(Cascade cascade, boolean resetView) {
    try {
      treeView.loadCascade(cascade, resetView);
    } catch (CascadeLoadException e) {
      Logger.getLogger(GenericCascadeRenderer.class).error(e);
    }
  }
}