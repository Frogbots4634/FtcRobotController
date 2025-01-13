 package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MotorPowers;
import java.util.Locale;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMush;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMushClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;

@Autonomous
public class Test2Auto extends LinearOpMode {

    IntoTheDeepDriveBase intoTheDeepDriveBase;


    private void Turn(double H){
        Pose2D pos = odo.getPosition();
        double Error = H - pos.getHeading(AngleUnit.DEGREES);
        double Kp = (double) 1/180;
        double LCorrection = Error * -Kp;
        double RCorrection = Error * Kp;
        MotorPowers motorPowers = new MotorPowers();

        intoTheDeepDriveBase.setMotorPowers(LCorrection, RCorrection);

    }
    private void Straight(double X){
        Pose2D pos = odo.getPosition();
        double Error = X - pos.getX(DistanceUnit.INCH);
        double Kp = (double) 1/80;
        double Correction = Error * Kp;
        MotorPowers motorPowers = new MotorPowers();

        intoTheDeepDriveBase.setMotorPowers(Correction);
    }
    private void Strafe(double Y){
        Pose2D pos = odo.getPosition();
        double Error = Y - pos.getY(DistanceUnit.MM);
        double Kp = (double) 1/80;
        double FLCorrection = Error * -Kp;
        double FRCorrection = Error * Kp;
        double RLCorrection = Error * Kp;
        double RRCorrection = Error * -Kp;
        MotorPowers motorPowers = new MotorPowers();

        intoTheDeepDriveBase.setMotorPowers(FLCorrection,FRCorrection,RLCorrection,RRCorrection);
    }

    @Override
    public void runOpMode() {

        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");

        odo.setOffsets(170, 58); //these are tuned for 3110-0002-0001 Product Insight #1

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        //odo.setEncoderResolution(13.26291192);

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
        SmushMush.setPosition(.7);
        waitForStart();

        while (opModeIsActive()) {
            odo.update();
            Pose2D pos = odo.getPosition();
            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.INCH), pos.getY(DistanceUnit.INCH), pos.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Position", data);

            telemetry.addData("Status", odo.getDeviceStatus());
            telemetry.update();
            Straight(24);
//            runGamepad();
        }
    }

    void runGamepad() {

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        float  speedFactor  = (float) .5;

        MecanumDrive.cartesian(intoTheDeepDriveBase, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);

    }
}