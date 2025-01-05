package org.firstinspires.ftc.teamcode.utilities;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VerticalArm {
    private final DcMotor armMotor;
    private static final int defaultStartingPosition = 0;
    private static final int defaultPower = 1;
    private static final double diameterOffset = 0;
    private static final double diameter = 1.5 + diameterOffset;
    private static final double circumference = Math.PI * diameter;
    private static final double minimumHeight = 1.5;
    private static final double maximumHeight = 30;
    private static final double countsPerRevolution = 537.689839572;

    private final Servo handLeftServo, handRightServo;
    private static final double openHandLeftPosition = 0;
    private static final double closeHandLeftPosition = 0.2;
    private static final double openHandRightPosition = 1;
    private static final double closeHandRightPosition = 0.8;

    public VerticalArm(HardwareMap hardwareMap) {
        handLeftServo = hardwareMap.get(Servo.class, "handLeft");
        handRightServo = hardwareMap.get(Servo.class, "handRight");

        armMotor = hardwareMap.get(DcMotor.class, "vArmMotor");
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setTargetPosition(defaultStartingPosition);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(defaultPower);
    }

    public class moveToHeight implements Action {

        private double desiredHeight;

        public moveToHeight(double height) {
            desiredHeight = height;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (desiredHeight < minimumHeight) { desiredHeight = minimumHeight; }
            if (desiredHeight > maximumHeight) { desiredHeight = maximumHeight; }

            double desiredDistance = desiredHeight - minimumHeight;
            double desiredRevolutions = desiredDistance / circumference;
            double desiredPosition = desiredRevolutions * countsPerRevolution;

            armMotor.setTargetPosition((int)Math.round(desiredPosition));
            return false;
        }
    }

    public Action moveToHeight(double height) {
        return new moveToHeight(height);
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
        return new openHand();
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
        return new closeHand();
    }
}
