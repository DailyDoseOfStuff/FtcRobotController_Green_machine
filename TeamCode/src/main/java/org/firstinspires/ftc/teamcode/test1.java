package org.firstinspires.ftc.teamcode;

import java.util.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "test1", group = "LinearOpMode")
public class test1 extends LinearOpMode {


    private DcMotor armRotate;
    private CRServo servo1;
    private double pos = 0;
    private static final double ticks = 537.7;
    private DcMotor armExtend;
    @Override
    public void runOpMode(){
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        armExtend = hardwareMap.get(DcMotor.class, "armExtend");
        if(gamepad1.a){
            encoder(2);
        }
        if(gamepad1.b){
            armExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
   }

    //turn the motor in a circular way make sure its the right way, if not make it negative
    public void encoder(int turnage){
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pos = ticks/turnage;
        armRotate.setTargetPosition((int)pos);
        armRotate.setPower(0.5);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
