package net.sf.anathema.scribe.perspective;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.sf.anathema.campaign.module.NoteTypeConfiguration;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.fx.PerspectivePane;

import java.util.Collection;

@PerspectiveAutoCollector
@Weight(weight = 200)
public class ScribePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("Kompass24.png");
    toggle.setTooltip("Scribe.Perspective.Name");
  }

  @Override
  public void initContent(Container container, final IAnathemaModel model, IResources resources) {
    final PerspectivePane perspectivePane = new PerspectivePane();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        VBox navigation = new VBox();
        for (PrintNameFile file : collectCharacterPrintNameFiles(model)) {
          navigation.getChildren().add(new Text(file.getPrintName()));
        }
        perspectivePane.setNavigationComponent(navigation);
      }
    });
    container.setSwingContent(perspectivePane.getComponent());
  }

  public Collection<PrintNameFile> collectCharacterPrintNameFiles(IAnathemaModel model) {
    IItemType characterItemType = NoteTypeConfiguration.ITEM_TYPE;
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }
}
