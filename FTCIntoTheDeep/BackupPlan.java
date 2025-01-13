//package org.firstinspires.ftc.teamcode.FTCIntoTheDeep;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
//import org.firstinspires.ftc.teamcode.FTCCenterStage.MecanumDrive;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoInnieV2;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoOutieV2;
//import org.firstinspires.ftc.teamcode.FTCCenterStage.drivebase.CenterStageDriveBase;
//
//import androidx.annotation.IntRange;
//
//import static org.firstinspires.ftc.teamcode.FTCCenterStage.Globals.Lift;
//
//@TeleOp
//public class BackupPlan extends LinearOpMode {
//
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
//    // Local Variables
//    private double leftStickX;
//    private double leftStickY;
//    private double rightStickX;
//    private float  speedFactor;
//
//    // Magic state machine thing
//    public static boolean RESETME;
//
//    // Sysinternals, dont touch
//    IntoTheDeepDriveBase centerStageDriveBase;
//    TrackingWheelIntegrator trackingWheelIntegrator;
//
//    // State Machine
//    GoOutieV2 GO = new GoOutieV2();
//    GoInnieV2 GI = new GoInnieV2();
//
//    public static double UppyCounts;
//    public static boolean Odemetry = false;
//    private boolean ClawOpen = false;
//    private boolean SmushMushDew = false;
//    private boolean BackUpSpecimen = false;
//    private boolean PlaceSpecimen = false;
//    //    private float  speedFactor;
//    public void runOpMode() {
//
//        // Drive Motors
//        FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
//        FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
//        RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
//        RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");
//
//        // Other Motors
//        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
////        UppyHangs = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyHangs");
//
//
//        // Servos
//        TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
//        UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
//        Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
//        Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
//        Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty" );
//        Holdy = (Servo) hardwareMap.get(Servo.class, "Holdy" );
//        SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
//        TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );
//
//
//        // Sysinit, don't touch
//        centerStageDriveBase = new CenterStageDriveBase();
//        centerStageDriveBase.init(hardwareMap);
//        centerStageDriveBase.enablePID();
//        Globals.robot=centerStageDriveBase;
//        Globals.driveBase=centerStageDriveBase;
//        Globals.trackingWheelIntegrator = trackingWheelIntegrator;
//        Globals.opMode = this;
//        Globals.robot.enableBrake(true);
//        trackingWheelIntegrator = new TrackingWheelIntegrator();
//
//        // Initial Servo positions
//        Rservopos = 1;
//        Lservopos = -1;
//
//        // Run to position
//        Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        Uppy.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//        Uppy.setTargetPosition(1);
//        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//
//        // Telemetry initialisation
//        telemetry.setMsTransmissionInterval(20);
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//
//
//        // StateM stuff
//        while (opModeIsActive()) {
//
//            // Telemetry
//            UppyCounts = Uppy.getCurrentPosition();
//            telemetry.addData("Uppy counts:", UppyCounts);
//            telemetry.addData("ServoRpos", Rservopos);
//            telemetry.addData("ServoLpos", Lservopos);
//            telemetry.update();
//
//            if (RESETME) {
//                GI.reset();
//                GO.reset();
//                TrashCan.setPosition(.8);
//
//                RESETME = false;
//            }
//            if (gamepad1.left_bumper) {
//                GI.runIteration();
//                runGamepad();
//            }
//            if (gamepad2.dpad_down && !RESETME) {
//                GO.runIteration();
//                runGamepad();
//            }
//            runGamepad();
//        }
//
//    }
//
//    void runGamepad() {
//
//        // Default speed, change with care
//        speedFactor = (float) .5;
//
//        // Map stick values to local variables
//        leftStickX = gamepad1.left_stick_x;
//        leftStickY = gamepad1.left_stick_y;
//        rightStickX = gamepad1.right_stick_x;
//
//        // If Statements
//
////  Controller 1
//        if (gamepad1.left_trigger > .5) {
//            TheToungue.setPosition(.65);
//            UppyMini.setPosition(.9);
//        }
//        if (gamepad1.right_trigger > .5) {
//            TheToungue.setPosition(.55);
//            UppyMini.setPosition(.9);
//        }
//        if (gamepad1.right_bumper) {
//            UppyMini.setPosition(.75);
//            Claw.setPosition(.3);
//            Wrist.setPosition(.2);
//        }
//        if (gamepad1.a) {
//            if (Claw.getPosition() == .3 && !ClawOpen) {
//                Claw.setPosition(.1);
//                ClawOpen = true;
//            } else if (Claw.getPosition() == .1 && !ClawOpen) {
//                Claw.setPosition(.3);
//                ClawOpen = true;
//            }
//            else if(!ClawOpen){
//                Claw.setPosition(.1);
//                ClawOpen = true;
//            }
//        } else {
//            ClawOpen = false;
//        }
//
//        if (gamepad1.b){
//            Claw.setPosition(0);
//        }
//        if (gamepad1.x) {
//            if (UppyMini.getPosition() == .4 && !BackUpSpecimen) {
//                UppyMini.setPosition(.8);
//                BackUpSpecimen = true;
//            }
//            else if (UppyMini.getPosition() == .8 && !BackUpSpecimen) {
//                UppyMini.setPosition(.4);
//                BackUpSpecimen = true;
//            }
//            else if(!BackUpSpecimen){
//                UppyMini.setPosition(.4);
//                BackUpSpecimen = true;
//            }
//        } else {
//            BackUpSpecimen = false;
//        }
//        if (gamepad1.y) {
//            if (UppyMini.getPosition() == .575 && !PlaceSpecimen) {
//                UppyMini.setPosition(.2);
//                PlaceSpecimen = true;
//            } else if (UppyMini.getPosition() == .2 && !PlaceSpecimen) {
//                UppyMini.setPosition(.575);
//                PlaceSpecimen = true;
//            }
//            else if(!PlaceSpecimen){
//                UppyMini.setPosition(.575);
//                PlaceSpecimen = true;
//            }
//        } else {
//            PlaceSpecimen = false;
//        }
//        if (gamepad1.dpad_up){
//            UppyMini.setPosition(.9);
//            Wrist.setPosition(.2);
//        }
//        if (gamepad1.dpad_left){
//            Wrist.setPosition(0);
//        }
//        if (gamepad1.dpad_right){
//            Wrist.setPosition(.4);
//        }
//        if (gamepad1.dpad_down){
//            Wrist.setPosition(.55);
//        }
//        /*
//        Lower bar = 600
//        Lower Bucket 3139
//         */
//        // Tim's stuff might use later
////        if (gamepad1.x) {
////            RHook.setPosition(1);
////            LHook.setPosition(0);
////            LHang.setPower(-.3);
////            RHang.setPower(-.3);
////        }
////        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
////            LHang.setPower(0);
////            RHang.setPower(0);
////        }
////
////        if (gamepad1.left_trigger > .5) {
////            RHook.setPosition(1);
////            LHook.setPosition(0);
////            LHang.setPower(-.95);
////            RHang.setPower(-.95);
////        }
////        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
////            LHang.setPower(0);
////            RHang.setPower(0);
////        }
////        if (gamepad1.dpad_right) {
////            RHook.setPosition(1);
////            LHook.setPosition(0);
////        }
//
////        LiftCounts = Lift.getCurrentPosition();
////
////        telemetry.addData("lift counts:", LiftCounts);
//
//
//
//
//        // Drive
//        MecanumDrive.cartesian(Globals.robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
//    }
//}
///*
//    Value Index
//        TrashCan Dump is .5
//        TrashCan retracted is .75
//        SmushMush open is .5
//        SmushMush closed is 7
//        Claw open = .1
//        Claw closed = 0
//        Uppy mini strait = .75
//        Uppy mini fold = 0
//        Uppy mini down = .95
//        Wrist 0.9
//        Wrist Forward = .2
//        Wrist L45 = 0
//        Wrist R45 = .4
//        Wrist 90 = .55
////        Holdy Loose = .25
////        Holdy tight = .4
////        Lifty Loose = 1
////        Lifty Tight = .75
//        TheToungue in = 1
//        TheToungue out = .45
// */
