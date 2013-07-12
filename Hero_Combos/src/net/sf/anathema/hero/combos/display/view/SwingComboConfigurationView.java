package net.sf.anathema.hero.combos.display.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboViewListener;
import net.sf.anathema.hero.combos.display.presenter.ComboViewProperties;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwingComboConfigurationView implements ComboConfigurationView, IView {
  private static final int TEXT_COLUMNS = 20;
  private final JPanel viewPort = new JPanel(new MigLayout(new LC().insets("6").fill().wrapAfter(5)));
  private final JComponent content = new JScrollPane(viewPort);
  private final Announcer<ComboViewListener> comboViewListeners = Announcer.to(ComboViewListener.class);
  private final JPanel namePanel = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(1)));

  @Override
  public void initGui(final ComboViewProperties viewProperties) {
    viewPort.add(new JLabel(viewProperties.getAvailableComboCharmsLabel()));
    viewPort.add(new JLabel());
    viewPort.add(new JLabel(viewProperties.getComboedCharmsLabel()));
    viewPort.add(new JLabel());
    viewPort.add(namePanel, new CC().spanY(2).alignY("top"));
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties viewProperties){
    SwingMagicLearnView magicLearnView = new SwingMagicLearnView(viewProperties);
    magicLearnView.addTo(viewPort);
    return magicLearnView;
  }

  @Override
  public ComboContainer addComboContainer() {
    SwingComboContainer container = new SwingComboContainer();
    container.adjustBackgroundColor(viewPort.getBackground());
    viewPort.add(container.getComponent(), new CC().spanX().grow().push());
    return container;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void addComboViewListener(final ComboViewListener listener) {
    comboViewListeners.addListener(listener);
  }

  @Override
  public ITextView addComboNameView(String viewTitle) {
    LineTextView textView = new LineTextView(TEXT_COLUMNS);
    return addTextView(viewTitle, textView);
  }

  @Override
  public ITextView addComboDescriptionView(String viewTitle) {
    AreaTextView textView = new AreaTextView(5, TEXT_COLUMNS);
    return addTextView(viewTitle, textView);
  }

  private ITextView addTextView(String viewTitle, SwingTextView textView) {
    namePanel.add(new JLabel(viewTitle));
    namePanel.add(textView.getComponent(), new CC().growX());
    return textView;
  }
}