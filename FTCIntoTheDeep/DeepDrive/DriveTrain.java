//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by michael on 10/2/18.
 */

public interface DriveTrain
{
    void init(HardwareMap hardwareMap);
    void setMotorPowers(MotorPowers pows);
    void setMotorPowers(double pow);
    void setMotorPowers(double left, double right);
    void setMotorPowers(double fl, double fr, double rl, double rr);
    void enableBrake(boolean brake);
    void stopMotors();
    void enablePID();
    void disablePID();
    void resetEncoders();
    void softResetEncoders();

    int encoderFrontLeft();
    int encoderFrontRight();
    int encoderRearLeft();
    int encoderRearRight();
}