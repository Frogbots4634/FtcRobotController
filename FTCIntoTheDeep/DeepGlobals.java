//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCIntoTheDeep;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;

import java.util.Locale;


public class DeepGlobals {

    public static GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer
    public static IntoTheDeepDriveBase driveBase;
    public static TrackingWheelIntegrator trackingWheelIntegrator;
    public static LinearOpMode opMode;
    public static LynxModule odoModule;
    public static double inch;
    public static double cmToInch;
    public static IntoTheDeepDriveBase robot;
    public static double Y;
    public static double X;
    public static double wheelH;
    public static double LastY;
    public static double LastX;


    IntoTheDeepDriveBase intoTheDeepDriveBase;
//
// Other Motors
    public static DcMotorEx Uppy;
    public static DcMotorEx UppyHangs;
    public static DcMotorEx UppyClings;

    // Servos
    public static Servo TheToungue;
    public static Servo UppyMini;
    public static Servo Claw;
    public static Servo Wrist;
    public static Servo Lifty;        //odemetry
    public static Servo Holdy;        //odemetry
    public static Servo SmushMush;
    public static Servo TrashCan;     //bucket
    public static Servo Spinnie;
    public static Servo Wheelie;
    public static Servo Booper;
    // probably important
    public static float Rservopos;
    public static float Lservopos;

    public static RevTouchSensor Maget;
    // Local Variables
    public static double leftStickX;
    public static double leftStickY;
    public static double rightStickX;
    public static float  speedFactor;

    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
//    IntoTheDeepDriveBase intoTheDeepDriveBase;

    //    public static Boolean Odemetry = false;
    //    private float  speedFactor;
    public static double UppyCounts;
    public static double UppyHangCounts;
    public static double UppyClingCounts;
    public static boolean BackUpSpecimen = false;
    public static boolean ClawToggle = false;
    public static boolean SmushMushDew = false;
    public static boolean PlaceSpecimen = false;
    public static boolean SpinnieToogle = false;
    public static boolean TrashCanToogle = false;
    public static boolean ToungeToogle = false;
    public static boolean AutoDone = false;

    public static int UppyLowB = 4600;
    public static int UppyHighB = 4000;
    public static int UppyPlaceHC = 2000;
    public static int UppyHighC = 2700;
    public static int UppyD = 5;
    public static int HangHangsHang =0;
    public static int HangGoUppy = -1300;

    public static double ClawOpen = .1;
    public static double ClawClosed = 0 ;
    public static double ClawOPEN = .3;
    public static double SmushMushOpen = .7;
    public static double SmushMushClosed = .5;
    public static double ToungueFar = .45;
    public static double ToungueClose = .65;
    public static double ToungueIn = 1;
    public static double Wriststraight = .2;
    public static double WristR45 = .4;
    public static double WristL45 = 0;
    public static double Wrist90 = .55;
    public static double UppyMiniDown = .9;
    public static double UppyMinistraight = .7;
    public static double UppyMiniIn = 0;
    public static double TrashCanDump = .4;
    public static double TrashCanStore = 1;
    public static double TrashCanReady = .7;
    public static double UppyMiniUp = .4;
    public static double UppyMiniSTRAIGHT = .9;
    public static double UppyMiniReadyPlace = .575;
    public static double UppyMiniPlace = .2;
    public static double In = 1;
    public static double Out = 0;
    public static double None = .5;
    public static double NoBoop = 0;
    public static double Boop = .4;
    public static double HoldyHold = 0;
    public static double LiftyLift = 1;
    public static double LiftyLoose = .5;
    public static double HoldyLoose = .5;




//    public static double  = .1;

    public static void  Hold() {
        Lifty.setPosition(LiftyLift);
        Holdy.setPosition(HoldyHold);
    }
    public static void  Loose() {
        Lifty.setPosition(LiftyLoose);
        Holdy.setPosition(HoldyLoose);
    }
    public static void UppyHB() {
        Uppy.setTargetPosition(UppyHighB);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Uppy.setPower(.75);
    }
    public static void UppyLB() {
        Uppy.setTargetPosition(UppyLowB);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Uppy.setPower(.75);
    }

