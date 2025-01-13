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
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;



import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;

// hello this is jere i seeee u :)
@Disabled
@TeleOp
public class TestCodeTwo extends LinearOpMode {

//    // Drive Motors
//    private DcMotorEx FL;
//    private DcMotorEx FR;
//    private DcMotorEx RL;
//    private DcMotorEx RR;

    // Other Motors
    private DcMotorEx Uppy;


    //    // Servos
//    private float Rservopos;
//    private float Lservopos;
    private Servo Claw;
    private Servo Wrist;
    private Servo UppyMini;
    private Servo SmushMush;
    private Servo Holdy;
    private Servo Lifty;
    private Servo TheToungue;

    // Local Variables
    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private float speedFactor;

    private boolean ClawPressed = true;
    private boolean WristPressed = true;
    private boolean ResetWrist = true;
    private boolean UppyMiniPressed = true;
    private boolean UppyMiniReset = true;
    private double WristPosition = 0;

    public static double UppyCounts;
    // Magic state machine thing
    public static boolean RESETME;
    private ColorSensor colorSensor;

    // Sysinternals, dont touch
    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;


    // State Machine


    public void runOpMode() {

        // Drive Motors
//        FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
//        FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
//        RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
//        RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");
//
//        // Other Motors
        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
//        thing for color sensor

        // Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        // Servos
//
//        LCLaw = (Servo) hardwareMap.get(Servo.class, "LCLaw");
        Claw = (Servo) hardwareMap.get(Servo.class, "Claw");
        Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist");
        UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini");
        SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush");
        Holdy = (Servo) hardwareMap.get(Servo.class, "Holdy");
        Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty");
        TheToungue = (Servo) hardwareMap.get(Servo.class, "TheToungue");

        // Sysinit, don't touch
//        intoTheDeepDriveBase = new IntoTheDeepDriveBase();
//        intoTheDeepDriveBase.init(hardwareMap);
//        intoTheDeepDriveBase.enablePID();
//        DeepGlobals.robot= intoTheDeepDriveBase;
//        DeepGlobals.driveBase= intoTheDeepDriveBase;
//        DeepGlobals.trackingWheelIntegrator = trackingWheelIntegrator;
//        DeepGlobals.opMode = this;
//        DeepGlobals.robot.enableBrake(true);
//        trackingWheelIntegrator = new TrackingWheelIntegrator();

        // Initial Servo positions
//        Rservopos = 1;
//        Lservopos = -1;

//        // Run to position
        Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        Uppy.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        Uppy.setTargetPosition(1);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);



//        UppyEc = Lift.getCurrentPosition();
//        telemetry.addData("UppyEc", UppyEc);
//        telemetry.update();

//        int UppyEncoders;
//        int Uppycounts;
//
//        int UppyEC = getMotorCurrentPosition(3);
//
//        UppyEC = Uppy.getCurrentPosition();

//        Uppy.setDirection(DcMotorEx.Direction.FORWARD);
//        int UppyCurrent = UppyEC;
        // Telemetry initialisation
//        telemetry.addData("LiftEC", UppyEC);

        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            runGamepad();
            UppyCounts = Uppy.getCurrentPosition();
            telemetry.addData("Uppy counts:", UppyCounts);
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

        if (gamepad1.left_bumper) {
            Claw.setPosition(0);
        }
        if (gamepad1.right_bumper) {
            Claw.setPosition(.1);
        }
        if (gamepad1.a) {
            Wrist.setPosition(.2);
        }
        if (gamepad1.b) {
            Wrist.setPosition(0);
        }

        if (ResetWrist) {
            Wrist.setPosition(0.65);
            UppyMini.setPosition(0);
            ResetWrist = false;
        }

        if (gamepad1.dpad_right || gamepad1.dpad_left) {
            if (WristPressed == false) {
                if (gamepad1.dpad_right && Wrist.getPosition() < 1) {
                    Wrist.setPosition(Wrist.getPosition() + 0.2);
                }
                if (gamepad1.dpad_left && Wrist.getPosition() > 0) {
                    Wrist.setPosition(Wrist.getPosition() - 0.2);
                }
            }
            WristPressed = true;
        } else {
            WristPressed = false;
        }
        if (gamepad1.dpad_up || gamepad1.dpad_down) {
            if (UppyMiniPressed == false) {
                if (gamepad1.dpad_down && UppyMini.getPosition() == 0 && UppyMiniPressed == false) {
                    UppyMini.setPosition(.62);
                    UppyMiniPressed = true;
                } else if (gamepad1.dpad_down && UppyMini.getPosition() == .62 && UppyMiniPressed == false) {
                    UppyMini.setPosition(1);
                    UppyMiniPressed = true;
                } else if (gamepad1.dpad_up && UppyMini.getPosition() == 1 && UppyMiniPressed == false) {
                    UppyMini.setPosition(.62);
                    UppyMiniPressed = true;
                } else if (gamepad1.dpad_up && UppyMini.getPosition() == .62 && UppyMiniPressed == false) {
                    UppyMini.setPosition(0);
                    UppyMiniPressed = true;
                }
            }
        } else {
            UppyMiniPressed = false;
        }

        if (gamepad1.a) {
            UppyMini.setPosition(1);
        }
        if (gamepad1.b) {
            UppyMini.setPosition(.7);
        }
        if (gamepad1.x) {
            UppyMini.setPosition(.5);
        }
        if (gamepad1.y) {
            UppyMini.setPosition(.25);
        }


//        if
//
//        (gamepad1.x){
//            Uppy.setPower(1);
        if (gamepad1.x) {
            Wrist.setPosition(.4);
        }
        if (gamepad1.y) {
            Wrist.setPosition(.55);
        }
        if (gamepad1.dpad_up) {
            Lifty.setPosition(1);
        }
        if (gamepad1.dpad_down) {
            Lifty.setPosition(0);
        }
        if (gamepad1.dpad_left) {
            Lifty.setPosition(.75);
            if (gamepad1.dpad_down) {
                UppyMini.setPosition(.75);
            }
            if (gamepad1.dpad_right) {
                UppyMini.setPosition(0);
            }
            if (gamepad1.dpad_left) {
                UppyMini.setPosition(.95);
            }
            if (gamepad1.right_trigger > .5) {
                TheToungue.setPosition(.45);
            }
            if (gamepad1.left_trigger > .5) {
                TheToungue.setPosition(1);
            }
            if (gamepad1.dpad_right) {
                Lifty.setPosition(.25);
            }
            if (gamepad1.a) {
                Holdy.setPosition(.5);
            }
            // no ^^^^^^^^^
            if (gamepad1.b) {
                Holdy.setPosition(.45);
            }
            if (gamepad1.x) {
                Holdy.setPosition(.4);
            }
            if (gamepad1.y) {
                Holdy.setPosition(.25);
            }


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
}

// VALUE INDEX
