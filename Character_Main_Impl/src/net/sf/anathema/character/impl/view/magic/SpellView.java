package net.sf.anathema.character.impl.view.magic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;

public class SpellView implements ISpellView {

  private MagicLearnView magicLearnView;
  private JLabel countLabel;

  private JScrollPane content;
  private JComboBox circleComboBox;
  private final GenericControl<IObjectValueChangedListener> circleControl = new GenericControl<IObjectValueChangedListener>();
  private JPanel detailsPanel;
  private JLabel nameLabel;
  private JLabel circleLabel;
  private JLabel costLabel;
  private JLabel sourceLabel;

  public JComponent getComponent() {
    return content;
  }

  public void initGui(IIdentificate[] circles, final ISpellViewProperties properties) {
    magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return properties.getRemoveButtonEnabledListener(button, list);
      }
    };
    JPanel viewPort = new JPanel(new GridDialogLayout(4, false));
    viewPort.setBorder(new EmptyBorder(3, 6, 6, 6));
    JPanel filterPanel = new JPanel(new GridDialogLayout(2, false));
    filterPanel.add(new JLabel(properties.getCircleString()));
    circleComboBox = new JComboBox(circles);
    circleComboBox.setRenderer(properties.getCircleSelectionRenderer());
    circleComboBox.addActionListener(new ActionListener() {
      @SuppressWarnings("unchecked")
      public void actionPerformed(ActionEvent e) {
        circleControl.forAllDo(new IClosure<IObjectValueChangedListener>() {
          public void execute(IObjectValueChangedListener input) {
            input.valueChanged(null, circleComboBox.getSelectedItem());
          }
        });
      }
    });
    filterPanel.add(circleComboBox);
    viewPort.add(filterPanel);
    countLabel = new JLabel("(0)"); //$NON-NLS-1$
    viewPort.add(new JLabel());
    JPanel learnedSpellsPanel = new JPanel();
    learnedSpellsPanel.add(new JLabel(properties.getLearnedSpellString()));
    learnedSpellsPanel.add(countLabel);
    viewPort.add(learnedSpellsPanel);
    viewPort.add(new JLabel());
    magicLearnView.init(properties);
    magicLearnView.addToGridDialogLayoutPanel(viewPort, new JButton[] {});
    GridDialogLayoutData detailsData = new GridDialogLayoutData();
    detailsData.setHorizontalSpan(4);
    detailsData.setHorizontalAlignment(GridAlignment.FILL);
    viewPort.add(createDetailsPanel(properties), detailsData);
    content = new JScrollPane(viewPort);
  }

  public void setSpellDetails(String name, String circle, String costString, String sourceString) {
    nameLabel.setText(name);
    circleLabel.setText(circle);
    costLabel.setText(costString);
    sourceLabel.setText(sourceString);
  }

  private JComponent createDetailsPanel(ISpellViewProperties properties) {
    detailsPanel = new JPanel(new GridDialogLayout(2, false));
    detailsPanel.setBorder(new TitledBorder(properties.getDetailTitle()));
    nameLabel = new JLabel();
    nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
    GridDialogLayoutData nameData = new GridDialogLayoutData();
    nameData.setHorizontalSpan(2);
    detailsPanel.add(nameLabel, nameData);
    detailsPanel.add(new JLabel(properties.getCircleString() + ":")); //$NON-NLS-1$
    circleLabel = new JLabel();
    detailsPanel.add(circleLabel);
    detailsPanel.add(new JLabel(properties.getCostString() + ":")); //$NON-NLS-1$
    costLabel = new JLabel();
    detailsPanel.add(costLabel);
    detailsPanel.add(new JLabel(properties.getSourceString() + ":")); //$NON-NLS-1$
    sourceLabel = new JLabel();
    detailsPanel.add(sourceLabel);
    return detailsPanel;
  }

  public void addSpellViewListener(IMagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
  }

  public synchronized void addCircleSelectionListener(IObjectValueChangedListener listener) {
    circleControl.addListener(listener);
  }

  public synchronized void addSpellSelectionListener(ListSelectionListener listener) {
    magicLearnView.addSelectionListListener(listener);
    magicLearnView.addOptionListListener(listener);
  }

  public void setLearnedSpells(Object[] spells) {
    countLabel.setText("(" + spells.length + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    magicLearnView.setLearnedMagic(spells);
  }

  public void setAllSpells(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  public void clearSelection() {
    magicLearnView.clearSelection();
  }

  public boolean needsScrollbar() {
    return false;
  }
}