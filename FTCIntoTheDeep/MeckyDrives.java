//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/

package org.firstinspires.ftc.teamcode.FTCIntoTheDeep;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoInnie;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoOutie;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoDowny;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.BackUpSpecimen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Booper;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawOPEN;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawOpen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawToggle;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.HangGoUppy;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.HangHangsHang;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Lservopos;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.NoBoop;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.PlaceSpecimen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.RESETME;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Rservopos;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMush;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushDew;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushOpen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueClose;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueFar;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueIn;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCan;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanDump;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanReady;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanStore;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Uppy;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyD;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyDown;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyHB;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyCounts;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyHC;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyLB;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyPlaceHC;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniDown;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniPlace;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniReadyPlace;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniSTRAIGHT;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniUp;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist90;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.WristL45;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.WristR45;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wriststraight;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.robot;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Maget;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanToogle;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungeToogle;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyHangs;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyClings;

@TeleOp
public class MeckyDrives extends LinearOpMode {

//    // Drive Motors
//    public static DcMotorEx FL;
//    public static DcMotorEx FR;
//    public static DcMotorEx RL;
//    public static DcMotorEx RR;
//
//    // Other Motors
//    public static DcMotorEx Uppy;
////    private DcMotorEx UppyHangs;
//
//    // Servos
//    public static Servo TheToungue;
//    public static Servo UppyMini;
//    public static Servo Claw;
//    public static Servo Wrist;
//    private Servo Lifty;        //odemetry
//    private Servo Holdy;        //odemetry
//    public static Servo SmushMush;
//    public static Servo TrashCan;     //bucket
//
//    // probably important
//    private float Rservopos;
//    private float Lservopos;
//
//
    // Local Variables
    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private float  speedFactor;
//
//    // Magic state machine thing
//    public static boolean RESETME;
//
    // Sysinternals, dont touch
    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;

//    Color Sensor


    // State Machine
    GoDowny GD = new GoDowny();
    GoOutie GO = new GoOutie();
    GoInnie GI = new GoInnie();
//
//    public static double UppyCounts;
//    public static Boolean Odemetry = false;
//    private Boolean ClawOpen = false;
//    private Boolean SmushMushDew = false;
//    private Boolean PlaceSpecimen = false;
//    private Boolean BackUpSpecimen = false;
//    private float  speedFactor;
    public void runOpMode() {

        // Drive Motors
//        DeepGlobals.FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
//        DeepGlobals.FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
//        DeepGlobals.RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
//        DeepGlobals.RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");

        // Other Motors
        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
        UppyClings = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyClings");
        UppyHangs = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyHangs");


        // Servos
        TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
        UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
        Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
        Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
        DeepGlobals.Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty" );
        DeepGlobals.Holdy = (Servo) hardwareMap.get(Servo.class, "Holdy" );
        SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
        TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );
        Booper = (Servo) hardwareMap.get(Servo.class, "Booper" );


        Maget = hardwareMap.get(RevTouchSensor.class, "Maget");
//        Color Sensor

        // Sysinit, don't touch
        intoTheDeepDriveBase = new IntoTheDeepDriveBase();
        intoTheDeepDriveBase.init(hardwareMap);
        intoTheDeepDriveBase.enablePID();
        DeepGlobals.robot = intoTheDeepDriveBase;
        DeepGlobals.driveBase = intoTheDeepDriveBase;
        DeepGlobals.trackingWheelIntegrator = trackingWheelIntegrator;
        DeepGlobals.opMode = this;
        DeepGlobals.robot.enableBrake(true);
        trackingWheelIntegrator = new TrackingWheelIntegrator();

        // Initial Servo positions
        Rservopos = 1;
        Lservopos = -1;

        // Run to position
        Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        Uppy.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        Uppy.setTargetPosition(1);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        UppyHangs.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        UppyHangs.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        UppyHangs.setTargetPosition(1);
        UppyHangs.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        UppyClings.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        UppyClings.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        UppyClings.setTargetPosition(1);
        UppyClings.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // Telemetry initialisation
        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

