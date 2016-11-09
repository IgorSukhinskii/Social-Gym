package com.github.si1en7ium.socialgym.models;

import com.github.si1en7ium.socialgym.R;

public enum SportKind {
    RUNNING_JOGGING(1, R.string.sport_running_jogging),
    FITNESS(2, R.string.sport_crossfit_fitness),
    CYCLING(3, R.string.sport_cycling),
    COMBAT(4, R.string.sport_combat),
    OTHER(0, R.string.sport_other);

    private final int id;
    public final int resourceId;

    private SportKind(int id, int resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }
}