    public static void UppyHC() {
        Uppy.setTargetPosition(UppyHighC);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Uppy.setPower(.75);
    }
    public static void UppyPlaceHC() {
        Uppy.setTargetPosition(UppyPlaceHC);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Uppy.setPower(.75);
    }
    public static void UppyD()  {
        Uppy.setTargetPosition(UppyD);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Uppy.setPower(.75);
    }
    public static void UppyDown()  {
        while (!Maget.isPressed()) {
            Uppy.setPower(-1);
        }
        UppyCounts = 0;
    }
    public static void HangGoUppy(){
        UppyHangs.setTargetPosition( HangGoUppy);
        UppyHangs.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        UppyHangs.setPower(1);

        UppyClings.setTargetPosition( HangGoUppy);
        UppyClings.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        UppyClings.setPower(1);

        Loose();

    }
    public static void HangHangsHang(){
        UppyHangs.setTargetPosition( HangHangsHang);
        UppyHangs.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        UppyHangs.setPower(1);

        UppyClings.setTargetPosition( HangHangsHang);
        UppyClings.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        UppyClings.setPower(1);

        Hold();
    }
    public static void ClawOpen()   {
        Claw.setPosition(ClawOpen);
    }
    public static void ClawClosed() {
        Claw.setPosition(ClawClosed);
    }
    public static void ClawOPEN ()  {
        Claw.setPosition(ClawOPEN);
    }
    public static void SmushMushOpen () {
        SmushMush.setPosition(SmushMushOpen);
    }
    public static void SmushMushClosed()    {
        SmushMush.setPosition(SmushMushClosed);
    }
    public static void ToungueFar() {
        TheToungue.setPosition(ToungueFar);
    }
    public static void ToungueClose()   {
        TheToungue.setPosition(ToungueClose);
    }
    public static void ToungueIn()  {
        TheToungue.setPosition(ToungueIn);
    }
    public static void Wriststraight()  {
        Wrist.setPosition(Wriststraight);
    }
    public static void WristL45()   {
        Wrist.setPosition(WristL45);
    }
    public static void WristR45()   {
        Wrist.setPosition(WristR45);
    }
    public static void Wrist90()    {
        Wrist.setPosition(Wrist90);
    }
    public static void UppyMiniDown()   {
        UppyMini.setPosition(UppyMiniDown);
    }
    public static void UppyMinistraight()   {
        UppyMini.setPosition(UppyMinistraight);
    }
    public static void UppyMiniIn() {
        UppyMini.setPosition(UppyMiniIn);
    }
    public static void TrashCanDump()   {
        TrashCan.setPosition(TrashCanDump);
    }
    public static void TrashCanStore()  {
        TrashCan.setPosition(TrashCanStore);
    }
    public static void TrashCanReady()  {
        TrashCan.setPosition(TrashCanReady);
    }
    public static void UppyMiniUp() {
        UppyMini.setPosition(UppyMiniUp);
    }
    public static void UppyMiniSTRAIGHT()   {
        UppyMini.setPosition(UppyMiniSTRAIGHT);
    }
    public static void UppyMiniReadyPlace() {
        UppyMini.setPosition(UppyMiniReadyPlace);
    }
    public static void UppyMiniPlace()  {
        UppyMini.setPosition(UppyMiniPlace);
    }
    public static void In() {
        Spinnie.setPosition(In);
        Wheelie.setPosition(Out);
    }
    public static void Out() {
        Spinnie.setPosition(Out);
        Wheelie.setPosition(In);
    }
    public static void  None() {
        Spinnie.setPosition(None);
        Wheelie.setPosition(None);
    }
    public static void  NoBoop() {
        Booper.setPosition(NoBoop);
    }
    public static void  Boop() {
        Booper.setPosition(Boop);
    }

//    public static void clearEnc()        {
////        ctrl.setMotorMode(3, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        ctrl.setMotorMode(1, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        ctrl.setMotorMode(2, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////
////
////        ctrl.setMotorMode(3, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
////        ctrl.setMotorMode(1, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
////        ctrl.setMotorMode(2, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//    }

    public static void ResetHX() {
        LastX =trackingWheelIntegrator.getX();
        LastY =trackingWheelIntegrator.getY();

    }

    public static void updateTracking()
    {

        //LynxModule.BulkData bulkData = odoModule.getBulkData();
        //'int UppyEC= bulkData.getMotorCurrentPosition(0);

        //odo.update();
        Pose2D pos = odo.getPosition();
        String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.MM), pos.getY(DistanceUnit.MM), pos.getHeading(AngleUnit.DEGREES));
        opMode.telemetry.addData("Position", data);
        opMode.telemetry.addData("Status", odo.getDeviceStatus());

//        FrightDistance = FrightDetector.getDistance(DistanceUnit.MM);
//        FrontDistance = FrontDS.getDistance(DistanceUnit.INCH);
//        cmToInch = Globals.FrontSonar.getDistanceSync();
//        inch = cmToInch/2.54;
//        int left = bulkData.getMotorCurrentPosition(2);
//
//        //left = (int) (left * 1.02462166);
//        int right = bulkData.getMotorCurrentPosition(1);
//        int aux = bulkData.getMotorCurrentPosition(3);
//        LiftPower = 0.1 + (LiftTarget - LiftPos)/LiftTarget;
//        trackingWheelIntegrator.update(left, right, aux);
//
//        Y = trackingWheelIntegrator.getY();
//        X = trackingWheelIntegrator.getX();
//        wheelH = trackingWheelIntegrator.getHeading();

//        trackingWheelIntegrator.update(left, right, aux);

//        Y = trackingWheelIntegrator.getY();
//        X = trackingWheelIntegrator.getX();
//        wheelH = trackingWheelIntegrator.getHeading();

//        opMode.telemetry.addData("X", X);
//        //opMode.telemetry.addData("FrontInch", inch);
//        opMode.telemetry.addData("Y", Y);
//        opMode.telemetry.addData("wheelH", wheelH);
        opMode.telemetry.update();

    }
}