package net.sf.anathema.hero.concept.display.description.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.view.AddToButtonPanel;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.concept.display.description.presenter.ICharacterDescriptionView;
import net.sf.anathema.hero.concept.display.description.presenter.IMultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import net.sf.anathema.swing.interaction.ActionInteraction;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class CharacterDescriptionView implements ICharacterDescriptionView, IView {

  private static final int TEXT_COLUMNS = 45;
  private final JPanel content = new JPanel(new MigLayout(withoutInsets().wrapAfter(2)));
  private final List<JPanel> buttonPanels = new ArrayList<>();

  @Override
  public IMultiComponentLine addMultiComponentLine() {
    MultiComponentLine componentLine = new MultiComponentLine(content);
    componentLine.init();
    return componentLine;
  }

  @Override
  public ITextView addLineView(String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMNS));
  }

  @Override
  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMNS));
  }

  private synchronized ITextView addTextView(String labelText, SwingTextView textView) {
    new LabelTextView(labelText, textView).addToMigPanel(content, new CC().split(2));
    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    content.add(buttonPanel);
    return textView;
  }

  @Override
  public Tool addEditAction() {
    ActionInteraction interaction = new ActionInteraction();
    JPanel mostRecentPanel = buttonPanels.get(buttonPanels.size()-1);
    interaction.addTo(new AddToButtonPanel(mostRecentPanel));
    return interaction;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }
}