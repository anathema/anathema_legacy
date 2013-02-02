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

  private CharacterStack characterStack = new CharacterStack();
  private CharacterButtonGrid buttonGrid = new CharacterButtonGrid();

  public String getTitle() {
    return "Character";
  }

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    JPanel panel = new JPanel(new BorderLayout());
    buttonGrid.fillFromRepository(model, characterStack);
    panel.add(buttonGrid.getContent(), BorderLayout.WEST);
    panel.add(characterStack.getContent(), BorderLayout.CENTER);
    return panel;
  }

}
