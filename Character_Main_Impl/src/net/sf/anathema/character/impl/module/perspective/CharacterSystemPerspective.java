package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.impl.module.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.character.perspective.CharacterGridPresenter;
import net.sf.anathema.character.perspective.CharacterStackBridge;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.character.perspective.ShowOnSelect;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.CharacterStackSwingBridge;
import net.sf.anathema.swing.character.perspective.CharacterSystemView;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("King-icon.png");
    toggle.setTooltip("Character");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources) {
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterSystemView view = new CharacterSystemView(resources);
    CharacterStackBridge bridge = new CharacterStackSwingBridge(model, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    ShowOnSelect showOnSelect = new ShowOnSelect(stackPresenter);
    RegExCharacterPrintNameFileScanner fileScanner =
            new RegExCharacterPrintNameFileScanner(CharacterGenericsExtractor.getGenerics(model).getCasteCollectionRegistry(),
                    model.getRepository().getRepositoryFileResolver());
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), showOnSelect, fileScanner, resources);
    gridPresenter.initPresentation();
    new InteractionPresenter(systemModel, view.getInteractionView(), resources, view.getGridView(), showOnSelect).initPresentation();
    container.setSwingContent(view.getComponent());
  }
}
