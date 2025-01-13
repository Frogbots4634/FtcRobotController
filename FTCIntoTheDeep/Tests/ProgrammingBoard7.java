package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProgrammingBoard7 {
    private ColorSensor colorSensor;

    public void init(HardwareMap hwMap) {
        colorSensor=hwMap.get(ColorSensor.class, "sensor_color_distance");
    }
    public int getAmountRed(){
        return colorSensor.red();
    }
}
