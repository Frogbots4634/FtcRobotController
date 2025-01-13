package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MotorPowers;


import java.util.Locale;

import static java.lang.Math.abs;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMush;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushOpen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCan;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Uppy;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyD;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyPlaceHC;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;

@Autonomous
public class MoveInOrder extends LinearOpMode {
    IntoTheDeepDriveBase intoTheDeepDriveBase;

    private void updateOdoTelemetry() {
        odo.update();
        Pose2D pos = odo.getPosition();
        String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.INCH), pos.getY(DistanceUnit.INCH), pos.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Position", data);
        telemetry.addData("Status", odo.getDeviceStatus());
        telemetry.update();
    }
    private void TurnR(double H){
        Pose2D pos = odo.getPosition();
        double GetH = pos.getHeading(AngleUnit.DEGREES);
        double Error = H - GetH;
        double Kp = (double) 1/180;
        double LCorrection = Error * Kp;
        double RCorrection = Error * -Kp;
        boolean hDone = GetH < (H+1) && GetH > (H-1);
        MotorPowers motorPowers = new MotorPowers();
        while (!hDone){
            odo.update();
            pos = odo.getPosition();
            Error = H - GetH;
            LCorrection = Error * Kp;
            RCorrection = Error * -Kp;
            GetH = pos.getHeading(AngleUnit.DEGREES);
            intoTheDeepDriveBase.setMotorPowers(LCorrection, RCorrection);
            hDone = GetH < (H+1) && GetH > (H-1);
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
        updateOdoTelemetry();
    }
    private void TurnL(double H){
        Pose2D pos = odo.getPosition();
        double GetH = pos.getHeading(AngleUnit.DEGREES);
        double Error = H - GetH;
        double Kp = (double) 1/180;
        double LCorrection = Error * -Kp;
        double RCorrection = Error * Kp;
        boolean hDone = GetH < (H+1) && GetH > (H-1);
        MotorPowers motorPowers = new MotorPowers();
        while (!hDone){
            odo.update();
            pos = odo.getPosition();
            Error = H - GetH;
            LCorrection = Error * -Kp;
            RCorrection = Error * Kp;
            GetH = pos.getHeading(AngleUnit.DEGREES);
            intoTheDeepDriveBase.setMotorPowers(LCorrection, RCorrection);
            hDone = GetH < (H+1) && GetH > (H-1);
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
        updateOdoTelemetry();
    }
    private void StraightNT(double X, double H){
        odo.update();
        Pose2D pos = odo.getPosition();
        double GetX = pos.getX(DistanceUnit.INCH);
        double GetH = pos.getHeading(AngleUnit.DEGREES);
        double Kp = (double) 1/48;
        double hKp = (double) 1/25;
        boolean Done = ((GetX < (X + 1)) && (GetX > (X - 1))) && ((GetH < (H + 1)) && (GetH > (H - 1)));
        while (!Done) {
            odo.update();
            pos = odo.getPosition();
            GetX = pos.getX(DistanceUnit.INCH);
            GetH = pos.getHeading(AngleUnit.DEGREES);
            double Error = X - GetX;
            double hError = H - GetH;
            double Correction = Error * Kp;
            double HLCorrection = hError * -hKp;
            double HRCorrection = hError * hKp;
            double LCorrection = Correction + HLCorrection;
            double RCorrection = Correction + HRCorrection;
            intoTheDeepDriveBase.setMotorPowers(LCorrection,RCorrection);
            Done = (GetX < (X+1) && GetX >(X-1)) && (GetH < (H+1) && GetH > (H-1));
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
//        telemetry.addData("I did it are you happy with me now",GetX);

        }
    private void StrafeNT(double Y, double H){
        odo.update();
        Pose2D pos = odo.getPosition();
        double GetY = pos.getY(DistanceUnit.INCH);
        double GetH = pos.getHeading(AngleUnit.DEGREES);
        double Kp = (double) 1/48;
        double hKp = (double) 1/25;
        boolean Done = ((GetY < (Y + 1)) && (GetY > (Y - 1))) && ((GetH < (H + 1)) && (GetH > (H - 1)));
        while (!Done) {
            odo.update();
            pos = odo.getPosition();
            GetY = pos.getY(DistanceUnit.INCH);
            GetH = pos.getHeading(AngleUnit.DEGREES);
            double Error = Y - GetY;
            double YFLCorrection = Error * -Kp;
            double YFRCorrection = Error * Kp;
            double YRLCorrection = Error * Kp;
            double YRRCorrection = Error * -Kp;

            double hError = H - GetH;
            double HLCorrection = hError * -hKp;
            double HRCorrection = hError * hKp;

            double FLCorrection = YFLCorrection + HLCorrection;
            double FRCorrection = YFRCorrection + HRCorrection;
            double RLCorrection = YRLCorrection + HLCorrection;
            double RRCorrection = YRRCorrection + HRCorrection;

            intoTheDeepDriveBase.setMotorPowers(FLCorrection,FRCorrection,RLCorrection,RRCorrection);
            Done = (GetY < (Y+1) && GetY >(Y-1)) && (GetH < (H+1) && GetH > (H-1));
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
    }
    private void Straight(double X){
        odo.update();
        Pose2D pos = odo.getPosition();
        double GetX = pos.getX(DistanceUnit.INCH);
        double Error;
        double Kp;
        double Correction;
        boolean xDone = GetX < (X+.5) && GetX >(X-.5);
//        MotorPowers motorPowers = new MotorPowers();
        while (!xDone) {
            odo.update();
            pos = odo.getPosition();
            GetX = pos.getX(DistanceUnit.INCH);
            Error = X - GetX;
            Kp = (double) 1/80;
            Correction = Error * Kp;
            xDone = GetX < (X+1) && GetX >(X-1);
            intoTheDeepDriveBase.setMotorPowers(Correction);
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
        updateOdoTelemetry();
    }
    private void Strafe(double Y){
        Pose2D pos = odo.getPosition();
        double GetY = pos.getY(DistanceUnit.INCH);
        double Error;
        double Kp;
        double FLCorrection;
        double FRCorrection;
        double RLCorrection;
        double RRCorrection;
//        MotorPowers motorPowers = new MotorPowers();
        boolean yDone = GetY < (Y+1) && GetY >(Y-1);
        while (!yDone) {
            odo.update();
            pos = odo.getPosition();
            GetY = pos.getY(DistanceUnit.INCH);
            Error = Y - GetY;
            Kp = (double) 1/80;
            FLCorrection = Error * -Kp;
            FRCorrection = Error * Kp;
            RLCorrection = Error * Kp;
            RRCorrection = Error * -Kp;
            yDone = GetY < (Y+1) && GetY >(Y-1);
            intoTheDeepDriveBase.setMotorPowers(FLCorrection,FRCorrection,RLCorrection,RRCorrection);
            updateOdoTelemetry();
        }
        intoTheDeepDriveBase.setMotorPowers(0);
        telemetry.addData("it worked yay :( i is tired", GetY);
        updateOdoTelemetry();
    }

    @Override
    public void runOpMode() {

        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
//        UppyHangs = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyHangs");


        // Servos
        TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
        UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
        Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
        Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
        SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
        TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );


        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");

        odo.setOffsets(170, 58); //these are tuned for 3110-0002-0001 Product Insight #1

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);


        odo.recalibrateIMU();
        odo.resetPosAndIMU();

        telemetry.addData("Status", "Initialized");
        telemetry.addData("X offset", odo.getXOffset());
        telemetry.addData("Y offset", odo.getYOffset());
        telemetry.update();

        intoTheDeepDriveBase = new IntoTheDeepDriveBase();
        intoTheDeepDriveBase.init(hardwareMap);
        intoTheDeepDriveBase.enablePID();

//        SmushMushClosed();
        waitForStart();

        while (opModeIsActive()) {

            updateOdoTelemetry();
            StrafeNT(48,90);
//            runGamepad();
//            if (gamepad1.a){
//                Specimen();
//            }
//            if (gamepad1.b){
//                tesy();
//            }
        }
    }
    void runGamepad() {

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        float  speedFactor  = (float) .5;

        MecanumDrive.cartesian(intoTheDeepDriveBase, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);

    }
//    void Specimen(){
//        Straight(48);
//        sleep(30000);
//    }
//    void tesy(){
//        StraightNT(48,15);
//        sleep(30000);
//    }
}