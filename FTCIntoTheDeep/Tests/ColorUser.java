package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests.SERVOSPIN.Spinnie;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests.SERVOSPIN.Wheelie;

@TeleOp
public class ColorUser extends LinearOpMode {



    @Override
    public void runOpMode() {

        Spinnie = (Servo) hardwareMap.get(Servo.class, "Spinie" );
        Wheelie = (Servo) hardwareMap.get(Servo.class, "Wheelie" );

        PredominantColorProcessor colorSensor = new PredominantColorProcessor.Builder()
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.1, 0.1, 0.1, -0.1))
                .setSwatches(
                        PredominantColorProcessor.Swatch.RED,
                        PredominantColorProcessor.Swatch.BLUE,
                        PredominantColorProcessor.Swatch.YELLOW)
                .build();
        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(colorSensor)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        while (opModeIsActive()) {

            PredominantColorProcessor.Result result = colorSensor.getAnalysis();

            telemetry.addData("Best Match:", result.closestSwatch);
            telemetry.addLine(String.format("R %3d, G %3d, B %3d", android.graphics.Color.red(result.rgb), android.graphics.Color.green(result.rgb), Color.blue(result.rgb)));
            telemetry.update();

            if (result.closestSwatch == PredominantColorProcessor.Swatch.BLUE) {
                Wheelie.setPosition(.5);
                Spinnie.setPosition(.5);
            }
            else if (result.closestSwatch == PredominantColorProcessor.Swatch.YELLOW) {
                Wheelie.setPosition(.5);
                Spinnie.setPosition(.5);
            }
            else if (result.closestSwatch == PredominantColorProcessor.Swatch.RED) {
                Wheelie.setPosition(1);
                Spinnie.setPosition(0);
            }
            else {
                Wheelie.setPosition(0);
                Spinnie.setPosition(1);
            }
        }
    }


}
