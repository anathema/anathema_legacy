package net.sf.anathema.character.impl.view.magic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;

public class SpellView implements ISpellView {

  private MagicLearnView magicLearnView;

  private JPanel content = new JPanel(new GridDialogLayout(1, false));
  private JComboBox circleComboBox;
  private final ObjectValueControl circleControl = new ObjectValueControl();
  private final IGridDialogPanel detailPanel = new DefaultGridDialogPanel();

  private final SpellViewProperties properties;

  public SpellView(final SpellViewProperties properties) {
    this.properties = properties;
    this.magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return properties.getRemoveButtonEnabledListener(button, list);
      }
    };
  }

  public JComponent getComponent() {
    return content;
  }

  public IValueView<String> addDetailValueView(String label) {
    LabelledStringValueView view = new LabelledStringValueView(label, new GridDialogLayoutData());
    view.addComponents(detailPanel);
    return view;
  }

  public void initGui(IIdentificate[] circles) {
    JComponent selectionPanel = createSelectionPanel(circles);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(GridAlignment.FILL);
    content.add(selectionPanel, data);
    final JPanel detailContent = detailPanel.getContent();
    detailContent.setBorder(new TitledBorder(properties.getDetailTitle()));
    content.add(detailContent, data);
  }

  private JPanel createSelectionPanel(IIdentificate[] circles) {
    JComboBox box = magicLearnView.addFilterBox(
        properties.getCircleString(),
        circles,
        properties.getCircleSelectionRenderer());
    box.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        circleControl.fireValueChangedEvent(circleComboBox.getSelectedItem());
      }
    });
    magicLearnView.init(properties);
    JPanel selectionPanel = new JPanel(new GridDialogLayout(4, false));
    magicLearnView.addTo(selectionPanel);
    selectionPanel.setBorder(new TitledBorder(properties.getSelectionTitle()));
    return selectionPanel;
  }

  public void addMagicViewListener(IMagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
  }

  public void addOptionListListener(ListSelectionListener listener) {
    magicLearnView.addOptionListListener(listener);
  }

  public void addSelectionListListener(ListSelectionListener listener) {
    magicLearnView.addSelectionListListener(listener);
  }

  public void addCircleSelectionListener(IObjectValueChangedListener listener) {
    circleControl.addObjectValueChangeListener(listener);
  }

  public void setLearnedMagic(Object[] spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  public void setMagicOptions(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  public void clearSelection() {
    magicLearnView.clearSelection();
  }

  public boolean needsScrollbar() {
    return false;
  }
}