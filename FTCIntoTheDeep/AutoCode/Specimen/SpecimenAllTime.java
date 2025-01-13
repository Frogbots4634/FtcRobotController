package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Specimen;

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
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyHC;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyPlaceHC;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;

@Autonomous(preselectTeleOp = "MeckyDrives")
public class SpecimenAllTime extends LinearOpMode {
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
        double Kp = (double) 1/abs(H);
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
        double Kp = (double) 1/abs(H);
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
    private void Straight(double X){
        odo.update();
        Pose2D pos = odo.getPosition();
        double GetX = pos.getX(DistanceUnit.INCH);
        double Error = X - GetX;
        double Kp = (double) 1/abs(X);
        double Correction = Error * Kp;
        boolean xDone = GetX < (X+.5) && GetX >(X-.5);
//        MotorPowers motorPowers = new MotorPowers();
        while (!xDone) {
            odo.update();
            pos = odo.getPosition();
            GetX = pos.getX(DistanceUnit.INCH);
            Error = X - GetX;
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
        double Error = Y - GetY;
        double Kp = (double) 1/abs(Y);
        double FLCorrection = Error * -Kp; 
        double FRCorrection = Error * Kp;
        double RLCorrection = Error * Kp;
        double RRCorrection = Error * -Kp;
//        MotorPowers motorPowers = new MotorPowers();
        boolean yDone = GetY < (Y+1) && GetY >(Y-1);
        while (!yDone) {
            odo.update();
            pos = odo.getPosition();
            GetY = pos.getY(DistanceUnit.INCH);
            Error = Y - GetY;
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

        SmushMushClosed();
        waitForStart();

        while (opModeIsActive()) {
//            odo.update();
//            Pose2D pos = odo.getPosition();
//            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.INCH), pos.getY(DistanceUnit.INCH), pos.getHeading(AngleUnit.DEGREES));
//            telemetry.addData("Position", data);
//            telemetry.addData("Status", odo.getDeviceStatus());
//            telemetry.update();
            updateOdoTelemetry();
//            runGamepad();
            Specimen();
        }
    }
    void runGamepad() {

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        float  speedFactor  = (float) .5;

        MecanumDrive.cartesian(intoTheDeepDriveBase, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);

    }
    void Specimen(){
        Straight(-22);
        UppyHC();
        sleep(3000);
        Straight(-31);
        UppyPlaceHC();
        sleep(1000);
        SmushMushOpen();
        Straight(-22);
        UppyD();
        TurnR(-90);
        sleep(30000);


    }
}