    // StateM stuff
        while (opModeIsActive()) {

            // Telemetry
            UppyCounts = Uppy.getCurrentPosition();
            telemetry.addData("Uppy counts:", UppyCounts);
            telemetry.addData("ServoRpos", Rservopos);
            telemetry.addData("ServoLpos", Lservopos);
            telemetry.update();

            if (RESETME) {
                GO.reset();
                GI.reset();
                GD.reset();
                TrashCan.setPosition(.8);

                RESETME = false;
            }
            if (gamepad1.left_bumper) {
                GI.runIteration();
                runGamepad();
            }
            if (gamepad1.right_bumper) {
                GO.runIteration();
                runGamepad();
            }
            if (gamepad2.dpad_up) {
                GD.runIteration();
                runGamepad();
            }
            if (Maget.isPressed()) {
                UppyCounts = 0;
                runGamepad();
            }
            if (SmushMush.getPosition()== SmushMushClosed) {
                TrashCanStore();
                runGamepad();
            }
            runGamepad();
        }
    }
    void runGamepad() {
        // Default speed, change with care
        speedFactor = (float) .5;

        // Map stick values to local variables
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;
        rightStickX = gamepad1.right_stick_x;

//  Controller 1
        if (gamepad1.left_trigger > .5) {
//            ToungueClose();
            if (UppyMini.getPosition() == ToungueClose && !ToungeToogle) {
                ToungueIn();
                ToungeToogle = true;
            } else if (UppyMini.getPosition() == ToungueIn && !ToungeToogle) {
                ToungueClose();
                ToungeToogle = true;
            }
            else if(!PlaceSpecimen){
                ToungueIn();
                ToungeToogle = true;
            }
        } else {
            ToungeToogle = false;
        }
        if (gamepad1.right_trigger > .5) {
            ToungueFar();
        }
        if (gamepad1.a) {
            if (Claw.getPosition() == ClawOPEN && !ClawToggle) {
                ClawOpen();
                ClawToggle = true;
            } else if (Claw.getPosition() == ClawOpen && !ClawToggle) {
                ClawOPEN();
                ClawToggle = true;
            }
            else if(!ClawToggle){
                ClawOpen();
                ClawToggle = true;
            }
        } else {
            ClawToggle = false;
        }

        if (gamepad1.b){
            ClawClosed();
        }
        if (gamepad1.x) {
            if (UppyMini.getPosition() == UppyMiniUp && !BackUpSpecimen) {
                UppyMiniSTRAIGHT();
                BackUpSpecimen = true;
            }
            else if (UppyMini.getPosition() == UppyMiniSTRAIGHT && !BackUpSpecimen) {
                UppyMiniUp();
                BackUpSpecimen = true;
            }
            else if(!BackUpSpecimen){
                UppyMiniUp();
                BackUpSpecimen = true;
            }
        } else {
            BackUpSpecimen = false;
        }
        if (gamepad1.y) {
            if (UppyMini.getPosition() == UppyMiniReadyPlace && !PlaceSpecimen) {
                UppyMiniPlace();
                PlaceSpecimen = true;
            } else if (UppyMini.getPosition() == UppyMiniPlace && !PlaceSpecimen) {
                UppyMiniReadyPlace();
                PlaceSpecimen = true;
            }
            else if(!PlaceSpecimen){
                UppyMiniReadyPlace();
                PlaceSpecimen = true;
            }
        } else {
            PlaceSpecimen = false;
        }
        if (gamepad1.dpad_up){
            UppyMiniDown();
            Wriststraight();
        }
        if (gamepad1.dpad_left){
            WristL45();
        }
        if (gamepad1.dpad_right){
            WristR45();
        }
        if (gamepad1.dpad_down){
            Wrist90();
        }
//  Controller 2
        if (gamepad2.a) {
            if (SmushMush.getPosition() == SmushMushClosed && !SmushMushDew) {
                SmushMushOpen();
                SmushMushDew = true;
            } else if (SmushMush.getPosition() == SmushMushOpen && !SmushMushDew) {
                SmushMushClosed();
            }
            else if(!SmushMushDew){
                SmushMushOpen();
                SmushMushDew = true;
            }
        } else {
            SmushMushDew = false;
        }
        if (gamepad2.b){
            SmushMushClosed();
        }
        if (gamepad2.y){
            TrashCanReady();
        }
        if (gamepad2.x){
            TrashCanStore();
            //TrashCan.setPosition(.5);
        }
//        if (gamepad2.right_bumper){
//            Uppy.setTargetPosition(6000);
//            Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//            Uppy.setPower(.5);
//        }
        if (gamepad2.right_bumper){
            UppyLB();
        }

        if (gamepad2.right_stick_button){
            UppyDown();
        }
        if (gamepad2.left_stick_button){
            NoBoop();
        }

        if (gamepad2.left_trigger > .5){
            UppyHC();
        }

        if (gamepad2.left_bumper) {
            UppyPlaceHC();
        }
        if (gamepad2.right_trigger >.5){
            UppyHB();
        }
        if (gamepad2.dpad_up){
            HangGoUppy();
        }
        if (gamepad2.dpad_down){
            HangHangsHang();
        }


        // Drive
        MecanumDrive.cartesian(robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
    }
}
/*
 Tim's stuff might use later
        if (gamepad1.x) {
            RHook.setPosition(1);
            LHook.setPosition(0);
            LHang.setPower(-.3);
            RHang.setPower(-.3);
        }
        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
            LHang.setPower(0);
            RHang.setPower(0);
        }

        if (gamepad1.left_trigger > .5) {
            RHook.setPosition(1);
            LHook.setPosition(0);
            LHang.setPower(-.95);
            RHang.setPower(-.95);
        }
        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
            LHang.setPower(0);
            RHang.setPower(0);
        }
        if (gamepad1.dpad_right) {
            RHook.setPosition(1);
            LHook.setPosition(0);
        }
*/
/*
    Value Index
        TrashCan Dump is .5
        TrashCan retracted is .75
        SmushMush open is .5
        SmushMush closed is 7
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
//        Holdy Loose = .25
//        Holdy tight = .4
//        Lifty Loose = 1
//        Lifty Tight = .75
        TheToungue in = 1
        TheToungue out = .45
 */