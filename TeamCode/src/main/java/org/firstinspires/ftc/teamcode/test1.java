package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "test1", group = "LinearOpMode")
public class test1 extends LinearOpMode {


    DcMotor armRotate;
    Servo servo1;
    double pos = 0;
    double ticks = 537.7;
    DcMotor armExtend;
    

    //turn the motor in a circular way make sure its the right way, if not make it negative
    public void encoder(int turnage){
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pos = ticks/turnage;
        armRotate.setTargetPosition((int)pos);
        armRotate.setPower(0.5);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    @Override
    public void runOpmode(){
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        servo1 = hardwareMap.get(CRservo.class, "servo1");
        armExtend = hardwareMap.get(DcMotor.class, "armExtend");
        if(gamepad1.a){
            encoder(2);
        }
        if(gamepad1.b){
            armExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
   }
}
