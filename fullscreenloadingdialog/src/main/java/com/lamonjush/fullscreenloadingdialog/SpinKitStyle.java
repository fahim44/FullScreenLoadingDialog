package com.lamonjush.fullscreenloadingdialog;

import com.github.ybq.android.spinkit.Style;

public enum SpinKitStyle {
    ROTATING_PLANE(0),
    DOUBLE_BOUNCE(1),
    WAVE(2),
    WANDERING_CUBES(3),
    PULSE(4),
    CHASING_DOTS(5),
    THREE_BOUNCE(6),
    CIRCLE(7),
    CUBE_GRID(8),
    FADING_CIRCLE(9),
    FOLDING_CUBE(10),
    ROTATING_CIRCLE(11),
    MULTIPLE_PULSE(12),
    PULSE_RING(13),
    MULTIPLE_PULSE_RING(14);

    private int value;

    SpinKitStyle(int value) {
        this.value = value;
    }

    public Style convertToLibStyle() {
        switch (value) {
            case 0:
                return Style.ROTATING_PLANE;
            case 1:
                return Style.DOUBLE_BOUNCE;
            case 2:
                return Style.WAVE;
            case 3:
                return Style.WANDERING_CUBES;
            case 4:
                return Style.PULSE;
            case 5:
                return Style.CHASING_DOTS;
            case 6:
                return Style.THREE_BOUNCE;
            case 7:
                return Style.CIRCLE;
            case 8:
                return Style.CUBE_GRID;
            case 9:
                return Style.FADING_CIRCLE;
            case 10:
                return Style.FOLDING_CUBE;
            case 11:
                return Style.ROTATING_CIRCLE;
            case 12:
                return Style.MULTIPLE_PULSE;
            case 13:
                return Style.PULSE_RING;
            case 14:
                return Style.MULTIPLE_PULSE_RING;
        }
        return Style.CIRCLE;
    }
}
