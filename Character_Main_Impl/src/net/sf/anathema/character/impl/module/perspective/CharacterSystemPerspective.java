package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {

  private CharacterStack characterStack = new CharacterStack();

  public String getTitle() {
    return "Character";
  }

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(createCharacterList(model), BorderLayout.WEST);
    panel.add(characterStack.getContent(), BorderLayout.CENTER);
    return panel;
  }

  private JComponent createCharacterList(final IAnathemaModel model) {
    JPanel buttonGrid = new JPanel(new GridLayout(0, 1));
    final IItemType characterItemType = collectCharacterItemType(model);
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    for (final PrintNameFile printNameFile : access.collectAllPrintNameFiles(characterItemType)) {
      buttonGrid.add(new JButton(new ShowCharacterAction(printNameFile, model, characterStack)));
    }
    JPanel panel = new JPanel();
    panel.add(buttonGrid);
    return panel;
  }

  private IItemType collectCharacterItemType(IAnathemaModel model) {
    return model.getItemTypeRegistry().getById("ExaltedCharacter");
  }

}
