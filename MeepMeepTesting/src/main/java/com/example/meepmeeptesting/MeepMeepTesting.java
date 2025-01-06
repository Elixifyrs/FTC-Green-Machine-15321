package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(52.48291908330528, 52.48291908330528, Math.toRadians(194.5102), Math.toRadians(187.94061000000002), 15.35)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, 0))
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d(0,-30), Math.toRadians(90))
                .waitSeconds(.1)
                .lineToY(-26)
                .lineToYConstantHeading(-38)
                .lineToXConstantHeading(40)
                .lineToYConstantHeading(-8)
                .splineToConstantHeading(new Vector2d(50,-4), Math.toRadians(90),
                        new TranslationalVelConstraint(15))
                .lineToYConstantHeading(-42)
                .waitSeconds(.1)
                .lineToYConstantHeading(-8)
                .splineToConstantHeading(new Vector2d(60,-4),Math.toRadians(90),
                        new TranslationalVelConstraint(15))
                .lineToY(-48)
                .splineToLinearHeading(new Pose2d(50,-46, Math.toRadians(270)), Math.toRadians(90))
                .waitSeconds(.1)
                .lineToYConstantHeading(-54.5, new TranslationalVelConstraint(15))
                .waitSeconds(250)
                .splineToLinearHeading(new Pose2d(3,-30, Math.toRadians(90)), Math.toRadians(270))
                .waitSeconds(.1)
                .lineToYConstantHeading(-26)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
        }
    }