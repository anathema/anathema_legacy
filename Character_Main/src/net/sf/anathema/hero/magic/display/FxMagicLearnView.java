package net.sf.anathema.hero.magic.display;

import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

public class FxMagicLearnView implements MagicLearnView {
  private final Announcer<MagicViewListener> control = Announcer.to(MagicViewListener.class);
  private final JList availableMagicList = new JList(new DefaultListModel());
  private final JList learnedMagicList = new JList(new DefaultListModel());
  private final JPanel centerButtons = new JPanel(new GridLayout(0, 1));
  private final JPanel endButtons = new JPanel(new GridLayout(0, 1));

  @Override
  public void setAvailableMagic(List magics) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setLearnedMagic(List magics) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addMagicViewListener(MagicViewListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean hasMoreThanOneElementLearned() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean hasAnyElementLearned() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addLearnedListListener(ChangeListener changeListener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
