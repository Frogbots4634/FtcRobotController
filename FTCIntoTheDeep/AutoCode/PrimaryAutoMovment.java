package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.MovingStatistics;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Control.AcceleratedGain;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMPointApproach;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMTrajectory;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.GoBildaPinpointDriver;

import static com.qualcomm.robotcore.util.TypeConversion.byteArrayToInt;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.SmushMush;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCan;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Uppy;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import java.nio.ByteOrder;
import java.util.Arrays;


import java.util.Locale;


@Autonomous(preselectTeleOp = "MecDrive")
public class PrimaryAutoMovment extends LinearOpMode {

    GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer
    double oldTime = 0;

    //    TrackingWheelIntegrator trackingWheelIntegrator = new TrackingWheelIntegrator();

    //      INT STATEM
    StateMTrajectory FirstMovement;


//      INT Movment Trajectory



    //Basics For Tracking
        MovingStatistics movingStatistics = new MovingStatistics(300);
//        static LynxDcMotorController ctrl;
//        LynxModule module;
    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;



    @Override
        public void runOpMode() throws InterruptedException
        {

//            trackingWheelIntegrator = new TrackingWheelIntegrator();

//            DeepGlobals.FL= (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
//            DeepGlobals.FR= (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
//            DeepGlobals.RL= (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
//            DeepGlobals.RR= (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");

            Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
//
            TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
            UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
            Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
            Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
            DeepGlobals.Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty" );
            DeepGlobals.Holdy = (Servo) hardwareMap.get(Servo.class, "Holdy" );
            SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
            TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );

            // Sysinit, don't touch
            intoTheDeepDriveBase = new IntoTheDeepDriveBase();
            intoTheDeepDriveBase.init(hardwareMap);
            intoTheDeepDriveBase.enablePID();
            DeepGlobals.robot= intoTheDeepDriveBase;
            DeepGlobals.driveBase= intoTheDeepDriveBase;
            DeepGlobals.trackingWheelIntegrator = trackingWheelIntegrator;
            DeepGlobals.opMode = this;
            DeepGlobals.robot.enableBrake(true);
            trackingWheelIntegrator = new TrackingWheelIntegrator();

//            trackingWheelIntegrator = new org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.TrackingWheelIntegrator();


            odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");

            odo.setOffsets(170, 58); //these are tuned for 3110-0002-0001 Product Insight #1

            odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

            odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);

            telemetry.setMsTransmissionInterval(20);

            odo.resetPosAndIMU();

            telemetry.addData("Status", "Initialized");
            telemetry.addData("X offset", odo.getXOffset());
            telemetry.addData("Y offset", odo.getYOffset());
            telemetry.addData("Device Version Number:", odo.getDeviceVersion());
            telemetry.addData("Device Scalar", odo.getYawScalar());
            telemetry.update();

            // Wait for the game to start (driver presses START)
            waitForStart();
            resetRuntime();


            while (!isStarted() && !isStopRequested())
            {
                //Vision During INT
//                DeepGlobals.updateTracking();
                odo.update();
                Pose2D pos = odo.getPosition();
                String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.INCH), pos.getY(DistanceUnit.INCH), pos.getHeading(AngleUnit.DEGREES));
                telemetry.addData("Position", data);
                telemetry.addData("Status", odo.getDeviceStatus());

            }

    buildTrajectory(); //Command for State Mechine.

            while (opModeIsActive()) {
//                DeepGlobals.updateTracking();
                odo.update();
                double newTime = getRuntime();
                double loopTime = newTime-oldTime;
                double frequency = 1/loopTime;
                oldTime = newTime;


                Pose2D pos = odo.getPosition();
                String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.INCH), pos.getY(DistanceUnit.INCH), pos.getHeading(AngleUnit.DEGREES));
                telemetry.addData("Position", data);
                telemetry.addData("Status", odo.getDeviceStatus());
//
                telemetry.update();
//                int left = LeftTW.getCurrentPosition();
//                int right = RightTW.getCurrentPosition();
//                int aux = -BackTW.getCurrentPosition();
//                trackingWheelIntegrator.update(left, right, aux);
//
//                Y = trackingWheelIntegrator.getY();
//                X = trackingWheelIntegrator.getX();
//                wheelH = trackingWheelIntegrator.getHeading();
//
//                telemetry.addData("X", X);
//                //opMode.telemetry.addData("FrontInch", inch);
//                telemetry.addData("Y", Y);
//                telemetry.addData("wheelH", wheelH);
              //  telemetry.update();

                FirstMovement.followInteration();

            }

        }



    public void buildTrajectory() {

        FirstMovement = new StateMTrajectory.Builder()

         .addMovement(new StateMPointApproach.Builder() //First movment.
                        .setTargetPosition(0,0)
                        .setMaxPower(.2) // .4 - 6
                        .setXyGain(.04)
                        .setTargetHeading(0)
                        .setHeadingDynamicGain(new AcceleratedGain(.012, -0.0004))
                        .setMaxTurnPower(.2)
                        .setMovementThresh(1)
                        .setHeadingThreshold(1)
                        .stopMotorsOnDone(true)
                        .build())

                .addMovement(new StateMPointApproach.Builder() //First movment.
                        .setTargetPosition(10,0)
                        .setMaxPower(.4) // .4 - 6
                        .setXyGain(.04)
                        .setTargetHeading(0)
                        .setHeadingDynamicGain(new AcceleratedGain(.012, -0.0004))
                        .setMaxTurnPower(.2)
                        .setMovementThresh(1)
                        .setHeadingThreshold(1)
                        .stopMotorsOnDone(true)
                        .build())
                .build();


    }
}
