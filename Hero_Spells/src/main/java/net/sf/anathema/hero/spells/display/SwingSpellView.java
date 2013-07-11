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
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingSpellView implements SpellView, IView {
  private final SwingMagicLearnView magicLearnView = new SwingMagicLearnView();
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final Announcer<ObjectValueListener> circleControl = Announcer.to(ObjectValueListener.class);

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void initGui(Identifier[] circles, ISpellViewProperties properties) {
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
  public void setLearnedMagic(List spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  @Override
  public void setAvailableMagic(List spells) {
    magicLearnView.setAvailableMagic(spells);
  }
}