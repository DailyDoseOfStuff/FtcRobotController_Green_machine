package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "testarm", group = "LinearOpMode")
public class testarm extends LinearOpMode {

    @Override
    public void runOpMode(){

        DcMotor armMotor = hardwareMap.dcMotor.get("arm-motor");




    }
}
