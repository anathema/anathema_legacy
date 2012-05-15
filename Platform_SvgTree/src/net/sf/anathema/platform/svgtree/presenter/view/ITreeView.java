package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.swing.IDisposable;

import java.awt.Color;

public interface ITreeView extends IView, IDisposable {
    void addNodeSelectionListener(CharmInteractionListener listener);

    void setNodeBackgroundColor(String nodeId, Color color);

    void setNodeAlpha(String nodeId, int alpha);

    void addDocumentLoadedListener(CascadeLoadedListener listener);

    void setCanvasBackground(Color color);
}
