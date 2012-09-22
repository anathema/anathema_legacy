package net.sf.anathema.campaign.music.impl.view.categorization;

import net.sf.anathema.campaign.music.presenter.ISelectionContainerView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.SmartJList;
import org.jmock.example.announcer.Announcer;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionContainerListView<V> implements ISelectionContainerView<V>, IView {

  private final SmartJList<V> smartList;
  private final Announcer<IChangeListener> changeControl = Announcer.to(IChangeListener.class);

  public SelectionContainerListView(Class<V> contentClass) {
    smartList = new SmartJList<V>(contentClass);
    smartList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
          return;
        }
        changeControl.announce().changeOccurred();
      }
    });
  }

  @Override
  public void populate(V[] contentValues) {
    smartList.setObjects(contentValues);
  }

  @Override
  public void setSelectedValues(V[] selectedValues) {
    smartList.setSelectedObjects(selectedValues);
  }

  @Override
  public JList getComponent() {
    return smartList;
  }

  @Override
  public void addSelectionChangeListener(IChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public V[] getSelectedValues() {
    return smartList.getSelectedValues();
  }
}