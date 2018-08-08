package net.sparksnetwork.app.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler io();

    Scheduler ui();
}
