package me.jesusmx.hubcore.utils.tablist.shared;

import me.jesusmx.hubcore.PornHub;

public class TabThread extends Thread {

    private TabHandler handler;

    /**
     * Constructor to make a new TabThread
     *
     * @param handler the handler to register it to
     */
    public TabThread(TabHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        PornHub.getInstance().getOnlinePlayers().forEach(this.handler::sendUpdate);
    }
}