package net.sf.anathema.platform.svgtree.presenter.view;

import org.dom4j.Document;

public interface ISvgTreeView extends ITreeView<Document> {

  ISpecialNodeViewManager getSpecialViewManager();
}