package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "TestAuto", group = "LinearOpMode")
public class TestAuto extends LinearOpMode {
    private SimplifiedOdometryRobot robot = new SimplifiedOdometryRobot(this);

    @Override
    public void runOpMode() {
        //format the wheels to correct position in SImpliedOdometryRobot
        robot.initialize(true);


    }
}
