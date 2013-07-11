package net.sf.anathema.hero.spells.display;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.hero.magic.display.SwingMagicLearnView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingSpellView implements SpellView, IView {
  private SwingMagicLearnView magicLearnView;
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final Announcer<ObjectValueListener> circleControl = Announcer.to(ObjectValueListener.class);
  private ISpellViewProperties properties;

  @Override
  public void prepare(final ISpellViewProperties properties) {
    this.properties = properties;
    this.magicLearnView = new SwingMagicLearnView();
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui(Identifier[] circles) {
    content.add(new JLabel(properties.getCircleLabel()), new CC().split(2));
    final JComboBox<Identifier> box = new JComboBox<>(circles);
    box.setRenderer(new ConfigurableListCellRenderer(properties.getCircleSelectionRenderer()));
    content.add(box, new CC().wrap());
    box.addActionListener(new ActionListener() {
      @SuppressWarnings("unchecked")
      @Override
      public void actionPerformed(ActionEvent e) {
        circleControl.announce().valueChanged(box.getSelectedItem());
      }
    });
    magicLearnView.init(properties);
    magicLearnView.addTo(content);
  }


  @Override
  public void addMagicViewListener(MagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
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
  public void setMagicOptions(List spells) {
    magicLearnView.setMagicOptions(spells);
  }

  @Override
  public void addLearnedMagic(List magics) {
    magicLearnView.addLearnedMagic(magics);
  }

  @Override
  public void addMagicOptions(List<Identifier> magics, Comparator<Identifier> comparator) {
    magicLearnView.addMagicOptions(magics, comparator);
  }

  @Override
  public void removeLearnedMagic(Object[] magics) {
    magicLearnView.removeLearnedMagic(magics);
  }

  @Override
  public void removeMagicOptions(List magics) {
    magicLearnView.removeMagicOptions(magics);
  }

  @Override
  public void clearSelection() {
    magicLearnView.clearSelection();
  }
}