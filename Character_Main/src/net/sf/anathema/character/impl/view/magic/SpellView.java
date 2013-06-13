package net.sf.anathema.character.impl.view.magic;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identified;
import org.jmock.example.announcer.Announcer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SpellView implements ISpellView {
  private MagicLearnView magicLearnView;
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final Announcer<ObjectValueListener> circleControl = Announcer.to(ObjectValueListener.class);
  private final SpellViewProperties properties;

  public SpellView(final SpellViewProperties properties) {
    this.properties = properties;
    this.magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(JButton button, JList list) {
        return properties.getRemoveButtonEnabledListener(button, list);
      }
    };
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui(Identified[] circles) {
    content.add(new JLabel(properties.getCircleLabel()), new CC().split(2));
    final JComboBox box = new JComboBox(circles);
    box.setRenderer(properties.getCircleSelectionRenderer());
    content.add(box, new CC().wrap());
    box.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        circleControl.announce().valueChanged(box.getSelectedItem());
      }
    });
    magicLearnView.init(properties);
    magicLearnView.addTo(content);
  }

  @Override
  public void addMagicViewListener(IMagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
  }

  @Override
  public void addOptionListListener(ListSelectionListener listener) {
    magicLearnView.addOptionListListener(listener);
  }

  @Override
  public void addSelectionListListener(ListSelectionListener listener) {
    magicLearnView.addSelectionListListener(listener);
  }

  @Override
  public void addCircleSelectionListener(ObjectValueListener<CircleType> listener) {
    circleControl.addListener(listener);
  }

  @Override
  public void setLearnedMagic(Object[] spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  @Override
  public void setMagicOptions(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  @Override
  public void addLearnedMagic(Object[] magics) {
    magicLearnView.addLearnedMagic(magics);
  }

  @Override
  public void addMagicOptions(Identified[] magics, Comparator<Identified> comparator) {
    magicLearnView.addMagicOptions(magics, comparator);
  }

  @Override
  public void removeLearnedMagic(Object[] magics) {
    magicLearnView.removeLearnedMagic(magics);
  }

  @Override
  public void removeMagicOptions(Object[] magics) {
    magicLearnView.removeMagicOptions(magics);
  }

  @Override
  public void clearSelection() {
    magicLearnView.clearSelection();
  }
}