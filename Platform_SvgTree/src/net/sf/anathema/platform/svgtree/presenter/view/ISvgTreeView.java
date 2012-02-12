package net.sf.anathema.platform.svgtree.presenter.view;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public interface ISvgTreeView extends ITreeView {

    void loadCascade(Document document, boolean resetView) throws DocumentException;

    ISpecialNodeViewManager getSpecialViewManager();
}