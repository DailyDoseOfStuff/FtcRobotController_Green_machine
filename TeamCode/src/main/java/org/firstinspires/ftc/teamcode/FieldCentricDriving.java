package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "FieldCentricDriving")
public class FieldCentricDriving extends LinearOpMode {

  private IMU imu_IMU;
  private DcMotor frontRight;
  private DcMotor backRight;
  private DcMotor frontLeft;
  private DcMotor backLeft;

  /**
   * This function is executed when this OpMode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    YawPitchRollAngles orientation;
    double y;
    double x;
    float rx;

    imu_IMU = hardwareMap.get(IMU.class, "imu");
    frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    backRight = hardwareMap.get(DcMotor.class, "backRight");
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    backLeft = hardwareMap.get(DcMotor.class, "backLeft");

    // Initialize the IMU.
    // Initialize the IMU with non-default settings. To use this block,
    // plug one of the "new IMU.Parameters" blocks into the parameters socket.
    // Create a Parameters object for use with an IMU in a REV Robotics Control Hub or
    // Expansion Hub, specifying the hub's orientation on the robot via the direction that
    // the REV Robotics logo is facing and the direction that the USB ports are facing.
    imu_IMU.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD)));
    // Prompt user to press start button.
    telemetry.update();
    frontRight.setDirection(DcMotor.Direction.FORWARD);
    backRight.setDirection(DcMotor.Direction.FORWARD);
    frontLeft.setDirection(DcMotor.Direction.REVERSE);
    backLeft.setDirection(DcMotor.Direction.REVERSE);
    frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        telemetry.addData("Yaw", "Press Dpad Up to Reset");
        // Check to see if reset yaw is requested.
        if (gamepad1.dpad_up) {
          imu_IMU.resetYaw();
        }
        orientation = imu_IMU.getRobotYawPitchRollAngles();
        y = -((Math.sin((gamepad1.right_stick_y * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.right_stick_x * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) + 90) / 180 * Math.PI)) * 0.6);
        x = Math.sin((gamepad1.right_stick_x * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.right_stick_y * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) - 90) / 180 * Math.PI);
        rx = gamepad1.left_stick_x;
        telemetry.addData("PowerY", y);
        telemetry.addData("PowerX", x);
        telemetry.addData("Orientation", orientation);
        telemetry.update();
        if (gamepad1.a) {
          frontLeft.setPower((y + x + rx) / 4);
          backLeft.setPower(((y - x) + rx) / 4);
          frontRight.setPower(((y - x) - rx) / 4);
          backRight.setPower(((y + x) - rx) / 4);
        } else {
          frontLeft.setPower(y + x + rx);
          backLeft.setPower((y - x) + rx);
          frontRight.setPower((y - x) - rx);
          backRight.setPower((y + x) - rx);
        }
      }
    }
  }
}
