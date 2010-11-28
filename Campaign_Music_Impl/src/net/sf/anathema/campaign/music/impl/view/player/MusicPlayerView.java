package net.sf.anathema.campaign.music.impl.view.player;

import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;

public class MusicPlayerView implements IMusicPlayerView {

  private final JButton playButton = new JButton();
  private final JButton stopButton = new JButton();
  private final JLabel totalTimeLabel = new JLabel();
  private final JLabel elapsedTimeLabel = new JLabel();
  private final JSlider slider = new JSlider(0, 100, 0);
  private JPanel content;

  public JComponent getContent(IMusicPlayerProperties properties) {
    if (content == null) {
      content = createContent(properties);
    }
    return content;
  }

  private JPanel createContent(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.add(slider);
    panel.add(createTimeDisplay(properties));
    panel.add(createControlDisplay());
    panel.setBorder(new TitledBorder(properties.getPlayerBorderString()));
    return panel;
  }

  private JComponent createTimeDisplay(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(properties.getTimeString() + ":")); //$NON-NLS-1$
    panel.add(elapsedTimeLabel);
    panel.add(new JLabel("/")); //$NON-NLS-1$
    panel.add(totalTimeLabel);
    return panel;
  }

  private JComponent createControlDisplay() {
    JPanel panel = new JPanel(new GridLayout(1, 0));
    panel.add(playButton);
    panel.add(stopButton);
    return panel;
  }

  public void setPlayAction(Action playAction) {
    playButton.setAction(playAction);
  }

  public void setStopAction(Action action) {
    stopButton.setAction(action);
  }

  public void addPositionChangeListener(ChangeListener listener) {
    slider.addChangeListener(listener);
  }

  public int getCurrentPosition() {
    return slider.getValue();
  }

  public void setMaximumPosition(int maximum, String time) {
    slider.setMaximum(maximum);
    totalTimeLabel.setText(time);
  }

  public void setCurrentPosition(int position, String time) {
    if (slider.getValueIsAdjusting()) {
      return;
    }
    slider.setValue(position);
    elapsedTimeLabel.setText(time);
  }
}