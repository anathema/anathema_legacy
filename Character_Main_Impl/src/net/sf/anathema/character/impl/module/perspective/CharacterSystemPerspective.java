package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {


  public String getTitle() {
    return "Character";
  }

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterStackPresenter characterStack = new CharacterStackPresenter(model, systemModel);
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(createButtonGridContent(model, characterStack), BorderLayout.WEST);
    panel.add(characterStack.getContent(), BorderLayout.CENTER);
    return panel;
  }

  private JComponent createButtonGridContent(IAnathemaModel model, CharacterStackPresenter characterStack) {
    CharacterButtonGrid buttonGrid = new CharacterButtonGrid();
    buttonGrid.fillFromRepository(model, characterStack);
    return buttonGrid.getContent();
  }

}
