package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "SampleAutonomous" , group = "OpMode")
public class SampleAutonomous extends LinearOpMode {

    private SimplifiedOdometryRobot robot = new SimplifiedOdometryRobot(this);

    @Override
    public void runOpMode() {
        robot.initialize(true);
        telemetry.addData(">", "Touch Play to run Auto");
        telemetry.update();


        waitForStart();
        robot.resetHeading();

        if(opModeIsActive()){
            robot.drive(84,0.60,0.25);
            robot.drive(  84, 0.60, 0.25);
            robot.turnTo(90, 0.45, 0.5);
            robot.drive(  72, 0.60, 0.25);
            robot.turnTo(180, 0.45, 0.5);
            robot.drive(  84, 0.60, 0.25);
            robot.turnTo(270, 0.45, 0.5);
            robot.drive(  72, 0.60, 0.25);
            robot.turnTo(0, 0.45, 0.5);

            sleep(500);

            // Drive the path again without turning.
            robot.drive(  84, 0.60, 0.15);
            robot.strafe( 72, 0.60, 0.15);
            robot.drive( -84, 0.60, 0.15);
            robot.strafe(-72, 0.60, 0.15);



        }
    }
}