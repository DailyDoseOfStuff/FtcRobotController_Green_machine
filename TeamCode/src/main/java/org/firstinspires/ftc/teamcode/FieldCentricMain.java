package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "FieldCentricMain", group = "LinearOpMode")
public class FieldCentricMain extends LinearOpMode {
    //driving things
    private IMU imu_IMU;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;

    //high hand
    private DcMotor armExtend1;
    private Servo HLeft;
    private Servo HRight;

    //low hand
    private DcMotor armExtend2;
    private Servo handLeft;
    private Servo handRight;
    private Servo handRotate;



    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        YawPitchRollAngles orientation;


        imu_IMU = hardwareMap.get(IMU.class, "imu");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        armExtend1 = hardwareMap.get(DcMotor.class, "armExtend");
        armExtend2 = hardwareMap.get(DcMotor.class, "Horiz_Extend");
        handRotate = hardwareMap.get(Servo.class, "Horiz_Rotate");
        handLeft = hardwareMap.get(Servo.class, "handLeft");
        handRight = hardwareMap.get(Servo.class, "handRight");

        //the hand for the uppies
        HLeft = hardwareMap.get(Servo.class, "Horiz_Left");
        HRight = hardwareMap.get(Servo.class,"Horiz_Right");

        // Initialize the IMU.
        // Initialize the IMU with non-default settings. To use this block,
        // plug one of the "new IMU.Parameters" blocks into the parameters socket.
        // Create a Parameters object for use with an IMU in a REV Robotics Control Hub or
        // Expansion Hub, specifying the hub's orientation on the robot via the direction that
        // the REV Robotics logo is facing and the direction that the USB ports are facing.
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu_IMU.initialize(parameters);

        waitForStart();        // Prompt user to press start button.
        telemetry.update();
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtend1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtend2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                telemetry.addData("Yaw", "Press Dpad Up to Reset");
                // Check to see if reset yaw is requested.
                double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
                double x = gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;
                if (gamepad1.dpad_up) {
                    imu_IMU.resetYaw();
                }
                orientation = imu_IMU.getRobotYawPitchRollAngles();
                y = -((Math.sin((gamepad1.left_stick_y * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.left_stick_x * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) + 90) / 180 * Math.PI)) * -0.6);
                x = Math.sin((gamepad1.left_stick_x * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.left_stick_y * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) - 90) / 180 * Math.PI);
                rx = gamepad1.right_stick_x;
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
                armExtend1.setPower(gamepad2.right_stick_y);
                armExtend2.setPower(gamepad2.left_stick_y);
                handRotate.setPosition(Math.max(0.4, 0.6 + gamepad2.left_stick_x / 3));
                if(gamepad2.right_trigger > 0){
                    HLeft.setPosition(0.1 + gamepad2.right_trigger/7);
                    HRight.setPosition(0.9-gamepad2.right_trigger/7);
                }
                if(gamepad2.left_trigger > 0){
                    handLeft.setPosition(0.1 + gamepad2.left_trigger / 7);
                    handRight.setPosition(0.9 - gamepad2.left_trigger / 7);
                }
            }
        }
    }
}



/*
 if (gamepad1.dpad_up) {
                    imu_IMU.resetYaw();
                }
                orientation = imu_IMU.getRobotYawPitchRollAngles();
                y = -((Math.sin((gamepad1.left_stick_y * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.left_stick_x * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) + 90) / 180 * Math.PI)) * -0.6);
                x = Math.sin((gamepad1.left_stick_x * 90) / 180 * Math.PI) * Math.cos(orientation.getYaw(AngleUnit.DEGREES) / 180 * Math.PI) - Math.sin((gamepad1.left_stick_y * 90) / 180 * Math.PI) * Math.cos((orientation.getYaw(AngleUnit.DEGREES) - 90) / 180 * Math.PI);
                rx = gamepad1.right_stick_x;
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
 */

/*
 if (gamepad1.dpad_up) {
                    imu_IMU.resetYaw();
                }
                telemetry.addData("PowerY", y);
                telemetry.addData("PowerX", x);
                telemetry.update();
                double botHeading = imu_IMU.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

                // Rotate the movement direction counter to the bot's rotation
                double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
                double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

                rotX = rotX * 1.1;  // Counteract imperfect strafing

                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio,
                // but only if at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
                double frontLeftPower = (rotY + rotX + rx) / denominator;
                double backLeftPower = (rotY - rotX + rx) / denominator;
                double frontRightPower = (rotY - rotX - rx) / denominator;
                double backRightPower = (rotY + rotX - rx) / denominator;

                frontLeft.setPower(frontLeftPower);
                backLeft.setPower(backLeftPower);
                frontRight.setPower(frontRightPower);
                backRight.setPower(backRightPower);
                armExtend1.setPower(gamepad2.right_stick_y);
                armExtend2.setPower(gamepad2.left_stick_y);
                handRotate.setPosition(Math.max(0.4, 0.6 + gamepad2.left_stick_x / 3));
 */