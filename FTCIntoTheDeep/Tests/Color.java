//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;

// hello this is jere i seeee u :)

@TeleOp
public class Color extends LinearOpMode {

//    // Drive Motors
//    private DcMotorEx FL;
//    private DcMotorEx FR;
//    private DcMotorEx RL;
//    private DcMotorEx RR;

    private ColorSensor colorSensor;


    public int getAmountRed(){
        return colorSensor.red();
    }
    public int getAmountBlue(){
        return colorSensor.blue();
    }
    public int getAmountGreen(){
        return colorSensor.green();
    }

    //    // Servos
//    private float Rservopos;
//    private float Lservopos;


    // Local Variables
    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private float speedFactor;



    public static double UppyCounts;
    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;


    // State Machine


    public void runOpMode() {

//        thing for color sensor
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");


        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Red:",getAmountRed());
            telemetry.addData("Blue:",getAmountBlue());
            telemetry.addData("Green:",getAmountGreen());
            telemetry.update();

        }
    }

    //private int getMotorCurrentPosition(int i) {
    //    return i;
    //}


    void runGamepad() {

        // Default speed, change with care
        speedFactor = (float) .5;

        // Map stick values to local variables
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;



//        else{
//        ClawPressed = false;
//        }
//
//        if (gamepad1.x){
//            Uppy.setPower(1);
//        }
//         else {
//            Uppy.setPower(0);
//        }


//        if
//
//        (gamepad1.x){
//            Uppy.setPower(1);


//        if (gamepad1.left_bumper){
//            Holdy.setPosition(1);
//        }

/*
         SmushMush open is .25
         SmushMush closed is 0
        Claw open = .1
        Claw closed = 0
        Uppy mini strait = .75
        Uppy mini fold = 0
        Uppy mini down = .95
        Wrist 0.9
        Wrist Forward = .2
        Wrist L45 = 0
        Wrist R45 = .4
        Wrist 90 = .55
        Holdy Loose = .25
        Holdy tight = .4
        Lifty Loose = 1
        Lifty Tight = .75
        TheToungue in = 1
        TheToungue out = .45
*/

            // Telemetry
//        telemetry.addData("lift counts:", Lift.getCurrentPosition());
//        telemetry.addData("ServoRpos", Rservopos);
//        telemetry.addData("ServoLpos", Lservopos);


            // Drive
//        uncomment maybe later
//        MecanumDrive.cartesian(Globals.robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
        }
    }


// VALUE INDEX
