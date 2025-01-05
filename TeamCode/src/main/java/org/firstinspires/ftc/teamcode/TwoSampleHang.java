package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.VerticalArm;

public class TwoSampleHang extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //We start at 26, -55.5, heading 90
        Pose2d beginPose = new Pose2d(26, -55.5, Math.toRadians(90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        VerticalArm vArm = new VerticalArm(hardwareMap);

        waitForStart();

        //Close hand to start
        Actions.runBlocking(
            new SequentialAction(
                vArm.closeHand(),
                vArm.moveToHeight(27)

            )
        );
    }
}
