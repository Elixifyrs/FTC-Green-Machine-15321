package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.HorizontalArm;

@Autonomous(name = "Horizontal Arm Test", group = "Test")
public class HorizontalArmTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        final long defaultWait = 1000;

        HorizontalArm hArm = new HorizontalArm(hardwareMap);

        waitForStart();

        hArm.openHand();
        sleep(defaultWait);
        hArm.closeHand();
        sleep(defaultWait);
        hArm.openHand();
        sleep(defaultWait);

        hArm.moveToExtensionDistance(9);
        sleep(defaultWait);
        hArm.moveToExtensionDistance(3);
        sleep(defaultWait);
        hArm.rotateHandDown();
        sleep(defaultWait);
        hArm.rotateHandUp();
        sleep(defaultWait);

        hArm.moveToExtensionDistance(0);
        sleep(defaultWait);
    }
}
