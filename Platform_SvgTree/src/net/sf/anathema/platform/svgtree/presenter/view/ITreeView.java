package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

import java.awt.*;

public interface ITreeView extends IView, IDisposable {
    void addNodeSelectionListener(INodeSelectionListener listener);

    void setNodeBackgroundColor(String nodeId, Color color);

    void setNodeAlpha(String nodeId, int alpha);

    void addDocumentLoadedListener(IDocumentLoadedListener listener);

    void setCanvasBackground(Color color);
}
