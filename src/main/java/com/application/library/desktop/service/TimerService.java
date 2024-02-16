package com.application.library.desktop.service;

import com.application.library.desktop.gui.common.TimeUpdatablePanel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TimerService {

    private static final List<TimeUpdatablePanel> timeUpdatablePanels = new ArrayList<>();

    private TimerService() {
        TaskExecutorService.scheduleAtFixedRate(this::run, 0, 1, TimeUnit.SECONDS);
    }

    public void run() {
        TaskExecutorService.submit(() -> timeUpdatablePanels.forEach(TimeUpdatablePanel::updateTime));
    }

    public static void addTimeUpdatablePanel(TimeUpdatablePanel timeUpdatablePanel) {
        timeUpdatablePanels.add(timeUpdatablePanel);
    }

}
