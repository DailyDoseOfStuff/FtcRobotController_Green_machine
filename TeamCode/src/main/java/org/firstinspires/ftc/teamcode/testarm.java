package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "testarm", group = "LinearOpMode")
public class testarm extends LinearOpMode {

    Servo RightHand;
    Servo Lefthand;
    public static final double ticks = 0;
    double currentTicks;

    @Override
    public void runOpMode(){

        DcMotor armRotate = hardwareMap.dcMotor.get("armRotate");
        armRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor armExtend = hardwareMap.dcMotor.get("armExtend");
        RightHand = hardwareMap.servo.get("RightHand");
        Lefthand = hardwareMap.servo.get("LeftHand");
        currentTicks = 0;
        double ticks_for_horizontal = 0;
        while (armRotate.getCurrentPosition() >= ticks_for_horizontal) {
                armExtend.setPower(0);
                armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        double stick = gamepad2.right_stick_y;


        if(gamepad2.right_stick_y){
            if(stick > 0){
                armRotate.setPower(-0.5);
            }
            else if(stick < 0){
                armRotate.setPower(0.5);
            }

        }

    }
}
