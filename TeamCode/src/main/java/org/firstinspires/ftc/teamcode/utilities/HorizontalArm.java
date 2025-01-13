package org.firstinspires.ftc.teamcode.utilities;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class HorizontalArm {

    private final Servo handLeftServo, handRightServo, handRotateServo;
    private final DcMotor armMotor;
    private static final double openHandLeftPosition = 0;
    private static final double closeHandLeftPosition = 0.3;
    private static final double openHandRightPosition = 1;
    private static final double closeHandRightPosition = 0.7;
    private static final int defaultStartingPosition = 0;
    private static final int defaultPower = 1;
    private static final double diameterOffset = 0;
    private static final double diameter = 1.5 + diameterOffset;
    private static final double circumference = Math.PI * diameter;
    private static final double minimumDistance = 0;
    private static final double maximumDistance = 17;
    private static final double countsPerRevolution = 537.689839572;

    public HorizontalArm(HardwareMap hardwareMap) {
        handLeftServo = hardwareMap.get(Servo.class, "Horiz_Left");
        handRightServo = hardwareMap.get(Servo.class, "Horiz_Right");
        handRotateServo = hardwareMap.get(Servo.class, "Horiz_Rotate");

        armMotor = hardwareMap.get(DcMotor.class, "hArm");
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setTargetPosition(defaultStartingPosition);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(defaultPower);
    }

    public class moveToHeight implements Action {

        private double desiredDistance;

        public moveToHeight(double height) {
            desiredDistance = height;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (desiredDistance < minimumDistance) {
                desiredDistance = minimumDistance;
            }
            if (desiredDistance > maximumDistance) {
                desiredDistance = maximumDistance;
            }

            double desiredRevolutions = desiredDistance / circumference;
            double desiredPosition = desiredRevolutions * countsPerRevolution;

            armMotor.setTargetPosition((int) Math.round(desiredPosition));
            return false;
        }
    }

    public Action moveToHeight(double height) {
        return new HorizontalArm.moveToHeight(height);
    }

    public class openHand implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            handLeftServo.setPosition(openHandLeftPosition);
            handRightServo.setPosition(openHandRightPosition);
            return false;
        }
    }

    public Action openHand() {
        return new HorizontalArm.openHand();
    }

    public class closeHand implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            handLeftServo.setPosition(closeHandLeftPosition);
            handRightServo.setPosition(closeHandRightPosition);
            return false;
        }
    }

    public Action closeHand() {
        return new HorizontalArm.closeHand();
    }

    public class handUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            handRotateServo.setPosition(0.3);
            return false;
        }
    }

    public Action handUp() {
        return new HorizontalArm.handUp();
    }

    public class handDown implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            handRotateServo.setPosition(0);
            return false;
        }
    }

    public Action handDown() {
        return new HorizontalArm.handDown();
    }
}
