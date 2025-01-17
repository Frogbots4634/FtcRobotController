//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;


@TeleOp
public class HangTest extends LinearOpMode {


    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;

    private DcMotorEx Uppy;
    private DcMotorEx UppyHangs;
    private DcMotorEx UppyClings;

    public static double UppyCounts;
    private double UppyHangCounts;
    private double UppyClingCounts;


    public void runOpMode() {
        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
        UppyHangs = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyHangs");
        UppyClings = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyClings");

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


        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        intoTheDeepDriveBase = new IntoTheDeepDriveBase();
        intoTheDeepDriveBase.init(hardwareMap);
        intoTheDeepDriveBase.enablePID();
        DeepGlobals.robot= intoTheDeepDriveBase;
        DeepGlobals.driveBase= intoTheDeepDriveBase;
        DeepGlobals.trackingWheelIntegrator = trackingWheelIntegrator;
        DeepGlobals.opMode = this;
        DeepGlobals.robot.enableBrake(true);
        trackingWheelIntegrator = new TrackingWheelIntegrator();
        while (opModeIsActive()){
            runGamepad();
            UppyCounts = Uppy.getCurrentPosition();
            UppyHangCounts = UppyHangs.getCurrentPosition();
            UppyClingCounts = UppyClings.getCurrentPosition();

            telemetry.addData("Uppy counts:", UppyCounts);
            telemetry.addData("Uppy Hang counts:", UppyHangCounts);
            telemetry.addData("Uppy Cling counts:", UppyClingCounts);
            telemetry.update();


        }
    }

    void runGamepad() {
        if (gamepad1.x) {
            UppyHangs.setTargetPosition(0);
            UppyHangs.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            UppyHangs.setPower(.75);

            UppyClings.setTargetPosition(0);
            UppyClings.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            UppyClings.setPower(.75);
        }
        if (gamepad1.a){
            UppyHangs.setTargetPosition(-1300);
            UppyHangs.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            UppyHangs.setPower(.75);

            UppyClings.setTargetPosition(-1300);
            UppyClings.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            UppyClings.setPower(.75);
        }

    }
}
